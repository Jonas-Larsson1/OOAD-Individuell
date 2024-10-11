package Game.Items.Unique;

import Game.Items.Barterable;
import Game.Items.Weapon;

public class LegendarySword extends Weapon implements Barterable {
  public LegendarySword(String name, double weight, int value, int damage) {
    super(name, weight, value, damage);
  }

  @Override
  public double minSellPriceAfterNegotiation(int charisma) {
//    kan ej säljas för lägre än det riktiga värdet
    return value;
  }

  @Override
  public double maxBuyPriceAfterNegotiation(int charisma) {
//    Kan köpas ännu dyrare
    double markup = (100.0 + charisma) / 100.0;
    return value * markup;
  }
}
