package com.example.olivier.myapplication.Menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.example.olivier.myapplication.R;

public class FAQFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater dialogInflater = getActivity().getLayoutInflater();
        //Warning : null on the View root because we don't care on which View we put it in (because it's on top of all)
        @SuppressWarnings("all")
        View openSourceLicensesView = dialogInflater.inflate(R.layout.fragment_faq, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(openSourceLicensesView)
                .setTitle((getString(R.string.action_FAQ)))
                .setNeutralButton(android.R.string.ok, null);

        return dialogBuilder.create();
    }

}