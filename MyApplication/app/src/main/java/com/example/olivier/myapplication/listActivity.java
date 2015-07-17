package com.example.olivier.myapplication;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class listActivity extends ActionBarActivity {

    private MediaPlayer play = new MediaPlayer();
    private String dir = Environment.getExternalStorageDirectory()+"/SoundRecorder/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        File file=new File(dir);
        List<Map<String,Object>> list=new ArrayList<>();
        if(file.list().length>0)
        {
            for(File f :file.listFiles()){
                Map<String,Object> m = new HashMap<>();
                m.put("fileName",f.getName());
                list.add(m);
            }
        }

        SimpleAdapter sa = new SimpleAdapter(this,list,R.layout.activity_iteam,new String[]{"fileName"},new int[]{R.id.textView3});
        ListView l = (ListView)findViewById(R.id.listView);
        l.setAdapter(sa);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = Environment.getExternalStorageDirectory() + "/SoundRecorder/" + ((TextView) view).getText();
                try {
                    play.setDataSource(name);
                    play.prepare();
                    play.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
