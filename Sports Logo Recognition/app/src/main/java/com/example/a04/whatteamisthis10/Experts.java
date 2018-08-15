package com.example.a04.whatteamisthis10;

//import android.app.Activity;
//import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.SparseArray;


import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.util.ArrayList;

public class Experts {
    Bitmap picture;
    TextRecognizer TextCheck;
    int databaseSize;
    ArrayList<TeamLogo> Results;

    public Experts(Bitmap Logo, TextRecognizer textRec){
        this.picture=Logo;
        this.TextCheck=textRec;

        this.Results=TeamLogoList.makeList();
        this.databaseSize=this.Results.size();


        ColourExpert();
        TextExpert();

        Combine();
    }
    public void ShapeExpert(){
      //Team[]
        //ranking list
      //Team[] possibleAnswers=opencv.shape(this.picture);
        //Team A=new Team (1,"no",56.5, 22.2,25.7);
        //Team [] B;//=[];

        //return B;

        for(int i=0;i<databaseSize;i++){

            this.Results.get(i).setRatingShape(50);
        }

    }
    public void TextExpert(){
        //Context context=getApplicationContext();
        //TextRecognizer checkText=new TextRecognizer.Builder(context).build();
        Frame frame= new Frame.Builder().setBitmap(this.picture).build();
        SparseArray<TextBlock> text=this.TextCheck.detect(frame);

        ArrayList<String> TextFound=new ArrayList<>();
        System.out.println("text");

        String totalText="";
        for (int i = 0; i < text.size(); ++i) {
            TextBlock item = text.valueAt(i);
            if (item != null && item.getValue() != null) {
                //System.out.println(item.getValue());
                TextFound.add(item.getValue());
                totalText+=item.getValue();
                totalText+=" ";
                //detectedTextView.setText(item.getValue());
            }
        }

        System.out.println("hello");
        //for(int i=0;i<TextFound.size();i++){
        //System.out.println(TextFound.get(0));
        //System.out.println(TextFound);
        System.out.println("nopeeeee");

        if(!TextFound.isEmpty()){
            String[] textFound2 = TextFound.get(0).split("\n");
            if(textFound2.length>1){

                System.out.println("end");

                //System.out.println(textFound2[0]);
                System.out.println("slalajsjsjsjs");

                for(int i=0;i<this.databaseSize;i++) {
                    int score = 0;
                    for (int j = 0; j < textFound2.length; j++) {
                        String[] storage = this.Results.get(i).getText().split(" ");
                        for (int g = 0; g < storage.length; g++) {
                            //if(i==1){
                            //  System.out.println("one");
                            // System.out.println(textFound2[j]);
                            //System.out.println("two");
                            //System.out.println(storage[g]);
                            //}
                            if (textFound2[j].trim().toUpperCase().equals(storage[g].trim().toUpperCase())) {
                                System.out.println("this is bs");
                                score = 100;
                                System.out.println(score);
                            }
                        }

                    }
                    this.Results.get(i).setRatingText(score);
                }
            }
            else {
                //String[] textFound2 = TextFound.get(0).split("\n");
                System.out.println("end");

                //System.out.println(textFound2[0]);
                System.out.println("slalajsjsjsjs");

                for(int i=0;i<this.databaseSize;i++) {
                    int score = 0;
                    for (int j = 0; j < TextFound.size(); j++) {
                        String[] storage = this.Results.get(i).getText().split(" ");
                        for (int g = 0; g < storage.length; g++) {
                            //if(i==1){
                            //  System.out.println("one");
                            // System.out.println(textFound2[j]);
                            //System.out.println("two");
                            //System.out.println(storage[g]);
                            //}
                            if (TextFound.get(j).trim().toUpperCase().equals(storage[g].trim().toUpperCase())) {
                                System.out.println("this is bs");
                                score = 100;
                                System.out.println(score);
                            }
                        }

                    }
                    this.Results.get(i).setRatingText(score);
                }
            }
        }
        else{

        }

    }
    public void ColourExpert(){


        double countRed=0;
        double countGreen=0;
        double countBlue=0;
        double tempA,tempB,tempC;
        int width=this.picture.getWidth();
        int height=this.picture.getHeight();
        System.out.println("colour");
        int x=0;
        int counter=0;
        for(int i=0;i<width;i++ ){
            for(int j=0;j<height;j++){
                x=this.picture.getPixel(i,j);
                tempA=Color.red(x);
                tempB=Color.green(x);
                tempC=Color.blue(x);

                if (tempA>tempB & tempA>tempC){
                    countRed++;
                    counter++;
                }
                else if (tempB>tempA & tempB>tempC){
                    countGreen++;
                    counter++;
                }
                else if (tempC>tempA & tempC>tempB){
                    countBlue++;
                    counter++;
                }
                else{

                }
               //System.out.println(x);
            }
        }
        countRed=countRed/counter;
        countGreen=countGreen/counter;
        countBlue=countBlue/counter;

        Image.colRed=countRed;
        Image.colGreen=countGreen;
        Image.colBlue=countBlue;


        System.out.println(countRed);
        System.out.println(countGreen);
        System.out.println(countBlue);
        System.out.println("end");


        for(int i=0;i<databaseSize;i++){
            double Red=this.Results.get(i).getRed();
            double Green=this.Results.get(i).getGreen();
            double Blue=this.Results.get(i).getBlue();
            double score = (2 - Math.abs(countRed-Red) - Math.abs(countGreen-Green) - Math.abs(countBlue-Blue))*50;
            this.Results.get(i).setRatingColour(score);
        }



        //access database for comparison

        //return best results
       //Team[] possibleAnswers=opencv.shape(this.picture);
    }

