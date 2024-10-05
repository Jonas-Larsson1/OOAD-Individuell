package Game.Items;

public interface Stackable {
  void changeQuantity(int amount);
  int getQuantity();
}