package com.example.twitterapp;

import java.util.ArrayList;
import twitter4j.Twitter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;



public class UserActivity extends Activity{

	
	
	Context mContext;
	TweetAdapter tweetAdapter;
	Twitter mTwitter;
	 LinearLayout container;
	ImageView profileImage,backgroundImage;
    TextView userInfo,userLocation,userName,tweetLabel,loading;
	String result,screenName,Location,Info;
	ListView listTweets;
	ProgressBar progress;
	Bitmap pic;
	 ArrayList<TweetItem> tweet;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		     
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
		
		setContentView(R.layout.user);
		
		tweet = new ArrayList<TweetItem>();
		
		
		//Getting the Extras from the Intent that started this Activity..
		Intent intent = getIntent();
		result = intent.getStringExtra("key");
		screenName = intent.getStringExtra("screenName");
		Location = intent.getStringExtra("Location");
		Info = intent.getStringExtra("Info");
		pic = (Bitmap)intent.getParcelableExtra("profileImg");
		
		
		//Getting reference to the UI Elements..
		  container = (LinearLayout) findViewById(R.id.containerUser);
		  userInfo = (TextView) findViewById(R.id.userInfo);
		  userLocation = (TextView) findViewById(R.id.userLocation);
	      userName = (TextView) findViewById(R.id.userName);
	      profileImage = (ImageView) findViewById(R.id.profileImage);
	      listTweets = (ListView) findViewById(R.id.listTweets);
	      tweetLabel = (TextView) findViewById(R.id.tweetLabel);
	     
	      loading = (TextView) findViewById(R.id.loading);
	      loading.setBackgroundColor(Color.rgb(79, 213, 214));
		
		 mContext = getApplicationContext();
	     tweetAdapter = new TweetAdapter(mContext);
		
	    //Executing the Tweet Downloader Async Task to download 5 recent Tweets of the User... 
        new TweetDownloader(this).execute(result);
        
		 
	    listTweets.setAdapter(tweetAdapter);
		 
		 
	
	}

	
	
	
     
  public void update(ArrayList<TweetItem> tItems){
	  
	    
     
      loading.setVisibility(TextView.GONE);
      container.setBackgroundColor(Color.rgb(79, 213, 214));
	  tweetLabel.setVisibility(TextView.VISIBLE);
 
	 
	  
		   profileImage.setImageBitmap(pic);
		   userName.setText(screenName);
	       userLocation.setText(Location);
	       userInfo.setText(Info); 
			
		   
		  for(int i=0 ; i < tItems.size();i++){
				 
			   TweetItem item = new TweetItem(tItems.get(i).getTweet());
				item.setProfileImage(pic);
				item.setScreenName(screenName);
			   tweet.add(item);
							 
			 }
		
		  
		  
		  for(int i=0 ; i < tweet.size();i++){
				 
				 tweetAdapter.add(tweet.get(i));
							 
			 }
		  
			   
					
	  }

	    	
	      
				
			
 }

	
	

