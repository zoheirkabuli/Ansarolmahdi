package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;

public class AddClass extends AppCompatActivity {

    private Toolbar cToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        cToolbar = findViewById(R.id.cToolbar);
        setSupportActionBar(cToolbar);
        cToolbar.setTitleTextColor(Color.WHITE);
        setTitle("Add Class");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }
}
