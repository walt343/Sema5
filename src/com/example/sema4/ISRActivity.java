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
                RtlsdrSource sdr = new RtlsdrSource("127.0.0.1", 1234);  //init sdr class
                
                if (sdr.open(this, this)) {
                    Toast.makeText(ACARSActivity.this, (String) "Connected to SDR!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ACARSActivity.this, (String) "Nope...sorry...", Toast.LENGTH_SHORT).show();
                }

                sdr.setFrequency(462610000);  //set to 462.61 MHz for ch 4 on FRS  -- TODO
                byte[] sample = sdr.getPacket(100);  //sample packet

                if (sdr.returnPacket(sample)) {
                    Toast.makeText(ACARSActivity.this, (String) "Sample is null!", Toast.LENGTH_SHORT).show();
                }
                else {
                    long time = System.currntTimeMillis();
                    String name = "/isr/SAMPLEPACKET" + time;
                    File newPacket = new File(Environment.getExternalStorageDirectory().getPath() + name);
                    try {
                        FileOutputStream f = new FileOutputStream(newPacket);
                        for (byte b:sample) {
                            f.write(b);
                        }
                        f.close();
                    }
                    catch (IOExcpetion e) {
                        Toast.makeText(ACARSActivity.this, (String) "EXCEPTION WHEN WRITING!!!", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
                    
                
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
