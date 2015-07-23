package com.example.olivier.myapplication.Menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.olivier.myapplication.R;


public class SettingsFragment extends DialogFragment {

    static String nFormat = null;
    static String nNamed = null;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater dialogInflater = getActivity().getLayoutInflater();
        //Warning : null on the View root because we don't care on which View we put it in (because it's on top of all)
        final View settingView = dialogInflater.inflate(R.layout.fragment_settings, null);


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(settingView)
                .setTitle((getString(R.string.action_settings)))
                .setNeutralButton(android.R.string.ok, null);
        EditText btnName = (EditText) settingView.findViewById(R.id.editText);
        String name = String.valueOf(btnName.getText());
        nNamed = name;

        RadioGroup format = (RadioGroup) settingView.findViewById(R.id.format);
        final RadioButton mp3 = (RadioButton) settingView.findViewById(R.id.radioButton1);
        final RadioButton wav = (RadioButton) settingView.findViewById(R.id.radioButton2);
        final RadioButton amr = (RadioButton) settingView.findViewById(R.id.radioButton3);

        format.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp3.isChecked()) {
                    nFormat = ".mp3";
                } else if (wav.isChecked()) {
                    nFormat = ".wav";
                } else if (amr.isChecked()) {
                    nFormat = ".amr";
                } else {
                    nFormat = ".mp4";
                }
            }
        });

        return dialogBuilder.create();
    }


    public static String getFormat() {
        if(nFormat == null){
            nFormat = ".mp4";
        }
        return nFormat;
    }

    public static String getNamed() {
    if (nNamed==null){
        nNamed = "My Recording";
    }
        return nNamed;
    }
}
