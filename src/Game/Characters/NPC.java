package Game.Characters;

import Game.Items.Item;

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
  }
}
