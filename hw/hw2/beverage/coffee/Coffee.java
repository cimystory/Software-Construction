/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.beverage.coffee;
import edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage;
import edu.cmu.qatar.cs214.hw.hw2.beverage.recipe.CoffeeDrink;
import edu.cmu.qatar.cs214.hw.hw2.beverage.recipe.Recipe;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.CoffeeSizeFactor;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.SizeFactor;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.TeaSizeFactor;

public abstract class Coffee extends Beverage
{
	/*
	 * Constructor that calls super constructor
	 */
	public Coffee(CoffeeSizeFactor CF)
	{
		super(CF
		,new CoffeeDrink(),
		"Coffee is a brewed drink with"
				+ " a distinct aroma and flavor,"
				+ "prepared from roasted coffee beans");
		
		//super.description = "Coffee is a brewed drink with a distinct aroma and flavor,prepared from roasted coffee beans";
		
	}
	@Override
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage#getCost()
	 */
	public int getCost()
	{
		return super.getCost();
	}
}
