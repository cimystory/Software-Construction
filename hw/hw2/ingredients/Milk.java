/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.ingredients;

import edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage;

public class Milk extends FlavoredBeverage 
{
	private int cost;
	/*
	 * Calls the default constructor to equate beverage to it
	 */
	
	public Milk(Beverage b) 
	{
		super(b);
		this.cost = 30;
		// TODO Auto-generated constructor stub
	}
	
	public void Milk()
	{
		this.cost = 30;
	}
	@Override
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.ingredients.FlavoredBeverage#getCost()
	 */
	
	public int getCost()
	{
		return this.cost + super.getCost();
	}
}
