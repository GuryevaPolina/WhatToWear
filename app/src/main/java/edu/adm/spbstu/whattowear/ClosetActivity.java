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
    TextView city;
    TextView temperature;
    TextView precip;
    int varToUpdate = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        constraintLayout = findViewById(R.id.view2);
        constraintLayout.setBackgroundColor(Color.parseColor("#CEF6F5"));
        city = findViewById(R.id.city);
        temperature = findViewById(R.id.temperature);
        precip = findViewById(R.id.precip);

        updateWeather();
        new Thread(() -> {
            varToUpdate ++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) { }
        }).start();
    }

    void updateWeather() {
        RemoteFetch fetch = new RemoteFetch(this);
        try {
            fetch.execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject json = fetch.getJson();

        city.setText("Saint-P");

        try {
            JSONObject currently = json.getJSONObject("currently");
            String temp = currently.getString("temperature");
            int tempInCelsium = (int) ((Double.valueOf(temp) - 32) / 2.0 * 1.1);
            temperature.setText(tempInCelsium + "°C");

            String prec = currently.getString("summary");
            precip.setText(prec);
        } catch (JSONException e) {
            System.out.println("json decoding error");
        }

    }

}
