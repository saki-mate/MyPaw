package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class activity_home<NOTIFICATION_ID> extends AppCompatActivity {
    Button btTvojiLjubimci;
    Button btPomoc;
    Button btPark;
    Button btVeterinar;
    Button btVreme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //notifikacije

        NotificationChannel();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 45);
        calendar.set(Calendar.SECOND, 00);

        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent intent = new Intent(activity_home.this, MemoBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

        btTvojiLjubimci = (Button) findViewById(R.id.btTvojiLjubimci);
        btTvojiLjubimci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_home.this, PrikazActivity.class));
            }
        });
        btPomoc = (Button) findViewById(R.id.btPomoc);
        btPomoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_home.this, HelpActivity.class));
            }
        });
        btPark = (Button) findViewById(R.id.btPark);
        btPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_home.this, SetnjaActivity.class));
            }
        });

        btVreme = (Button) findViewById(R.id.btVreme);
        btVreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_home.this, VremeActivity.class));
            }
        });
        btVeterinar = (Button) findViewById(R.id.btVeterinar);
        btVeterinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_home.this, MapaActivity.class));
            }
        });
    }

    private void NotificationChannel() {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        CharSequence name = "MOJA SAPA";
        String description = "MOJA SAPA KANAL";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("Notification", name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}

}