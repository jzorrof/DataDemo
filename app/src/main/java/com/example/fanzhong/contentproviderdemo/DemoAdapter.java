package com.example.fanzhong.contentproviderdemo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.LinkedList;

/**
 * Created by fanzhong on 16-7-12.
 */
public class DemoAdapter {
    private static final String LOG_TAG = "Fanzhong";

    private ContentResolver resolver = null;
    private int count;

    public DemoAdapter(Context context) {
        resolver = context.getContentResolver();
    }

    public long insertArticle(DemoData packageList) {
        ContentValues values = new ContentValues();
        values.put(DemoData.PACKAGE_NAME, packageList.getTitle());

        Uri uri = resolver.insert(DemoData.CONTENT_URI, values);
        String itemId = uri.getPathSegments().get(1);

        return Integer.valueOf(itemId).longValue();
    }

    public boolean updateArticle(DemoData packageList) {
        Uri uri = ContentUris.withAppendedId(DemoData.CONTENT_URI, packageList.getId());

        ContentValues values = new ContentValues();
        values.put(DemoData.PACKAGE_NAME, packageList.getTitle());

        int count = resolver.update(uri, values, null, null);

        return count > 0;
    }

    public boolean removeArticle(int id) {
        Uri uri = ContentUris.withAppendedId(DemoData.CONTENT_URI, id);

        int count = resolver.delete(uri, null, null);

        return count > 0;
    }

    public boolean removeArticle(int id, String packageName){
        Uri uri = ContentUris.withAppendedId(DemoData.CONTENT_URI, id);

        int count = resolver.delete(uri, null, null);

        return count > 0;
    }

    public LinkedList<DemoData> getAllArticles() {
        LinkedList<DemoData> articles = new LinkedList<DemoData>();

        String[] projection = new String[] {
                DemoData.ID,
                DemoData.PACKAGE_NAME,
        };

        Cursor cursor = resolver.query(DemoData.CONTENT_URI, projection, null, null, DemoData.DEFAULT_SORT_ORDER);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);

                DemoData article = new DemoData(id, title);
                articles.add(article);
            } while(cursor.moveToNext());
        }

        return articles;
    }

//    public int getArticleCount() {
//        int count = 0;
//
//        try {
//            IContentProvider provider = resolver.acquireProvider(Articles.CONTENT_URI);
//            Bundle bundle = provider.call(Articles.METHOD_GET_ITEM_COUNT, null, null);
//            count = bundle.getInt(Articles.KEY_ITEM_COUNT, 0);
//        } catch(RemoteException e) {
//            e.printStackTrace();
//        }
//
//        return count;
//    }

    public DemoData getArticleById(int id) {
        Uri uri = ContentUris.withAppendedId(DemoData.CONTENT_URI, id);

        String[] projection = new String[] {
                DemoData.ID,
                DemoData.PACKAGE_NAME,
        };

        Cursor cursor = resolver.query(uri, projection, null, null, DemoData.DEFAULT_SORT_ORDER);

        Log.i(LOG_TAG, "cursor.moveToFirst");

        if (!cursor.moveToFirst()) {
            return null;
        }

        String title = cursor.getString(1);

        return new DemoData(id, title);
    }

    public DemoData getArticleByPos(int pos) {
        Uri uri = ContentUris.withAppendedId(DemoData.CONTENT_POS_URI, pos);

        String[] projection = new String[] {
                DemoData.ID,
                DemoData.PACKAGE_NAME,
        };

        Cursor cursor = resolver.query(uri, projection, null, null, DemoData.DEFAULT_SORT_ORDER);
        if (!cursor.moveToFirst()) {
            return null;
        }

        int id = cursor.getInt(0);
        String title = cursor.getString(1);

        return new DemoData(id, title);
    }

    public int getCount(){
        return count;
    }
//
//    public PackageList2 getPackageListsbyName(String name){
//        PackageList2 pls2 = null;
//
//        String[] projection = new String []{
//                PackageL.ID,
//                PackageL.PACKAGE_NAME,
//        };
//        Cursor cursor = resolver.query(PackageL.CONTENT_TITLE_URI, projection, null, null, PackageL.DEFAULT_SORT_ORDER);
//        if(!cursor.moveToFirst()){
//            return null;
//        }
//        int id = cursor.getInt(0);
//        String tiltle = cursor.getString(1);
//
//        pls2 = new PackageList2(id ,tiltle);
//
//        return pls2;
//    }
}
