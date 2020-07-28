package common.networkrequestlibrary.download;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chasen on 2018/3/20.
 */

public class DownInfoDb extends SQLiteOpenHelper {

    private static final String DATEBASE_NAME = "library.db";
    private static final String TABLE_NAME = "downinfo";
    private static final int VERSION = 1;
    private SQLiteDatabase database;

    public DownInfoDb(Context context) {
        super(context, DATEBASE_NAME, null, VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        database = sqLiteDatabase;
        database.beginTransaction();
        try {
            database.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(_id integer NOT NULL PRIMARY KEY, _url varchar NOT NULL UNIQUE, " +
                    "_savePath varchar NOT NULL UNIQUE, _readLength long, _countLength long)");
            database.setTransactionSuccessful();
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            database = sqLiteDatabase;
            database.beginTransaction();
            try {
                database.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(_id integer NOT NULL PRIMARY KEY, _url varchar NOT NULL UNIQUE, " +
                        "_savePath varchar NOT NULL UNIQUE, _readLength long, _countLength long)");
                database.setTransactionSuccessful();
            } catch (final Exception e) {
                e.printStackTrace();
            } finally {
                database.endTransaction();
            }
        }
    }

    /**
     * 添加或更新一个DownInfo
     *
     * @param downInfo
     */
    public boolean addDownInfo(DownInfo downInfo) {
        if (null == downInfo)
            return false;
        try {
            ContentValues values = new ContentValues();
            values.put("_url", downInfo.getUrl());
            values.put("_savePath", downInfo.getSavePath());
            values.put("_readLength", downInfo.getReadLength());
            values.put("_countLength", downInfo.getCountLength());

            Cursor c = database.rawQuery("select * from " + TABLE_NAME + " where _url='" + downInfo.getUrl() + "'", null);
            if (c.moveToFirst()) {
                String[] args = {downInfo.getUrl()};
                database.update(TABLE_NAME, values, "_url=?", args);
            } else {
                database.insert(TABLE_NAME, null, values);
            }
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除一个DownInfo
     *
     * @param url
     */
    public boolean deleteDownInfo(String url) {
        String sql = "delete from " + TABLE_NAME + " where _url = '" + url + "'";
        try {
            database.execSQL(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除全部DownInfo
     */
    public boolean deleteAllDownInfo() {
        String sql = "delete from " + TABLE_NAME;
        try {
            database.execSQL(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取所有下载信息
     *
     * @return
     */
    public List<DownInfo> getAllDownInfo() {
        List<DownInfo> list = new ArrayList<DownInfo>();
        String sql = "select * from " + TABLE_NAME + " order by _id desc";
        try {
            Cursor cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                DownInfo downInfo = new DownInfo();
                downInfo.setUrl(cursor.getString(cursor.getColumnIndex("_url")));
                downInfo.setSavePath(cursor.getString(cursor.getColumnIndex("_savePath")));
                downInfo.setReadLength(cursor.getLong(cursor.getColumnIndex("_readLength")));
                downInfo.setCountLength(cursor.getLong(cursor.getColumnIndex("_countLength")));
                list.add(downInfo);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取DownInfo
     *
     * @param url
     * @return
     */
    public DownInfo getDownInfo(String url) {
        DownInfo downInfo = new DownInfo();
        String sql = "select * from " + TABLE_NAME + " where _url = '" + url + "'";
        try {
            Cursor cursor = database.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                downInfo.setUrl(cursor.getString(cursor.getColumnIndex("_url")));
                downInfo.setSavePath(cursor.getString(cursor.getColumnIndex("_savePath")));
                downInfo.setReadLength(cursor.getLong(cursor.getColumnIndex("_readLength")));
                downInfo.setCountLength(cursor.getLong(cursor.getColumnIndex("_countLength")));
                cursor.close();
            } else {
                cursor.close();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downInfo;
    }

    public void close() {
        database.close();
    }
}
