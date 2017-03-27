/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.beverage.recipe;
/*
 * TeaDrink class gives us the recipe for the tea
 */
public class TeaDrink implements Recipe
{
	@Override
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.beverage.recipe.Recipe#prepare()
	 */
	public void prepare()
	{
		System.out.print("Add water \n"
				+ "Add tea bags \n"
				+ "Add all ingredients");
	}
	
}
