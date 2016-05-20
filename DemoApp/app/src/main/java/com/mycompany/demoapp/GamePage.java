package com.mycompany.demoapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GamePage extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar progressBar;
    private TextView questionTextView;
    private TextView answer1TextView;
    private TextView answer2TextView;
    private TextView answer3TextView;
    private TextView answer4TextView;
    private TextView scoreTextView;
    private int delay;
    private long beginningTime;
    private long endTime;
    private long durationTime;
    private int countOfquestions;
    private int countOfRightAnswers;


    //игра останавливается, когда gameProgress достигает максимума. Также изменяется по ходу игры
    private volatile Integer gameProgress;

    int score;
    private Game game = new Game();
    GameItem currentItem = new GameItem();
    UpdatingProgressBar task = new UpdatingProgressBar();

    public void addQuestionsToGame() {
        game.addGameItem(new GameItem("25 + 17 = ?", "39", "42", "43", "41", "42", false));
        game.addGameItem(new GameItem("18 x 3 = ?", "56", "61", "48", "54", "54", false));
        game.addGameItem(new GameItem("120 - 47 = ?", "77", "73", "93", "83", "73", false));
        game.addGameItem(new GameItem("15% от 100 = ?", "15", "1", "25", "93", "15", false));
        game.addGameItem(new GameItem("1000/20 = ?", "250", "60", "50", "80", "50", false));
        game.addGameItem(new GameItem("99/1 = ?", "1", "33", "99", "90", "99", false));
        game.addGameItem(new GameItem("84 x 3 = ?", "256", "252", "196", "214", "252", false));
        game.addGameItem(new GameItem("20% от 120 = ?", "26", "30", "44", "24", "24", false));
        game.addGameItem(new GameItem("18 - (-18) = ?", "0", "18", "36", "-18", "36", false));
        game.addGameItem(new GameItem("98 х 2 = ?", "181", "191", "196", "186", "196", false));
        game.addGameItem(new GameItem("11 х 11 = ?", "111", "131", "121", "141", "121", false));
        game.addGameItem(new GameItem("13 х 13 = ?", "118", "213", "179", "169", "169", false));
        game.addGameItem(new GameItem("36 + 18 = ?", "52", "48", "56", "54", "54", false));
        game.addGameItem(new GameItem("8% от 20 = ?", "16", "1,4", "1,6", "14", "1,6", false));
        game.addGameItem(new GameItem("32/2 = ?", "18", "17", "16", "15", "16", false));
        game.addGameItem(new GameItem("98 - 19 = ?", "69", "79", "81", "71", "79", false));
        game.addGameItem(new GameItem("18 + 3 - 7 = ?", "15", "13", "14", "11", "14", false));
        game.addGameItem(new GameItem("100 / 2 x 5 = ?", "55", "250", "255", "25", "250", false));
        game.addGameItem(new GameItem("19 х 6 = ?", "110", "112", "144", "114", "114", false));
        game.addGameItem(new GameItem("11 - 30 = ?", "19", "-9", "-17", "-19", "-19", false));
        game.addGameItem(new GameItem("25 + 33 = ?", "56", "48", "43", "58", "58", false));
        game.addGameItem(new GameItem("66 - 17 = ?", "39", "49", "58", "44", "49", false));
        game.addGameItem(new GameItem("67 - 7 + 13 = ?", "77", "71", "63", "73", "73", false));
        game.addGameItem(new GameItem("16 x 9 = ?", "160", "116", "154", "144", "144", false));
        game.addGameItem(new GameItem("17 x 4 = ?", "69", "67", "56", "68", "68", false));
        game.addGameItem(new GameItem("114 / 2 = ?", "58", "77", "57", "47", "57", false));
        game.addGameItem(new GameItem("12 x 5 = ?", "55", "40", "60", "50", "60", false));
        game.addGameItem(new GameItem("20% от 80 = ?", "20", "40", "14", "16", "16", false));
        game.addGameItem(new GameItem("50% от 124 = ?", "64", "62", "81", "56", "62", false));
        game.addGameItem(new GameItem("10% от 100 = ?", "25", "20", "10", "100", "10", false));
        game.addGameItem(new GameItem("25 - 24 = ?", "23", "-11", "1", "0", "1", false));
        game.addGameItem(new GameItem("100% от 89 = ?", "8,9", "9", "87", "89", "89", false));
        game.addGameItem(new GameItem("7 + 7 + 3 = ?", "17", "13", "21", "27", "17", false));
        game.addGameItem(new GameItem("8 - 4 + 8 = ?", "4", "8", "14", "12", "12", false));
        game.addGameItem(new GameItem("11 - 7 + 5 = ?", "9", "8", "10", "12", "9", false));
        game.addGameItem(new GameItem("23 - 11 + 7 = ?", "12", "18", "19", "17", "19", false));
        game.addGameItem(new GameItem("93/93 = ?", "93", "11", "1", "2", "1", false));
        game.addGameItem(new GameItem("11 x 4 = ?", "44", "14", "11", "41", "44", false));
        game.addGameItem(new GameItem("58 - 14 x 2 = ?", "42", "26", "32", "30", "30", false));
        game.addGameItem(new GameItem("99 - 107 = ?", "8", "-8", "-7", "-9", "-8", false));
        game.addGameItem(new GameItem("11 + 143 = ?", "154", "155", "140", "159", "154", false));
        game.addGameItem(new GameItem("8 x 3 / 3 = ?", "24", "28", "3", "8", "8", false));
        game.addGameItem(new GameItem("12 x 12 = ?", "124", "144", "134", "142", "144", false));
        game.addGameItem(new GameItem("15 x 15 = ?", "155", "215", "225", "245", "225", false));
        game.addGameItem(new GameItem("16 x 16 = ?", "236", "242", "254", "256", "256", false));
        game.addGameItem(new GameItem("17 x 17 = ?", "289", "279", "286", "284", "289", false));
        game.addGameItem(new GameItem("18 x 18 = ?", "286", "314", "324", "328", "324", false));
        game.addGameItem(new GameItem("19 x 19 = ?", "281", "297", "371", "361", "361", false));
        game.addGameItem(new GameItem("20 x 20 = ?", "240", "400", "200", "280", "400", false));
        game.addGameItem(new GameItem("21 x 21 = ?", "441", "402", "411", "414", "441", false));
        game.addGameItem(new GameItem("58 - 17 + 1 = ?", "42", "39", "43", "38", "42", false));
        game.addGameItem(new GameItem("-17 + 30 - 4 = ?", "8", "-9", "4", "9", "9", false));
        game.addGameItem(new GameItem("11 x 3 - 13 = ?", "30", "33", "23", "20", "20", false));
        game.addGameItem(new GameItem("14 / 2 + 8 = ?", "15", "13", "14", "18", "15", false));
        game.addGameItem(new GameItem("17 x 2 - 10 = ?", "22", "24", "31", "32", "24", false));
        game.addGameItem(new GameItem("99 - 9 / 3 = ?", "33", "30", "90", "96", "96", false));
        game.addGameItem(new GameItem("88 - 8 + (-88) = ?", "168", "-8", "8", "-88", "-8", false));
        game.addGameItem(new GameItem("77 - 17 + 7 = ?", "70", "60", "57", "67", "67", false));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        this.addQuestionsToGame();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        answer1TextView = (TextView) findViewById(R.id.answer1TextView);
        answer2TextView = (TextView) findViewById(R.id.answer2TextView);
        answer3TextView = (TextView) findViewById(R.id.answer3TextView);
        answer4TextView = (TextView) findViewById(R.id.answer4TextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);

        answer1TextView.setOnClickListener(this);
        answer2TextView.setOnClickListener(this);
        answer3TextView.setOnClickListener(this);
        answer4TextView.setOnClickListener(this);

        progressBar.setProgress(0);
        progressBar.setMax(1000);
        score = 0;
        delay = 20;
        beginningTime = System.currentTimeMillis();
        countOfquestions = 0;
        countOfRightAnswers = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        task.execute();
        updateScreen();
    };

    class UpdatingProgressBar extends AsyncTask<Void, Integer, Void> {
        private boolean isStopped = false;

        @Override
        protected Void doInBackground(Void... params) {
            gameProgress = 0;

            do{
                gameProgress++;
                sleep();
                publishProgress(gameProgress);
            }
            while(gameProgress < 1000 && !isCancelled() && !isStopped);

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //замеряем текущее время на момент окончания сессии и рассчитываем ее продолжительность
            endTime = System.currentTimeMillis();
            durationTime = (endTime - beginningTime)/1000 - 2;

            Intent intent = new Intent(GamePage.this, ResultActivity.class);

            intent.putExtra("score", String.valueOf(score));
            intent.putExtra("duration time", String.valueOf(durationTime));
            intent.putExtra("count of questions", String.valueOf(countOfquestions));
            intent.putExtra("count of right answers", String.valueOf(countOfRightAnswers));

            startActivity(intent);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            this.stop();
        }

        private void sleep() {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void stop() {
            isStopped = true;
        }
    }

    @Override
    public void onClick(View v) {
        //не дадим нажать повторно ни на один textView
        setClickAbility(false);

        //получаем ответ с выбранного textView
        final TextView pressedTextView = (TextView)v;
        String textViewText = pressedTextView.getText().toString();

        countOfquestions++;

        //красим textView в зеленый или красный цвет в случае правильного или
        // неправильного ответа соответственно
        if(textViewText.equals(currentItem.getRightAnswer())){
            v.setBackgroundResource(R.color.sucsessColor);
            gameProgress = gameProgress - 100;
            score += 100;
            countOfRightAnswers++;
        }
        else {
            score -= 20;
            v.setBackgroundResource(R.color.failColor);
        }

        scoreTextView.setText("Score: " + score);

        //через секунду после выбора ответа, убираем цвет, возвращаем кликабельность
        //и сменяем задание на экране
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                setClickAbility(true);
                pressedTextView.setBackgroundResource(0);
                updateScreen();
            }
        },1000);
    }

    public void updateScreen(){
        currentItem = getNewQuestion();
        questionTextView.setText(currentItem.getTask());
        answer1TextView.setText(currentItem.getAnswer1());
        answer2TextView.setText(currentItem.getAnswer2());
        answer3TextView.setText(currentItem.getAnswer3());
        answer4TextView.setText(currentItem.getAnswer4());
    }

    public void setClickAbility(boolean b){
        answer1TextView.setClickable(b);
        answer2TextView.setClickable(b);
        answer3TextView.setClickable(b);
        answer4TextView.setClickable(b);
    }

    public GameItem getNewQuestion(){
        GameItem newGameItem = game.getRandomItem();
        //выбираем только не использованные вопросы
        //пока вопросов не так много, но шанс использовать все за одну игру мал, потому что
        //время ограничено
        while (newGameItem.isUsed() == true)
            newGameItem = game.getRandomItem();
        newGameItem.setUsed(true);
        return newGameItem;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, FirstPage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        task.cancel(true);
        startActivity(i);
    }
}
