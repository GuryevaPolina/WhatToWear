package edu.adm.spbstu.whattowear;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class ClosetActivity extends AppCompatActivity {

    static final String fileName = "user_info";

    private ConstraintLayout constraintLayout;
    TextView city, temperature, precip;
    String currCity = "Samara";
    boolean isWoman = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadViews();
        if (isFirstLaunching()) {
            showGenderAlert();
        } else {
            getGender();
        }
        updateWeather();
    }

    void getGender() {
        try {
            FileInputStream fis = this.openFileInput(fileName);
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

        File file = this.getFileStreamPath(fileName);
        return !file.exists();
    }

    @Override
    protected void onPause() {
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
