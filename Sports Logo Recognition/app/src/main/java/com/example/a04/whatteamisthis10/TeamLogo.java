package com.example.a04.whatteamisthis10;

public class TeamLogo {
    String teamName, yearFounded, league, text;
    int ID;
    double red, blue, green;
    double RatingText;
    double RatingShape;
    double RatingColour;
    double TotalScore;
    public TeamLogo(int id, String tn, String yf, String l, String t, double r, double g, double b){
        this.ID = id;
        this.teamName = tn;
        this.yearFounded = yf;
        this.league = l;
        this.text = t;
        this.red = r;
        this.green = g;
        this.blue = b;
        this.RatingText=0;
        this.RatingShape=0;
        this.RatingColour=0;
        this.TotalScore=0;
    }
    public String getTeamName(){
        return this.teamName;
    }
    public String getYearFounded(){
        return this.yearFounded;
    }
    public String getLeague(){
        return this.league;
    }
    public String getText(){
        return this.text;}
    public double getRed(){
        return this.red;
    }
    public double getBlue(){
        return this.blue;
    }
    public double getGreen(){
        return this.green;
    }
    public int getID(){
        return this.ID;
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

    public void setScore(double score){
        this.TotalScore=score;
    }
    public double getScore(){
        return this.TotalScore;
    }


}

