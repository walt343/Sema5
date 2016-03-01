package com.example.sema4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ACARSActivity extends Activity implements View.OnClickListener{

	/*static {
		System.loadLibrary("Sema4");
	}*/
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acars);
        
        findViewById(R.id.acars_capture_button).setOnClickListener(this);
        findViewById(R.id.acars_read_button).setOnClickListener(this);
        findViewById(R.id.acars_save_button).setOnClickListener(this);

    }
        
    public native String getText();

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.acars_capture_button:
				Toast.makeText(ACARSActivity.this, getText(), Toast.LENGTH_SHORT).show();
				break;
			case R.id.acars_read_button:
				 Intent intent = new Intent(ACARSActivity.this, ReadACARS.class);
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
