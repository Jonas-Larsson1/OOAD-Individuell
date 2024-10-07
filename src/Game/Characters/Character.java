package Game.Characters;

import Game.Inventory.Inventory;
import Game.Items.Barterable;
import Game.Items.Item;
import Game.Trading.Trade;

import java.util.ArrayList;

public abstract class Character {
  protected String name;
  protected Inventory inventory;
  protected int charisma;

  public Character(String name, int charisma) {
    this.name = name;
    this.charisma = Math.max(0, Math.min(charisma, 100));
    this.inventory = new Inventory();
  }

  public Inventory getInventory() {
    return inventory;
  }

  public ArrayList<Item> getBarterableItems() {
    ArrayList<Item> barterableItems = new ArrayList<>();
    for (Item item : inventory.getItems()) {
      if (item instanceof Barterable) {
        barterableItems.add(item);
      }
    }
    return barterableItems;
  }

  public int getCharisma() {
    return charisma;
  }

  public String getName() {
    return name;
  }

  public abstract double getInitiatorOffer(Trade trade);
  public abstract double getResponderOffer(Trade trade);
}
