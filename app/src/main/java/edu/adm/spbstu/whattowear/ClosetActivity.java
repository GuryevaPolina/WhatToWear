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
import android.widget.TextView;
import android.widget.Toast;

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
    ImageView girl, boy, girl_more_20_clothes, boy_more_20_clothes,
                         girl_10_20_clothes, boy_10_20_clothes,
                         girl_0_10_clothes, boy_0_10_clothes,
                         girl_less_0_clothes, boy_less_0_clothes,
                         umbrella, cap_boy, cap_girl;
    TextView temperature, precip, windSpeed,
             firstThing, secondThing, thirdThing;
    PrecipType precipType;
    String currCity;
    CityCoordinate cityCoordinate;
    boolean isWoman = true;

    Animator animator;
    WeatherUpdater globalWeatherUpdater;

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
                                girl.setVisibility(View.INVISIBLE);
                                boy.setVisibility(View.VISIBLE);
                                break;
                            case "female":
                                isWoman = true;
                                girl.setVisibility(View.VISIBLE);
                                boy.setVisibility(View.INVISIBLE);
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
        builder.setTitle(R.string.chooseGender);
        String[] items = {getString(R.string.male), getString(R.string.female)};
        builder.setItems(items, (dialog, which) -> {
            switch (which) {
                case 0: isWoman = false;
                    girl.setVisibility(View.INVISIBLE);
                    boy.setVisibility(View.VISIBLE);
                    break;
                case 1: isWoman = true;
                    girl.setVisibility(View.VISIBLE);
                    boy.setVisibility(View.INVISIBLE);
                    break;
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @SuppressLint("SetTextI18n")
    void updateWeather() {
        animator.stopAnimation();
        animator.stopCloudsAnimation();
        WeatherUpdater weatherUpdater = new WeatherUpdater();
        globalWeatherUpdater = weatherUpdater;
        precipType = weatherUpdater.updateWeather(this, currCity);
        int speed = (int) Float.parseFloat(weatherUpdater.getWindSpeed());
        switch (precipType) {
            case RAIN: animator.rainAnimation(); break;
            case SNOW: animator.snowAnimation(speed);break;
            case NONE: break;
        }
        animator.startCloudsAnimation(speed);

        temperature.setText(weatherUpdater.getTemperature() + "°C");
        precip.setText(weatherUpdater.getPrecip());
        windSpeed.setText(weatherUpdater.getWindSpeed() + " м/с");
        updateClothes();
    }

    void updateClothes() {
        hideAllClothes();
        ChoosingClothes choosingClothes = new ChoosingClothes(isWoman, precipType, this);
        Clothes[] clothes;
        String[] clothesLabels;
        Temperatures temperature;
        Integer tempInt = Integer.parseInt(globalWeatherUpdater.getTemperature());
        if (tempInt > 25) {
            temperature = Temperatures.more_then_twenty_five;
        } else if (tempInt > 20) {
            temperature = Temperatures.more_then_twenty;
        } else if (10 <= tempInt && tempInt <= 20) {
            temperature = Temperatures.from_ten_to_twenty;
        } else if (0 < tempInt && tempInt < 10) {
            temperature = Temperatures.from_zero_to_ten;
        } else {
            temperature = Temperatures.less_than_zero;
        }

        clothes = choosingClothes.getClothes(temperature);
        clothesLabels = choosingClothes.getClothesLabels(temperature);

        for (Clothes thing: clothes) {
            switch (thing) {
                case umbrella: umbrella.setVisibility(View.VISIBLE); break;
                case girl_more_20: girl_more_20_clothes.setVisibility(View.VISIBLE); break;
                case boy_more_20: boy_more_20_clothes.setVisibility(View.VISIBLE); break;
                case girl_10_20: girl_10_20_clothes.setVisibility(View.VISIBLE); break;
                case boy_10_20: boy_10_20_clothes.setVisibility(View.VISIBLE); break;
                case girl_0_10: girl_0_10_clothes.setVisibility(View.VISIBLE); break;
                case boy_0_10: boy_0_10_clothes.setVisibility(View.VISIBLE); break;
                case girl_less_0: girl_less_0_clothes.setVisibility(View.VISIBLE); break;
                case boy_less_0: boy_less_0_clothes.setVisibility(View.VISIBLE); break;
                case cap_boy: cap_boy.setVisibility(View.VISIBLE);
                case cap_girl: cap_girl.setVisibility(View.VISIBLE);
            }
        }
        firstThing.setText(clothesLabels[0]);
        secondThing.setText(clothesLabels[1]);
        thirdThing.setText(clothesLabels[2]);
    }

    void hideAllClothes() {
        girl_more_20_clothes.setVisibility(View.INVISIBLE);
        boy_more_20_clothes.setVisibility(View.INVISIBLE);
        girl_10_20_clothes.setVisibility(View.INVISIBLE);
        boy_10_20_clothes.setVisibility(View.INVISIBLE);
        girl_0_10_clothes.setVisibility(View.INVISIBLE);
        boy_0_10_clothes.setVisibility(View.INVISIBLE);
        girl_less_0_clothes.setVisibility(View.INVISIBLE);
        boy_less_0_clothes.setVisibility(View.INVISIBLE);
        umbrella.setVisibility(View.INVISIBLE);
        cap_boy.setVisibility(View.INVISIBLE);
        cap_girl.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("NewApi")
    void loadViews() {
        cityCoordinate = new CityCoordinate(this);
        temperature = findViewById(R.id.temperature);
        precip = findViewById(R.id.precip);
        windSpeed = findViewById(R.id.windSpeed);
        constraintLayout = findViewById(R.id.weatherView);
        girl = findViewById(R.id.girl);
        boy = findViewById(R.id.boy);

        girl_more_20_clothes = findViewById(R.id.girl_20_more);
        boy_more_20_clothes = findViewById(R.id.boy_20_more);
        girl_10_20_clothes = findViewById(R.id.girl_10_20);
        boy_10_20_clothes = findViewById(R.id.boy_10_20);
        girl_0_10_clothes = findViewById(R.id.girl_0_10);
        boy_0_10_clothes = findViewById(R.id.boy_0_10);
        girl_less_0_clothes = findViewById(R.id.girl_less_0);
        boy_less_0_clothes = findViewById(R.id.boy_less_0);
        umbrella = findViewById(R.id.umbrella);
        cap_boy = findViewById(R.id.cap_boy);
        cap_girl = findViewById(R.id.cap_girl);

        firstThing = findViewById(R.id.first);
        secondThing = findViewById(R.id.second);
        thirdThing = findViewById(R.id.third);

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

        ArrayList<String> list = cityCoordinate.getCities();
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

        if (MainActivity.isConnectingToInternet(this)) {
            updateWeather();
        } else {
            Toast toast = Toast.makeText(this, R.string.noInternet, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}
