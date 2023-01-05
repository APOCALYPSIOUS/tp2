package com.example.tp2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE1 = 1;
    private static final int REQUEST_CODE2 = 2;
    private static final int REQUEST_CODE3 = 3;
    private String phone;
    private String challenge1result;
    private String Challenge2result;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button browser = (Button) findViewById(R.id.browse);
        Button call = (Button) findViewById(R.id.call);
        Button laptop = (Button) findViewById(R.id.laptop);
        EditText editTextPhoneNumber = findViewById(R.id.phonetext);
        EditText editTextbrowser = findViewById(R.id.browsetext);
        EditText challenge1input = findViewById(R.id.challenge1input);
        EditText challenge2input = findViewById(R.id.challenge2input);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = editTextPhoneNumber.getText().toString();
                phone=phoneNumber;

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);


                startActivityForResult(intent, REQUEST_CODE1);

            }
        });
        browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editTextbrowser.getText().toString();
                link=url;
                String challenge1 = challenge1input.getText().toString();
                String challenge2 = challenge2input.getText().toString();
                challenge1result=challenge1;
                Challenge2result=challenge2;
                Intent challenges = new Intent(MainActivity.this,Check.class);

                challenges.putExtra("challenge1", challenge1);
                challenges.putExtra("challenge2",challenge2);
                startActivityForResult(challenges, REQUEST_CODE2);





//                Log.i("LOG", url);
//                if (url.isEmpty()) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.emi.ac.ma/"));
//                    startActivity(intent);
//                } else if (!url.startsWith("http://") && !url.startsWith("https://")) {
//                    url = "http://" + url;
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    startActivity(intent);
//                }


            }
        });

        laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);


                startActivityForResult(intent, REQUEST_CODE3);


            }
        });


    }

    public void callNumber( Intent data){
        String username = data.getStringExtra("username");
        String password = data.getStringExtra("password");

        if (username.equals("TPandINFO") && password.equals("secret")) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, proceed as usual
            } else {
                // Permission has been revoked, show a message to the user
                Toast.makeText(MainActivity.this, "Call phone permission has been revoked", Toast.LENGTH_SHORT).show();
            }

            Uri uri = Uri.parse("tel:" + phone);

            Intent intent = new Intent(Intent.ACTION_CALL, uri);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "loging  or password invalid", Toast.LENGTH_SHORT).show();
        }
    }
    public void persoAcess(Intent data){
        String username = data.getStringExtra("username");
        String password = data.getStringExtra("password");

        if (username.equals("admin") && password.equals("admin")) {
            startActivity(new Intent(MainActivity.this, persoActivity.class));
        }
        else {
            Toast.makeText(MainActivity.this, "loging  or password invalid", Toast.LENGTH_SHORT).show();
        }
    }



    public void check(Intent data){

        String somme = data.getExtras().getString("somme");

        if(Integer.parseInt(somme)==Integer.parseInt(challenge1result)+Integer.parseInt(Challenge2result)){

            if (link.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.emi.ac.ma/"));
                    startActivity(intent);
                } else if (!link.startsWith("http://") && !link.startsWith("https://")) {
                    link = "http://" + link;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    startActivity(intent);
                }
        }
        else{
            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE1) {
            if (resultCode == RESULT_OK) {
                Log.i("sum","hey");

                callNumber(data);
                check(data);



            }
        }
        if (requestCode == REQUEST_CODE2) {
            if (resultCode == RESULT_OK) {
                Log.i("sum","hey");

                check(data);



            }
        }
        if (requestCode == REQUEST_CODE3) {
            if (resultCode == RESULT_OK) {
                Log.i("sum","hey");

                persoAcess(data);



            }
        }



    }
}