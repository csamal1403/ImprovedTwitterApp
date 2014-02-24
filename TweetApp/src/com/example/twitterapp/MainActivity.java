package com.example.twitterapp;


import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;



public class MainActivity extends Activity implements OnItemClickListener{
    
	
	TextView loader;
	ListView listUsers;
	Context mContext;
    UserAdapter adapter;
   
    ArrayList<Bitmap> profileImg;
    ArrayList<TweetItem> tweet;
    
   String[] user = {"@HannahTrigwell","@BestMackleMore","@Eminem","@taylorswift13","@GabrielleAplin","@Imaginedragons","@ladygaga"};
   String[] screenName,Location,Info;
	
 
   @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      
       setContentView(R.layout.activity_main); 
       
       tweet = new ArrayList<TweetItem>();
       profileImg = new ArrayList<Bitmap>();
        
       //Get reference to UI elements..
       loader = (TextView) findViewById(R.id.loaderText);
       listUsers = (ListView) findViewById(R.id.listUsers);
       
       
       loader.setBackgroundColor(Color.rgb(79, 213, 214));
        
        mContext = getApplicationContext();
        adapter = new UserAdapter(mContext);
        
        //Use the AsyncTask to Download..
        new UsersDownloader(this).execute(user);
      
        
        listUsers.setAdapter(adapter);
        listUsers.setOnItemClickListener(this); 
       
       
	
	}

  
    
  //Update the UI elements when the AsyncTask have done Downloading..        
  public void update(ArrayList<TweetItem> tItems){
	
	  
	  loader.setVisibility(TextView.GONE);
	  
	  
	 for(int i=0 ; i < tItems.size();i++){
	
		tweet.add(tItems.get(i));
		 adapter.add(tItems.get(i));
		 
	 }
	 
	 
	  
	
  }



//UserActivity starts onItemClick..
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	
	
	 Log.i("Twitter App", "onItem Clicked");
	 
	 Intent intent = new Intent(getApplicationContext(), UserActivity.class);
	 intent.putExtra("key", user[position]);
	 intent.putExtra("screenName", tweet.get(position).getScreenName());
	 intent.putExtra("Info", tweet.get(position).getInfo());
	 intent.putExtra("Location", tweet.get(position).getLocation());
	 intent.putExtra("profileImg", tweet.get(position).getProfileImage());
	 
	 Log.i("Twitter App", "Starting User Activity");
	 
     startActivity(intent);
	
	
}




}


