package com.example.sema4;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

public class ReadACARS extends Activity {
		
	private ListView lv;
	ArrayAdapter<String> adapter;
    EditText inputSearch;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_dir);

        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/acars");
    	   	
    	File listfile[] = root.listFiles();
    	ArrayList<String> names = new ArrayList<String>();
    	for(int i = 0; i < listfile.length; i++)
    	{
    		names.add(listfile[i].getName());
    	}

    	lv = (ListView) findViewById(R.id.list);
    	lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String message = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/acars/" + (String) parent.getItemAtPosition(position));
				Intent intent = new Intent(ReadACARS.this, ShowACARS.class);
				intent.putExtra("message", message);
		        startActivity(intent);
			}
    		
    	});
    	inputSearch = (EditText) findViewById(R.id.inputSearch);
    	
    	adapter = new ArrayAdapter<String>(this,
    			R.layout.list_item, R.id.file_name, names);
    	lv.setAdapter(adapter);
    	
    	inputSearch.addTextChangedListener(new TextWatcher() {
   	     
    	    @Override
    	    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
    	        // When user changed the Text
    	        ReadACARS.this.adapter.getFilter().filter(cs);   
    	    }
    	     
    	    @Override
    	    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
    	            int arg3) {
    	        // TODO Auto-generated method stub
    	         
    	    }
    	     
    	    @Override
    	    public void afterTextChanged(Editable arg0) {
    	        // TODO Auto-generated method stub                          
    	    }
    	});
    }
}
