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


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    ImageButton mainRecord;
    ImageButton mainPause;
    TextView explanation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecord = (ImageButton) findViewById(R.id.mainButton_play);
        mainRecord.setVisibility(View.VISIBLE);
        mainRecord.setOnClickListener(this);

        mainPause = (ImageButton) findViewById(R.id.mainButton_pause);
        mainPause.setVisibility(View.INVISIBLE);

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

    @Override
    public void onClick(View v) {

        //Code part of Recording

        mainRecord.setVisibility(View.INVISIBLE);
        mainPause.setVisibility(View.VISIBLE);
        explanation.setText("Recording");
    }
}
