package com.codepath.apps.restclienttemplate;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.GridView;
import android.widget.ListView;

import com.codepath.apps.restclienttemplate.models.Tweet;

public class TimelineActivity extends Activity {
	TweetArrayAdapter tweetAdapter;
	ArrayList<Tweet> tweetResults = new ArrayList<Tweet>();
	ListView lvTweets;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setupViews();

		Intent i = getIntent();
		String jsonString = i.getStringExtra("jsonString");
		
		JSONArray jsonTweets;
		try {
			jsonTweets = new JSONArray(jsonString);
			tweetAdapter = new TweetArrayAdapter(this, tweetResults);

			tweetAdapter.addAll(Tweet.fromJson(jsonTweets));
			lvTweets.setAdapter(tweetAdapter);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		
		Log.d("DEBUG","onCreate TimelineActivity" + jsonString);

		
	}

	public void setupViews() {
		lvTweets = (ListView) findViewById(R.id.lvTweets);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}

}
