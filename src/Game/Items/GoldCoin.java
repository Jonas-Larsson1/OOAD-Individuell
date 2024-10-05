package Game.Items;

public class GoldCoin extends Item implements Stackable {
  private int quantity;

  public GoldCoin(int quantity) {
    super("Gold coin", 0.01, 1);
    this.quantity = quantity;
  }

  @Override
  public void changeQuantity(int amount) {
    this.quantity += amount;
  }

  @Override
  public int getQuantity() {
    return quantity;
  }

  public String getDescription() {
    return super.getDescription() + String.format("\nQuantity: %d", quantity);
  }
}
