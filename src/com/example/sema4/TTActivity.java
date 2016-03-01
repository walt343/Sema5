package com.example.sema4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TTActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tt);

        findViewById(R.id.tt_capture_button).setOnClickListener(this);
        findViewById(R.id.tt_read_button).setOnClickListener(this);
        findViewById(R.id.tt_save_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tt_capture_button:
                //TODO
                break;
            case R.id.tt_read_button:
                Intent intent = new Intent(TTActivity.this, ReadTT.class);
			    startActivity(intent);
                break;
            case R.id.tt_save_button:
                //TODO
                break;
            default:
                break;
        }
    }
}
