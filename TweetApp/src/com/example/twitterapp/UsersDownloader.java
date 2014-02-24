package com.example.twitterapp;



import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;




@TargetApi(Build.VERSION_CODES.CUPCAKE)
@SuppressLint("NewApi")
public class UsersDownloader extends AsyncTask<String, Void, ArrayList<TweetItem>> {


	
	Status status;
	
	ArrayList<TweetItem> mTweets;
	TweetItem tweet;
	
	private MainActivity mParentActivity;

	
    Twitter mTwitter;
	
    //CAUTION....These are my keys...these keys are Unique to an App...
    //Get your Own keys from Twitter by registering your App at Twitter..
    //Register your App at dev.twitter.com
    
    String consumer_key = "Ny8sPUjmdvyIlRwrp8cxeg";
   	String consumer_secret = "ktyxzrpF0dVj5pENft5BSpbbRpC05qwWGpwGDXU66Js";
   	String access_token = "2355083988-YEQmzejzl9mz9JJv1YDhuwt3GLW5PN5eqArYN9K";
   	String access_token_secret = "vJXB8oBUcBKXnUyecU852MeFJdckwkCylBH3hKI8Pg14u";

    
   	

   	// Constructor
	public UsersDownloader(MainActivity parentActivity) {
		super();
        
	   
		mParentActivity = parentActivity;
		

	}

	
   
	//Download the Profile Image, Description, Name, Location of the User  from TWITTER..
	@Override
	protected ArrayList<TweetItem> doInBackground(String... users) {
		
		
		
		mTweets = new ArrayList<TweetItem>();
		 
		 try {
			mTwitter = getTwitter();
			
			
			
			for(int i = 0; i < users.length ;i++){
			   
			 
				
				String args = users[i];
			    ResponseList<twitter4j.Status> status1 = mTwitter.getUserTimeline(args);
			
			    List<twitter4j.Status> statuses = status1.subList(0, 1);
				
				
			  
			  for (twitter4j.Status stat : statuses) {
					
					String screenName = stat.getUser().getScreenName();
					String Location = stat.getUser().getLocation();				
					String Info = stat.getUser().getDescription();
					
					String img = stat.getUser().getBiggerProfileImageURL();
					URL img_url = new URL(img);
					Bitmap bmp = BitmapFactory.decodeStream(img_url.openConnection().getInputStream());
					
					
					
					tweet = new TweetItem(screenName, bmp , Info,Location);
					mTweets.add(tweet);
					
			    } 
			
			}
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (TwitterException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	
		
		return mTweets;
		
		
	
	}

	

	
	// Call back to the MainActivity to update the feed display
	
	@Override
	protected void onPostExecute(ArrayList<TweetItem> result) {
		super.onPostExecute(result);

		if (mParentActivity != null) {
			mParentActivity.update(result);
		}

	}
	
	
	
	//Authorization needed to fetch data from TWITTER...
	 private Twitter getTwitter() {
	        ConfigurationBuilder cb = new ConfigurationBuilder();
	        cb.setOAuthConsumerKey(consumer_key);
	        cb.setOAuthConsumerSecret(consumer_secret);
	        cb.setOAuthAccessToken(access_token);
	        cb.setOAuthAccessTokenSecret(access_token_secret);
	        return new TwitterFactory(cb.build()).getInstance();
	    }

	  
	  
	   	
	

}