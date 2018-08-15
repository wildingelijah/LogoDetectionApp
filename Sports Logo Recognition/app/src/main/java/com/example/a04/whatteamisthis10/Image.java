package com.example.a04.whatteamisthis10;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.text.TextRecognizer;

import java.util.ArrayList;

public class Image extends AppCompatActivity {
    public static double colRed=0;
    public static double colGreen=0;
    public static double colBlue=0;
    public static String text="";

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    Button whichTeam;
    public static ArrayList<TeamLogo> Teams;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        this.imageView = (ImageView) this.findViewById(R.id.imageView);
        Button photoButton = (Button) this.findViewById(R.id.button3);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                whichTeam.setVisibility(View.VISIBLE);

            }
        });
        whichTeam = (Button) this.findViewById(R.id.button6);
        whichTeam.setVisibility(View.INVISIBLE);
        whichTeam.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Image.this);
                builder.setTitle("TheTeamIs");

                String show = "Team : " + Image.Teams.get(0).getTeamName() + "\n";


                if (Preference.league){
                    show = show + ("League: " + Image.Teams.get(0).getLeague() + "\n");
                }
                if (Preference.year){
                    show = show + ("Year Founded: " + Image.Teams.get(0).getYearFounded() + "\n");
                }
                if (!Preference.league & !Preference.year) {
                    show = show + ("League: " + Image.Teams.get(0).getLeague() + "\n" + "Year Founded: " + Image.Teams.get(0).getYearFounded());
                }
                builder.setMessage(show);
                builder.setPositiveButton("Home", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent mainscreen = new Intent(Image.this, MainActivity.class);
                        startActivity(mainscreen);
                    }
                });


                builder.create().show();
            }

        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            System.out.println("help");
            TextRecognizer checkText=new TextRecognizer.Builder(this).build();
            Experts Calculation=new Experts(photo,checkText);
            System.out.println("yeah");
            //TextView redNow = (TextView) this.findViewById(R.id.textRed);
            //redNow.setText(Double.toString(colRed));
            //TextView greenNow = (TextView) this.findViewById(R.id.textGreen);
            //greenNow.setText(Double.toString(colGreen));
            //TextView blueNow = (TextView) this.findViewById(R.id.textBlue);
            //blueNow.setText(Double.toString(colBlue));
            //TextView textinpic = (TextView) this.findViewById(R.id.foundTextValues);
            //textinpic.setText(text);

        }
    }

}

