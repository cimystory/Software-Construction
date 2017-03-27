/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.beverage.tea;

import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.TeaSizeFactor;

public class WhiteTea extends Tea
{
	private int cost;
	/*
	 * Constructor calling super constructor
	 */
	public WhiteTea(TeaSizeFactor input)
	{
		
		super(input);
		this.cost = 100;
	}
	@Override
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.beverage.tea.Tea#getCost()
	 */
	public int getCost()
	{	
		return this.cost + super.getCost();
	}
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage#prepare()
	 */
	public void prepare()
	{
		super.prepare();
	}
	
}
