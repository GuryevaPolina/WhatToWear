package edu.adm.spbstu.whattowear;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    public static final int	VIEW_INTRO		= 0;
    public static final int	VIEW_GAME       = 1;

    private  AppIntro				m_app;
    private ViewIntro               m_viewIntro;

    int  m_viewCur = -1;

    AppIntro getApp() {
        return m_app;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        if (!isConnectingToInternet(this)) {
//            Button onTheSiteButton = findViewById(R.id.onTheSite);
//            onTheSiteButton.setBackgroundColor(Color.GRAY);
//            onTheSiteButton.setEnabled(false);
//        }
//
//        ConstraintLayout constraintLayout = findViewById(R.id.view);
//        constraintLayout.setBackgroundColor(Color.rgb(0,0, 0));
//

//        super.onCreate(savedInstanceState);
//        // Detect language
//        String strLang = Locale.getDefault().getDisplayLanguage();
//        int language;
//        if (strLang.equalsIgnoreCase("english")) {
//            language = AppIntro.LANGUAGE_ENG;
//        } else if (strLang.equalsIgnoreCase("русский")) {
//            language = AppIntro.LANGUAGE_RUS;
//        } else {
//            language = AppIntro.LANGUAGE_UNKNOWN;
//
//        }
//
//        ConstraintLayout constraintLayout = findViewById(R.id.view);
//        constraintLayout.setBackgroundColor(Color.rgb(0,0, 0));


        super.onCreate(savedInstanceState);
        // Detect language
        String strLang = Locale.getDefault().getDisplayLanguage();
        int language;
        if (strLang.equalsIgnoreCase("english")) {
            language = AppIntro.LANGUAGE_ENG;
        } else if (strLang.equalsIgnoreCase("русский")) {
            language = AppIntro.LANGUAGE_RUS;
        } else {
            language = AppIntro.LANGUAGE_UNKNOWN;

        }
        // Create application
        m_app = new AppIntro(this, language);
        // Create view
        setView(VIEW_INTRO);
    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }


    public void setView(int viewID)
    {
        if (m_viewCur == viewID) {
            return;
        }

        m_viewCur = viewID;
        if (m_viewCur == VIEW_INTRO)
        {
            m_viewIntro = new ViewIntro(this);
            setContentView(m_viewIntro);
        }

    }

    public void onTheSiteButtonClicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amd.spbstu.ru"));
        startActivity(intent);
    }

    public void startButtonClicked(View v) {
        Intent intent = new Intent(this, ClosetActivity.class);
        startActivity(intent);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int touchType = AppIntro.TOUCH_DOWN;

        if (event.getAction() == MotionEvent.ACTION_MOVE)
            touchType = AppIntro.TOUCH_MOVE;
        if (event.getAction() == MotionEvent.ACTION_UP)
            touchType = AppIntro.TOUCH_UP;

        return m_viewCur != VIEW_INTRO || m_viewIntro.onTouch(x, y, touchType);
    }


    protected void onResume()
    {
        super.onResume();
        if (m_viewCur == VIEW_INTRO)
            m_viewIntro.start();

    }
}
