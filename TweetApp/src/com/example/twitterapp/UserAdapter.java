package com.example.twitterapp;



import java.util.ArrayList;
import java.util.List;


import twitter4j.Status;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserAdapter extends BaseAdapter{

	
	private List<TweetItem> mItems = new ArrayList<TweetItem>();
	
	
    private final Context mContext;
    Status mStatus;
  
	
     
	public UserAdapter(Context context) {
	
		 mContext = context;
		 
		
	}

	
	public void add(TweetItem tweetItem) {

		mItems.add(tweetItem);
		notifyDataSetChanged();

	}
	
	
	
	    
	    
	    @Override
	public int getCount() {
		// TODO Auto-generated method stub
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
	    convertView = mInflater.inflate(R.layout.row, null, true);
		
	   }
	   
	   
		 TweetItem user = mItems.get(position);
		
		ImageView pic = (ImageView) convertView.findViewById(R.id.imgView);
		TextView name = (TextView) convertView.findViewById(R.id.txtStatus);
		LinearLayout listUser = (LinearLayout) convertView.findViewById(R.id.listUsers);
		
		
		listUser.setBackgroundColor(Color.rgb(79, 213, 214));
		pic.setImageBitmap(user.getProfileImage());
		name.setText(user.getScreenName());
		
	
		
		return convertView;
	}

}
