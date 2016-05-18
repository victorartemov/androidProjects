package com.mycompany.demoapp;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyDialog extends DialogFragment implements View.OnClickListener{
    Button gotIt;
    EditText userNameInput;
    String userName;
    Communicator communicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator)activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_dialog, null);
        gotIt = (Button) view.findViewById(R.id.buttonGotIt);
        gotIt.setOnClickListener(this);
        userNameInput = (EditText) view.findViewById(R.id.userNameInput);
        getDialog().setTitle("Новый рекорд!");
        setCancelable(false);
        return view;
    }

    @Override
    public void onClick(View v) {
        userName = userNameInput.getText().toString();
        communicator.onDialogMessage(userName);
        dismiss();
    }

    interface Communicator {
        public void onDialogMessage(String message);
    }
}
