package Game.Items;

public class Consumable extends Item implements Barterable {
  private int potency;
  private double duration;

  public Consumable(String name, double weight, int value, int potency, double duration) {
    super(name, weight, value);
    this.potency = potency;
    this.duration = duration;
  }

  public String getDescription() {
    return super.getDescription() + String.format("\nPotency: %d\nDuration: %.2f", potency, duration);
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
