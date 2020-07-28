package com.firebaselibrary;

import android.content.Context;

import com.firebaselibrary.constant.SPConstant;

import common.baselibrary.baseutil.AbstractSettingsManager;

/**
 * Created by chasen on 2018/4/9.
 * 利用操作SharedPreference的方法集合类
 */

public class SettingsManager extends AbstractSettingsManager {

    public static void setHaveInitNavigation(Context context, boolean haveInitNavigation) {
        setBoolean(context, SPConstant.HAVE_INIT_NAVIGATION, haveInitNavigation);
    }

    public static boolean isHaveInitNavigation(Context context) {
        return getBoolean(context, SPConstant.HAVE_INIT_NAVIGATION, false);
    }

    public static void setCityname(Context context, String cityName) {
        setString(context, SPConstant.CITY_NAME, cityName);
    }

    public static String getCityName(Context context) {
        return getString(context, SPConstant.CITY_NAME, "南昌");
    }

    public static void setDivision(Context context, String division) {
        setString(context, SPConstant.DIVISION, division);
    }

    public static String getDivision(Context context) {
        return getString(context, SPConstant.DIVISION, "1000");
    }

    public static void setDivisionId(Context context, String divisionId) {
        setString(context, SPConstant.DIVISIONID, divisionId);
    }

    public static String getDivisionId(Context context) {
        return getString(context, SPConstant.DIVISIONID, "120119000000");
    }

    public static void setAppId(Context context, String appId) {
        setString(context, SPConstant.APP_ID, appId);
    }

    public static String getAppId(Context context) {
        return getString(context, SPConstant.APP_ID, "");
    }

    public static final String KEY_CAMERA_ID = "cameraId";
    public static final int CAMERA_FACING_BACK = 0;
    public static final String KEY_VIDEO_BIT_RATE = "videoBitRate";
    public static final String KEY_RESOLUTION = "resolution";
    public static final String KEY_FRAME_RATE = "frameRate";
    public static final String KEY_GOP_LENGTH = "gopLength";
    public static final String KEY_VIDEO_ENCODE_METHOD = "videoEncodeMethod";
    public static final String KEY_STREAM_IP = "streamIp";
    public static final String KEY_RTMP_PORT = "rtmpPort";
    public static final String KEY_HTTP_PORT = "httpPort";
    public static final String KEY_IM_IP = "imIp";
    public static final String KEY_IM_PORT = "imPort";

    public static int getCameraId(Context context) {
        return Integer.parseInt(getString(context, KEY_CAMERA_ID, CAMERA_FACING_BACK + ""));

    }

    public static void setCameraId(Context context, int cameraId) {
        setString(context, KEY_CAMERA_ID, cameraId + "");
    }

    public static int getVideoRateBit(Context context) {
        return Integer.parseInt(getString(context, KEY_VIDEO_BIT_RATE, "400000"));

    }

    public static void setVideoBitRate(Context context, int bitRate) {
        setString(context, KEY_VIDEO_BIT_RATE, bitRate + "");
    }

    public static void setResolution(Context context, String resolution) {
        setString(context, KEY_RESOLUTION, resolution);
    }

    public static String getResolution(Context context) {
        return getString(context, KEY_RESOLUTION, "320X240");
    }

    public static int getFrameRate(Context context) {
        return Integer.parseInt(getString(context, KEY_FRAME_RATE, "8"));
    }

    public static int getGopLength(Context context) {
        return Integer.parseInt(getString(context, KEY_GOP_LENGTH, "10"));
    }

    public static int getVideoEncodeMethod(Context context) {
        return Integer.parseInt(getString(context, KEY_VIDEO_ENCODE_METHOD, "1"));
    }

    public static void setStreamIp(Context context, String ip) {
        setString(context, KEY_STREAM_IP, ip);
    }

    public static String getStreamIp(Context context) {
        return getString(context, KEY_STREAM_IP, "218.68.17.180");
    }

    public static void setRTMPPort(Context context, int port) {
        setString(context, KEY_RTMP_PORT, port + "");
    }

    public static int getRTMPPort(Context context) {
        return Integer.parseInt(getString(context, KEY_RTMP_PORT, "6065"));
    }

    public static void setHTTPPort(Context context, int port) {
        setString(context, KEY_HTTP_PORT, port + "");
    }

