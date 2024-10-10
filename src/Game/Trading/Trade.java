package Game.Trading;

import Game.Characters.Character;
import Game.Characters.NPC;
import Game.Items.Barterable;
import Game.Items.GoldCoin;
import Game.Items.Item;

public class Trade {
  private static final String RESET = "\u001B[0m";
  private static final String HEADER = "\u001B[30m";
  private static final String ITEM = "\u001B[1m";
  private static final String SUCCESS = "\u001B[32m";
  private static final String FAIL = "\u001B[31m";
  private static final String INITIATOR = "\u001B[33m";
  private static final String RESPONDER = "\u001B[35m";

  protected Character initiator;
  protected Character responder;
  protected Item requestedItem;
  protected double maxPrice;
  protected double minPrice;
  protected double currentInitiatorOffer = 0.0;
  protected double currentResponderOffer = 0.0;

  public Trade(Character initiator, Character responder, Item requestedItem) {
    this.initiator = initiator;
    this.responder = responder;
    this.requestedItem = requestedItem;
    if (requestedItem instanceof Barterable) {
      this.maxPrice = ((Barterable) requestedItem).maxBuyPriceAfterNegotiation(responder.getCharisma());
      this.minPrice = ((Barterable) requestedItem).minSellPriceAfterNegotiation(responder.getCharisma());
    } else {
      this.maxPrice = requestedItem.getValue();
      this.minPrice = requestedItem.getValue();
    }
  }

  public void negotiateTrade() {

    System.out.printf("%s=========================================%s\n", HEADER, RESET);
    System.out.printf("%sTrade Proposal: %s wants to trade with %s for: %s\n",
        INITIATOR, initiator.getName(), responder.getName(),  RESET);
    System.out.printf("%s%s%S\n",ITEM, requestedItem.getDescription(), RESET);
    System.out.printf("%s=========================================%s\n", HEADER, RESET);

    int attempts = 0;
    boolean tradeSuccessful = false;
    int patience =
        initiator instanceof NPC ? ((NPC) initiator).getPatience() :
        responder instanceof NPC ? ((NPC) responder).getPatience() :
        99;

    while (attempts < patience) {
      System.out.printf("%s%s is making an offer...%s\n",
          INITIATOR, initiator.getName(), RESET);

      currentInitiatorOffer = initiator.makeOffer(this);

      System.out.printf("%s%s offers: %.2f gold.%s\n",
          INITIATOR, initiator.getName(), currentInitiatorOffer, RESET);

      System.out.printf("%s%s is thinking...%s\n",
          RESPONDER, responder.getName(), RESET);

      currentResponderOffer = responder.makeCounterOffer(this);

      System.out.printf("%s=========================================%s\n", HEADER, RESET);

      if (currentResponderOffer == currentInitiatorOffer) {
        System.out.printf("%s✔️ %s accepts %s's offer of %.2f gold.%s.\n",
            RESPONDER, responder.getName(), initiator.getName(), currentInitiatorOffer, RESET);
        tradeSuccessful = true;
        break;
      } else {
        System.out.printf("%s%s suggests: %.2f gold.%s\n",
            RESPONDER, responder.getName(), currentResponderOffer, RESET);
      }

      System.out.printf("%s%s considers the counter offer...%s\n",
          INITIATOR, initiator.getName(), RESET);

      tradeSuccessful = initiator.respondToCounterOffer(this);

      if (tradeSuccessful) {
        break;
      } else {
        attempts++;
      }

      if (attempts < patience) {
        System.out.printf("%s%s rejects the offer. You have %d more attempt(s) to negotiate.%s\n",
            FAIL, initiator instanceof NPC ? initiator.getName() : responder.getName(),  (patience - attempts), RESET);
      } else {
        System.out.printf("%s%s has lost patience. Trade failed.%s\n",
            FAIL, initiator instanceof NPC ? initiator.getName() : responder.getName(), RESET);
      }
    }

    if (tradeSuccessful) {
      System.out.printf("%sTrade successful! %s acquires: %s",
          SUCCESS, initiator.getName(), RESET);
      System.out.printf("%s%s%s\n", ITEM, requestedItem.getName(), RESET);
      completeTrade();
    }
  }

  private void completeTrade() {

    initiator.getInventory().removeItem(new GoldCoin((int) currentInitiatorOffer));
    responder.getInventory().addItem(new GoldCoin((int) currentInitiatorOffer));

    responder.getInventory().removeItem(requestedItem);
    initiator.getInventory().addItem(requestedItem);
  }

  public double getMaxPrice() {
    return maxPrice;
  }

  public double getMinPrice() {
    return minPrice;
  }

  public double getCurrentInitiatorOffer() {
    return currentInitiatorOffer;
  }

  public Item getRequestedItem() {
    return requestedItem;
  }

  public double getCurrentResponderOffer() {
    return currentResponderOffer;
  }
}
