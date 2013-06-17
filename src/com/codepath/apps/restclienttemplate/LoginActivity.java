package com.codepath.apps.restclienttemplate;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.oauth.OAuthLoginActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

public class LoginActivity extends OAuthLoginActivity<TwitterClient> {

	ArrayList<Tweet> tweetResults = new ArrayList<Tweet>();
	TweetArrayAdapter tweetAdapter; // TODO init
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("DEBUG", "onCreate");
		tweetAdapter = new TweetArrayAdapter(this,  tweetResults);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	// Inflate the menu; this adds items to the action bar if it is present.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	// OAuth authenticated successfully, launch primary authenticated activity
	// i.e Display application "homepage"
	@Override
    public void onLoginSuccess() {
		Log.d("DEBUG", "onLoginSuccess");
    	getClient().getHomeTimeline(new  JsonHttpResponseHandler() {

			/* (non-Javadoc)
			 * @see com.loopj.android.http.JsonHttpResponseHandler#onSuccess(org.json.JSONArray)
			 */ 
    		
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				//jsonTweets.get(0).getString("text");
				//jsonTweets.get(0).getJSONObject("user");
				
				Log.d("DEBUG", jsonTweets.toString());
				// Move on to TimelineActivity
				Intent i = new Intent(getApplicationContext(),
									TimelineActivity.class);
				i.putExtra("jsonString", jsonTweets.toString());
				startActivity(i);
				
			}
			
			@Override
			public void onFailure(Throwable arg0, JSONObject arg1) {
				super.onFailure(arg0, arg1);
			}
    	});
    	
    }

	// OAuth authentication flow failed, handle the error
	// i.e Display an error dialog or toast
	@Override
	public void onLoginFailure(Exception e) {
		Log.d("DEBUG", "onLoginFailure");
		e.printStackTrace();
	}

	// Click handler method for the button used to start OAuth flow
	// Uses the client to initiate OAuth authorization
	// This should be tied to a button used to login
	public void loginToRest(View view) {
		getClient().connect();
	}

}
