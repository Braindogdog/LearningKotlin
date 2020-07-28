package common.baselibrary.baseutil;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by zhanhongfei on 15/8/9.
 */
public abstract class AbstractSettingsManager {


    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String getString(Context context, String key, String def) {
        return getSharedPreferences(context).getString(key, def);
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static long getLong(Context context, String key, long def) {
        return getSharedPreferences(context).getLong(key, def);
    }

    public static void setLong(Context context, String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static double getDouble(Context context, String key, double def) {
        return Double.parseDouble(getSharedPreferences(context).getString(key, def + ""));
    }

    public static void setDouble(Context context, String key, double value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, value + "");
        editor.commit();
    }


    public static int getInt(Context context, int keyId, int defId) {

        return getInt(context, context.getString(keyId), context.getResources().getInteger(defId));

    }

    public static void setInt(Context context, int keyId, int value) {
        setInt(context, context.getString(keyId), value);
    }

    public static long getLong(Context context, int keyId, int defId) {

        return getLong(context, context.getString(keyId), context.getResources().getInteger(defId));

    }

    public static void setLong(Context context, int keyId, Long value) {
        setLong(context, context.getString(keyId), value);
    }

    public static double getDouble(Context context, int keyId, int defId) {


        return getDouble(context, context.getString(keyId), Double.parseDouble(context.getString(defId)));

    }

    public static void setDouble(Context context, int keyId, Double value) {
        setDouble(context, context.getString(keyId), value);
    }

    public static String getString(Context context, int keyId, int defId) {

        return getString(context, context.getString(keyId), context.getString(defId));
    }

    public static String getString(Context context, int keyId, String defValue) {

        return getString(context, context.getString(keyId), defValue);
    }

    public static void setString(Context context, int keyId, String value) {

        setString(context, context.getString(keyId), value);
    }


    public static boolean getBoolean(Context context, int keyId, int defId) {

        return getBoolean(context, context.getString(keyId), context.getResources().getBoolean(defId));
    }

    public static void setBoolean(Context context, int keyId, boolean value) {

        setBoolean(context, context.getString(keyId), value);
    }

    public static int getInt(Context context, String key, int def) {
        return getSharedPreferences(context).getInt(key, def);
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean def) {
        return getSharedPreferences(context).getBoolean(key, def);
    }

    public static boolean getBoolean(Context context, int keyId, boolean def) {
        return getSharedPreferences(context).getBoolean(context.getString(keyId), def);
    }

    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static float getFloat(Context context, String key, float def) {
        return getSharedPreferences(context).getFloat(key, def);
    }

    public static void setFloat(Context context, String key, float value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putFloat(key, value);
        editor.commit();
    }


}
