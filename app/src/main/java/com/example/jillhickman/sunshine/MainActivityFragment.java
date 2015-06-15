package com.example.jillhickman.sunshine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Create an array list of weather forecast list items
        String[] forecastArray = {
                "Today 6/14 - Sunny - 88/64",
                "Monday 6/15 - Cloudy - 77/60",
                "Tuesday 6/16 - Sunny - 80/60",
                "Wednesday 6/17 - Sunny - 84/64",
                "Thursday 6/18 - Sunny - 88/68",
                "Friday 6/19 - Sunny - 92/72"
        };
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));

        //Create an ArrayAdapter to populate the ListView it's attached to
        ArrayAdapter<String>mForecastAdapter =
                new ArrayAdapter<String>(getActivity(),R.layout.list_item_forecast, R.id.list_item_forecast_text_view, weekForecast);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //Get reference to the ListView, and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);

        return rootView;

    }
}
