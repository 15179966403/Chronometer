package com.hrc.administrator.chronometer;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Chronometer.OnChronometerTickListener{
    private Chronometer chronometer;
    private TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer=(Chronometer)findViewById(R.id.chronometertime);
        tvTime=(TextView)findViewById(R.id.tvTime);
        Button start=(Button)findViewById(R.id.btnstart);
        Button stop=(Button)findViewById(R.id.btnstop);
        Button reset=(Button)findViewById(R.id.btnreset);
        Button btn=(Button)findViewById(R.id.intent);
        btn.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        reset.setOnClickListener(this);
        chronometer.setOnChronometerTickListener(this);
        chronometer.setFormat("计时器： %s");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnstart:
                chronometer.start();
                break;
            case R.id.btnstop:
                chronometer.stop();
                break;
            case R.id.btnreset:
                chronometer.setBase(SystemClock.elapsedRealtime());
                break;
            case R.id.intent:
                Intent intent=new Intent(this,HandlerActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        tvTime.setText("当前时间："+sdf.format(new Date()));
    }
}
