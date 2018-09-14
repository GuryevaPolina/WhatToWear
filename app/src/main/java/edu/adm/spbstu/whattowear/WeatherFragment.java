package edu.adm.spbstu.whattowear;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Locale;

public class WeatherFragment extends Fragment {

    Handler handler;
    private TextView currCity;
    private TextView currTemperature;

    public WeatherFragment(){
        handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_closet, container, false);
        currCity = rootView.findViewById(R.id.city);
        currTemperature = rootView.findViewById(R.id.weatherValue);

        currCity.setText("Санкт-Петербург");
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateWeatherData(new CityPreference(getActivity()).getCity());
    }

    private void updateWeatherData(final String city) {
        new Thread() {
            public void run() {
                final JSONObject json = RemoteFetch.getJSON(getActivity(), city);
                if(json == null){
                    handler.post(new Runnable(){
                        public void run(){
                            Toast.makeText(getActivity(),
                                    getActivity().getString(R.string.place_not_found),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        public void run(){
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void renderWeather(JSONObject json){
        try {
            currCity.setText(json.getString("name").toUpperCase(Locale.US) + ", " +
                    json.getJSONObject("sys").getString("country"));

//            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
//            detailsField.setText(
//                    details.getString("description").toUpperCase(Locale.US) +
//                            "\n" + "Humidity: " + main.getString("humidity") + "%" +
//                            "\n" + "Pressure: " + main.getString("pressure") + " hPa");

            currTemperature.setText(String.format("%.2f", main.getDouble("temp"))+ " ℃");

//            DateFormat df = DateFormat.getDateTimeInstance();
//            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
//            updatedField.setText("Last update: " + updatedOn);
//
//            setWeatherIcon(details.getInt("id"),
//                    json.getJSONObject("sys").getLong("sunrise") * 1000,
//                    json.getJSONObject("sys").getLong("sunset") * 1000);

        } catch(Exception e) {
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }
}
