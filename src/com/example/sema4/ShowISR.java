//TODO
package com.example.sema4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

public class ShowISR extends Activity {
   
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt_message);  //TODO
       
        Intent intent = getIntent();
        String message = intent.getExtras().getString("message");
        String filename = message;
        String text = readFile(filename);
               
    	TextView tv = (TextView) findViewById(R.id.TT_message);  //TODO
    	tv.setText(text);
	}
	
	public String  readFile(String s) {
		String res = "";
	
		
		try {
	        InputStream inputStream = new FileInputStream(new File(s));

	        if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();

	            while ( (receiveString = bufferedReader.readLine()) != null ) {
	                stringBuilder.append(receiveString);
	            }

	            inputStream.close();
	            res = stringBuilder.toString();
	            }
		}
	    catch(Exception e)
	    {}

		return res;
	}
}
