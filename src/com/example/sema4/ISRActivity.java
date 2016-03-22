package com.example.sema4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ISRActivity extends Activity implements View.OnClickListener{

	public static final String SERVERIP = "127.0.0.1";
    public static final int SERVERPORT = 1234;
    public int FREQ = 462610000;  //462.61 MHz = FRS Channel 4
    private IQConverter iqconvert = new Unsigned8BitIQConverter();
    
    //Intent readAmp = new Intent(Intent.ACTION_VIEW, Uri.parse("iqsrc://-a " + SERVERIP + " -p " + String.valueOf(SERVERPORT) + " -f " + String.valueOf(FREQ) + " -s 1200000"));
    
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isr);

        findViewById(R.id.isr_capture_button).setOnClickListener(this);
        findViewById(R.id.isr_read_button).setOnClickListener(this);
        findViewById(R.id.isr_save_button).setOnClickListener(this);
        findViewById(R.id.isr_freq_button).setOnClickListener(this);
    }

    	public void onClick(View v) {
		switch(v.getId())
		{
			case R.id.isr_capture_button:
				iqconvert.setFrequency((long) FREQ);  //set iq converter to desired frequency
			    Intent readAmp = new Intent(Intent.ACTION_VIEW, Uri.parse("iqsrc://-a " + SERVERIP + " -p " + String.valueOf(SERVERPORT) + " -f " + String.valueOf(FREQ) + " -s 1200000"));
				startActivityForResult(readAmp, 123);

				break;
			case R.id.isr_read_button:
				 Intent intent = new Intent(ISRActivity.this, ReadISR.class);
			     startActivity(intent);
				break;
			case R.id.isr_save_button:
				new listenForTCP().execute();
				Toast.makeText(ISRActivity.this, (String) "Started reading from SDR", Toast.LENGTH_SHORT).show();

				//Toast.makeText(ISRActivity.this, (String) "save", Toast.LENGTH_SHORT).show();
				break;
			case R.id.isr_freq_button:
				//change FREQ
				EditText newFreqBox = (EditText) findViewById(R.id.isr_freq_box);
				float newFreq = Float.parseFloat(newFreqBox.getText().toString());  //get num from box
				
				//assuming number is >100MHz and <1000MHz
				int flToIntFreq = (int)(newFreq * 1000000);
				FREQ = flToIntFreq;  //change frequency
				Toast.makeText(ISRActivity.this, (String) "Tuned to " + newFreq + " MHz", Toast.LENGTH_SHORT).show();

				break;
			default:
				break;
		}
	}
    	
    	  @Override
    	    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data){
    	        runOnUiThread(new Runnable() {
    	            @Override
    	            public void run() {
    	                if(requestCode==123){
    	                    if(resultCode==RESULT_OK) {
    	                    	Toast.makeText(ISRActivity.this, (String) "Connected to SDR!", Toast.LENGTH_SHORT).show();
    	                    }
    	                    else {
    	                    	Toast.makeText(ISRActivity.this, (String) "Error, can't start RTL_TCP", Toast.LENGTH_SHORT).show();
    	                    }
    	                }
    	            }
    	        });
    	    }
    	    
    	    private class listenForTCP extends AsyncTask<String, Void, String > {
    	    	public byte samples[];
    	    	
    	        protected String doInBackground(String... ip) {
    	        	samples = new byte[1024];
    	        	int cntr = 0;
    	            try {
	            		Socket socket = new Socket(SERVERIP, SERVERPORT);
	            		while(cntr<8) {
	            			socket.getInputStream().read(samples, 0, 1024);
	            			/*SamplePacket aPkt = new SamplePacket(samples.length/8);
	            			iqconvert.mixPacketIntoSamplePacket(samples, aPkt, FREQ);  //mix sample to sample packet
	            			Toast.makeText(ISRActivity.this, aPkt.toString(), Toast.LENGTH_SHORT).show();*/
	            			publishProgress();
	            			cntr++;
	            		}
	            		socket.close();
    	            }catch (IOException e) {
       					Toast.makeText(ISRActivity.this, (String) "ERROR", Toast.LENGTH_SHORT).show();
    	            }
    	            return "No longer reading. ";
    	        }
    	        
    	        protected void onProgressUpdate(Void... temp) {
    	        	//Toast.makeText(ISRActivity.this, (String) "HERE in update", Toast.LENGTH_SHORT).show();
    	        	SamplePacket aPkt = new SamplePacket(samples.length/8);
        			iqconvert.mixPacketIntoSamplePacket(samples, aPkt, (long) FREQ);  //mix sample to sample packet
        			Toast.makeText(ISRActivity.this, aPkt.toString(), Toast.LENGTH_SHORT).show();
    	        }
    	        
    	        protected void onPostExecute(String result){
    	        	Toast.makeText(ISRActivity.this, (String) "Capture complete", Toast.LENGTH_SHORT).show();

    				//SimpleDateFormat date = new SimpleDateFormat("ddMMMyyyy_hhmmss");
    				//File newPacket = new File(Environment.getExternalStorageDirectory().getPath() + "/isr/" + date.format(Calendar.getInstance().getTime()) + ".isr");
    	                
    				 
    				/*TODO: write samples to file*/
    				 
    				 if(samples == null)
    					 Toast.makeText(ISRActivity.this, (String) "Empty File Created", Toast.LENGTH_SHORT).show();
    				 else {
    					 Toast.makeText(ISRActivity.this, (String) "Something Was Captured", Toast.LENGTH_SHORT).show();
    					 SimpleDateFormat date = new SimpleDateFormat("ddMMMyyyy_hhmmss");
    		             File newPacket = new File(Environment.getExternalStorageDirectory().getPath() + "/isr/" + date.format(Calendar.getInstance().getTime()) + ".isr");
    		             try {
							FileOutputStream f = new FileOutputStream(newPacket);
							 f.write(samples);
							 f.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				 }

                    
    	        }
    	    }

}
