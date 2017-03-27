/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.beverage.recipe;
/*
 * CoffeeDrink class gives us the recipe for coffee
 */
public class CoffeeDrink implements Recipe
{
	@Override
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.beverage.recipe.Recipe#prepare()
	 */
	public void prepare()
	{
		System.out.print("Add Coffee \n"
				+ "Process Ingredients \n"
				+ "Add Ingredients");
	}
	
}
