package com.example.olivier.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity {

    ImageButton mainRecord;
    ImageButton mainStop;
    ImageButton pause;
    ImageButton pause_play;
    TextView explanation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecord = (ImageButton) findViewById(R.id.mainButton_play);
        mainRecord.setVisibility(View.VISIBLE);
        mainRecord.setOnClickListener(globalClick);

        mainStop = (ImageButton) findViewById(R.id.mainButton_stop);
        mainStop.setVisibility(View.INVISIBLE);
        mainStop.setOnClickListener(globalClick);

        pause = (ImageButton) findViewById(R.id.pause);
        pause.setVisibility(View.VISIBLE);
        pause.setOnClickListener(globalClick);

        pause_play = (ImageButton) findViewById(R.id.pause_play);
        pause_play.setVisibility(View.INVISIBLE);
        pause_play.setOnClickListener(globalClick);

        explanation = (TextView) findViewById(R.id.recording_text);


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


    final View.OnClickListener globalClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {

            switch (v.getId()) {
                case R.id.mainButton_play:
                    //The app is recording
                    mainRecord.setVisibility(View.INVISIBLE);
                    mainStop.setVisibility(View.VISIBLE);
                    explanation.setText("Recording");
                    break;

                case R.id.mainButton_stop:
                    //the recording stop
                    //save button must appear
                    mainRecord.setVisibility(View.VISIBLE);
                    mainStop.setVisibility(View.INVISIBLE);
                    explanation.setText("Record");
                    break;

                case R.id.pause:
                    //set the recording in pause mode if the app is playing
                    //stop if stop button is pressed
                    if (explanation.getText() == "Recording") {
                        mainRecord.setVisibility(View.INVISIBLE);
                        mainStop.setVisibility(View.VISIBLE);
                        pause_play.setVisibility(View.VISIBLE);
                        pause.setVisibility(View.INVISIBLE);
                        explanation.setText("Pause");
                    }
                    break;

                case R.id.pause_play:
                    //continue recording after pause
                    if (explanation.getText()=="Pause"){
                        mainRecord.setVisibility(View.INVISIBLE);
                        mainStop.setVisibility(View.VISIBLE);
                        pause.setVisibility(View.VISIBLE);
                        pause_play.setVisibility(View.INVISIBLE);
                        explanation.setText("Recording");
                    }
                    break;
            }
        }
    };
}
