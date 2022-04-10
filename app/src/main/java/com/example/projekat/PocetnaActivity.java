package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PocetnaActivity extends AppCompatActivity {
    Button btDalje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);

        DialogFragmentJezik dijalogFragmentJezik = new DialogFragmentJezik();
        dijalogFragmentJezik.show(getSupportFragmentManager(), "FragmentJezik");

        btDalje = (Button) findViewById(R.id.btDalje);
        btDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PocetnaActivity.this, activity_home.class));
            }
        });
    }
}