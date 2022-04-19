package com.example.abubaker_midt2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity3 extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText etxtDelID;
    Button bttnFetch, bttnDel, bttnAct22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        etxtDelID = (EditText) findViewById(R.id.etxtDelID);
        bttnFetch = (Button) findViewById(R.id.bttnFetch);
        bttnDel = (Button) findViewById(R.id.bttnDel);
        bttnAct22 = (Button)findViewById(R.id.bttnAct22);
        bttnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = myDb.ViewUsers();
                StringBuffer buffer = new StringBuffer();
                while(cur.moveToNext()) {
                    buffer.append("ID: " + cur.getString(0)+ "\n");
                    buffer.append("Name: " + cur.getString(1)+ "\n");
                    buffer.append("Surname: " + cur.getString(2)+ "\n");
                    buffer.append("National ID: " + cur.getString(3)+ "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setCancelable(true);
                builder.setTitle("All Users");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        bttnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb.DeleteUser(etxtDelID.getText().toString());
                Toasty.success(getBaseContext(), "Delete Successful", Toast.LENGTH_LONG,
                        true).show();
                Log.d("Faisal", "Delete Successful");
            }
        });
        bttnAct22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, MainActivity2.class));
            }
        });
    }
}