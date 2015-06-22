package com.example.jillhickman.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Get the intent from DetailActivity to the fragment
        Intent intent = getActivity().getIntent();
        //Inflate the view
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        //Get the forcastData
        String forecastData = intent.getStringExtra("REAL_DATA");
        //Always remember I need to cast for any specific view
        TextView textView = (TextView) rootView.findViewById(R.id.detail_text);
        //The view updates to the forecastData
        textView.setText(forecastData);
        return rootView;
    }
}