    public void Combine(){
        System.out.println("Combine");
        System.out.println(this.databaseSize);
        double tempScore=0;

        System.out.println(this.Results.get(0).getTeamName());
        System.out.println(this.Results.get(0).getRatingText());
        System.out.println(this.Results.get(0).getRatingColour());


        for(int i=0;i<this.databaseSize;i++){

            tempScore=0;
            if(this.Results.get(i).getRatingText()==100){
                tempScore=99;
                tempScore+= (this.Results.get(i).getRatingColour()/100);
            }
            else{
                tempScore=0;
                tempScore+= (this.Results.get(i).getRatingColour());
            }
            this.Results.get(i).setScore(tempScore);

        }

        for (int ii = 0; ii < this.Results.size() - 1; ii++)
        {
            int index = ii;
            for (int jj = ii + 1; jj < this.Results.size(); jj++)
                if (this.Results.get(jj).getScore() > this.Results.get(index).getScore())
                    index = jj;

            TeamLogo smallerNumber = this.Results.get(index);
            this.Results.set(index,this.Results.get(ii));
            this.Results.set(ii,smallerNumber);
        }

        for(int k=0;k<this.Results.size();k++){
            //System.out.println(this.Results.get(k).getTeamName());
            //System.out.println(this.Results.get(k).getRatingText());
            //System.out.println(this.Results.get(k).getScore());
        }

        System.out.println("done testing");
        Image.Teams=this.Results;

    }

}


/*
class Team{
    int ID;
    String TeamName;
    double red;
    double green;
    double blue;
    double RatingText;
    double RatingShape;
    double RatingColour;
    String[] textFound;



    public Team(int id, String name,double R, double G, double B, String[] text, double ratText, double ratColour,double ratShape ){
        this.ID=id;
        this.TeamName=name;
        this.red=R;
        this.green=G;
        this.blue=B;
        this.textFound=text;
        this.RatingText=ratText;
        this.RatingColour=ratColour;
        this.RatingShape=ratShape;
    }

    public int getID(){
        return this.ID;
    }

    public String getTeamName(){
        return this.TeamName;
    }

    public double getRatingText(){
        return this.RatingText;
    }
    public double getRatingColour(){
        return this.RatingColour;
    }
    public double getRatingShape(){
        return this.RatingShape;
    }

    public void setRatingText(double textRating){
        this.RatingText=textRating;
    }
    public void setRatingColour(double colourRating){
        this.RatingColour=colourRating;
    }
    public void setRatingShape(double shapeRating){
        this.RatingShape=shapeRating;
    }

    public double getRed(){
        return this.red;
    }
    public double getGreen(){
        return this.green;
    }
    public double getBlue(){
        return this.blue;
    }

    public void setRed(double colour){
        this.red=colour;
    }
    public void setGreen(double colour){
        this.green=colour;
    }
    public void setBlue(double colour){
        this.blue=colour;
    }

    public String[] getText(){
        return this.textFound;
    }

    public void setText(String[] message){
        this.textFound=message;
    }


}

*/