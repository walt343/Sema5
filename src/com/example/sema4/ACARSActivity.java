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
import android.widget.Toast;

public class ACARSActivity extends Activity implements View.OnClickListener{

	/*static {
		System.loadLibrary("Sema4");
	}*/
	
	public static final String SERVERIP = "127.0.0.1";
    public static final int SERVERPORT = 1234;
    public int FREQ = 131550000;  //131.550 MHz = ACARS
    
    //Intent readAmp = new Intent(Intent.ACTION_VIEW, Uri.parse("iqsrc://-a " + SERVERIP + " -p " + String.valueOf(SERVERPORT) + " -f " + String.valueOf(FREQ) + " -s 1200000"));
	
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
				//Toast.makeText(ACARSActivity.this, getText(), Toast.LENGTH_SHORT).show();
				
				/*SimpleDateFormat date = new SimpleDateFormat("ddMMMyyyy_hhmmss");
                File newPacket = new File(Environment.getExternalStorageDirectory().getPath() + "/acars/NCC170102" + date.format(Calendar.getInstance().getTime()) + ".acars");

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

                Toast.makeText(ACARSActivity.this, (String) "Packet Received and saved!" , Toast.LENGTH_SHORT).show();*/
				Intent readAmp = new Intent(Intent.ACTION_VIEW, Uri.parse("iqsrc://-a " + SERVERIP + " -p " + String.valueOf(SERVERPORT) + " -f " + String.valueOf(FREQ) + " -s 1200000"));
				startActivityForResult(readAmp, 123);
				
				break;
			case R.id.acars_read_button:
				 Intent intent = new Intent(ACARSActivity.this, ReadACARS.class);
			     startActivity(intent);
				break;
			case R.id.acars_save_button:
				//Toast.makeText(ACARSActivity.this, (String) "save", Toast.LENGTH_SHORT).show();
				new listenForTCP().execute();
				Toast.makeText(ACARSActivity.this, (String) "Started reading from SDR", Toast.LENGTH_SHORT).show();

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
	                    	Toast.makeText(ACARSActivity.this, (String) "Connected to SDR!", Toast.LENGTH_SHORT).show();
	                    }
	                    else {
	                    	Toast.makeText(ACARSActivity.this, (String) "Error, can't start RTL_TCP", Toast.LENGTH_SHORT).show();
	                    }
	                }
	            }
	        });
	    }
	    
	    private class listenForTCP extends AsyncTask<String, Void, String > {
	    	public byte samples[];
	    	
	        protected String doInBackground(String... ip) {
	        	samples = new byte[204800];
	        	int cntr = 0;
	            try {
          		Socket socket = new Socket(SERVERIP, SERVERPORT);
          		while(cntr<8) {
          			socket.getInputStream().read(samples, 0, 204800);
          			publishProgress();
          			cntr++;
          		}
          		socket.close();
	            }catch (IOException e) {
 					Toast.makeText(ACARSActivity.this, (String) "ERROR", Toast.LENGTH_SHORT).show();
	            }
	            return "No longer reading. ";
	        }
	        
	        protected void onProgressUpdate(Void... temp) {
	        	//Toast.makeText(ACARSActivity.this, (String) "HERE in update", Toast.LENGTH_SHORT).show();
	        	Toast.makeText(ACARSActivity.this, (String) "HERE in update", Toast.LENGTH_SHORT).show();
	        }
	        
	        protected void onPostExecute(String result){
	        	Toast.makeText(ACARSActivity.this, (String) "Capture complete", Toast.LENGTH_SHORT).show();

				//SimpleDateFormat date = new SimpleDateFormat("ddMMMyyyy_hhmmss");
				//File newPacket = new File(Environment.getExternalStorageDirectory().getPath() + "/isr/" + date.format(Calendar.getInstance().getTime()) + ".isr");
	                
				 
				/*TODO: write samples to file*/
				 
				 if(samples == null)
					 Toast.makeText(ACARSActivity.this, (String) "Empty File Created", Toast.LENGTH_SHORT).show();
				 else {
					 Toast.makeText(ACARSActivity.this, (String) "Something Was Captured", Toast.LENGTH_SHORT).show();
					 SimpleDateFormat date = new SimpleDateFormat("ddMMMyyyy_hhmmss");
		             File newPacket = new File(Environment.getExternalStorageDirectory().getPath() + "/acars/" + date.format(Calendar.getInstance().getTime()) + ".acarsbyte");
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
