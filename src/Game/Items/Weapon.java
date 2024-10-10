package Game.Items;

public class Weapon extends Item implements Barterable {
  private int damage;

  public Weapon(String name, double weight, int value, int damage) {
    super(name, weight, value);
    this.damage = damage;
  }

  public String getDescription() {
    return super.getDescription() + String.format("\nDamage: %d", damage);
  }

  @Override
  public double minSellPriceAfterNegotiation(int charisma) {
    double discount = (100.0 - (double) charisma / 2) / 100.0;
    return value * discount;
  }

  @Override
  public double maxBuyPriceAfterNegotiation(int charisma) {
    double markup = (100.0 + charisma) / 100.0;
    return value * markup;
  }
}
