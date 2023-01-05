package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static int  access =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editTextlogning = findViewById(R.id.editTextTextEmailAddress);
        EditText editTextpassword = findViewById(R.id.editTextTextPassword);
        Button ok = (Button) findViewById(R.id.ok);
        Button cancel = (Button) findViewById(R.id.cancel);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextlogning.getText().toString();
                String password = editTextpassword.getText().toString();

                Intent data = new Intent();

                data.putExtra("username", username);
                data.putExtra("password",password);


                setResult(RESULT_OK, data);
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                intent.putExtra("username", username);
//                intent.putExtra("password",password);
//                access=1;
//                startActivity(intent);
                finish();



            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                Toast.makeText(LoginActivity.this, "operation annulé", Toast.LENGTH_SHORT).show();

            }
        });

    }
}