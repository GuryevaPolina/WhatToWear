package edu.adm.spbstu.whattowear;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ClosetActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static final String fileName = "user_info";
    ConstraintLayout constraintLayout;
    Switch genderSwitch;
    ImageView human;
    TextView temperature, precip, windSpeed;
    String currCity = "Moscow";
    boolean isWoman = true;

    Animator animator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);

        loadViews();
        if (isFirstLaunching()) {
            showGenderAlert();
        } else {
            getGender();
        }

    //    System.out.println(Locale.getDefault().getDisplayLanguage());
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
                            case "male":
                                isWoman = false;
                                human.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.boy));
                                break;
                            case "female":
                                isWoman = true;
                                human.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.girl));
                                break;
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
                case 0: isWoman = false;
                    human.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.boy));
                    break;
                case 1: isWoman = true;
                    human.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.girl));
                    break;
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        alert.getWindow().setLayout(600, 1200);
    }

    @SuppressLint("SetTextI18n")
    void updateWeather() {
        animator.stopAnimation();
        animator.stopCloudsAnimation();

        WeatherUpdater weatherUpdater = new WeatherUpdater();
        switch (weatherUpdater.updateWeather(this, currCity)) {
            case RAIN: animator.rainAnimation(); break;
            case SNOW: animator.snowAnimation();break;
            case NONE: break;
        }
        animator.startCloudsAnimation((int) Float.parseFloat(weatherUpdater.getWindSpeed()));

        temperature.setText(weatherUpdater.getTemperature() + "°C");
        precip.setText(weatherUpdater.getPrecip());
        windSpeed.setText(weatherUpdater.getWindSpeed() + " м/с");
    }

    @SuppressLint("NewApi")
    void loadViews() {
        temperature = findViewById(R.id.temperature);
        precip = findViewById(R.id.precip);
        windSpeed = findViewById(R.id.windSpeed);
        constraintLayout = findViewById(R.id.weatherView);
        human = findViewById(R.id.human);
        genderSwitch = findViewById(R.id.genderSwitch);

        genderSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isWoman = !isWoman;
            if (isWoman) {
                human.setImageDrawable(ContextCompat.getDrawable(
                        ClosetActivity.this.getApplicationContext(), R.drawable.girl));
            } else {
                human.setImageDrawable(ContextCompat.getDrawable(
                        ClosetActivity.this.getApplicationContext(), R.drawable.boy));
            }
        });

        Date currentTime = Calendar.getInstance().getTime();
        switch (currentTime.getMonth()) {
            case Month.JUNE: case Month.JULY: case Month.AUGUST:
                constraintLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.summer));
                break;
            case Month.SEPTEMBER: case Month.OCTOBER: case Month.NOVEMBER:
                constraintLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.autumn));
                break;
            case Month.DECEMBER: case Month.JANUARY: case Month.FEBRUARY:
                constraintLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.winter));
                break;
            case Month.MARCH: case Month.APRIL: case Month.MAY:
                constraintLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.spring));
                break;
            default: break;
        }

        ArrayList<String> list = new ArrayList<>(CityCoordinate.map.keySet());
        Spinner spinner = findViewById(R.id.citySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.custom_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        animator = new Animator(this, constraintLayout);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currCity = parent.getItemAtPosition(position).toString();
        updateWeather();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}
