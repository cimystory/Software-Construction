package edu.cmu.qatar.cs214.hw.hw4.plugin;

import java.util.ArrayList;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import edu.cmu.qatar.cs214.hw.hw4.framework.Data;
import edu.cmu.qatar.cs214.hw.hw4.framework.DataPlugin;

public class TwitterDP implements DataPlugin
{
	// Storing it manually so I can provide the source.
	private String source = "www.twitter.com";
	private Twitter twitter;
	private int limit;
	
	public TwitterDP()
	{
		/* The following code is taken from Twitter4J
		 * website : http://twitter4j.org/en/configuration.html
		 * except a few changes of tokens
		 */
		this.limit = 1000;
		this.twitter = TwitterFactory.getSingleton();
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("XjbcfFgRSqRw2VKjqrwzSWtKt")
		.setOAuthConsumerSecret("vQmHn7IVtVwZcTNfeQ3ItiHrnuUWVEQf8oamgk0PFA7xHPX3Gn")
		.setOAuthAccessToken("134687994-ACAmcoLxzZ7J39mG2QIpUhjh6ofcqgWLwxKs75Cm")
		.setOAuthAccessTokenSecret("INp9G2TNDFxw90yLcv44DdSiAA5BNx4aHBldSroIc7L7Y");
		TwitterFactory tf = new TwitterFactory(cb.build());
		this.twitter = tf.getInstance();
	}
	

	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw4.framework.DataPlugin#getPosts(java.lang.String)
	 */
	
	@Override
	public ArrayList<Data> getData(String Qry) 
	{
	    // The factory instance is re-useable and thread safe.
		
		/* Some of the following code is taken from Twitter4J
		 * website : http://twitter4j.org/en/configuration.html
		 */
		
		ArrayList<Data> returnArr = new ArrayList<Data>();
	    Query query = new Query(Qry);
	    query.setCount(this.limit);
	    QueryResult result = null;
		try {
			result = this.twitter.search(query);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for (Status status : result.getTweets()) 
	    {
	    	Data temp = new Data();
	    	
	    	temp.setId(status.getUser().getScreenName());
	    	temp.setPost(status.getText());
	    	temp.setDate(status.getCreatedAt().toString());
	    	System.out.println(status.getText().toString());
	    	returnArr.add(temp);
	    }
		return returnArr;
	}

	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw4.framework.DataPlugin#getSource()
	 */
	@Override
	public String getSource() 
	{
		// TODO Auto-generated method stub
		return this.source;
	}


	@Override
	public int getCountOfPosts() 
	{
		// TODO Auto-generated method stub
		return this.limit;
	}
	
}
