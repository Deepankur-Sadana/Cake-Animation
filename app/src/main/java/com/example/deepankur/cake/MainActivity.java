package com.example.deepankur.cake;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    RelativeLayout relativeLayout;
    Button replayBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        replayBtn = (Button) findViewById(R.id.replatBtn);
        replayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cakeLayerList.clear();
                if (anim != null) {
                    anim.cancel();
                    anim.removeAllListeners();
                }
                replayAnimation();
            }
        });
    }

    ArrayList<View> cakeLayerList = new ArrayList<>();

    private void replayAnimation() {
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            relativeLayout.getChildAt(i).setTranslationY(0);
            cakeLayerList.add(relativeLayout.getChildAt(i));
            relativeLayout.getChildAt(i).setVisibility(View.INVISIBLE);
        }
        initAnimation();
    }

    ObjectAnimator anim;

    private void initAnimation() {
        anim = ObjectAnimator.ofFloat(cakeLayerList.get(0), "translationY", 0, 800f);
        anim.setDuration(500);
        anim.start();
        cakeLayerList.get(0).setVisibility(View.VISIBLE);
        anim.addListener(animationListener);
    }

    private Animator.AnimatorListener animationListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if (cakeLayerList.size() > 0) {
                cakeLayerList.remove(0);
            }
            if (cakeLayerList.size() > 0) {
                anim = ObjectAnimator.ofFloat(cakeLayerList.get(0), "translationY", 0, 800f);
                anim.start();
                cakeLayerList.get(0).setVisibility(View.VISIBLE);
                anim.removeAllListeners();
                anim.addListener(animationListener);
            } else Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };
}