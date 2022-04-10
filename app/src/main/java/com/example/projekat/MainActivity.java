package com.example.projekat;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekat.database.AppDatabase;
import com.example.projekat.database.Pas;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView datumRodjenja;
    EditText rasaUnos;
    EditText imeUnos;
    EditText tezinaUnos;
    RadioButton polMuski;
    RadioButton polZenski;
    static TextView datumR;
    Button btDatum;
    Button btDodaj;
    Button btDodajSliku;
    Uri slika;
    String putanjaSlika;

    public static final int PICK_IMAGE = 1000;
        @Override
        protected void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            datumRodjenja = findViewById(R.id.datumRodjenja);
            rasaUnos = findViewById(R.id.rasaUnos);
            imeUnos = findViewById(R.id.imeUnos);
            tezinaUnos = findViewById(R.id.tezinaUnos);
            polMuski = (RadioButton) findViewById(R.id.polMuski);
            polZenski = (RadioButton) findViewById(R.id.polZenski);
            datumR = findViewById(R.id.datumR);
            btDatum = (Button) findViewById(R.id.btDatum);
            btDodaj = (Button) findViewById(R.id.btDodaj);

            btDatum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "datePicker");
                }
            });

            btDodaj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pol = "";
                    if (polMuski.isChecked()) {
                        pol = "Muski";
                    } else {
                        pol = "Zenski";
                    }
                    sacuvajNovogPsa(imeUnos.getText().toString(), rasaUnos.getText().toString(),
                            tezinaUnos.getText().toString(), pol, datumR.getText().toString(), putanjaSlika);
                }
            });
            //SLIKA
            btDodajSliku = (Button) findViewById(R.id.btDodajSliku);
            btDodajSliku.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, PICK_IMAGE);
                }
            });
        }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    slika = data.getData();
                    putanjaSlika = slika.toString();
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

        private void sacuvajNovogPsa (String ime, String rasa, String tezina, String pol, String datum, String putanjaSlika){
            AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
            Pas pas = new Pas();
            pas.ime = ime;
            pas.rasa = rasa;
            pas.tezina = tezina;
            pas.pol = pol;
            pas.datumRodjenja = datum;
            pas.slicica = putanjaSlika;
            db.pasDao().insertPas(pas);

            finish();
        }

        public static class DatePickerFragment extends DialogFragment
                implements DatePickerDialog.OnDateSetListener {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                // Use the current date as the default date in the picker
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // Create a new instance of DatePickerDialog and return it
                return new DatePickerDialog(getActivity(), this, year, month, day);
            }

            //poziva se kada korisnik izabere datum, ovde bi trebalo da ispišemo to
            //što je izabrao u onoj viewKomponenti za ispis ...
            public void onDateSet(DatePicker view, int year, int month, int day) {
                // Do something with the date chosen by the user
                datumR.setText(day + "/" + month + "/" + year);
            }
        }
        @Override
        protected void onRestart () {
            super.onRestart();  // Always call the superclass method first
        }
    }