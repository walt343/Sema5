package com.example.m164818.mold;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        String directories = {"acars", "isr", "train"};  //check for folders
        for (String dir:directories) {
            File toCheck = new File(dir);
            if (!toCheck.exists()) {  //if dir doesn't exist, create it
                try {
                    toCheck.mkdir();  //create folder
                }
                catch(SecurityException se) {
                    System.err.println("CANNOT MAKE DIRECTORY " + dir);
                }
            }
        }
    }

    public void goToACARS(View view) {
        Intent intent = new Intent(MainMenu.this, ACARSActivity.class);
        startActivity(intent);
    }

    public void goToTT(View view) {
        Intent intent = new Intent(MainMenu.this, TTActivity.class);
        startActivity(intent);
    }

    public void goToISR(View view) {
        Intent intent = new Intent(MainMenu.this,ISRActivity.class);
        startActivity(intent);
    }
}
