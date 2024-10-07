package Game.Characters;

import Game.Items.Item;

import java.util.Scanner;

public class Player extends Character {
  public Player(String name, int charisma) {
    super(name, charisma);
  }

  public double getOfferForItem(Item requestedItem) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("You are negotiating for: " + requestedItem.getDescription());
    System.out.println("How much gold are you willing to offer?");

    double offer = Double.parseDouble(scanner.nextLine());
    System.out.printf("%s has offered %.2f gold.\n", name, offer);

    return offer;
  }
}
