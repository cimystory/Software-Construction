/*
 * Name  : Shahan Ali Memon
 * ID    : samemon
 * HW    : 4
 */


package edu.cmu.qatar.cs214.hw.hw4.plugin;

/*
 * Code taken from : http://www.gioviz.com/2013/05/how-to-sentiment-analysis-of-tweets.html
 */

import java.io.File;
import java.io.IOException;

import com.aliasi.classify.ConditionalClassification;
import com.aliasi.classify.LMClassifier;
import com.aliasi.util.AbstractExternalizable;

public class SentimentClassifier 
{

	private String[] categories;
	private LMClassifier cf;

	public SentimentClassifier() {
	try 
	{
		this.cf = (LMClassifier) AbstractExternalizable.readObject(new File("C:\\Users\\Shahan\\Desktop\\classifier.txt"));
		categories = cf.categories();
	}
	catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	catch (IOException e) {
		e.printStackTrace();
	}

	}

	public String classify(String text) 
	{
		ConditionalClassification classification = cf.classify(text);
		return classification.bestCategory();
	}
	
}
