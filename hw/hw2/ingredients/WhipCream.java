/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.ingredients;

import edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage;

public class WhipCream extends FlavoredBeverage

{
	private int cost;
	/*
	 * Sets the beverage by calling super constructor
	 */
	public WhipCream(Beverage b) 
	{
		super(b);
		this.cost = 30;
		// TODO Auto-generated constructor stub
	}
	
	public void WhipCream()
	{
		this.cost = 30;
	}
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.ingredients.FlavoredBeverage#getCost()
	 */
	@Override
	public int getCost()
	{
		return this.cost + super.getCost();
	}
}
