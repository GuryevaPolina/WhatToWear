package edu.adm.spbstu.whattowear;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class ClosetActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    TextView city, temperature, precip;
    String currCity = "Samara";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadViews();
        updateWeather();
    }

    void updateWeather() {
        WeatherUpdater weatherUpdater = new WeatherUpdater();
        weatherUpdater.updateWeather(this, currCity);
        city.setText(currCity);
        temperature.setText(weatherUpdater.getTemperature());
        precip.setText(weatherUpdater.getPrecip());
    }

    void loadViews() {
        setContentView(R.layout.activity_closet);
        constraintLayout = findViewById(R.id.view2);
        constraintLayout.setBackgroundColor(Color.parseColor("#CEF6F5"));
        city = findViewById(R.id.city);
        temperature = findViewById(R.id.temperature);
        precip = findViewById(R.id.precip);
    }


}
