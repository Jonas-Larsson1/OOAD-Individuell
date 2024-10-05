package Game.Items;

abstract class Item {
  protected String name;
  protected double weight;
  protected double value;
  protected int stackSize;
//  protected int maxStackSize;
//  protected static final int DEFAULT_STACK_SIZE = 16;

  public Item(String name, double weight, int value) {
    this.name = name;
    this.weight = weight;
    this.value = value;
    this.stackSize = 1;
  }

  public String getName() {
    return name;
  }

  public double getWeight() {
    return weight;
  }

  public double getValue() {
    return value;
  }

  public String getDescription() {
    return String.format("%s\nValue: %.2f\nweight: %.2f", name, value, weight);
  }
}
