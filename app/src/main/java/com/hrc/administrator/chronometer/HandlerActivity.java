package com.hrc.administrator.chronometer;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener,Runnable{
    private int count=0;
    private Handler handler;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        textView=(TextView)findViewById(R.id.tv);
        Button start=(Button)findViewById(R.id.btnstart);
        Button stop=(Button)findViewById(R.id.btnstop);
        Button show=(Button)findViewById(R.id.btnshow);
        show.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        handler=new Handler();
    }

    class RunToast implements Runnable{
        private Context context;

        public RunToast(Context context){
            this.context=context;
        }

        @Override
        public void run() {
            Toast.makeText(context,"15秒后显示Toast提示信息",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnstart:
                handler.postDelayed(this,5000);
                break;
            case R.id.btnstop:
                handler.removeCallbacks(this);
                break;
            case R.id.btnshow:
                handler.postAtTime(new RunToast(this){},android.os.SystemClock.uptimeMillis()+15*1000);
                break;
        }
    }

    @Override
    public void run() {
        textView.setText("Count:"+String.valueOf(++count));
        handler.postDelayed(this,5000);
    }
}
