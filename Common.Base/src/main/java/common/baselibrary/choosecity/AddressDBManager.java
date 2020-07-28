package common.baselibrary.choosecity;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import common.baselibrary.baseutil.AssetUtil;

/**
 * Created by chasen on 2018/3/23.
 */

public class AddressDBManager {

    private static final String DATABASE_NAME = "address.db";
    private static final String TABLE_NAME_CITY = "city";
    private static final String TABLE_NAME_AREA = "area";
    private static final String TABLE_NAME_PROVINCE = "province";
    private Context mContext;

    public AddressDBManager(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 把assets下的数据库拷贝到内存卡上并获取数据库
     *
     * @return
     */
    public SQLiteDatabase getAddressDatabase() {
        File dbFile = new File(mContext.getExternalFilesDir("db"), DATABASE_NAME);

        if (!dbFile.exists()) {
            try {
                AssetUtil.copyAssetFile(mContext, DATABASE_NAME, dbFile);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        return SQLiteDatabase.openOrCreateDatabase(dbFile, null);
    }

    public ArrayList<ProvinceModel> getProvinces(SQLiteDatabase database) {
        ArrayList<ProvinceModel> list = new ArrayList<>();
        list.add(new ProvinceModel("---", 0));
        String sql = "select * from " + TABLE_NAME_PROVINCE;
        try {
            Cursor cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                ProvinceModel provinceModel = new ProvinceModel();
                provinceModel.setName(cursor.getString(cursor.getColumnIndex("_name")));
                provinceModel.setCode(cursor.getInt(cursor.getColumnIndex("_code")));
                list.add(provinceModel);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<CityModel> getCitys(SQLiteDatabase database, int provincesCode) {
        ArrayList<CityModel> list = new ArrayList<>();
        list.add(new CityModel("---", 0, 0));
        String sql = "select * from " + TABLE_NAME_CITY + " where _provinceCode = " + provincesCode;
        try {
            Cursor cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                CityModel cityModel = new CityModel();
                cityModel.setName(cursor.getString(cursor.getColumnIndex("_name")));
                cityModel.setCode(cursor.getInt(cursor.getColumnIndex("_code")));
                cityModel.setProvinceCode(cursor.getInt(cursor.getColumnIndex("_provinceCode")));
                list.add(cityModel);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<AreaModel> getAreas(SQLiteDatabase database, int cityCode) {
        ArrayList<AreaModel> list = new ArrayList<>();
        list.add(new AreaModel("---", 0, 0, 0));
        String sql = "select * from " + TABLE_NAME_AREA + " where _cityCode = " + cityCode;
        try {
            Cursor cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                AreaModel areaModel = new AreaModel();
                areaModel.setName(cursor.getString(cursor.getColumnIndex("_name")));
                areaModel.setCode(cursor.getInt(cursor.getColumnIndex("_code")));
                areaModel.setProvinceCode(cursor.getInt(cursor.getColumnIndex("_provinceCode")));
                areaModel.setCityCode(cursor.getInt(cursor.getColumnIndex("_cityCode")));
                list.add(areaModel);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
