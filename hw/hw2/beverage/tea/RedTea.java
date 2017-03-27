/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.beverage.tea;

import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.TeaSizeFactor;

public class RedTea extends Tea
{
	private int cost;
	/*
	 * Constructor calling supper constructor
	 */
	public RedTea(TeaSizeFactor input)
	{
		super(input);
		this.cost = 80;
		
	}
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.beverage.tea.Tea#getCost()
	 */
	@Override
	public int getCost()
	{
		return this.cost + super.getCost();
	}
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage#prepare()
	 */
	@Override
	public void prepare()
	{
		super.prepare();
	}
	
}
