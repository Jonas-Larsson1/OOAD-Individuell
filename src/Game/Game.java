package Game;

import Game.Characters.NPC;
import Game.Characters.Player;
import Game.Items.*;

public class Game {
  private Player player;
  private NPC npc;

  private void setupGame() {
    player = new Player("Charles", 50);
    npc = new NPC("Dingus", 0, 2);

    player.getInventory().addItem(new Weapon("Big Sword", 10, 15, 25));
    player.getInventory().addItem(new Armor("Small Shield", 5, 10, 5));
    player.getInventory().addItem(new GoldCoin(100));

    npc.getInventory().addItem(new Weapon("Huge Axe", 12, 20, 30));
    npc.getInventory().addItem(new Armor("Plate Armor", 15, 50, 10));
    npc.getInventory().addItem(new GoldCoin(50));
  }

  public void start() {
    setupGame();
    npc.requestRandomPlayerItem(player);
  }
}
