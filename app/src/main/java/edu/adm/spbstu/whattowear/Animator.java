package edu.adm.spbstu.whattowear;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class Animator {

    private Activity activity;
    private ImageView[][] precips = null;
    private ImageView[] clouds = null;
    private ConstraintLayout constraintLayout;
    private int n = 10, m = 30;

    private int screenWidth;
    private int screenHeight;


    Animator(Activity activity, ConstraintLayout constraintLayout) {
        this.activity = activity;
        this.constraintLayout = constraintLayout;
        screenWidth = activity.getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = activity.getWindowManager().getDefaultDisplay().getHeight();
    }

    void startCloudsAnimation(int speed) {

        if (speed == 0) {
            speed = 1;
        }

        clouds = new ImageView[2];
        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new ImageView(activity);
            clouds[i].setImageResource(R.drawable.clouds);
            clouds[i].setY(0);
            System.out.println(screenWidth);
            clouds[i].setX(i * screenWidth / 2);

            constraintLayout.addView(clouds[i], i);

            Animation animation = new TranslateAnimation(clouds[i].getX(), clouds[i].getX() - screenWidth,
                    clouds[i].getY(), clouds[i].getY());
            animation.setDuration(60000 / speed);
            animation.setRepeatCount(Animation.INFINITE);
            clouds[i].startAnimation(animation);
        }
    }

    void stopCloudsAnimation() {
        if (clouds != null) {
            for (ImageView cloud : clouds) {
                cloud.clearAnimation();
                cloud.setVisibility(View.INVISIBLE);
            }
        }
    }

    void rainAnimation() {

        precips = new ImageView[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                precips[i][j] = new ImageView(activity);
                precips[i][j].setImageResource(R.drawable.drop);
                precips[i][j].setLayoutParams(new ConstraintLayout.LayoutParams(10, 40));
                precips[i][j].setX(j * screenWidth / m + i * 10);
                precips[i][j].setY(-50);

                constraintLayout.addView(precips[i][j], 0);

                Animation animation = new TranslateAnimation(precips[i][j].getX(),
                        precips[i][j].getX() + 10,
                        precips[i][j].getY(),
                        precips[i][j].getY() + 2 * screenHeight / 3);
                animation.setDuration(800);
                animation.setRepeatCount(Animation.INFINITE);
                animation.setStartOffset(i*500 + j*100);

                precips[i][j].startAnimation(animation);
            }
        }

    }

    void snowAnimation(int speed) {

        if (speed == 0){
            speed = 1;
        }

        precips = new ImageView[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                precips[i][j] = new ImageView(activity);
                precips[i][j].setImageResource(R.drawable.snowflake);
                precips[i][j].setLayoutParams(new ConstraintLayout.LayoutParams(40, 40));
                precips[i][j].setX((float) (Math.random() * (screenWidth + 1)));
                precips[i][j].setY(-50);

                constraintLayout.addView(precips[i][j], 0);

                Animation animation = new TranslateAnimation(precips[i][j].getX(),
                        precips[i][j].getX() + 10,
                        precips[i][j].getY(),
                        precips[i][j].getY() + 2 * screenHeight / 3);
                animation.setDuration(10000 / speed);
                animation.setRepeatCount(Animation.INFINITE);
                animation.setStartOffset(i*500 + j*100);

                precips[i][j].startAnimation(animation);
            }
        }
    }

    void stopAnimation() {
        if (precips != null) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    precips[i][j].clearAnimation();
                }
            }
        }
    }
}
