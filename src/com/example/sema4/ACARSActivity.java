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
                File newPacket = new File(Environment.getExternalStorageDirectory().getPath() + "/acars/NCC170102Mar16_070314.acars");

                try {
                    FileOutputStream f = new FileOutputStream(newPacket);
                    AcarsMessage msg = new AcarsMessage("NCC1701", "74656", 0, 0, "", "ACK", 42, "THIS IS A TEST MESSAGE");
                    String buffer = msg.render();
                    f.write(buffer.getBytes());
                    f.close();
                }
                catch (IOException x) {
                    x.printStackTrace();
                }

                Toast.makeText(ACARSActivity.this, (String) "Packet Received and saved!" , Toast.LENGTH_SHORT).show();
                
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
