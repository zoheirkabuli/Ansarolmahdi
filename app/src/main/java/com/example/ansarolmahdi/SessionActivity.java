package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SessionActivity extends AppCompatActivity {
    private TextView homeWorkTitle;
    private Toolbar seToolbar;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        init();
        intentGetter();
        toolbarOption();
    }

    private void init() {
        seToolbar = findViewById(R.id.seToolbar);
        homeWorkTitle = findViewById(R.id.tv_homeworktitle);
    }

    private void intentGetter(){
        intent = getIntent();
        homeWorkTitle.setText("تکلیف " + intent.getStringExtra("TESTTEST"));
    }

    private void toolbarOption() {
        setSupportActionBar(seToolbar);
        setTitle(intent.getStringExtra("TESTTEST"));
        seToolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        seToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
