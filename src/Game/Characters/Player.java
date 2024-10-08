package Game.Characters;

import Game.Trading.Trade;

import java.util.Scanner;

public class Player extends Character {
  public Player(String name, int charisma) {
    super(name, charisma);
  }

  public double makeCounterOffer(Trade trade) {
    Scanner scanner = new Scanner(System.in);
    double newOffer;

    System.out.print("Do you accept the offer? (y/n): ");
    String response = scanner.nextLine().toUpperCase();
    if (response.equals("Y")) {
      newOffer = trade.getCurrentInitiatorOffer();
    } else {
      System.out.print("Enter counter-offer: ");
      newOffer = Double.parseDouble(scanner.nextLine());
    }

    return newOffer;
  }

  @Override
  public boolean respondToCounterOffer(Trade trade) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Do you accept the counter offer? (y/n): ");
    String response = scanner.nextLine().toUpperCase();
    return response.equals("Y");
  }

  public double makeOffer(Trade trade) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("How much gold do you want offer for it?");

    return Double.parseDouble(scanner.nextLine());
  }
}
