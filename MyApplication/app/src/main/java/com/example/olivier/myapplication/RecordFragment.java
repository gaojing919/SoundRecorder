package com.example.olivier.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olivier.myapplication.Menu.SettingsFragment;
import com.melnykov.fab.FloatingActionButton;

import java.io.File;


public class RecordFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    @SuppressWarnings("unused")
    private static final String LOG_TAG = RecordFragment.class.getSimpleName();

    //Recording controls
    private FloatingActionButton mRecordButton = null;
    private FloatingActionButton mPauseButton = null;
    private FloatingActionButton mSettingsButton = null;

    private TextView mRecordingPrompt;
    private int mRecordPromptCount = 0;

    private boolean mStartRecording = true;
    private boolean mPauseRecording = true;

    private Chronometer mChronometer = null;
    long timeWhenPaused = 0; //stores time when user clicks pause button

    public static RecordFragment newInstance(int position) {
        RecordFragment f = new RecordFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);

        return f;
    }

    public RecordFragment() {
    }

    @Override
    @SuppressWarnings("unused")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View recordView = inflater.inflate(R.layout.fragment_record, container, false);

        mChronometer = (Chronometer) recordView.findViewById(R.id.chronometer);
        //update recording prompt text
        mRecordingPrompt = (TextView) recordView.findViewById(R.id.recording_status_text);

        mRecordButton = (FloatingActionButton) recordView.findViewById(R.id.btnRecord);
        mRecordButton.setColorNormal(getResources().getColor(R.color.primary));
        mRecordButton.setColorPressed(getResources().getColor(R.color.primary_dark));
        mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
            }
        });

        mPauseButton = (FloatingActionButton) recordView.findViewById(R.id.btnPause);
        mPauseButton.setVisibility(View.INVISIBLE); //hide pause button before recording starts
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPauseRecord(mPauseRecording);
                mPauseRecording = !mPauseRecording;
            }
        });

        mSettingsButton = (FloatingActionButton) recordView.findViewById(R.id.btnSetting);
        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsFragment settingsFragment = new SettingsFragment();
                settingsFragment.show(getFragmentManager().beginTransaction(), "dialog_settings");
            }
        });

        return recordView;
    }

    // Recording Start/Stop
    @SuppressWarnings("all")
    private void onRecord(boolean start){

        Intent intent = new Intent(getActivity(), RecordingService.class);

        if (start) {
            // start recording
            mRecordButton.setImageResource(R.drawable.ic_media_stop);
            mPauseButton.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(),R.string.toast_recording_start,Toast.LENGTH_SHORT).show();
            File folder = new File(Environment.getExternalStorageDirectory() + "/SoundRecorder");
            if (!folder.exists()) {
                //folder /SoundRecorder doesn't exist, create the folder
                //the method will do it automatically but it's better to write it
                folder.mkdir();
            }

            //start Chronometer
            mChronometer.setBase(SystemClock.elapsedRealtime());
            mChronometer.start();
            mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    if (mRecordPromptCount == 0) {
                        mRecordingPrompt.setText(getString(R.string.record_in_progress) + ".");
                    } else if (mRecordPromptCount == 1) {
                        mRecordingPrompt.setText(getString(R.string.record_in_progress) + "..");
                    } else if (mRecordPromptCount == 2) {
                        mRecordingPrompt.setText(getString(R.string.record_in_progress) + "...");
                        mRecordPromptCount = -1;
                    }

                    mRecordPromptCount++;
                }
            });

            //start RecordingService
            getActivity().startService(intent);
            //keep screen on while recording
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            mRecordingPrompt.setText(getString(R.string.record_in_progress) + ".");
            mRecordPromptCount++;

        } else {
            //stop recording
            mRecordButton.setImageResource(R.drawable.ic_media_record);
            mChronometer.stop();
            mPauseButton.setVisibility(View.INVISIBLE);
            if (mPauseRecording==false){
                mPauseRecording = !mPauseRecording;
            }
            mChronometer.setBase(SystemClock.elapsedRealtime());
            timeWhenPaused = 0;
            mRecordingPrompt.setText(getString(R.string.record_prompt));

            getActivity().stopService(intent);
            //allow the screen to turn off again once recording is finished
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    //TODO: suppress the pause button after stop
    private void onPauseRecord(boolean pause) {
        if (pause) {
            //pause recording
            //mPauseButton.setCompoundDrawablesWithIntrinsicBounds(0 ,0 ,0 ,0);
            mPauseButton.setImageResource(R.drawable.ic_media_play);
            mRecordingPrompt.setText(getString(R.string.resume_recording_button));
            timeWhenPaused = mChronometer.getBase() - SystemClock.elapsedRealtime();
            mChronometer.stop();
        } else {
            //resume recording
            //mPauseButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_media_pause, 0, 0, 0);
            mPauseButton.setImageResource(R.drawable.ic_media_pause);
            mRecordingPrompt.setText(getString(R.string.pause_recording_button));
            mChronometer.setBase(SystemClock.elapsedRealtime() + timeWhenPaused);
            mChronometer.start();
        }
    }
}