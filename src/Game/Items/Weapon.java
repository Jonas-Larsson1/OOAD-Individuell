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
  public double sellPriceAfterNegotiation(int charisma) {
    return value + charisma;
  }

  @Override
  public double buyPriceAfterNegotiation(int charisma) {
    return value - charisma;
  }
}
