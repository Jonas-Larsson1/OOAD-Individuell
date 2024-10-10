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

  public double makeCounterOffer(Trade trade) {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    double offerModifier;
    double currentResponderOffer = trade.getCurrentResponderOffer();
    double currentInitiatorOffer = trade.getCurrentInitiatorOffer();
    double itemValue = trade.getRequestedItem().getValue();
    double minPrice = trade.getMinPrice();
    double newOffer;

    if (currentResponderOffer == 0.0) {
//      100 charisma erbjuder 150% av item value, 0 charisma erbjuder 100% av item value.
      offerModifier = ((100 - ((double) charisma / 2)) / 100.0) + 1;
      newOffer = itemValue * offerModifier;
    } else if (currentInitiatorOffer >= minPrice) {
      newOffer = currentInitiatorOffer;
    } else {
      double halfwayOffer = (itemValue - trade.getMinPrice()) / 2;
//      100 charisma erbjuder erbjuder halvvägs mellan item value och minpris, 0 charisma erbjuder item value.
      offerModifier = ((100 - charisma) / 100.0) + 1;
      newOffer = itemValue - (halfwayOffer * offerModifier);
    }

    return newOffer;
  }

  public boolean respondToCounterOffer(Trade trade) {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return trade.getCurrentResponderOffer() <= trade.getMaxPrice();
  }

  public double makeOffer(Trade trade) {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    double offerModifier;
    double currentOffer = trade.getCurrentInitiatorOffer();
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
