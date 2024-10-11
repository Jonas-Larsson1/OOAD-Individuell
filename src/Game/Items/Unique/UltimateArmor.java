package Game.Items.Unique;

import Game.Items.Barterable;
import Game.Items.Weapon;

public class UltimateArmor extends Weapon implements Barterable {
  public UltimateArmor(String name, double weight, int value, int damage) {
    super(name, weight, value, damage);
  }

  @Override
  public double minSellPriceAfterNegotiation(int charisma) {
//    har inte lika hög discount
    double discount = (100.0 - (double) charisma / 4) / 100.0;
    return value * discount;
  }

  @Override
  public double maxBuyPriceAfterNegotiation(int charisma) {
//    kan inte ha lika hög buy price
    double markup = (100.0 + (double) charisma / 2) / 100.0;
    return value * markup;
  }
}
