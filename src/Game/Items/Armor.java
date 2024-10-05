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
  public double sellPriceAfterNegotiation(int charisma) {
    return value + charisma;
  }

  @Override
  public double buyPriceAfterNegotiation(int charisma) {
    return value - charisma;
  }
}
