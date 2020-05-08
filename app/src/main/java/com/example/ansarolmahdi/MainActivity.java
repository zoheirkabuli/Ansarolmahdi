package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private RadioButton rbManager,rbStudent,rbParent,rbTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        checkListener();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Classes.class);
                startActivity(intent);
            }
        });


    }

    private void init(){
        login = findViewById(R.id.btn_login);
        rbManager = findViewById(R.id.rb_manager);
        rbParent = findViewById(R.id.rb_parent);
        rbStudent = findViewById(R.id.rb_student);
        rbTeacher = findViewById(R.id.rb_teacher);
    }

    public void handleChecked(String rb){
        switch (rb){
            case "manager":
                rbParent.setChecked(false);
                rbStudent.setChecked(false);
                break;
            case "teacher":
                rbStudent.setChecked(false);
                rbParent.setChecked(false);
                break;
            case "parent":
                rbManager.setChecked(false);
                rbTeacher.setChecked(false);
                break;
            case "student":
                rbTeacher.setChecked(false);
                rbManager.setChecked(false);
                break;
        }
    }

    public void checkListener(){
        rbManager.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleChecked("manager");
            }
        });

        rbTeacher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleChecked("teacher");
            }
        });

        rbStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleChecked("student");
            }
        });

        rbParent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleChecked("parent");
            }
        });
    }
}
