package com.example.projekat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Locale;

public class DialogFragmentJezik extends DialogFragment {
    Button btSrpski;
    Button btEngleski;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_jezik, container, false);

        btSrpski = (Button) v.findViewById(R.id.btSrpski);
        btSrpski.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("sr");
                onDestroyView();
            }
        });
        btEngleski = (Button) v.findViewById(R.id.btEngleski);
        btEngleski.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");
                onDestroyView();
            }
        });
        return v;
    }
    private void setLocale(String language){
        Resources resources = getContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = new Configuration();
        configuration.setLocale(new Locale(language));
        getResources().updateConfiguration(configuration, metrics);
        onConfigurationChanged(configuration);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
