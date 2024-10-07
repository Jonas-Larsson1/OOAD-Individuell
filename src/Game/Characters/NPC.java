package Game.Characters;

import Game.Items.Item;
import Game.Trading.Trade;

import java.util.Random;
import java.util.ArrayList;


public class NPC extends Character {
  private int patience;

  public NPC(String name, int charisma, int patience) {
    super(name, charisma);
    this.patience = patience;
  }

  public int getPatience() {
    return patience;
  }

  public void requestRandomPlayerItem(Player player) {
    Random random = new Random();
    ArrayList<Item> playerItems = player.getBarterableItems();

    int index = random.nextInt(playerItems.size());

    Item randomItem = playerItems.get(index);

    Trade trade = new Trade(this, player, randomItem);

    trade.negotiateTrade();
  }

  public double getResponderOffer(Trade trade) {
//    Scanner scanner = new Scanner(System.in);
//    System.out.println("You are selling: " + requestedItem.getName());
//    System.out.println("How much gold do you want to sell it for?");
//
//    return Double.parseDouble(scanner.nextLine());
    return 0.0;
  }

  public double getInitiatorOffer(Trade trade) {
    double offerModifier;
    double currentOffer = trade.getCurrentOffer();
    double itemValue = trade.getRequestedItem().getValue();
    double newOffer;

    if (currentOffer == 0.0) {
//      100 charisma erbjuder 50% av item value, 0 charisma erbjuder 100% av item value.
      offerModifier = (100 - ((double) charisma / 2)) / 100.0;
      newOffer = itemValue * offerModifier;
    } else {
      double halfwayOffer = (trade.getMaxPrice() - itemValue) / 2;
//      100 charisma erbjuder endast item value, 0 charisma erbjuder halvvägs mellan item value och maxpris.
      offerModifier = (100 - charisma) / 100.0;
      newOffer = itemValue + (halfwayOffer * offerModifier);
    }

    return newOffer;
  }
}
