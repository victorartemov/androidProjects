package com.mycompany.demoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GamePage extends AppCompatActivity implements View.OnClickListener {

    TextView timeTextView;
    TextView scoreTextView;

    TextView questionTextView;
    TextView ans1;
    TextView ans2;
    TextView ans3;
    TextView ans4;

    int score = 0;
    int currentQuestionNumber = 0;
    int countOfQuestions = 0;

    Game game = new Game();
    GameItem currentQuestion = new GameItem();

    public void addQuestionsToGame() {
        game.addGameItem(new GameItem("25 + 17 = ?", "39", "42", "43", "41", "42"));
        game.addGameItem(new GameItem("18 x 3 = ?", "56", "61", "48", "54", "54"));
        game.addGameItem(new GameItem("120 - 47 = ?", "77", "73", "93", "83", "73"));
        game.addGameItem(new GameItem("15% от 100 = ?", "15", "1", "25", "93", "83"));
        game.addGameItem(new GameItem("1000/20 = ?", "250", "60", "50", "80", "50"));
        game.addGameItem(new GameItem("99/1 = ?", "1", "33", "99", "90", "99"));
        game.addGameItem(new GameItem("84 x 3 = ?", "256", "252", "196", "214", "252"));
        game.addGameItem(new GameItem("20% от 120 = ?", "26", "30", "44", "24", "24"));
        game.addGameItem(new GameItem("18 - (-18) = ?", "0", "18", "36", "-18", "36"));
        game.addGameItem(new GameItem("98 х 2 = ?", "181", "191", "196", "186", "196"));
        game.addGameItem(new GameItem("11 х 11 = ?", "111", "131", "121", "141", "121"));
        game.addGameItem(new GameItem("13 х 13 = ?", "118", "213", "179", "169", "169"));
        game.addGameItem(new GameItem("36 + 18 = ?", "52", "48", "56", "54", "54"));
        game.addGameItem(new GameItem("8% от 20 = ?", "16", "1,4", "1,6", "14", "1,6"));
        game.addGameItem(new GameItem("32/2 = ?", "18", "17", "16", "15", "16"));
        game.addGameItem(new GameItem("98 - 19 = ?", "69", "79", "81", "71", "79"));
        game.addGameItem(new GameItem("18 + 3 - 7 = ?", "15", "13", "14", "11", "14"));
        game.addGameItem(new GameItem("100 / 2 x 5 = ?", "55", "250", "255", "25", "250"));
        game.addGameItem(new GameItem("19 х 6 = ?", "110", "112", "144", "114", "114"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        timeTextView = (TextView) findViewById(R.id.timeTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        ans1 = (TextView) findViewById(R.id.ans1);
        ans2 = (TextView) findViewById(R.id.ans2);
        ans3 = (TextView) findViewById(R.id.ans3);
        ans4 = (TextView) findViewById(R.id.ans4);
        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);

        this.addQuestionsToGame();
        score = 0;
        currentQuestionNumber = 0;
        countOfQuestions = 3
        ;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateScreen();
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ans1:
                if (ans1.getText().equals(currentQuestion.getRightAnswer()))
                    score += 100;
                break;

            case R.id.ans2:
                if (ans2.getText().equals(currentQuestion.getRightAnswer()))
                    score += 100;
                break;

            case R.id.ans3:
                if (ans3.getText().equals(currentQuestion.getRightAnswer()))
                    score += 100;
                break;

            case R.id.ans4:
                if (ans4.getText().equals(currentQuestion.getRightAnswer()))
                    score += 100;
                break;
        }

        Intent i = new Intent(GamePage.this, ResultActivity.class);
        if (currentQuestionNumber < countOfQuestions) {
            updateScreen();
            currentQuestionNumber++;
        } else {
            i.putExtra("score", String.valueOf(score));
            startActivity(i);
        }
    }

    public void updateScreen() {
        currentQuestion = game.getRandomItem();
        questionTextView.setText(currentQuestion.getTask());
        ans1.setText(currentQuestion.getAnswer1());
        ans2.setText(currentQuestion.getAnswer2());
        ans3.setText(currentQuestion.getAnswer3());
        ans4.setText(currentQuestion.getAnswer4());
        scoreTextView.setText("Счет: " + String.valueOf(score));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, FirstPage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
