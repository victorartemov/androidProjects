package com.mycompany.demoapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;
import java.util.logging.LogRecord;
import android.os.Message;
import android.os.Handler;

public class GetReady extends AppCompatActivity {
    TextView bigRedNumber;
    Animation anim;
    String buf;

    //В этой activity два потока, основной, и поток, выводящий цифры. Переход на activity игры
    //выполняется из дополнительного потока, поэтому если нажать кнопку "назад" в то время, когда
    //еще не вывелись все цифры, выполнится переход на начальную activity, но когда тот дополнительный
    //поток закончит вывод цифр, все равно сработает intent, перекидывающий нас на activity игры.
    //Скорее всего при нажатии кнопки можно остановить поток, и проблема исчезнет, но я пока еще
    //не умею так сделать и довольствуюсь флагом. Как только лучше узнаю потоки, перепишу этот код.
    boolean backButtonWasPressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_ready);

        bigRedNumber = (TextView) findViewById(R.id.bigRedNumber);
        bigRedNumber.setTextColor(Color.RED);
        anim = AnimationUtils.loadAnimation(this,R.anim.myscale);
        backButtonWasPressed = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread updatingBigRedNumbers = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        buf = "3";
                        h.sendEmptyMessage(0);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        buf = "2";
                        h.sendEmptyMessage(0);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        buf = "1";
                        h.sendEmptyMessage(0);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(!backButtonWasPressed) {
                            Intent i = new Intent(GetReady.this, GamePage.class);
                            startActivity(i);
                        }

                    }
                }
        );
        updatingBigRedNumbers.start();
    };

    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            bigRedNumber.setText(buf);
            bigRedNumber.startAnimation(anim);
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this,FirstPage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        backButtonWasPressed = true;
        startActivity(i);
    }
}
