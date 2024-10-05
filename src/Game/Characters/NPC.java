package Game.Characters;

import Game.Items.GoldCoin;
import Game.Items.Item;
import Game.Items.Barterable;

import java.util.Random;
import java.util.ArrayList;

public class NPC extends Character {

  public NPC(String name, int charisma) {
    super(name, charisma);
  }

  public void requestRandomItem(Player player) {
    Random random = new Random();
    ArrayList<Item> playerItems = player.getBarterableItems();

    int index = random.nextInt(playerItems.size());

    Item randomItem = playerItems.get(index);

    System.out.println("NPC requests: " + randomItem.getDescription());

    negotiateTrade(player, randomItem);
  }

  public void negotiateTrade(Player player, Item requestedItem) {
    double minPrice = ((Barterable) requestedItem).minSellPriceAfterNegotiation(player.getCharisma());

    double offer = player.getOfferForItem(requestedItem);

    System.out.println(name + " is willing to sell for: " + minPrice);
    System.out.println(player.getName() + " offers: " + offer);

    if (offer >= minPrice) {
      System.out.println("Trade successful! " + player.getName() + " acquires " + requestedItem.getName());
      completeTrade(player, requestedItem, offer);
    } else {
      System.out.println(name + " rejects the trade. " + player.getName() + "'s offer was too low.");
    }
  }

  public void completeTrade(Player player, Item requestedItem, double offer) {

    player.getInventory().removeItem(new GoldCoin((int) offer));
    inventory.addItem(new GoldCoin((int) offer));

    player.getInventory().removeItem(requestedItem);
    inventory.addItem(requestedItem);
  }
}
