package com.example.olivier.myapplication;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

//import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    ImageButton start;
    ImageButton stop;
    ImageButton pause;
    ImageButton pause_play;

    TextView explanation;

    Button list;
    Button save;

    private MediaRecorder audio = new MediaRecorder();
    final String tag = "tag";

    private void init(){

        explanation = (TextView) findViewById(R.id.recording_text);
        //explanation.setVisibility(View.VISIBLE);
        //explanation.setText("Welcome");

        list = (Button)findViewById(R.id.button3);

        start = (ImageButton)findViewById(R.id.mainButton_play);
        start.setVisibility(View.VISIBLE);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File file;
                audio.setAudioSource(MediaRecorder.AudioSource.MIC);
                audio.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
                audio.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                boolean exist= Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

                start.setVisibility(View.INVISIBLE);
                stop.setVisibility(View.VISIBLE);

                if (exist)
                {
                    File dir=new File(Environment.getExternalStorageDirectory()+"/SoundRecorder");
                    if(!dir.exists()) {
                        //warning because the file creation is automatic
                        dir.mkdir();
                    }
                        Date d=new Date();
                        //warning because we should use pre-existing format
                        SimpleDateFormat s=new SimpleDateFormat("yyMMddHHmmss");
                        String name=s.format(d)+".amr";
                        file=new File(dir,name);
                        if(!file.exists()){
                            try{
                                //same warning as for the mkdir
                                file.createNewFile();
                                audio.setOutputFile(file.getAbsolutePath());
                            }
                            catch(IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                }
                try{
                    explanation.setText("Recording");
                    audio.prepare();
                    audio.start();;
                }
                catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                }

            }
        });

        stop = (ImageButton)findViewById(R.id.mainButton_stop);
        stop.setVisibility(View.INVISIBLE);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);

                //MediaRecorder a=new MediaRecorder();
                explanation.setText("Stop");
                audio.stop();
                audio.release();

            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mylist = new Intent(MainActivity.this, listActivity.class);
                startActivity(mylist);
            }
        });

        pause = (ImageButton) findViewById(R.id.pause);
        pause.setVisibility(View.INVISIBLE);
        //pause.setOnClickListener();

        pause_play = (ImageButton) findViewById(R.id.pause_play);
        pause_play.setVisibility(View.INVISIBLE);
        //pause_play.setOnClickListener();

        save = (Button) findViewById(R.id.save_button);
        save.setVisibility(View.INVISIBLE);
        //save.setOnClickListener();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Log.i(tag, "onCreat");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tag, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(tag, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(tag, "onDestroy");

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
