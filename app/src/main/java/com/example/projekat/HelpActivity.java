package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class HelpActivity extends AppCompatActivity {
    ImageView ibEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ibEmail = (ImageView) findViewById(R.id.ibEmail);
        ibEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posaljiEmail();
            }
        });
    }
    protected void posaljiEmail() {
        Log.i("Send email", "");
        String[] TO = {"mojaSapaNoviSad@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(HelpActivity.this, "Ne postoji email klijent instaliran.", Toast.LENGTH_SHORT).show();
        }
    }
}