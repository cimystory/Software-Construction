/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.beverage.tea;
import edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage;
import edu.cmu.qatar.cs214.hw.hw2.beverage.recipe.CoffeeDrink;
import edu.cmu.qatar.cs214.hw.hw2.beverage.recipe.Recipe;
import edu.cmu.qatar.cs214.hw.hw2.beverage.recipe.TeaDrink;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.CoffeeSizeFactor;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.SizeFactor;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.TeaSizeFactor;

public abstract class Tea extends Beverage
{	
	/*
		Tea Calling super constructor- beverage.
 	*/
	
	public Tea(TeaSizeFactor TF)
	{
		super(TF,new TeaDrink(),"Tea is an aromatic "
				+ "beverage commonly prepared by "
				+ "pouring hot or boiling water over "
				+ "cured leaves of the Camellia sinensis,"
				+ " an evergreen shrub native to Asia.");
	}
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage#getCost()
	 */
	@Override
	
	public int getCost()
	{
		return super.getCost();
	}
}
