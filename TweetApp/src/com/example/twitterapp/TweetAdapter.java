package com.example.twitterapp;

import java.util.ArrayList;
import java.util.List;


import twitter4j.Status;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetAdapter extends BaseAdapter{

	
	private List<TweetItem> mItems = new ArrayList<TweetItem>();
	private final Context mContext;
        Status mStatus;
  
	
     
	public TweetAdapter(Context context) {
	
		 mContext = context;
		 
		
	}

	public void add(TweetItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}
	
	
	
	    
	    
	    @Override
	public int getCount() {
		
		return mItems.size();
	}

	@Override
	public TweetItem getItem(int position) {
		
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
	  if(convertView == null){
		  
		LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = mInflater.inflate(R.layout.user_adapter, parent, false);
		
	  }
	  
	  
		TweetItem tweet = mItems.get(position);
		
		ImageView tweetImage = (ImageView)convertView.findViewById(R.id.tweetImage);
		TextView tweetName = (TextView)convertView.findViewById(R.id.tweetName);
		TextView tweetStatus =  (TextView) convertView.findViewById(R.id.tweetStatus);
		
		
					
		tweetImage.setImageBitmap(tweet.getProfileImage());	
		tweetName.setText(tweet.getScreenName());
		tweetStatus.setText(tweet.getTweet());
	
		
		return convertView;
	}

}
