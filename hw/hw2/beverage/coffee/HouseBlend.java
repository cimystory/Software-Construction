/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.beverage.coffee;

import edu.cmu.qatar.cs214.hw.hw2.beverage.recipe.Recipe;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.CoffeeSizeFactor;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.SizeFactor;

public class HouseBlend extends Coffee
{
	private int cost;
	
	public HouseBlend(CoffeeSizeFactor input)
	{
		super(input);
		this.cost = 80;
	}
	
	public int getCost()
	{
		return this.cost + super.getCost();
	}
	
	public void prepare()
	{
		super.prepare();
	}
	
}