    public static int getHTTPPort(Context context) {
        return Integer.parseInt(getString(context, KEY_HTTP_PORT, "6064"));
    }

    public static void setIMIp(Context context, String ip) {
        setString(context, KEY_IM_IP, ip);
    }

    public static String getIMIp(Context context) {
        return getString(context, KEY_IM_IP, "120.206.255.18");
    }

    public static void setIMPort(Context context, int port) {
        setString(context, KEY_IM_PORT, port + "");
    }

    public static int getIMPort(Context context) {
        return Integer.parseInt(getString(context, KEY_IM_PORT, "6061"));
    }


    public static final String KEY_ROSTER_VERSION = "rosterVersion";
    public static final String KEY_USER_PHOTO = "user_photo";
    public static final String KEY_USER_POWER = "user_power";

    public static int getRosterVersion(Context context) {
        return Integer.parseInt(getString(context, KEY_ROSTER_VERSION, "0"));

    }

    public static void setRosterVersion(Context context, int rosterVersion) {
        setString(context, KEY_ROSTER_VERSION, rosterVersion + "");
    }

    public static void setUserPhoto(Context context, String photo) {
        setString(context, KEY_USER_PHOTO, photo);
    }

    public static String getUserPhoto(Context context) {

        return getString(context, KEY_USER_PHOTO, "");

    }

    public static void setUserPower(Context context, String power) {
        setString(context, KEY_USER_POWER, power);
    }

    public static String getUserPower(Context context) {

        return getString(context, KEY_USER_POWER, "");

    }

    //单位
    public static void setUnitId(Context context, String unitId) {
        setString(context, context.getString(R.string.unitId), unitId);
    }

    public static String getUnitId(Context context) {
        return getString(context, context.getString(R.string.unitId), context.getString(R.string.unit_top));
    }

    public static void setUnitTree(Context context, String unitTree) {
        setString(context, context.getString(R.string.settings_key_unit_tree), unitTree);
    }

    public static String getUnitTree(Context context) {
        return getString(context, context.getString(R.string.settings_key_unit_tree), context.getString(R.string.unit_top));
    }

    public static void setUnitName(Context context, String unitName) {
        setString(context, context.getString(R.string.unitName), unitName);
    }

    public static String getUnitName(Context context) {
        return getString(context, context.getString(R.string.unitName), "");
    }

    public static void setVirtualUnitId(Context context, String virtualUnitId) {
        setString(context, context.getString(R.string.settings_virtual_unit_id), virtualUnitId);
    }

    public static String getVirtualUnitId(Context context) {
        return getString(context, context.getString(R.string.settings_virtual_unit_id), "968df696-b81e-79b9-a186-6a988546a740");
    }

    public static void setVirtualUnitName(Context context, String virtualUnitName) {
        setString(context, context.getString(R.string.settings_virtual_unit_name), virtualUnitName);
    }

    public static String getVirtualUnitName(Context context) {
        return getString(context, context.getString(R.string.settings_virtual_unit_name), "");
    }

    //数据是否加载完毕
    public static void setDataLoaded(Context context, boolean loaded) {
        setBoolean(context, context.getString(R.string.settings_key_loaded), loaded);
    }

    public static boolean isDataLoaded(Context context) {
        return getBoolean(context, R.string.settings_key_loaded, false);
    }

    //是否登录
    public static void setHaveLogin(Context context, boolean logined) {
        setBoolean(context, context.getString(R.string.settings_have_login), logined);
    }

    public static boolean isHaveLogin(Context context) {
        return getBoolean(context, R.string.settings_have_login, false);
    }

    public static void setUserId(Context context, String userid) {
        setString(context, context.getString(R.string.userId), userid);
    }

    public static String getUserId(Context context) {
        return getString(context, context.getString(R.string.userId), "");
    }

    public static void setLoginId(Context context, String userid) {
        setString(context, context.getString(R.string.settings_login_id), userid);
    }

    public static String getLoginId(Context context) {
        return getString(context, context.getString(R.string.settings_login_id), "");
    }

    public static void setPassword(Context context, String userid) {
        setString(context, context.getString(R.string.settings_password), userid);
    }

    public static String getPassword(Context context) {
        return getString(context, context.getString(R.string.settings_password), "");
    }

    public static void setUserHeader(Context context, String userHeader) {
        setString(context, context.getString(R.string.settings_user_header), userHeader);
    }

