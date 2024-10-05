package Game.Items;

public class Consumable extends Item implements Barterable, Stackable {
  private int potency;
  private double duration;
  private int quantity;

  public Consumable(String name, double weight, int value, int potency, double duration, int quantity) {
    super(name, weight, value);
    this.potency = potency;
    this.duration = duration;
    this.quantity = quantity;
  }

  public String getDescription() {
    return super.getDescription() + String.format("\nPotency: %d\nDuration: %.2f\nQuantity: %d", potency, duration, quantity);
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

  @Override
  public void changeQuantity(int amount) {
    this.quantity += amount;
  }

  @Override
  public int getQuantity() {
    return quantity;
  }
}
