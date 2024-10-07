package Game.Characters;

import Game.Trading.Trade;

import java.util.Scanner;

public class Player extends Character {
  public Player(String name, int charisma) {
    super(name, charisma);
  }

  public double getResponderOffer(Trade trade) {
    Scanner scanner = new Scanner(System.in);
    double newOffer;

    System.out.print("Do you accept the offer? (y/n): ");
    String response = scanner.nextLine().toUpperCase();
    if (response.equals("Y")) {
      newOffer = trade.getCurrentOffer();
    } else {
      System.out.print("Enter counter-offer: ");
      newOffer = Double.parseDouble(scanner.nextLine());
    }

    return newOffer;
  }

  public double getInitiatorOffer(Trade trade) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("How much gold do you want to buy it for?");

    return Double.parseDouble(scanner.nextLine());
  }
}