    public static String getUserHeader(Context context) {
        return getString(context, context.getString(R.string.settings_user_header), "");
    }

    public static void setUserName(Context context, String userName) {
        setString(context, context.getString(R.string.userName), userName);
    }

    public static String getUserName(Context context) {
        return getString(context, context.getString(R.string.userName), "");
    }

    public static void setUserDuty(Context context, String userDuty) {
        setString(context, context.getString(R.string.settings_user_duty), userDuty);
    }

    public static String getUserDuty(Context context) {
        return getString(context, context.getString(R.string.settings_user_duty), "");
    }

    public static void setUserPhone(Context context, String userPhone) {
        setString(context, context.getString(R.string.settings_user_phone), userPhone);
    }

    public static String getUserPhone(Context context) {
        return getString(context, context.getString(R.string.settings_user_phone), "");
    }

    public static void setSex(Context context, String sex) {
        setString(context, context.getString(R.string.settings_user_sex), sex);
    }

    public static String getSex(Context context) {
        return getString(context, context.getString(R.string.settings_user_sex), "");
    }

    public static void setUserTele(Context context, String userTele) {
        setString(context, context.getString(R.string.settings_user_tele), userTele);
    }

    public static String getUserTele(Context context) {
        return getString(context, context.getString(R.string.settings_user_tele), "");
    }

    public static void setUpdateTime(Context context, long time) {
        setLong(context, context.getString(R.string.settings_update_time), time);
    }

    public static long getUpdateTime(Context context) {
        return getLong(context, context.getString(R.string.settings_update_time), 0L);
    }

    public static void setNeedRefresh(Context context, boolean needRefredh) {
        setBoolean(context, context.getString(R.string.settings_need_refresh), needRefredh);
    }

    public static boolean isNeedRefresh(Context context) {
        return getBoolean(context, R.string.settings_need_refresh, false);
    }

    //单位
    public static void setDataVersion(Context context, int version) {
        setInt(context, context.getString(R.string.settings_key_data_version), version);
    }

    public static int getDataVersion(Context context) {
        return getInt(context, context.getString(R.string.settings_key_data_version), 0);
    }

    //单位
    public static void setDataVersionRemote(Context context, int version) {
        setInt(context, context.getString(R.string.settings_key_data_version_remote), version);
    }

    public static int getDataVersionRemote(Context context) {
        return getInt(context, context.getString(R.string.settings_key_data_version_remote), 0);
    }

    public static void setFunction(Context context, String function) {
        setString(context, context.getString(R.string.settings_key_function), function);
    }

    public static String getFunction(Context context) {
        return getString(context, context.getString(R.string.settings_key_function), "");
    }

    public static void haveNewPhone(Context context, boolean haveNewPhone) {
        setBoolean(context, context.getString(R.string.settings_haveNewPhone), haveNewPhone);
    }

    public static boolean isHaveNewPhone(Context context) {
        return getBoolean(context, R.string.settings_haveNewPhone, true);
    }

    public static void setNeedShowRed(Context context, boolean needShowRed) {
        setBoolean(context, context.getString(R.string.settings_needShowRed), needShowRed);
    }

    public static boolean isNeedShowRed(Context context) {
        return getBoolean(context, R.string.settings_needShowRed, true);
    }

    /**
     * 最后一次获取token 时间戳
     *
     */
    public static void setLastTokenTime(Context context, long lastTokenTime) {
        setLong(context, context.getString(R.string.settings_lasttokentime), lastTokenTime);
    }

    public static long getLastTokenTime(Context context) {
        return getLong(context, context.getString(R.string.settings_lasttokentime), 0L);
    }

    public static void setToken(Context context, String tokeb) {
        setString(context, context.getString(R.string.token), tokeb);
    }

    public static String getToken(Context context) {
        return getString(context, context.getString(R.string.token), "");
    }

    /**
     * 在公司的“配置管理中心”中获取到的数据
     */
    public static void setSystemConfig(Context context, String tokeb) {
        setString(context, context.getString(R.string.token), tokeb);
    }

    public static String getSystemConfig(Context context) {
        return getString(context, context.getString(R.string.token), "");
    }

    public static void setDHToken(Context context, String token) {
        setString(context,context.getString(R.string.settings_dhtoken),token);
    }
    public static String getDHToken(Context context) {
        return getString(context,context.getString(R.string.settings_dhtoken),"");
    }
}
