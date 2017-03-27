/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.ingredients;

import edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage;

public class Jasmine extends FlavoredBeverage

{
	private int cost;
	/*
	 * Constructor calling super constructor
	 */
	public Jasmine(Beverage b)
	{
		super(b);
		this.cost = 50;
		// TODO Auto-generated constructor stub
	}
	
	public void	Jasmine()
	{
		this.cost = 50;
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
