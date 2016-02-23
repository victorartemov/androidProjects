package com.mycompany.demoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTime;
    TextView resultScore;
    TextView bestScore;

    Button exitButton;
    Button replayButton;

    int score = 333;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTime = (TextView) findViewById(R.id.timeTextView);
        resultScore = (TextView) findViewById(R.id.scoreTextView);
        bestScore = (TextView) findViewById(R.id.bestTextView);

        exitButton = (Button) findViewById(R.id.exitButton);
        replayButton = (Button) findViewById(R.id.replayButton);

        exitButton.setOnClickListener(this);
        replayButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Intent incomeData = getIntent();
        //score = incomeData.getIntExtra("score",0);

        //updateScreen();
    }

    /*public void updateScreen()
    {
        resultScore.setText("333");
        resultTime.setText("222");
        bestScore.setText("111");
    }*/

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.exitButton:
                intent = new Intent(this, FirstPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.replayButton:
                intent = new Intent(this, GetReady.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, FirstPage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
