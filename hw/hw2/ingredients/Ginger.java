/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.ingredients;

import edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage;

public class Ginger extends FlavoredBeverage
{
	

	private int cost;
	/*
	 * Calls the super constructor to the set the beverage
	 */
	public Ginger(Beverage b) 
	{
		super(b);
		this.cost = 60;
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Sets the cost
	 */
	public void Ginger()
	{
		this.cost = 60;
	}
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.ingredients.FlavoredBeverage#getCost()
	 */
	@Override
	public int getCost()
	{
		return this.cost+super.getCost();
	}
	
}
