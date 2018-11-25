package edu.adm.spbstu.whattowear;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isConnectingToInternet(this)) {
            Button onTheSiteButton = findViewById(R.id.onTheSite);
            onTheSiteButton.setBackgroundColor(Color.GRAY);
            onTheSiteButton.setEnabled(false);
        }

        ConstraintLayout constraintLayout = findViewById(R.id.view);
        constraintLayout.setBackgroundColor(Color.rgb(0,0, 0));
    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public void onTheSiteButtonClicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amd.spbstu.ru"));
        startActivity(intent);
    }

    public void startButtonClicked(View v) {
        Intent intent = new Intent(this, ClosetActivity.class);
        startActivity(intent);
    }
}
