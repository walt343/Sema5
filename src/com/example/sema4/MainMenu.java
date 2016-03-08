package com.example.sema4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        String[] directories = {"acars", "isr", "train"};  //check for folders
        for (String dir:directories) {
            File toCheck = new File(Environment.getExternalStorageDirectory().getPath() +"/"+ dir);
            if (!toCheck.exists()) {  //if dir doesn't exist, create it
                    toCheck.mkdirs();  //create folder                    
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
