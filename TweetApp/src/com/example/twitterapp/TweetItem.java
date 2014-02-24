package com.example.twitterapp;

import android.graphics.Bitmap;

//TweetItem contains some Data of a Twitter User...
public class TweetItem {

	
	
	private String ScreenName ;
	private String Location;
	private String Info;
	private String Tweet;
	private Bitmap ProfileImage;
	
	
	

	
	
	public TweetItem(String tweet) {
		
		
		this.Tweet = tweet;
		
	}


	public TweetItem(String screenName, Bitmap profileImage, String info,String location) {
		
		this.ScreenName = screenName;
		this.ProfileImage = profileImage;
		this.Info = info;
		this.Location = location;
	}


	
	
	public String getScreenName() {
		return ScreenName;
	}
	
	
	public void setScreenName(String screenName) {
		ScreenName = "@" + screenName;
	}
	
	
	public String getLocation() {
		return Location;
	}
	
	
	public void setLocation(String location) {
		Location = location;
	}
	
	
	public String getInfo() {
		return Info;
	}
	
	
	public void setInfo(String info) {
		Info = info;
	}
	
	
	public String getTweet() {
		return Tweet;
	}
	
	
	public void setTweet(String tweet) {
		Tweet = tweet;
	}
	
	
	public Bitmap getProfileImage() {
		return ProfileImage;
	}
	
	
	public void setProfileImage(Bitmap profileImage) {
		ProfileImage = profileImage;
	}
	
}
