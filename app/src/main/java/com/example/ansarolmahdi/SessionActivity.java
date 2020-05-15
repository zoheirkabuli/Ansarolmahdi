package com.example.ansarolmahdi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.ansarolmahdi.classes.HomeWork;
import com.example.ansarolmahdi.classes.MyDate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.ansarolmahdi.AddCourse.DATE_FORMAT;
import static com.example.ansarolmahdi.Courses.KEY_COURSE;
import static com.example.ansarolmahdi.DetailCourse.KEY_SESSION;
import static com.example.ansarolmahdi.DetailCourse.KEY_SESSIONNUM;

public class SessionActivity extends AppCompatActivity {

//    server

    private Response.Listener<String> resListener;
    private Response.ErrorListener errorListener;
    private URLMaker url,urlAddHome;
    private Map<String,String> map,mapAddHome;
    private JSONObject resJsonObject;
    private String res;

//    variables

    private TextView homeWorkTitle,desc,deadlineDate;
    private Button addHome;
    private Toolbar seToolbar;
    private Intent intent;
    private String deadline;
    private HomeWork homeWork;


//    intent
    private int sessionID;
    private int courseID;
    private String sessionNum;
    private ProgressDialog progressDialog;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private MyDate deadlineMyDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        init();
        intentGetter();

        toolbarOption();
        mSetText();
        deadlineDatePicker();
        descSetText();
        postActionGetHome();
        attemptGetHomework();

        postActionAddHome();

        addHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptAddHome();
            }
        });


    }

    private void descSetText() {
        desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (desc.getText().toString().equals("ندارد") || deadlineDate.getText().toString().equals("ندارد")){
                    final EditText descInput = new EditText(SessionActivity.this);
                    new AlertDialog.Builder(SessionActivity.this)
                            .setTitle("توضیحات")
                            .setView(descInput)
                            .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    desc.setText(descInput.getText().toString());
                                }
                            })
                            .setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .create()
                            .show();
                }else {

                }
            }
        });
    }

    private void mSetText() {
        homeWorkTitle.setText("تکلیف " + sessionNum);
    }

    private void init() {
        addHome = findViewById(R.id.btn_addhome);
        desc = findViewById(R.id.tv_desc);
        deadlineDate = findViewById(R.id.tv_deadline_date);
        seToolbar = findViewById(R.id.seToolbar);
        homeWorkTitle = findViewById(R.id.tv_homeworktitle);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.wait));

        url = new URLMaker(getString(R.string.tetabyte),getString(R.string.ansar),
                getString(R.string.homework),this);
        map = new HashMap<>();

        urlAddHome = new URLMaker(getString(R.string.tetabyte),getString(R.string.ansar),
                getString(R.string.addhomework),this);

        mapAddHome = new HashMap<>();
    }

    private void intentGetter(){
        intent = getIntent();
        sessionNum = intent.getStringExtra(KEY_SESSIONNUM);
        sessionID = intent.getIntExtra(KEY_SESSION,0);
        courseID = intent.getIntExtra(KEY_COURSE,0);
        Log.d("TAGTAG", "onItemClick: " + sessionID);
    }

    private void toolbarOption() {
        setSupportActionBar(seToolbar);
        setTitle(sessionNum);
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

    private void attemptGetHomework() {
        progressDialog.show();
        map.put("sessionid",sessionID+"");
        final VolleyHandle vh = new VolleyHandle(url.getURL(),
                getString(R.string.request_tag_string),SessionActivity.this,
                resListener,errorListener,map);
        vh.sendRequest();
    }

    public void postActionGetHome(){
        resListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {


                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("homework").equals("no")){
                        toast("تکلیفی موجود نیست");
                    }else {
                        addHome.setVisibility(View.GONE);
                        JSONObject ja = obj.getJSONArray("homework").getJSONObject(0);

                        homeWork = new HomeWork();
                        homeWork.setDeadline(ja.getString("deadline"));
                        homeWork.setText(ja.getString("description"));
                        homeWork.setSessionID(ja.getInt("sessionid"));
                        homeWork.setHomeworkID(ja.getInt("homeworkid"));

                        afterResSet(homeWork.getDeadline(),homeWork.getText());
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

    private void attemptAddHome() {

        if(deadlineDate.getText().toString().length() == 0 || deadlineDate.getText().toString().equals("ندارد")
                || desc.getText().toString().length() == 0 || desc.getText().toString().equals("ندارد")
                ){
            toast("تاریخ و توضیحات را وارد کنید");
        }else{


                progressDialog.show();
                String mDesc = desc.getText().toString();
                String mDeadline = deadlineDate.getText().toString();


                map.put("description",mDesc);
                map.put("deadline",mDeadline);
                map.put("sessionid",sessionID+"");


                final VolleyHandle vh = new VolleyHandle(urlAddHome.getURL(),
                        getString(R.string.request_tag_string),SessionActivity.this,
                        resListener,errorListener,map);
                vh.sendRequest();

        }
    }

    public void postActionAddHome(){
        resListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("TAGTAG",response.toString());
                    progressDialog.dismiss();
                    resJsonObject = new JSONObject(response);
                    res = resJsonObject.getString("response");
                    Log.d("TAGTAG",res);
                    if(res.equals(getString(R.string.yes))){
                        toast("تکلیف ذخیره شد");
                        addHome.setVisibility(View.GONE);

                    }else{
                        toast(getString(R.string.wrong_addhome));
                        res = resJsonObject.getString("msg");
                        Log.d("TAGTAG",res);
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

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void afterResSet(String dead,String txt){
        deadlineDate.setText(dead);
        desc.setText(txt);
    }

    private void deadlineDatePicker() {

        deadlineDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (desc.getText().toString().equals("ندارد") || deadlineDate.getText().toString().equals("ندارد")){

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(SessionActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    deadlineMyDate = new MyDate();
                                    deadlineMyDate.setYear(year);
                                    deadlineMyDate.setMonth(monthOfYear);
                                    deadlineMyDate.setDate(dayOfMonth);

                                    deadlineDate.setText(DATE_FORMAT.format(deadlineMyDate));

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();


                }else {

                }



            }
        });
    }

    private String changeStyleTime(String time) {
        return String.valueOf(new char[]{ time.charAt(0) , time.charAt(1) , time.charAt(2) , time.charAt(3) ,time.charAt(4)});
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
