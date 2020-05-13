package com.example.ansarolmahdi;

import android.content.Context;

/**
 * Created by AmirHamzeh on 10/10/2017.
 */

public class URLMaker {
    private String mainDomain,subDir="",fileName;
    private Context ctx;

    public URLMaker(String mainDomain, String fileName, Context ctx){
        this.mainDomain = mainDomain;
        this.fileName = fileName;
        this.ctx = ctx;
    }
    public URLMaker(String mainDomain, String subDir, String fileName, Context ctx){
        this.mainDomain = mainDomain;
        this.subDir = subDir;
        this.fileName = fileName;
        this.ctx = ctx;
    }

    public String getURL(){

        if(subDir.equals("")){
            return ctx.getString(R.string.url_first)+
                    mainDomain+ctx.getString(R.string.slash)
                    +fileName+ctx.getString(R.string.php_format);
        }else{
            return ctx.getString(R.string.url_first)+
                    mainDomain+ctx.getString(R.string.slash)+subDir+
                    ctx.getString(R.string.slash)+
                    fileName+ctx.getString(R.string.php_format);
        }
    }
}
