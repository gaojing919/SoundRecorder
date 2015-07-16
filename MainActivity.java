package com.example.administrator.soundrecorder;

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
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {
    private Button start;
    private Button stop;
    private Button list;
    private MediaRecorder audio=new MediaRecorder();
    final String tag="tag";
    private void init(){
        start=(Button)findViewById(R.id.button2);
        stop=(Button)findViewById(R.id.button1);
        list=(Button)findViewById(R.id.button3);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file;
                audio.setAudioSource(MediaRecorder.AudioSource.MIC);
                audio.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
                audio.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                boolean exist= Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
                if (exist)
                {
                    File dir=new File(Environment.getExternalStorageDirectory()+"/SoundRecorder");
                    if(!dir.exists()) {
                        dir.mkdir();
                    }
                        Date d=new Date();
                        SimpleDateFormat s=new SimpleDateFormat("yyMMddHHmmss");
                        String name=s.format(d)+".amr";
                        file=new File(dir,name);
                        if(!file.exists()){
                            try{
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
                    TextView label=(TextView)findViewById(R.id.textView2);
                    label.setText("recording.......");
                    audio.prepare();
                    audio.start();;
                }
                catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                catch(IOException e){
                    e.printStackTrace();
                }

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // MediaRecorder a=new MediaRecorder();
                TextView label=(TextView)findViewById(R.id.textView2);
                label.setText("Stop");
                audio.stop();
                audio.release();

            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mylist=new Intent(MainActivity.this,listActivity.class);
                startActivity(mylist);
            }
        });
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
