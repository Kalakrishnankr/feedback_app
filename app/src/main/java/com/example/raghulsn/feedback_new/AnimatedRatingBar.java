package com.example.raghulsn.feedback_new;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.raghulsn.feedback_new.model.FeedbackApp;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.ScaleModifier;

/**
 * Created by raghul.sn on 12/10/16.
 */

public class AnimatedRatingBar extends LinearLayout implements View.OnClickListener {
    private AttributeSet attrset;
    private Context context;
    public ImageView star1, star2, star3, star4, star5;
    public OnRatingsChangedListner onRatingsChangedListner;
    public int ratingBarNo;
    int value = 0;
    public Animation pulse;
    StarAnimationListener mOnClickListener;

    public void setRatingsTo(int rating, View v) {
        switch (rating) {
            case 1:
                anim(v, 1);

                if (!FeedbackApp.isSettingsScreen) {
                    pulse = AnimationUtils.loadAnimation(context, R.anim.pulse);

                    star1.startAnimation(pulse);
                    star2.clearAnimation();
                    star3.clearAnimation();
                    star4.clearAnimation();
                    star5.clearAnimation();
                }
                star1.setImageResource(R.drawable.star2);
                star2.setImageResource(R.drawable.star1);
                star3.setImageResource(R.drawable.star1);
                star4.setImageResource(R.drawable.star1);
                star5.setImageResource(R.drawable.star1);
                value = 1;


                break;
            case 2:
                anim(v, 2);
                if (!FeedbackApp.isSettingsScreen) {
                    pulse = AnimationUtils.loadAnimation(context, R.anim.pulse);
                    star1.startAnimation(pulse);
                    star2.startAnimation(pulse);
                    star3.clearAnimation();
                    star4.clearAnimation();
                    star5.clearAnimation();
                }
                star1.setImageResource(R.drawable.star2);
                star2.setImageResource(R.drawable.star2);
                star3.setImageResource(R.drawable.star1);
                star4.setImageResource(R.drawable.star1);
                star5.setImageResource(R.drawable.star1);
                value = 2;

                break;
            case 3:
                anim(v, 3);
                if (!FeedbackApp.isSettingsScreen) {
                    pulse = AnimationUtils.loadAnimation(context, R.anim.pulse);
                    star1.startAnimation(pulse);
                    star2.startAnimation(pulse);
                    star3.startAnimation(pulse);
                    star4.clearAnimation();
                    star5.clearAnimation();
                }
                star1.setImageResource(R.drawable.star2);
                star2.setImageResource(R.drawable.star2);
                star3.setImageResource(R.drawable.star2);
                star4.setImageResource(R.drawable.star1);
                star5.setImageResource(R.drawable.star1);
                value = 3;

                break;
            case 4:
                anim(v, 4);
                if (!FeedbackApp.isSettingsScreen) {
                    pulse = AnimationUtils.loadAnimation(context, R.anim.pulse);
                    star1.startAnimation(pulse);
                    star2.startAnimation(pulse);
                    star3.startAnimation(pulse);
                    star4.startAnimation(pulse);
                    star5.clearAnimation();
                }
                star1.setImageResource(R.drawable.star2);
                star2.setImageResource(R.drawable.star2);
                star3.setImageResource(R.drawable.star2);
                star4.setImageResource(R.drawable.star2);
                star5.setImageResource(R.drawable.star1);
                value = 4;

                break;
            case 5:
                anim(v, 5);
                if (!FeedbackApp.isSettingsScreen) {
                    pulse = AnimationUtils.loadAnimation(context, R.anim.pulse);
                    star1.startAnimation(pulse);
                    star2.startAnimation(pulse);
                    star3.startAnimation(pulse);
                    star4.startAnimation(pulse);
                    star5.startAnimation(pulse);
                }
                star1.setImageResource(R.drawable.star2);
                star2.setImageResource(R.drawable.star2);
                star3.setImageResource(R.drawable.star2);
                star4.setImageResource(R.drawable.star2);
                star5.setImageResource(R.drawable.star2);
                value = 5;

                break;

            case 0:
                star1.setImageResource(R.drawable.star1);
                star2.setImageResource(R.drawable.star1);
                star3.setImageResource(R.drawable.star1);
                star4.setImageResource(R.drawable.star1);
                star5.setImageResource(R.drawable.star1);
                break;
        }
    }


    public void setOnStarClickListener(StarAnimationListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }


    public interface OnRatingsChangedListner {
        public void ratingChanged(int ratingBarNo, int rating);
    }

    public AnimatedRatingBar(Context context) {
        super(context);
        this.context = context;
        init();
    }


    public AnimatedRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrset = attrs;
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AnimatedRatingBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        this.attrset = attrs;
        init();
    }

    public AnimatedRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrset = attrs;
        init();
    }

    private void init() {

        if (attrset != null && context != null) {

            TypedArray a = context.obtainStyledAttributes(attrset,
                    R.styleable.AnimatedRatingBar, 0, 0);
            Integer titleText = a.getResourceId(R.styleable.AnimatedRatingBar_layoutToUse, R.layout.animated_ratinglayout);

            a.recycle();
            LayoutInflater.from(getContext()).inflate(
                    titleText, this);

            star1 = (ImageView) findViewById(R.id.imgV1);
            star2 = (ImageView) findViewById(R.id.imgV2);
            star3 = (ImageView) findViewById(R.id.imgV3);
            star4 = (ImageView) findViewById(R.id.imgV4);
            star5 = (ImageView) findViewById(R.id.imgV5);

            star1.setTag(1);
            star2.setTag(2);
            star3.setTag(3);
            star4.setTag(4);
            star5.setTag(5);

            star1.setOnClickListener(this);
            star2.setOnClickListener(this);
            star3.setOnClickListener(this);
            star4.setOnClickListener(this);
            star5.setOnClickListener(this);


        }


    }


    @Override
    public void onClick(View v) {
        //FeedbackApp.isSettingsScreen=false;

        if (!FeedbackApp.isSettingsScreen) {
            int rating = (int) v.getTag();


            setRatingsTo(rating, v);


            if (onRatingsChangedListner != null) {
                onRatingsChangedListner.ratingChanged(ratingBarNo, value);
            }
            if (mOnClickListener != null) {
                mOnClickListener.onClick();
            }
        }

    }

    void anim(View view, int n) {

        if (view == null) return;
/*
        ParticleSystem ps = new ParticleSystem(this, 100, R.drawable.anim_wheel, 800);
        ps.setScaleRange(0.7f, 1.3f);
        ps.setSpeedRange(0.1f, 0.25f);
        ps.setAcceleration(0.0001f, 90);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new AccelerateInterpolator());
        ps.oneShot(view, 100);*/


        //star
        new ParticleSystem((Activity) context, n, R.drawable.star, 1000)
                .setSpeedByComponentsRange(-0.1f, 0.1f, -0.1f, 0.02f)
                .setAcceleration(0.000003f, 90)
                .setInitialRotationRange(0, 360)
                .setRotationSpeed(120)
                .setFadeOut(1000)
                .addModifier(new ScaleModifier(0f, 1.5f, 0, 1500))
                .oneShot(view, 10);
    }
}