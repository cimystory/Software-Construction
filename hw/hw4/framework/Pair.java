package edu.cmu.qatar.cs214.hw.hw4.framework;

/*
	Code taken from : http://stackoverflow.com/questions/521171/a-java-collection-of-value-pairs-tuples
*/
public class Pair<L,R> {

	  private final L left;
	  private final R right;

	  public Pair(L left, R right) 
	  {
	    this.left = left;
	    this.right = right;
	  }

	  public L getLeft() 
	  { 
		  return left; 
	  }
	  public R getRight() 
	  {	
		  return right; 
	  }
	  
}
