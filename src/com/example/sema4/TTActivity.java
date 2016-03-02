package com.example.sema4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TTActivity extends Activity {

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
                File newPacket = new File(Environment.getExternalStorageDirectory().getPath() + "/train/A4742D02Mar16_070314.telem");

                try {
                    FileOutputStream f = new FileOutputStream(newPacket);
                    TrainMessage msg = new TrainMessage(3.14159, "A4742D", 47, true);
                    String buffer = msg.render();
                    f.write(buffer.getBytes());
                    f.close();
                }
                catch (IOException x) {
                    x.printStackTrace();
                }

                Toast.makeText(TTActivity.this, (String) "Packet Received and saved!" , Toast.LENGTH_SHORT).show();
                
                break;
            case R.id.tt_read_button:
                Intent intent = new Intent(TTActivity.this, ReadTT.class);
			    startActivity(intent);
                break;
            case R.id.tt_save_button:
                //TODO
                break;
            default:
                break;
        }
    }
}
