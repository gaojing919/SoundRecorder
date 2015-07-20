package com.example.olivier.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by olivier on 17/07/2015.
 */
public class RecordPage extends Fragment {

    private MediaPlayer play = new MediaPlayer();
    private String dir = Environment.getExternalStorageDirectory()+"/SoundRecorder/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentActivity faActivity = (FragmentActivity) super.getActivity();
        RelativeLayout rLayout =(RelativeLayout) inflater.inflate(R.layout.record_list, container, false);
        //TextView title = (TextView) rLayout.findViewById(R.id.list);
        //title.setText("ahah");

        File file = new File(dir);
        List<Map<String,Object>> list = new ArrayList<>();

        if(file.list()!=null)
        {
            for(File f :file.listFiles()){
                Map<String,Object> m = new HashMap<>();
                m.put("fileName",f.getName());
                list.add(m);
            }
        }
        else{
            Map<String,Object> m = new HashMap<>();
            m.put("fileName",file.getName());
            list.add(m);
        }

        SimpleAdapter sa = new SimpleAdapter(faActivity, list, R.layout.activity_iteam, new String[]{"fileName"}, new int[]{R.id.textView3});
        ListView l = (ListView)rLayout.findViewById(R.id.listView);
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


        return rLayout;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu, inflater);
    }

}
