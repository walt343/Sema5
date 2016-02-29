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
