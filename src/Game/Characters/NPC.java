package Game.Characters;

import Game.Items.GoldCoin;
import Game.Items.Item;
import Game.Items.Barterable;

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

  public void requestRandomItem(Player player) {
    Random random = new Random();
    ArrayList<Item> playerItems = player.getBarterableItems();

    int index = random.nextInt(playerItems.size());

    Item randomItem = playerItems.get(index);

    System.out.println("NPC requests: " + randomItem.getName());

    negotiateTrade(player, randomItem);
  }

  private void negotiateTrade(Player player, Item requestedItem) {
    double minPrice = ((Barterable) requestedItem).minSellPriceAfterNegotiation(player.getCharisma());
    int attempts = 0;
    while (attempts < patience) {
      double offer = player.getOfferForItem(requestedItem);

      if (offer >= minPrice) {
        System.out.println("Trade successful! " + player.getName() + " acquires " + requestedItem.getName());
        completeTrade(player, requestedItem, offer);
        return;
      } else {
        attempts++;
        System.out.printf("%s rejects the offer. %s's offer was too low.%n", name, player.getName());
      }

      if (attempts < patience) {
        System.out.printf("You have %d more attempts to negotiate.%n", (patience - attempts));
      } else {
        System.out.printf("%s has lost patience. Trade failed.%n", name);
      }
    }
  }

  private void completeTrade(Player player, Item requestedItem, double offer) {

    player.getInventory().removeItem(new GoldCoin((int) offer));
    inventory.addItem(new GoldCoin((int) offer));

    player.getInventory().removeItem(requestedItem);
    inventory.addItem(requestedItem);
  }
}
