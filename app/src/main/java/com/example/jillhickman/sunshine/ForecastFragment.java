package com.example.jillhickman.sunshine;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jillhickman.sunshine.data.WeatherContract;


/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {
    //Instance of ForecastAdapter
    private ForecastAdapter mForecastAdapter;


    public ForecastFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //for fragment to handle menu events
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        //Inflates the menu
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Handle action bar item click here.
        //Action bar will automatically handle clicks on Home/up button,
        //as long as a specified parent activity in AndroidManifest.xml
        int id = item.getItemId();
        if (id==R.id.action_refresh){
            //Update weather data when refresh button clicked
            updateWeather();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Get data from the database.
        String locationSetting = Utility.getPreferredLocation(getActivity());

        // Sort order:  Ascending, by date.
        String sortOrder = WeatherContract.WeatherEntry.COLUMN_DATE + " ASC";
        Uri weatherForLocationUri = WeatherContract.WeatherEntry.buildWeatherLocationWithStartDate(
                locationSetting, System.currentTimeMillis());

        Cursor cur = getActivity().getContentResolver().query(weatherForLocationUri,
                null, null, null, sortOrder);
        // The CursorAdapter will take data from our cursor and populate the ListView
        // However, we cannot use FLAG_AUTO_REQUERY since it is deprecated, so we will end
        // up with an empty list the first time we run.
        mForecastAdapter = new ForecastAdapter(getActivity(), cur, 0);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //Get reference to the ListView, and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);

        return rootView;
    }

    //Helper method so that this method can be called during onStart() to update with weather data
    //
    private void updateWeather() {

        FetchWeatherTask weatherTask = new FetchWeatherTask(getActivity());
        String location = Utility.getPreferredLocation(getActivity());
        weatherTask.execute(location);
    }

    //When starting device, updateWeather() called so that the view populates with weatherData
    //Override so "refresh" happen when the fragment start
    @Override
    public void onStart(){
        super.onStart();
        updateWeather();

    }
    
}



