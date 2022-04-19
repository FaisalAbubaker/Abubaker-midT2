package com.example.abubaker_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    TextView txtDate, txtTemp, txtHum;
    String weatherWebserviceURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toasty.normal(getBaseContext(), "Welcome to the project of Faisal Abubaker, 200453",Toasty.LENGTH_LONG).show();
        Button bttnDate = (Button) findViewById(R.id.bttnDate);
        Button bttnAct2 = (Button)findViewById(R.id.bttnAct2);
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtTemp = (TextView) findViewById(R.id.txtTemp);
        txtHum = (TextView) findViewById(R.id.txtHum);
        weatherWebserviceURL = "https://api.openweathermap.org/data/2.5/weather?q=london&appid=50ba39989d0554169754c92486a2e86f&units=metric";
        weather(weatherWebserviceURL);
        bttnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, d,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        bttnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
    }

    Calendar c = Calendar.getInstance();
    DateFormat fmtDate = DateFormat.getDateInstance();
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year,
                              int monthOfYear, int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            txtDate.setText("Your reservation is " +
                    fmtDate.format(c.getTime()));
        }
    };

    public void weather(String url) {
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Faisal", "Response OK");
                Log.d("Faisal", response.toString());
                try {
                    JSONObject jsonMain = response.getJSONObject("main");
                    Double temp = jsonMain.getDouble("temp");
                    txtTemp.setText(String.valueOf(temp));
                    Log.d("Faisal-temp", String.valueOf(temp));
                    Double humid = jsonMain.getDouble("humidity");
                    Log.d("Faisal-humidity", String.valueOf(humid));
                    txtHum.setText(String.valueOf(humid));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Faisal", "Response ERROR");
            }
        });
    }
}