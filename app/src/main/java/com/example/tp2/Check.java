package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Check extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        Button cancel = (Button) findViewById(R.id.cancel2);
        Button ok = (Button) findViewById(R.id.ok2);
        String challenge1 = getIntent().getStringExtra("challenge1");
        String challenge2 = getIntent().getStringExtra("challenge2");
        TextView challenge1recu = (TextView) findViewById(R.id.challenge1);
        TextView challenge2recu = (TextView) findViewById(R.id.challenge2);
        challenge1recu.setText(challenge1);
        challenge2recu.setText(challenge2);






        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Check.this, MainActivity.class));
                Toast.makeText(Check.this, "operation annulé", Toast.LENGTH_SHORT).show();

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                Bundle bundle = new Bundle();
                EditText somme2 = findViewById(R.id.somme);
                String sum= somme2.getText().toString();
                bundle.putString("somme", sum);
                data.putExtras(bundle);
                setResult(RESULT_OK, data);
                Log.i("sum",sum);
                Toast.makeText(Check.this, "valid", Toast.LENGTH_SHORT).show();
                finish();
            }
        });



    }
}