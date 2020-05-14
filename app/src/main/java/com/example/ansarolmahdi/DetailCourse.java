package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailCourse extends AppCompatActivity {
//      Views
    private Toolbar dcToolbar;
    private TextView mTime,mTimeClock,mCost,mCostNumber,mExamTime,mExamTimeClock;
    private Button mStudentsList;
    private RecyclerView mSessions;

//    fields

    private MyAdapter myAdapter;

//    server fields
    private Response.Listener<String> resListener;
    private Response.ErrorListener errorListener;
    private URLMaker urlCourseSessions;
    private Map<String,String> map;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);

        init();
        attemptSessions();
        postAction();

    }

    private void init() {
        mTime = findViewById(R.id.tv_time);
        mTimeClock = findViewById(R.id.tv_clock);
        mCost = findViewById(R.id.tv_tuition_text);
        mCostNumber = findViewById(R.id.tv_tuition);
        mExamTime = findViewById(R.id.tv_exam);
        mExamTimeClock = findViewById(R.id.tv_exam_time);

        urlCourseSessions = new URLMaker(getString(R.string.tetabyte),getString(R.string.ansar),
                getString(R.string.coursesessions),DetailCourse.this);
        map = new HashMap<>();
    }

    private void attemptSessions(){
        map.put("courseid",21+"");
        final VolleyHandle vh = new VolleyHandle(urlCourseSessions.getURL(),
                getString(R.string.request_tag_string),DetailCourse.this,
                resListener,errorListener,map);
        vh.sendRequest();

        Log.d("TAGTAG", "attemptSessions: " + urlCourseSessions.getURL());
    }

    private void postAction(){
        resListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAGTAG", "onResponse: " + response.toString());
            }
        };
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAGTAG", "onErrorResponse: " + error.getMessage());
            }
        };
    }
}
