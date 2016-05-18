package com.mycompany.demoapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ResultActivity extends AppCompatActivity implements View.OnClickListener, MyDialog.Communicator {

    private TextView resultTime;
    private TextView resultScore;
    private TextView bestScore;
    private TextView rightQuestionsInfo;
    private TextView playerName;

    private Button exitButton;
    private Button replayButton;
    private String score;
    private int currentBestScore;
    private String gameDuration;
    private String countOfQuestions;
    private String countOfRightAnswers;
    private String winnerName;

    SharedPreferences spref;
    final String SAVED_SCORE = "saved score";
    final String WINNER_NAME = "winner name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //присваиваем ссылки
        resultTime = (TextView) findViewById(R.id.resultTimeTextView);
        resultScore = (TextView) findViewById(R.id.resultScoreTextView);
        bestScore = (TextView) findViewById(R.id.bestTextView);
        rightQuestionsInfo = (TextView) findViewById(R.id.rightQuestionsInfo);
        playerName = (TextView) findViewById(R.id.playerName);
        exitButton = (Button) findViewById(R.id.exitButton);
        replayButton = (Button) findViewById(R.id.replayButton);

        //присваиваем обработчик нажатия
        exitButton.setOnClickListener(this);
        replayButton.setOnClickListener(this);

        spref = getPreferences(MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isNewScoreBetter();
        updateScreen();
    }

    public void isNewScoreBetter()
    {
        //получаем интент и дополнительные данные из него
        Intent intent = getIntent();
        score = intent.getStringExtra("score");
        gameDuration = intent.getStringExtra("duration time");
        countOfQuestions = intent.getStringExtra("count of questions");
        countOfRightAnswers = intent.getStringExtra("count of right answers");
        int newScore = Integer.decode(score);


        currentBestScore = spref.getInt(SAVED_SCORE, 0);

        if(newScore > currentBestScore)
        {
            showDialog();
            currentBestScore = newScore;
            SharedPreferences.Editor ed = spref.edit();
            ed.putInt(SAVED_SCORE, newScore);
            ed.commit();
        }
        winnerName = spref.getString(WINNER_NAME,"player");
    }

    public void updateScreen()
    {
        resultTime.setText("Время: " + gameDuration + " cек.");
        resultScore.setText("Результат: " + score);
        bestScore.setText("Рекорд: " + String.valueOf(currentBestScore));
        rightQuestionsInfo.setText("Правильных ответов " + countOfRightAnswers + " из " + countOfQuestions);
        playerName.setText("by " + winnerName);
    }

    public void showDialog(){
        FragmentManager manager = getFragmentManager();
        MyDialog myDialog = new MyDialog();
        myDialog.show(manager,"myDialog");
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

    @Override
    public void onDialogMessage(String message) {
        winnerName = message;
        SharedPreferences.Editor ed = spref.edit();
        ed.putString(WINNER_NAME, winnerName);
        ed.commit();
        updateScreen();
    }
}
