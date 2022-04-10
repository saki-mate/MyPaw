package com.example.projekat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.projekat.database.AppDatabase;
import com.example.projekat.database.Pas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class PrikazActivity extends AppCompatActivity {
        private PasListAdapter pasListAdapter;
        FloatingActionButton btDodajNovogPsa1;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_prikaz);

            btDodajNovogPsa1 =(FloatingActionButton) findViewById(R.id.btDodajNovogPsa);
            btDodajNovogPsa1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(PrikazActivity.this, MainActivity.class), 100);
                }
            });
            initRecyclerView();

            loadPasList();
        }

    private void initRecyclerView() {
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(dividerItemDecoration);

            pasListAdapter = new PasListAdapter(this);
            recyclerView.setAdapter(pasListAdapter);
        }

        private void loadPasList() {
            AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
            List<Pas> pasList =db.pasDao().getAllPas();
            pasListAdapter.setPasList(pasList);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            if(requestCode == 100) {
                loadPasList();
            }

            super.onActivityResult(requestCode, resultCode, data);
        }

        private void obrisiSvePse(){
           AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
           db.pasDao().deleteAll();
           finish();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.MObrisiSve:
                obrisiSvePse();
                pasListAdapter.clear();
                return true;
            default:
                return super.onOptionsItemSelected(item);
            }
        }
    }