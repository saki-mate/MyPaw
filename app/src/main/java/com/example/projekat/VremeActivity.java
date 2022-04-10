package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VremeActivity extends AppCompatActivity {

    TextView tvTemperatura;
    TextView tvGrad;
    TextView tvDatumVreme;
    TextView tvNajNaj;
    TextView tvRFdeg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vreme);

        tvGrad = (TextView) findViewById(R.id.tvGrad);
        tvTemperatura = (TextView) findViewById(R.id.tvTemperatura);
        tvDatumVreme = (TextView) findViewById(R.id.tvDatumVreme);
        tvNajNaj = (TextView) findViewById(R.id.tvNajNaj);
        tvRFdeg = (TextView) findViewById(R.id.tvRFdeg);

        getVremeInfo("Novi Sad");
    }

    public void getVremeInfo(String grad){
        String url="https://api.weatherapi.com/v1/forecast.json?key=a0ca8349ea5a409badf162947220104&q=Novi Sad&days=1&aqi=no&alerts=no";
        RequestQueue requestQueue = Volley.newRequestQueue(VremeActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String temperatura = response.getJSONObject("current").getString("temp_c");
                    String datumVreme = response.getJSONObject("location").getString("localtime");
                    JSONObject day = response.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getJSONObject("day");
                    String najvisa = day.getString("maxtemp_c");
                    String najniza = day.getString("mintemp_c");
                    String subOsecaj = response.getJSONObject("current").getString("feelslike_c");

                    tvTemperatura.setText(temperatura + "°C");
                    tvDatumVreme.setText(datumVreme);
                    tvNajNaj.setText(najvisa + "° / " + najniza + " °");
                    tvRFdeg.setText(subOsecaj + "°");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Volej eror je:" + error.toString());
                Toast.makeText(VremeActivity.this, "Molim vas unesite validno ime grada", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}