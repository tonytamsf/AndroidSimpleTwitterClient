package com.codepath.apps.restclienttemplate;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.image.SmartImageView;

public class TweetArrayAdapter extends ArrayAdapter<Tweet>{

	/**
	 * @param context
	 * @param textViewResourceId
	 * @param objects
	 */
	public TweetArrayAdapter(Context context, List<Tweet> tweets) {
		super(context, R.layout.single_tweet, tweets);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View tweetView;
		Tweet tweet = this.getItem(position);
		Log.d("DEBUG", tweet.getUser().getName());

		if (convertView	 == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			tweetView = (View) inflator.inflate(
					R.layout.single_tweet,
					parent,
					false
					);
			
		} else {
			tweetView = (View) convertView;
			// TODO
		}		// TODO set the image, username, title, description
		TextView tvFullName = (TextView) tweetView.findViewById(R.id.tvFullName);
		tvFullName.setText(tweet.getUser().getName());
		
		TextView tvBody = (TextView) tweetView.findViewById(R.id.tvBody);
		tvBody.setText(tweet.getBody());
		
		TextView tvUsername = (TextView) tweetView.findViewById(R.id.tvUserName);
		tvUsername.setText("@" + tweet.getUser().getScreenName());
		
		SmartImageView profileImage = (SmartImageView) tweetView.findViewById(R.id.simageProfile);
		profileImage.setImageUrl(tweet.getUser().getProfileImageUrl());
		return tweetView;
	}


	 
}
