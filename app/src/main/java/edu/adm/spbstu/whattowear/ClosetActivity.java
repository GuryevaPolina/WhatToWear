package edu.adm.spbstu.whattowear;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ClosetActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    TextView city, temperature, precip;
    String currCity = "Samara";
    boolean isWoman = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadViews();
        showGenderAlert();
        updateWeather();

    }

    void showGenderAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choose gender, please");
        String[] items = {"male", "female"};
        builder.setItems(items, (dialog, which) -> {
            switch (which) {
                case 0: isWoman = false; break;
                case 1: isWoman = true; break;
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
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
        constraintLayout.setBackgroundColor(getResources().getColor(R.color.backgroundClothet));
        city = findViewById(R.id.city);
        temperature = findViewById(R.id.temperature);
        precip = findViewById(R.id.precip);
    }


}
