package com.mycompany.demoapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class GetReady extends AppCompatActivity {
    private TextView bigRedNumber;
    private Animation anim;
    private volatile String buf;
    private int mDelay = 1000;

    ShowBigRedNumbersTask task = new ShowBigRedNumbersTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_ready);

        bigRedNumber = (TextView) findViewById(R.id.bigRedNumber);
        bigRedNumber.setTextColor(Color.RED);
        bigRedNumber.setVisibility(TextView.INVISIBLE);
        anim = AnimationUtils.loadAnimation(this,R.anim.myscale);
    }

    @Override
    protected void onResume() {
        super.onResume();
        task.execute();
    };

    class ShowBigRedNumbersTask extends AsyncTask<Void, String, Void> {

        private boolean isStopped = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bigRedNumber.setVisibility(TextView.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            while(!isCancelled() && !isStopped) {
                for (int i = 3; i > 0; i--) {
                    buf = String.valueOf(i);
                    publishProgress(buf);
                    sleep();
                    if(i == 1) {
                      stop();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            bigRedNumber.setText(buf);
            bigRedNumber.startAnimation(anim);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(GetReady.this, GamePage.class);
            startActivity(intent);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            this.stop();
        }

        private void sleep() {
            try {
                Thread.sleep(mDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void stop() {
            isStopped = true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this,FirstPage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        task.cancel(true);
        startActivity(i);
    }
}
