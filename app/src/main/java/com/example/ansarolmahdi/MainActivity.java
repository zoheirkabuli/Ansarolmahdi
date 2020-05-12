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

                finish();
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



    public void checkListener(){
        rbManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCheck(rbManager);
            }
        });

        rbTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCheck(rbTeacher);
            }
        });

        rbStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCheck(rbStudent);
            }
        });

        rbParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCheck(rbParent);
            }
        });
    }

    public void updateCheck(Button btn){
        if(btn.getId() == rbManager.getId()){
            rbManager.setChecked(true);
            rbTeacher.setChecked(false);
            rbStudent.setChecked(false);
            rbParent.setChecked(false);
        }else if(btn.getId() == rbTeacher.getId()){
            rbManager.setChecked(false);
            rbTeacher.setChecked(true);
            rbStudent.setChecked(false);
            rbParent.setChecked(false);
        }else if(btn.getId() == rbStudent.getId()){
            rbManager.setChecked(false);
            rbTeacher.setChecked(false);
            rbStudent.setChecked(true);
            rbParent.setChecked(false);
        }else{
            rbManager.setChecked(false);
            rbTeacher.setChecked(false);
            rbStudent.setChecked(false);
            rbParent.setChecked(true);
        }

    }
}
