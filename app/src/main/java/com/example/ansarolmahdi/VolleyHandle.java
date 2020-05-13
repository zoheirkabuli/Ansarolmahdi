package com.example.ansarolmahdi;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by AmirHamzeh on 27/09/2017.
 */

public class VolleyHandle {
    private JSONArray mJsonArray;
    private JSONObject mJsonObject;
    private String url;
    private String request_tag;
    private Context mContext;
    private RequestQueue mQueue;
    private Response.Listener<String> resListenerStr;
    private Response.Listener<JSONObject> resListenerObj;
    private Response.Listener<JSONArray> resListenerArr;
    private Response.ErrorListener errListener;
    private Map<String,String> params;
    public VolleyHandle(JSONArray jArray, String url, String request_tag, Context mContext,
                        Response.Listener<JSONArray> resListener,
                        Response.ErrorListener errListener){
        this.mJsonArray=jArray;
        this.url=url;
        this.request_tag=request_tag;
        this.mContext=mContext;
        this.resListenerArr = resListener;
        this.errListener = errListener;
    }
    public VolleyHandle(JSONObject jObject, String url, String request_tag, Context mContext,
                        Response.Listener<JSONObject> resListener,
                        Response.ErrorListener errListener){
        this.mJsonObject=jObject;
        this.url=url;
        this.request_tag=request_tag;
        this.mContext=mContext;
        this.resListenerObj = resListener;
        this.errListener = errListener;
    }
    public VolleyHandle(String url, String request_tag, Context mContext,
                        Response.Listener<String> resListener,
                        Response.ErrorListener errListener, Map<String,String> params){
        this.url=url;
        this.request_tag=request_tag;
        this.mContext=mContext;
        this.resListenerStr = resListener;
        this.errListener = errListener;
        this.params = params;
    }
    public void stopQueue() {
        if (mQueue != null) {
            mQueue.cancelAll(request_tag);
        }
    }


    public void sendRequest()  {
        mQueue = CustomVolleyRequestQueue.getInstance(mContext.getApplicationContext())
                .getRequestQueue();
        if(request_tag.equals("OBJECT")){

            final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method
                    .POST, url,
                    mJsonObject, resListenerObj,errListener,params);
            jsonRequest.setTag(request_tag);
            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                    8000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            mQueue.add(jsonRequest);

        }else if(request_tag.equals("ARRAY")){
            final CustomJSONArrayRequest jsonRequest = new CustomJSONArrayRequest(Request.Method
                    .POST, url,
                    new JSONArray(), resListenerArr, errListener){
                @Override
                protected Map<String, String> getParams()  {
                    Map<String,String> param = params;
                    return param;
                }
            };
            jsonRequest.setTag(request_tag);
            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                    8000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            mQueue.add(jsonRequest);
        }else if(request_tag.equals("STRING")){
            StringRequest jsonRequest = new StringRequest(Request.Method.POST,url,resListenerStr,errListener){
                @Override
                protected Map<String, String> getParams()  {
                    Map<String,String> param = params;
                    return param;
                }
            };
            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                    8000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            mQueue.add(jsonRequest);
        }


    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
