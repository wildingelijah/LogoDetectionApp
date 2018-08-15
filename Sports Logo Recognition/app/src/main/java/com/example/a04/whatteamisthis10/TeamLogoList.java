package com.example.a04.whatteamisthis10;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class TeamLogoList {
    static InputStream dataFile;
    static HashMap<TeamLogo, Integer> mapIDToTeam;
    static HashMap<Integer, TeamLogo> mapTeamToID;
    static ArrayList<TeamLogo> teamList;

    public int getID(TeamLogo tl) {
        return mapIDToTeam.get(tl);
    }
    public TeamLogo getTeamLogo(int i){
        return mapTeamToID.get(i);
    }

    public static ArrayList<TeamLogo> makeList() {
        mapIDToTeam = new HashMap<>();
        mapTeamToID = new HashMap<>();
        teamList = new ArrayList<>();
        TeamLogo temp;
        int count = 0;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {

            br = new BufferedReader(new InputStreamReader(dataFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                temp = new TeamLogo(count, data[0], data[1], data[2], data[3], Double.parseDouble(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]));
                teamList.add(temp);
                //mapIDToTeam.put(temp, count);
                //mapTeamToID.put(count, temp);
                count++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teamList;

    }
    public static void main(String[] args){
        makeList();
    }
}
