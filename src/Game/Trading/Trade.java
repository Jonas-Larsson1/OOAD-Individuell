package Game.Trading;

import Game.Characters.Character;
import Game.Characters.NPC;
import Game.Characters.Player;
import Game.Items.Barterable;
import Game.Items.GoldCoin;
import Game.Items.Item;

public class Trade {
  private static final String RESET = "\u001B[0m";
  private static final String GREEN = "\u001B[32m";
  private static final String RED = "\u001B[31m";
  private static final String YELLOW = "\u001B[33m";

  protected Character initiator;
  protected Character responder;
  protected Item requestedItem;
  protected double maxPrice;
  protected double minPrice;
  protected double currentOffer = -0.0;

  public Trade(Character initiator, Character responder, Item requestedItem) {
    this.initiator = initiator;
    this.responder = responder;
    this.requestedItem = requestedItem;
    this.maxPrice = ((Barterable) requestedItem).maxBuyPriceAfterNegotiation(responder.getCharisma());
    this.minPrice = ((Barterable) requestedItem).minSellPriceAfterNegotiation(responder.getCharisma());
  }

  public void negotiateTrade() {

    System.out.printf("%s wants to trade with %s for: %s\n",
        initiator.getName(), responder.getName(), requestedItem.getDescription());

    int attempts = 0;
    int patience = initiator instanceof NPC ? ((NPC) initiator).getPatience() : ((NPC) responder).getPatience();

    while (attempts < patience) {
      double initiatorOffer = initiator.getInitiatorOffer(this);
      currentOffer = initiatorOffer;
      System.out.printf("%s has offered %.2f gold.\n",
          initiator.getName(), initiatorOffer);

      double responderOffer = responder.getResponderOffer(this);

      if (responderOffer == currentOffer) {
        System.out.printf("%s accepts %s's offer of %.2f gold.\n",
            responder.getName(), initiator.getName(), currentOffer);
      } else {
        System.out.printf("%s has suggested %.2f gold.\n",
            responder.getName(), responderOffer);
      }

      if (responderOffer <= maxPrice) {
        System.out.printf("%sTrade successful! %s acquires %s%s%n",
            GREEN, initiator.getName(), requestedItem.getName(), RESET);
        currentOffer = (responderOffer);
        completeTrade();
        return;
      } else {
        attempts++;
      }

      if (attempts < patience) {
        System.out.printf("%s rejects the offer. You have %d more attempt(s) to negotiate.\n",
            initiator instanceof NPC ? initiator.getName() : responder.getName(),  (patience - attempts));
      } else {
        System.out.printf("%s has lost patience. Trade failed.\n",
            initiator instanceof NPC ? initiator.getName() : responder.getName());
      }
    }


//    while (attempts < patience) {
//      double offer = player.getOfferForItem(requestedItem);
//
//      if (offer <= maxPrice) {
//        System.out.println("Trade successful! " + name + " acquires " + requestedItem.getName());
//        completeTrade(player, requestedItem, offer);
//        return;
//      } else {
//        attempts++;
//        System.out.printf("%s rejects the offer. %s's requested price was too high.%n", name, player.getName());
//      }
//
//      if (attempts < patience) {
//        System.out.printf("You have %d more attempts to negotiate.%n", (patience - attempts));
//      } else {
//        System.out.printf("%s has lost patience. Trade failed.%n", name);
//      }
//    }
  }

  private void completeTrade() {

    initiator.getInventory().removeItem(new GoldCoin((int) currentOffer));
    responder.getInventory().addItem(new GoldCoin((int) currentOffer));

    responder.getInventory().removeItem(requestedItem);
    initiator.getInventory().addItem(requestedItem);
  }

  public double getMaxPrice() {
    return maxPrice;
  }

  public double getCurrentOffer() {
    return currentOffer;
  }

  public Item getRequestedItem() {
    return requestedItem;
  }
}
