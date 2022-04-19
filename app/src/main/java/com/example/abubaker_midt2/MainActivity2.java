package com.example.abubaker_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper myDb;
    Button bttnInsert;
    EditText etxtId, etxtName, etxtSurname, etxtNat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myDb = new DatabaseHelper(MainActivity2.this);
        etxtId = (EditText) findViewById(R.id.etxtId);
        etxtName = (EditText) findViewById(R.id.etxtName);
        etxtSurname = (EditText) findViewById(R.id.etxtSurname);
        etxtNat = (EditText) findViewById(R.id.etxtNat);
        bttnInsert = (Button) findViewById(R.id.bttnInsert);
        Button bttnAct1 = (Button) findViewById(R.id.bttnAct1);
        Button bttnAct3 = (Button)findViewById(R.id.bttnAct3);
        bttnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etxtId.getText().toString() != "" && etxtName.getText().toString() != "" && etxtSurname.getText().toString() != "" && etxtNat.getText().toString() != ""){
                    myDb.addData(etxtId.getText().toString(),etxtName.getText().toString(),etxtSurname.getText().toString(),etxtNat.getText().toString());
                    Toasty.success(getBaseContext(), "Insert Successful", Toast.LENGTH_LONG,
                            true).show();
                    Log.d("Faisal", "Insert successful");
                }
                else{
                    Toasty.error(getBaseContext(), "Insert Failed",
                            Toast.LENGTH_LONG, true).show();
                    Log.d("Faisal", "Insert failed");
                }
            }
        });
        bttnAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });
        bttnAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            }
        });
    }
}