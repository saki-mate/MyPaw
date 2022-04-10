package com.example.projekat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projekat.database.AppDatabase;
import com.example.projekat.database.Pas;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

public class PasListAdapter extends RecyclerView.Adapter<PasListAdapter.MyViewHolder> {

        private Context context;
        public List<Pas> pasList;

        public PasListAdapter(Context context) {
            this.context = context;
        }

        public void setPasList(List<Pas> pasList) {
            this.pasList = pasList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public PasListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PasListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.tvIme.setText("Ime: "+ this.pasList.get(position).ime);
            holder.tvRasa.setText("Rasa: " + this.pasList.get(position).rasa);
            holder.tvTezina.setText("Tezina: " + this.pasList.get(position).tezina + "kg");
            holder.tvTezina.setText("Datum rodjenja: " + this.pasList.get(position).datumRodjenja);
            holder.tvPol.setText("Pol: " + this.pasList.get(position).pol);

            if(this.pasList.get(position).slicica != null){
                Uri uri = Uri.parse(this.pasList.get(position).slicica);
                holder.ivSlika.setImageURI(uri);
            }
            holder.btObrisi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pas p = pasList.get(position);
                    AppDatabase db = AppDatabase.getDbInstance(context);
                    pasList.remove(p);
                    db.pasDao().delete(p);
                    //da bi se odmah update-ovala lista
                    notifyItemRemoved(position);
                }
            });
    }

    @Override
        public int getItemCount() {
            return  this.pasList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tvIme;
            TextView tvRasa;
            TextView tvTezina;
            TextView tvPol;
            TextView tvDatum;
            Button btObrisi;
            ImageView ivSlika;

            public MyViewHolder(View view) {
                super(view);
                tvIme = view.findViewById(R.id.tvIme);
                tvRasa = view.findViewById(R.id.tvRasa);
                tvTezina = view.findViewById(R.id.tvTezina);
                tvPol = view.findViewById(R.id.tvPol);
                tvDatum = view.findViewById(R.id.tvDatum);
                btObrisi = view.findViewById(R.id.btObrisi);
                ivSlika = view.findViewById(R.id.ivSlika);
            }
        }

    public void clear() {
        pasList.clear();
        notifyDataSetChanged();
    }
}