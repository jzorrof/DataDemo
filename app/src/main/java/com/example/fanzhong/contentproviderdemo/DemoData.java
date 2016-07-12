package com.example.fanzhong.contentproviderdemo;

import android.net.Uri;

/**
 * Created by fanzhong on 16-7-12.
 */
public class DemoData {
    private int id;
    private String title;

    /*Data Field*/
    public static final String ID = "_id";
    public static final String PACKAGE_NAME = "_title";
    public static final String AUTHORITY = "com.sunmi.provider.packagelist";


    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/item");
    public static final Uri CONTENT_POS_URI = Uri.parse("content://" + AUTHORITY + "/pos");
    public static final Uri CONTENT_TITLE_URI = Uri.parse("content://" + AUTHORITY + "/title");
    /*Default sort order*/
    public static final String DEFAULT_SORT_ORDER = "_id asc";

    public DemoData(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
