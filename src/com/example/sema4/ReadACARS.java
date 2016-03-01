package com.example.sema4;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ReadACARS extends ListActivity {
		
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/acars");
    	   	
    	File listfile[] = root.listFiles();

    	ArrayList<String> fileList;

    	fileList = new ArrayList<String>();
    	
    	if(listfile != null)
    	{
	    	for(int i = 0; i < listfile.length; i++)
	    	{
	    		fileList.add(listfile[i].getName());
	    	}
    	}
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    			R.layout.list_item, fileList);
    	setListAdapter(adapter);
    }
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String message = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/acars/" + (String) getListAdapter().getItem(position));
		Intent intent = new Intent(ReadACARS.this, ShowACARS.class);
		intent.putExtra("message", message);
        startActivity(intent);
	}
}
