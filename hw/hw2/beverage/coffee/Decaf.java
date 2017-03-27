/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.beverage.coffee;

import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.CoffeeSizeFactor;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.SizeFactor;

public class Decaf extends Coffee
{
	private int cost;
	/*
	 * Constructor that calls super class constructor
	 */
	public Decaf(CoffeeSizeFactor input)
	{
		super(input);
		this.cost = 50;
	}
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.beverage.coffee.Coffee#getCost()
	 */
	@Override
	public int getCost()
	{
		return this.cost + super.getCost();
	}
	@Override
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage#prepare()
	 */
	public void prepare()
	{
		super.prepare();
	}
	
}
