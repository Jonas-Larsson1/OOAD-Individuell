package Game;

import Game.Characters.NPC;
import Game.Characters.Player;
import Game.Items.*;
import Game.Trading.Trade;

public class Game {
  private Player player;
  private NPC npc;

  private void setupGame() {
    player = new Player("Jonas", 50);
    npc = new NPC("Dingus", 100, 2);

    player.getInventory().addItem(new Weapon("Big Sword", 10, 15, 25));
    player.getInventory().addItem(new Armor("Small Shield", 5, 10, 5));
    player.getInventory().addItem(new GoldCoin(100));

    npc.getInventory().addItem(new Weapon("Huge Axe", 12, 20, 30));
    npc.getInventory().addItem(new Armor("Plate Armor", 15, 50, 10));
    npc.getInventory().addItem(new GoldCoin(50));

  }

  public void start() {
    setupGame();
//    npc påbörjar trade med spelaren
//    npc.requestRandomPlayerItem(player);

//    spelaren påbörjar trade med npc
    Weapon npcItem = new Weapon("Shiny Dagger", 4, 10, 20);
    npc.getInventory().addItem(npcItem);
    Trade playerInitiatedTrade = new Trade(player, npc, npcItem);
    playerInitiatedTrade.negotiateTrade();
  }
}
