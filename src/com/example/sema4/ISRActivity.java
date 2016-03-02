package com.example.sema4;

import android.app.Activity;
import android.os.Bundle;

public class ISRActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isr);

        findViewById(R.id.isr_capture_button).setOnClickListener(this);
        findViewById(R.id.isr_read_button).setOnClickListener(this);
        findViewById(R.id.isr_save_button).setOnClickListener(this);
    }

    	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.acars_capture_button:
				Toast.makeText(ACARSActivity.this, (String) "TO BE IMPLEMENTED SOON!", Toast.LENGTH_SHORT).show();
                //TODO
                
				break;
			case R.id.acars_read_button:
				 Intent intent = new Intent(ISRActivity.this, ReadISR.class);
			        startActivity(intent);
				break;
			case R.id.acars_save_button:
				Toast.makeText(ACARSActivity.this, (String) "save", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
	}
}
