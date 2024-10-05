package Game.Items;

public class Armor extends Item implements Barterable {
  private int defense;

  public Armor(String name, double weight, int value, int defense) {
    super(name, weight, value);
    this.defense = defense;
  }

  public String getDescription() {
    return super.getDescription() + String.format("\nDefense: %d", defense);
  }

  @Override
  public double minSellPriceAfterNegotiation(int charisma) {
    double discount = (100.0 - charisma) / 100.0;
    return value * discount;
  }

  @Override
  public double maxBuyPriceAfterNegotiation(int charisma) {
    double markup = (100.0 + charisma) / 100.0;
    return value * markup;
  }
}
