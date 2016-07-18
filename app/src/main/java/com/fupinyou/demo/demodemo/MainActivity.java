package com.fupinyou.demo.demodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreView = (LinearLayout) findViewById(R.id.score_View);
        scoreView.addView(new MyView(this,100));
    }
}
