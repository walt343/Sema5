package com.example.sema4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

public class TTActivity extends Activity implements View.OnClickListener {
	public static final String SERVERIP = "127.0.0.1";
    public static final int SERVERPORT = 1234;
    public int FREQ = 457937500;  //457.9375 MHz
	
	Intent readAmp = new Intent(Intent.ACTION_VIEW, Uri.parse("iqsrc://-a " + SERVERIP + " -p " + String.valueOf(SERVERPORT) + " -f " + String.valueOf(FREQ) + " -s 1200000"));

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
                //Toast.makeText(ACARSActivity.this, getText(), Toast.LENGTH_SHORT).show();
            	
            	startActivityForResult(readAmp, 123);

                //Toast.makeText(TTActivity.this, (String) "Packet Received and saved!" , Toast.LENGTH_SHORT).show();
                
                break;
            case R.id.tt_read_button:
                Intent intent = new Intent(TTActivity.this, ReadTT.class);
			    startActivity(intent);
                break;
            case R.id.tt_save_button:
            	new listenForTCP().execute();
				Toast.makeText(TTActivity.this, (String) "Started Reading from SDR", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
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
					Toast.makeText(TTActivity.this, (String) "ERROR", Toast.LENGTH_SHORT).show();
            }
            return "No longer reading. ";
        }
        
        protected void onProgressUpdate(Void... temp) {
        	Toast.makeText(TTActivity.this, (String) "Bytes ingested", Toast.LENGTH_SHORT).show();
        }
        
        protected void onPostExecute(String result){
        	Toast.makeText(TTActivity.this, (String) "Capture complete", Toast.LENGTH_SHORT).show();

			//SimpleDateFormat date = new SimpleDateFormat("ddMMMyyyy_hhmmss");
			//File newPacket = new File(Environment.getExternalStorageDirectory().getPath() + "/isr/" + date.format(Calendar.getInstance().getTime()) + ".isr");
                
			 
			/*TODO: write samples to file*/
			 
			 if(samples == null)
				 Toast.makeText(TTActivity.this, (String) "Empty File Created", Toast.LENGTH_SHORT).show();
			 else {
				 Toast.makeText(TTActivity.this, (String) "Something Was Captured", Toast.LENGTH_SHORT).show();
				 SimpleDateFormat date = new SimpleDateFormat("ddMMMyyyy_hhmmss");
	             File newPacket = new File(Environment.getExternalStorageDirectory().getPath() + "/train/" + date.format(Calendar.getInstance().getTime()) + ".ttbyte");
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
