package com.example.ansarolmahdi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Response.Listener<String> resListener;
    private Response.ErrorListener errorListener;
    private URLMaker url;
    private Map<String,String> map;
    private JSONObject resJsonObject;
    private String res;
    private static int roleID;


    private ProgressDialog progressDialog;
    private Button login;
    private RadioButton rbManager,rbStudent,rbParent,rbTeacher;
    private TextInputEditText et_email,et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        checkListener();
        postAction();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });




    }

    private void init(){
        login = findViewById(R.id.btn_login);
        rbManager = findViewById(R.id.rb_manager);
        rbParent = findViewById(R.id.rb_parent);
        rbStudent = findViewById(R.id.rb_student);
        rbTeacher = findViewById(R.id.rb_teacher);
        et_email = findViewById(R.id.et_username);
        et_pass = findViewById(R.id.et_password);
        progressDialog = new ProgressDialog(MainActivity.this);
        url = new URLMaker(getString(R.string.tetabyte),getString(R.string.ansar),
                getString(R.string.login),this);
        map = new HashMap<>();
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
            roleID = 1;
        }else if(btn.getId() == rbTeacher.getId()){
            rbManager.setChecked(false);
            rbTeacher.setChecked(true);
            rbStudent.setChecked(false);
            rbParent.setChecked(false);
            roleID = 2;
        }else if(btn.getId() == rbStudent.getId()){
            rbManager.setChecked(false);
            rbTeacher.setChecked(false);
            rbStudent.setChecked(true);
            rbParent.setChecked(false);
            roleID = 3;
        }else{
            rbManager.setChecked(false);
            rbTeacher.setChecked(false);
            rbStudent.setChecked(false);
            rbParent.setChecked(true);
            roleID = 4;
        }

    }

    private void attemptLogin() {

        if(et_email.length()==0 || et_pass.length()==0){
            toaster(getString(R.string.user_pass_necessary));
        }else{
            progressDialog.show();
            String useremail=et_email.getText().toString();
            String password=et_pass.getText().toString();
            map.put("email",useremail);
            map.put("password",password);
            map.put("role",roleID+"");
            final VolleyHandle vh = new VolleyHandle(url.getURL(),
                    getString(R.string.request_tag_string),MainActivity.this,
                    resListener,errorListener,map);
            vh.sendRequest();
        }
    }
    public void postAction(){
        resListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("response",response.toString());
                    progressDialog.dismiss();
                    resJsonObject = new JSONObject(response);
                    res = resJsonObject.getString("response");
                    Log.d("response",res);
                    if(res.equals(getString(R.string.yes))){
                        login();
                    }else{
                        toaster(getString(R.string.wrong_login));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response",error.getMessage());
            }
        };
    }
    private void login(){
        Intent intent = new Intent(MainActivity.this,Courses.class);
        startActivity(intent);
        finish();
    }
    public void toaster(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
