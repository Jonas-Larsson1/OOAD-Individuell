package Game.Inventory;

import Game.Items.Barterable;
import Game.Items.Item;
import Game.Items.Stackable;

import java.util.ArrayList;

public class Inventory {
  private ArrayList<Item> items;

  public Inventory() {
    items = new ArrayList<>();
  }

  public ArrayList<Item> getItems() {
    return items;
  }

  public void addItem(Item item) {
    if (!(item instanceof Stackable)) {
      items.add(item);
      return;
    }
    for (Item inventoryItem : items) {
      if (inventoryItem.getName().equals(item.getName())) {
        ((Stackable) inventoryItem).changeQuantity(1);
        return;
      }
    }
  }

  public void addItem(Item item, int amount) {
    if (!(item instanceof Stackable)) {
      items.add(item);
      return;
    }
    for (Item inventoryItem : items) {
      if (inventoryItem.getName().equals(item.getName())) {
        ((Stackable) inventoryItem).changeQuantity(amount);
        return;
      }
    }
  }

  public void removeItem(Item item, int amount) {
    if (!(item instanceof Stackable)) {
      items.remove(item);
      return;
    }
    for (Item inventoryItem : items) {
      if (inventoryItem.getName().equals(item.getName())) {
        ((Stackable) inventoryItem).changeQuantity(-amount);
        return;
      }
    }
  }

  public void showItems() {
    if (items.isEmpty()) {
      System.out.println("Inventory is empty.");
      return;
    }
    for (Item item : items) {
      System.out.println(item.getDescription());
    }
  }
}
