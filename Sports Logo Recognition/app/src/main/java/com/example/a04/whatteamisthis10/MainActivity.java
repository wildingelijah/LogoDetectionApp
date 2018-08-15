package com.example.a04.whatteamisthis10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button btnCamera;
    Button pref;
    InputStream data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCamera = (Button) findViewById(R.id.button);
        pref = (Button) findViewById(R.id.button2);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picIntent = new Intent(MainActivity.this, Image.class);
                startActivity(picIntent);

            }
        });
        pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prefScreen = new Intent(MainActivity.this, Preference.class);
                startActivity(prefScreen);

            }
        });
        try {
            data=getAssets().open("database.csv");
            TeamLogoList.dataFile=data;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
