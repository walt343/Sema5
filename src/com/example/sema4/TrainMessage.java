package com.example.sema4;

import java.util.Date;
import java.text.*;
import java.io.*;
public class TrainMessage extends Message {

    private double brakeLinePress;  //brake line pressure of train
    private String unitID;
    private int batteryStatus;
    private boolean inMotion;

    public TrainMessage() {  //default constructor
        brakeLinePress = 0.0;  //default to 0
        unitID = "Unknown";
        batteryStatus = 100;
        inMotion = false;
    }

    public TrainMessage(double brakeLinePress, String unitID, int batteryStatus, boolean inMotion) {  //non-default constructor
        this.brakeLinePress = brakeLinePress;
        this.unitID = unitID;
        this.batteryStatus = batteryStatus;
        this.inMotion = inMotion;
    }

    public String render() {
        String ret =  "Brake Line Pressure: " + brakeLinePress + "\n";  //brake line pressure line
        ret = ret + "Unit ID: " + unitID + "\n";  //unit ID line
        ret = ret + "Battery: " + batteryStatus + "\n";  //battery line
        
        if(inMotion) {  //determine if in motion
            ret = ret + "In Motion: True\n";
        }
        else {
            ret = ret + "In Motion: False\n";
        }

        return ret;
    }

    public void convertToFile()
    {
        long captureTime = new Date().getTime();
        DateFormat date = new SimpleDateFormat("ddMMMyyyy_hhmmss");
        String fileName = unitID + date.format(captureTime) + ".telem";
        fileName = "train/" + fileName;
        try
        {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(this.render());
            writer.close();
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        
    }
}
