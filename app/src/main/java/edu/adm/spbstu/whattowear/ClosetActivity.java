package edu.adm.spbstu.whattowear;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ClosetActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static final String fileName = "user_info";

    TextView temperature, precip;
    String currCity = "Moscow";
    boolean isWoman = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);

        loadViews();
        if (isFirstLaunching()) {
            showGenderAlert();
        } else {
            getGender();
        }
    }


    void getGender() {
        try {
            FileInputStream fis = openFileInput(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            for ( String splitBySemicolon : bufferedReader.readLine().split(";")) {
                String[] splitByColon = splitBySemicolon.split(":");

                switch (splitByColon[0].trim()) {
                    case "gender":
                        switch (splitByColon[1].trim()) {
                            case "male": isWoman = false; break;
                            case "female": isWoman = true; break;
                            default: System.out.println("error parse file");
                        }
                        break;

                    case "city":
                        currCity = splitByColon[1].trim();
                        break;
                    default:
                        System.out.println("error parse file");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean isFirstLaunching() {
        File file = getFileStreamPath(fileName);
        return !file.exists();
    }

    @Override
    public void onPause() {
        super.onPause();
        FileOutputStream fos;
        String fileContent;
        if (isWoman) {
            fileContent = "city:" + currCity + "; gender: female";
        } else {
            fileContent = "city:" + currCity + "; gender: male";
        }

        try {
            fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(fileContent.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        temperature.setText(weatherUpdater.getTemperature());
        precip.setText(weatherUpdater.getPrecip());
    }

    void loadViews() {
        temperature = findViewById(R.id.temperature);
        precip = findViewById(R.id.precip);

        ArrayList<String> list = new ArrayList<>(CityCoordinate.map.keySet());
        Spinner spinner = findViewById(R.id.citySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.custom_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currCity = parent.getItemAtPosition(position).toString();
        updateWeather();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}
