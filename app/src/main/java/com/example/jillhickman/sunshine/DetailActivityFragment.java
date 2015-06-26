package com.example.jillhickman.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private static final String LOG_TAG = DetailActivityFragment.class .getSimpleName();

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    private String mForecastStr;

    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Get the intent from DetailActivity to the fragment
        Intent intent = getActivity().getIntent();
        //Inflate the view
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            //Get the forecast data, but now renamed from member variable
             mForecastStr = intent.getStringExtra("REAL_DATA");
        //Always remember I need to cast for any specific view
        TextView textView = (TextView) rootView.findViewById(R.id.detail_text);
        //The view updates to the forecast data
        textView.setText(mForecastStr);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater){
        //Inflate the menu, this adds items to the action bar if present.
        inflater.inflate(R.menu.detailfragment, menu);

        //Retrieve the share menu item
        MenuItem menuItem = menu.findItem(R.id.action_share);

        //Get the provider and hold onto it to set/change the share intent.
        ShareActionProvider mShareActionProvider= (ShareActionProvider)
                MenuItemCompat.getActionProvider(menuItem);

        //Attach and intent to this ShareActionProvider. Can update this anytime.
        //like when the user selects a new piece of data they might like to share.
        if (mShareActionProvider != null){
            mShareActionProvider.setShareIntent(createShareForecastIntent());
        } else {
            Log.d(LOG_TAG, "Share Action Provider is null?");
        }
    }



    //Create a share intent of the mForecastStr with the the hashtag
    private Intent createShareForecastIntent(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        //(important) Prevents the activity we are sharing to from being
        //placed on the activity stack. It will take you back to your application.
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, mForecastStr + FORECAST_SHARE_HASHTAG);

        return  shareIntent;
    }
}
