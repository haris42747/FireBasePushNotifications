package com.haris.android.firebasepushnotification.Activities;


import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.haris.android.firebasepushnotification.Adapters.ViewPagerAdapter;
import com.haris.android.firebasepushnotification.R;

public class MainActivity extends AppCompatActivity {

    private TextView mProfileLabel;
    private TextView mUserLabel;
    private TextView mNotificationLabel;

    private ViewPager mMainPager;

    private ViewPagerAdapter viewPagerAdapter;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            sendToLogin();
        }
    }

    private void sendToLogin() {

        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();

        mProfileLabel = findViewById(R.id.profileLabel);
        mUserLabel = findViewById(R.id.userLabel);
        mNotificationLabel = findViewById(R.id.notificationLabel);
        mMainPager = findViewById(R.id.mainPager);

        mMainPager.setOffscreenPageLimit(2);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mMainPager.setAdapter(viewPagerAdapter);

        mProfileLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPager.setCurrentItem(0);
            }
        });

        mUserLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPager.setCurrentItem(1);
            }
        });

        mNotificationLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPager.setCurrentItem(2);
            }
        });

        mMainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onPageSelected(int i) {
                changeTabs(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void changeTabs(int i) {

        if (i == 0) {
            mProfileLabel.setTextColor(getColor(R.color.textTabBright));
            mProfileLabel.setTextSize(22);

            mUserLabel.setTextColor(getColor(R.color.textTabLight));
            mUserLabel.setTextSize(16);

            mNotificationLabel.setTextColor(getColor(R.color.textTabLight));
            mNotificationLabel.setTextSize(16);
        }

        if (i == 1) {
            mProfileLabel.setTextColor(getColor(R.color.textTabLight));
            mProfileLabel.setTextSize(16);

            mUserLabel.setTextColor(getColor(R.color.textTabBright));
            mUserLabel.setTextSize(22);

            mNotificationLabel.setTextColor(getColor(R.color.textTabLight));
            mNotificationLabel.setTextSize(16);
        }

        if (i == 2) {
            mProfileLabel.setTextColor(getColor(R.color.textTabLight));
            mProfileLabel.setTextSize(16);

            mUserLabel.setTextColor(getColor(R.color.textTabLight));
            mUserLabel.setTextSize(16);

            mNotificationLabel.setTextColor(getColor(R.color.textTabBright));
            mNotificationLabel.setTextSize(22);
        }

    }
}
