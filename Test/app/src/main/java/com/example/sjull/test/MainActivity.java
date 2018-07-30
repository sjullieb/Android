package com.example.sjull.test;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static int m_volume;
    public static int m_minutes;
    private TextView m_viewVolume;
    private TextView m_viewTimer;

    Timer timer;
    TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_volume = 50;
        m_minutes = 30;
        m_viewVolume = (TextView) findViewById(R.id.textVolume);
        m_viewTimer = (TextView) findViewById(R.id.textTimer);


        ImageButton viewVolumeDown = (ImageButton) findViewById(R.id.imgBtnVolumeDown);
        viewVolumeDown.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                decreaseVolume(view);
                return false;
            }
        });

        ImageButton viewVolumeUp = (ImageButton) findViewById(R.id.imgBtnVolumeUp);
        viewVolumeUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                    stopTimerTask();
                else
                    startTimer();
                return false;
            }
        });

       /* viewVolumeUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                while (event.getAction() != MotionEvent.ACTION_UP) {
                    for (int i = 1; i < 2000; i++) {
                        //
                    }
                    increaseVolume(view);

                }
                return false;
            }
        });*/
        updateVolume();
        updateTimer();

    }

    public void startTimer(){
        timer = new Timer();

        initializeTimerTask();

        timer.schedule(timerTask, 5000, 5000);

    }

    public void initializeTimerTask() {
        timerTask = new TimerTask(){

            @Override
            public void run() {
               increaseVolume(null);
               updateVolume();
            }
        };
    }

    public void stopTimerTask(){
        if (timer != null)
            timer.cancel();
            timer = null;
    }

    public void changeVolume(View view) {
        int viewID = view.getId();
        switch (viewID){
            case R.id.imgBtnVolumeUp:
                if (m_volume < 100)
                    m_volume += 5;
                break;
            case R.id.imgBtnVolumeDown:
                if (m_volume > 0)
                    m_volume -= 5;
        }
    }

    public void increaseVolume(View view) {
        if (m_volume < 100)
            m_volume += 5;
            //updateValue(view);
            updateVolume();
    }

    public void decreaseVolume(View view) {
        if (m_volume > 0)
            m_volume -= 5;
            //updateValue(view);
            updateVolume();
    }

    public void updateVolume() {
        //Get the ID of the button that was clicked
        //tvVolume.setText(Integer.toString(iVolume) + "%");
        m_viewVolume.setText(String.format("%3d", m_volume) + "%");
    }

    public void updateTimer() {
        //Get the ID of the button that was clicked
        int hours;
        int minutes;

        hours = m_minutes / 60;
        minutes = m_minutes % 60;
        m_viewTimer.setText(String.format("%02d:%02d", hours, minutes));
    }

    public void updateValue(View view) {
        //Get the ID of the button that was clicked
        int viewID = view.getId();

        if ((viewID == R.id.imgBtnVolumeUp) || (viewID == R.id.imgBtnVolumeDown)){
            m_viewVolume.setText(Integer.toString(m_volume) + "%");
        }

        else if ((viewID == R.id.imgBtnTimerUp) || (viewID == R.id.imgBtnTimerDown)){
            //update the TextView
            int hours;
            int minutes;

            hours = m_minutes / 60;
            minutes = m_minutes % 60;
            m_viewTimer.setText(String.format("%02d:%02d", hours, minutes));
        }
    }

    public void increaseTimer(View view) {
        if (m_minutes < 100)
            m_minutes += 5;
            updateTimer();
            //updateValue(view);
    }

    public void decreaseTimer(View view) {
        if (m_minutes > 0)
            m_minutes -= 5;
            updateTimer();
            //updateValue(view);
    }

    public boolean onLongClick( View yourButton) {
        //increaseTimer(View);
        return true;
    }

}
