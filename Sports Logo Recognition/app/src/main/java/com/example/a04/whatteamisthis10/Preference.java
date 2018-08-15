package com.example.a04.whatteamisthis10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class Preference extends AppCompatActivity {
    public static boolean league = false;
    public static boolean year = false;
    CheckBox leg;
    CheckBox yer;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        ok = (Button)this.findViewById(R.id.button3);
        leg = (CheckBox) findViewById(R.id.radioButton3);
        yer = (CheckBox) findViewById(R.id.radioButton4);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                league = leg.isChecked();
                year = yer.isChecked();
                Log.i("league check", String.valueOf(league));
                Intent mainscreen = new Intent(Preference.this, MainActivity.class);
                startActivity(mainscreen);
            }
        });

    }



}