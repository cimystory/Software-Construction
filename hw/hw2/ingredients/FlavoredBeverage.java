/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.ingredients;

import edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage;
/*
 * Abstract Class which is COMPOSED of Beverage and thus
 * has an object of type beverage
 */
public abstract class FlavoredBeverage extends Beverage {

    private Beverage bev;
    /*
     * Constructor
     */
    public FlavoredBeverage(Beverage b)
    {
    	this.bev = b;
    }
    
    /*
     * (non-Javadoc)
     * @see edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage#getCost()
     */
    
    @Override
    public int getCost() 
    {
    	return bev.getCost();
        // TODO: implement this
    }

}
