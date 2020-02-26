package com.example.sensordatalogger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<SensorData> {
    ListViewAdapter(Context context, ArrayList<SensorData> sensorData){
        super(context, 0, sensorData);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SensorData sensorData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sensor_data_template, parent, false);
        }
        // Lookup view for data population
        TextView value = convertView.findViewById(R.id.textValue);
        TextView label = convertView.findViewById(R.id.textLabel);
        // Populate the data into the template view using the data object
        value.setText(sensorData.value);
        label.setText(sensorData.label);
        // Return the completed view to render on screen
        return convertView;
    }
}
