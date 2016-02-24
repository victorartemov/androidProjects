package com.mycompany.demoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTime;
    TextView resultScore;
    TextView bestScore;

    Button exitButton;
    Button replayButton;
    String score;
    int currentBestScore;

    SharedPreferences spref;
    final String SAVED_SCORE = "saved score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTime = (TextView) findViewById(R.id.resultTimeTextView);
        resultScore = (TextView) findViewById(R.id.resultScoreTextView);
        bestScore = (TextView) findViewById(R.id.bestTextView);

        exitButton = (Button) findViewById(R.id.exitButton);
        replayButton = (Button) findViewById(R.id.replayButton);

        exitButton.setOnClickListener(this);
        replayButton.setOnClickListener(this);

        isNewScoreBetter();
    }

    public void isNewScoreBetter()
    {
        Intent intent = getIntent();
        score = intent.getStringExtra("score");
        int newScore = Integer.decode(score);
        spref = getPreferences(MODE_PRIVATE);
        currentBestScore = spref.getInt(SAVED_SCORE, 0);
        if(newScore > currentBestScore)
        {
            currentBestScore = newScore;
            SharedPreferences.Editor ed = spref.edit();
            ed.putInt(SAVED_SCORE,newScore);
            ed.commit();
            Toast.makeText(ResultActivity.this, "Новый рекорд!",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateScreen();
    }

    public void updateScreen()
    {
        resultTime.setText("Время: 00:00");
        resultScore.setText("Результат: " + score);
        bestScore.setText("Рекорд: " + String.valueOf(currentBestScore));
    }

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
