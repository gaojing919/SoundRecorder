package com.example.administrator.soundrecorder;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class listActivity extends ActionBarActivity {
    private String dir= Environment.getExternalStorageDirectory()+"/SoundRecorder/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
       File file=new File(dir);
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        if(file.list().length>0)
        {
            for(File f :file.listFiles()){
                Map<String,Object> m=new HashMap<String, Object>();
                m.put("fileName",f.getName());
                list.add(m);

            }
        }

        SimpleAdapter sa=new SimpleAdapter(this,list,R.layout.activity_iteam,new String[]{"fileName"},new int[]{R.id.textView3});
        ListView l=(ListView)findViewById(R.id.listView);
        l.setAdapter(sa);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
