package edu.adm.spbstu.whattowear;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class Animator {

    private Context context;
    private ImageView[][] precips = null;
    private ConstraintLayout constraintLayout;
    private int n = 10, m = 30;

    private int screenWidth;
    private int screenHeight;


    Animator(Context context, ConstraintLayout constraintLayout) {
        this.context = context;
        this.constraintLayout = constraintLayout;
        screenWidth = ((Activity)context).getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = ((Activity)context).getWindowManager().getDefaultDisplay().getHeight();
    }

    void rainAnimation() {

        precips = new ImageView[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                precips[i][j] = new ImageView(context);
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

    void snowAnimation() {
        precips = new ImageView[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                precips[i][j] = new ImageView(context);
                precips[i][j].setImageResource(R.drawable.snowflake);
                precips[i][j].setLayoutParams(new ConstraintLayout.LayoutParams(40, 40));
                precips[i][j].setX((float) (Math.random() * (screenWidth + 1)));
                precips[i][j].setY(-50);

                constraintLayout.addView(precips[i][j], 0);

                Animation animation = new TranslateAnimation(precips[i][j].getX(),
                        precips[i][j].getX() + 10,
                        precips[i][j].getY(),
                        precips[i][j].getY() + 2 * screenHeight / 3);
                animation.setDuration(10000);
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
