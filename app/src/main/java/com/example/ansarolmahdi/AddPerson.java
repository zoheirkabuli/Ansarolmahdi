package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddPerson extends AppCompatActivity {

    private Response.Listener<String> resListener;
    private Response.ErrorListener errorListener;
    private URLMaker url;
    private Map<String,String> map;
    private JSONObject resJsonObject;
    private String res;
    private static int roleID;
    private ProgressDialog progressDialog;

    private RadioButton rbManager,rbParent,rbStudent,rbTeacher;
    private Toolbar apToolbar;
    private TextInputEditText fName,lName,mEmail,mPassword,cPassword,mPhone;
    private Button mAdd;
    private int mRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        init();

        toolbarDo();

        checkListener();

        postAction();

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();
            }
        });

    }

    private void init(){
        rbManager = findViewById(R.id.rb_ap_manager);
        rbParent = findViewById(R.id.rb_ap_parent);
        rbStudent = findViewById(R.id.rb_ap_student);
        rbTeacher = findViewById(R.id.rb_ap_teacher);
        apToolbar = findViewById(R.id.apToolbar);
        fName = findViewById(R.id.et_first_name);
        lName = findViewById(R.id.et_last_name);
        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_ap_password);
        cPassword = findViewById(R.id.et_confirm_password);
        mPhone = findViewById(R.id.et_phone);
        mAdd = findViewById(R.id.btn_add);

        progressDialog = new ProgressDialog(AddPerson.this);
        url = new URLMaker(getString(R.string.tetabyte),getString(R.string.ansar),
                getString(R.string.signup),this);
        map = new HashMap<>();
    }

    private void attemptSignUp() {

        if(fName.length() == 0 || lName.length() == 0 || mEmail.length() == 0 || mPassword.length() == 0
        || cPassword.length() == 0 || intRole() == 0){
            toast("تمامی فیلدها اجباری هستند");
        }else{
            if (mPassword.getText().toString().equals(cPassword.getText().toString())){

                progressDialog.show();
                String firstName = fName.getText().toString();
                String lastName = lName.getText().toString();
                String email = mEmail.getText().toString();
                String phone = mPhone.getText().toString();
                String password = mPassword.getText().toString();
                roleID = intRole();

                map.put("firstname",firstName);
                map.put("lastname",lastName);
                map.put("email",email);
                map.put("password",password);
                map.put("role",roleID+"");
                map.put("phone",phone);
                Log.d("TAGTAG",firstName+","+lastName+","+email+","+password+","+roleID+","+phone);

                final VolleyHandle vh = new VolleyHandle(url.getURL(),
                        getString(R.string.request_tag_string),AddPerson.this,
                        resListener,errorListener,map);
                vh.sendRequest();
            }else {

            }

        }
    }

    public void postAction(){
        resListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAGTAG",response.toString());
                try {
                    Log.d("responseAdd",response.toString());
                    progressDialog.dismiss();
                    resJsonObject = new JSONObject(response);
                    res = resJsonObject.getString("response");
                    Log.d("responseAdd",res);
                    if(res.equals(getString(R.string.yes))){
                        toast("شخص اضافه شد");

                    }else{
                        toast(getString(R.string.wrong_login));
                        res = resJsonObject.getString("msg");
                        Log.d("responseAdd",res);
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
                toast(getString(R.string.add_person_unsuccessful));
            }
        };
    }

    private void toast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
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


    private void toolbarDo(){

        setSupportActionBar(apToolbar);
        setTitle("اضافه کردن شخص");
        apToolbar.setTitleTextColor(Color.WHITE);
        apToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private int intRole(){
        if (rbManager.isChecked()){
            return 1;
        }else if (rbTeacher.isChecked()){
            return 2;
        }else if (rbParent.isChecked()){
            return 4;
        }else if (rbStudent.isChecked()){
            return 3;
        }

        return 0;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
