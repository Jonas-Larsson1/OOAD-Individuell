package Game.Characters;

import Game.Inventory.Inventory;
import Game.Items.Barterable;
import Game.Items.Item;

import java.util.ArrayList;

abstract class Character {
  protected String name;
  protected Inventory inventory;
  protected int charisma;

  public Character(String name, int charisma) {
    this.name = name;
    this.charisma = charisma;
    this.inventory = new Inventory(); // Initialize inventory
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

  protected int getCharisma() {
    return charisma;
  }

  protected String getName() {
    return name;
  }
}
