package Game.Characters;

import Game.Items.Item;

public class Player extends Character {
  public Player(String name, int charisma) {
    super(name, charisma);
  }

  public double getOfferForItem(Item requestedItem) {
    return requestedItem.getValue() * 0.9;
  }
}
