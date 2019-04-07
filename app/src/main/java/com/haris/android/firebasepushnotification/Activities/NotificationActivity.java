package com.haris.android.firebasepushnotification.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.haris.android.firebasepushnotification.R;

public class NotificationActivity extends AppCompatActivity {

    private TextView mNotifData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        String dataMessage = getIntent().getStringExtra("message");
        String dataFrom = getIntent().getStringExtra("from_user_id");

        mNotifData = findViewById(R.id.notif_text);
        mNotifData.setText(" FROM : " + dataFrom + " | MESSAGE : " + dataMessage);
    }
}
