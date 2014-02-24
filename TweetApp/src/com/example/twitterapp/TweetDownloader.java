package com.example.twitterapp;



import java.util.ArrayList;
import java.util.List;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import android.os.AsyncTask;




public class TweetDownloader extends AsyncTask<String, Integer, ArrayList<TweetItem>> {


	
	



	Status status;
	
	ArrayList<TweetItem> mTweets;
	TweetItem tweet;
	
	private UserActivity mParentActivity;

    Twitter mTwitter;
	
    
    //CAUTION....These are my keys...these keys are Unique to an App...
    //Get your Own keys from Twitter by registering your App at Twitter..
    //Register your App at dev.twitter.com
   
    String consumer_key = "Ny8sPUjmdvyIlRwrp8cxeg";
   	String consumer_secret = "ktyxzrpF0dVj5pENft5BSpbbRpC05qwWGpwGDXU66Js";
   	String access_token = "2355083988-YEQmzejzl9mz9JJv1YDhuwt3GLW5PN5eqArYN9K";
   	String access_token_secret = "vJXB8oBUcBKXnUyecU852MeFJdckwkCylBH3hKI8Pg14u";

    
   	

   	// Constructor
	public TweetDownloader(UserActivity userActivity) {
		super();
        
	   
		mParentActivity = userActivity;
	

	}

	

	
	@Override
	protected ArrayList<TweetItem> doInBackground(String... users) {
		
		
	
		
		mTweets = new ArrayList<TweetItem>();
		 
		 
			//Downloads five recent tweets of the User Selected..
			
				try {
					ResponseList<twitter4j.Status> status1;
					
						mTwitter = getTwitter();
						 String args = users[0];
						 status1 = mTwitter.getUserTimeline(args);
						 List<twitter4j.Status> statuses = status1.subList(0, 5);
					
					 
					 
					 for (twitter4j.Status stat : statuses) {
							
							
							String status = stat.getText();
							tweet = new TweetItem( status);
							
							mTweets.add(tweet);
							
						}
				} catch (TwitterException e) {
					
					e.printStackTrace();
				}
				  
		
	
	       return mTweets;
		
		
	
	}
	

	
	// Call back to the UserActivity to update the feed display
	
	@Override
	protected void onPostExecute(ArrayList<TweetItem> result) {
		super.onPostExecute(result);

		if (mParentActivity != null) {
			mParentActivity.update(result);
		}

	}
	
	
	
	// Authorization needed to get data from  Twitter...
	 private Twitter getTwitter() {
		 
	        ConfigurationBuilder cb = new ConfigurationBuilder();
	        cb.setOAuthConsumerKey(consumer_key);
	        cb.setOAuthConsumerSecret(consumer_secret);
	        cb.setOAuthAccessToken(access_token);
	        cb.setOAuthAccessTokenSecret(access_token_secret);
	        return new TwitterFactory(cb.build()).getInstance();
	   
	 }

	  
	  

}
