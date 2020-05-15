package com.example.ansarolmahdi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.ansarolmahdi.classes.Student;
import com.github.clans.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.ansarolmahdi.Courses.KEY_COURSE;

public class CourseStudent extends AppCompatActivity implements MyAdapter.OnItemListener {

    //    server
    private Response.Listener<String> resListener;
    private Response.ErrorListener errorListener;
    private URLMaker urlStudents, urlAllStudents;
    private Map<String, String> map, allMap;
    private ProgressDialog progressDialog;

    //    variables
    private Toolbar csToolbar;
    private FloatingActionButton fabAdd;
    private ArrayList<String> studentsName, allStudentsName;
    private ArrayList<Student> students, allStudents;
    private Bundle intent;
    private int courseID;
    private RecyclerView rvStudents;
    private MyAdapter myAdapter;
    private Dialog di;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_student);

        init();

        getFromIntent();

        toolbarDo();

        postActionAllStudents();

        attemptAllStudents();

        fabRun();

    }

    private void fabRun() {

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAGTAG", "onClick: " + allStudentsName.size());
                new AlertDialog.Builder(CourseStudent.this)
                        .setItems(listToArray(allStudentsName), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                Toast.makeText(CourseStudent.this, listToArray(allStudentsName)[which], Toast.LENGTH_LONG).show();
                                studentsName.add(listToArray(allStudentsName)[which]);
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .create()
                        .show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        postActionStudents();
        attemptStudents();

    }

    private void getFromIntent() {
        intent = getIntent().getExtras();
        courseID = intent.getInt("COURSEID");
    }

    private void init() {
        fabAdd = findViewById(R.id.fab_add);
        rvStudents = findViewById(R.id.rv_course_student);
        csToolbar = findViewById(R.id.csToolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.wait));
        urlStudents = new URLMaker(getString(R.string.tetabyte), getString(R.string.ansar),
                getString(R.string.coursestudent), CourseStudent.this);
        map = new HashMap<>();

        urlAllStudents = new URLMaker(getString(R.string.tetabyte), getString(R.string.ansar),
                getString(R.string.allstudents), CourseStudent.this);
        allMap = new HashMap<>();
    }

    public static String[] listToArray(ArrayList<String> arrayList) {
        String[] strings = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {

            strings[i] = arrayList.get(i);

        }
        return strings;
    }

    private void toolbarDo() {

        setSupportActionBar(csToolbar);
        setTitle(intent.getString(KEY_COURSE));
        csToolbar.setTitleTextColor(Color.WHITE);
        csToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void postActionStudents() {
        resListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    studentsName = new ArrayList<>();
                    students = new ArrayList<>();
                    JSONObject resJsonObject = new JSONObject(response);
                    JSONArray ja = resJsonObject.getJSONArray("students");
                    for (int i = 0; i < ja.length(); i++) {
                        Student student = new Student();
                        JSONObject temp = ja.getJSONObject(i);
                        student.setStudentID(temp.getInt("studentid"));
                        student.setfName(temp.getString("firstname"));
                        student.setlName(temp.getString("lastname"));
                        student.seteMail(temp.getString("email"));
                        student.setPassword(temp.getString("password"));
                        student.setPersonID(temp.getInt("personid"));
                        studentsName.add(student.getfName() + " " + student.getlName());
                        students.add(student);
                    }

                    runAdapter();
//                    fabRun();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response", error.getMessage());
            }
        };
    }

    private void runAdapter() {
        myAdapter = new MyAdapter(CourseStudent.this, studentsName, this);
        rvStudents.setLayoutManager(new LinearLayoutManager(CourseStudent.this));
        rvStudents.setAdapter(myAdapter);
    }

    private void attemptStudents() {
        progressDialog.show();
        map.put("courseid", courseID + "");
        final VolleyHandle vh = new VolleyHandle(urlStudents.getURL(),
                getString(R.string.request_tag_string), CourseStudent.this,
                resListener, errorListener, map);
        vh.sendRequest();
    }

    private void attemptAllStudents() {
        final VolleyHandle vh = new VolleyHandle(urlAllStudents.getURL(),
                getString(R.string.request_tag_string), CourseStudent.this,
                resListener, errorListener, map);
        vh.sendRequest();
    }

    private void postActionAllStudents() {
        resListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAGTAG", "onResponse: all :" + response);
                try {
                    allStudentsName = new ArrayList<>();
                    allStudents = new ArrayList<>();
                    JSONObject resJsonObject = new JSONObject(response);
                    JSONArray ja = resJsonObject.getJSONArray("students");
                    for (int i = 0; i < ja.length(); i++) {
                        Student student = new Student();
                        JSONObject temp = ja.getJSONObject(i);
                        student.setStudentID(temp.getInt("studentid"));
                        student.setfName(temp.getString("firstname"));
                        student.setlName(temp.getString("lastname"));
                        student.seteMail(temp.getString("email"));
                        student.setPassword(temp.getString("password"));
//                        student.setPersonID(temp.getInt("personid"));
                        allStudentsName.add(student.getfName() + " " + student.getlName());
                        allStudents.add(student);
                        Log.d("TAGTAG", "onResponse: size : " + allStudentsName.size());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response", error.getMessage());
            }
        };
    }

    private void dialogSureSecond(final Student student) {

        di = new Dialog(CourseStudent.this, R.style.dialog_theme);
        di.requestWindowFeature(Window.FEATURE_NO_TITLE);
        di.setContentView(R.layout.dialog_sure_second);
        di.getWindow().getAttributes().windowAnimations = R.anim.abc_fade_out;
        di.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        di.setCanceledOnTouchOutside(false);
        di.setCancelable(false);
        di.show();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        final int width = point.x;
        di.getWindow().setLayout(((90 * width) / 100), ((90 * width) / 100));

        Button close = di.findViewById(R.id.btn_attend_yes);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                di.dismiss();
            }
        });

        TextView tvNameDialog = di.findViewById(R.id.tv_name_dialog);
        tvNameDialog.setText(student.getfName() + " " + student.getlName());

        final TextView tvEmailDialog = di.findViewById(R.id.tv_email_dialog);
        tvEmailDialog.setText(student.geteMail());
        tvEmailDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", tvEmailDialog.getText().toString(), null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

        final TextView tvPhoneDialog = di.findViewById(R.id.tv_phone_dialog);
//        tvPhoneDialog.setText(student.getPhone());
        tvPhoneDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPermissionGranted()) {
                    callAction(tvPhoneDialog.getText().toString());
                }
            }
        });

    }

    private boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }

    private void callAction(String toString) {

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + toString));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), getString(R.string.permissiongranted), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.permissiondenied), Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onItemClick(int position) {
        dialogSureSecond(students.get(position));
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}

