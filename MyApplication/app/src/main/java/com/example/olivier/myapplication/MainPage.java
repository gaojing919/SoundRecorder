package com.example.olivier.myapplication;



import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainPage extends Fragment {

    ImageButton start;
    ImageButton stop;
    ImageButton pause;
    ImageButton pause_play;

    TextView explanation;

   // Button list;
    Button save;

    private MediaRecorder audio = new MediaRecorder();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FragmentActivity faActivity = (FragmentActivity) super.getActivity();
        RelativeLayout rLayout = (RelativeLayout) inflater.inflate(R.layout.main_page, container, false);


        explanation = (TextView) rLayout.findViewById(R.id.recording_text);
        //explanation.setVisibility(View.VISIBLE);
        //explanation.setText("Welcome");

        //list = (Button)rLayout.findViewById(R.id.button3);

        start = (ImageButton)rLayout.findViewById(R.id.mainButton_play);
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

        stop = (ImageButton)rLayout.findViewById(R.id.mainButton_stop);
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

     /*   list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mylist = new Intent(faActivity, RecordPage.class);
                startActivity(mylist);
            }
        });
*/
        pause = (ImageButton) rLayout.findViewById(R.id.pause);
        pause.setVisibility(View.INVISIBLE);
        //pause.setOnClickListener();

        pause_play = (ImageButton) rLayout.findViewById(R.id.pause_play);
        pause_play.setVisibility(View.INVISIBLE);
        //pause_play.setOnClickListener();

        save = (Button) rLayout.findViewById(R.id.save_button);
        save.setVisibility(View.INVISIBLE);
        //save.setOnClickListener();

        return rLayout;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu, inflater);
    }

/*
    private final View.OnClickListener globalClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {

            switch (v.getId()) {
                case R.id.mainButton_play:
                    //The app is recording
                    start.setVisibility(View.INVISIBLE);
                    stop.setVisibility(View.VISIBLE);
                    save.setVisibility(View.INVISIBLE);
                    explanation.setText("Recording");
                    break;

                case R.id.mainButton_stop:
                    //the recording stop
                    //save button must appear
                    start.setVisibility(View.VISIBLE);
                    stop.setVisibility(View.INVISIBLE);
                    save.setVisibility(View.VISIBLE);
                    explanation.setText("Record");
                    break;

                case R.id.pause:
                    //set the recording in pause mode if the app is playing
                    //stop if stop button is pressed
                    if (explanation.getText() == "Recording") {
                        start.setVisibility(View.INVISIBLE);
                        stop.setVisibility(View.VISIBLE);
                        pause_play.setVisibility(View.VISIBLE);
                        pause.setVisibility(View.INVISIBLE);
                        save.setVisibility(View.INVISIBLE);
                        explanation.setText("Pause");
                    }
                    break;

                case R.id.pause_play:
                    //continue recording after pause
                    if (explanation.getText()=="Pause"){
                        start.setVisibility(View.INVISIBLE);
                        stop.setVisibility(View.VISIBLE);
                        pause.setVisibility(View.VISIBLE);
                        pause_play.setVisibility(View.INVISIBLE);
                        save.setVisibility(View.INVISIBLE);
                        explanation.setText("Recording");
                    }
                    break;

                case R.id.save_button:
                    //save the file
                    //appear only when on stop
                    save.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    };
    */
}
