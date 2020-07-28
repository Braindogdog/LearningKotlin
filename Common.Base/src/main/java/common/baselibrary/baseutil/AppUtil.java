package common.baselibrary.baseutil;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * App 相关工具类
 */
public final class AppUtil {
    private static final String TAG = AppUtil.class.getSimpleName();
    /**
     * App 的运行状态
     */
    /* App 运行在前台 */
    public static final int APP_FORE = 1;
    /* App 运行在后台 */
    public static final int APP_BACK = 2;
    /* App 已经被杀死 */
    public static final int APP_DEAD = 3;

    private AppUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断 App 是否存活
     *
     * @param packageName 应用程序包名
     * @return {@code true}: 依然存活<br>{@code false}: 已被杀死
     */
    public static boolean isAppAlive(Context context, @NonNull String packageName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();
        for (int i = 0; i < processInfos.size(); i++) {
            if (processInfos.get(i).processName.equals(packageName)) {
//                //LogUtil.i(TAG, String.format("AppAliveInfo ========> App %s is running", packageName));
                return true;
            }
        }
//        //LogUtil.i(TAG, String.format("AppAliveInfo ========> App %s has been killed", packageName));
        return false;
    }

    /**
     * 获取 App 的状态
     *
     * @param packageName 应用程序包名
     * @return {@link #APP_FORE}: 运行在前台<br>{@link #APP_BACK}: 运行在后台<br>{@link #APP_DEAD}: 已被杀死
     */
    public int getAppStatus(Context context, @NonNull String packageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfos = am.getRunningTasks(20);
        // 判断 App 是否在栈顶
        if (taskInfos.get(0).topActivity.getPackageName().equals(packageName)) {
            //LogUtil.i(TAG, String.format("AppStatusInfo ========> App %s is running onForeground", packageName));
            return APP_FORE;
        } else {
            // 判断 App 是否在堆栈中
            for (ActivityManager.RunningTaskInfo info : taskInfos) {
                if (info.topActivity.getPackageName().equals(packageName)) {
                    //LogUtil.i(TAG, String.format("AppStatusInfo ========> App %s is running onBackground", packageName));
                    return APP_BACK;
                }
            }
            //LogUtil.i(TAG, String.format("AppStatusInfo ========> App %s has been killed", packageName));
            return APP_DEAD;
        }
    }

    /**
     * 判断 App 是否安装
     *
     * @param packageName 应用程序包名
     * @return {@code true}: 已安装<br>{@code false}: 未安装
     */
    public static boolean isAppInstalled(Context context, @NonNull String packageName) {
        PackageManager pm = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pInfos = pm.getInstalledPackages(0);
        // 用于存储所有已安装程序的包名
        List<String> pNames = new ArrayList<String>();
        if (pInfos != null) {
            for (int i = 0; i < pInfos.size(); i++) {
                String pn = pInfos.get(i).packageName;
                pNames.add(pn);
            }
        }
        return pNames.contains(packageName);
    }

    /**
     * 安装 App
     *
     * @param filePath  apk 文件的路径
     * @param authority 7.0 及以上安装需要传入清单文件中的{@code <provider>}的 authorities 属性，
     *                  即 AndroidManifest.xml 中的 FileProvider 的路径
     */
    public static void installApp(Context context, @NonNull String filePath, String authority) {
        installApp(context, new File(filePath), authority);
    }

    /**
     * 安装 App
     *
     * @param file      apk 文件
     * @param authority 7.0 及以上安装需要传入清单文件中的{@code <provider>}的 authorities 属性，
     *                  即 AndroidManifest.xml 中的 FileProvider 的路径
     */
    public static void installApp(Context context, File file, String authority) {
        if (file == null || !file.exists()) return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri;
        // 在 7.0 之前安装的时候,只需要通过隐式 Intent 来跳转,并且指定安装的文件 Uri 即可
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            uri = Uri.fromFile(file);
        } else {
            // 在 7.0 以上是不能直接通过 Uri 访问的
            // 参数1 : 上下文
            // 参数2 : Provider 主机地址，和清单文件中保持一致
            // 参数3 : apk 文件
            uri = FileProvider.getUriForFile(context, authority, file);
            // 对目标应用临时授权该 Uri 所代表的文件
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 静默安装 App
     * <p>此处的静默安装需要 Root 权限</p>
     *
     * @param filePath apk 文件路径
     * @return {@code true}: 安装成功<br>{@code false}: 安装失败
     */
    public static boolean installAppSilent(@NonNull String filePath) {
        boolean result = false;
        DataOutputStream dataOutputStream = null;
        BufferedReader errorStream = null;
        try {
            // 申请 su 权限（静默安装需要 root 权限）
            Process process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            // pm install 命令
            String command = "pm install -r " + filePath + "\n";
            dataOutputStream.write(command.getBytes(Charset.forName("utf-8")));
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            // 执行命令
            process.waitFor();
            errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String msg = "";
            String line;
            // 读取命令的执行结果
            while ((line = errorStream.readLine()) != null) {
                msg += line;
            }
            //LogUtil.d("", "install msg is " + msg);
            // 如果执行结果中包含 Failure 字样就认为是安装失败，否则就认为安装成功
            if (!msg.contains("Failure")) {
                result = true;
            }
        } catch (Exception e) {
            //LogUtil.e("InstallAppSilent", "InstallException ========> " + e.getMessage());
        } finally {
            try {
                if (dataOutputStream != null) dataOutputStream.close();
                if (errorStream != null) errorStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                //LogUtil.e("InstallAppSilent", "InstallException ========> " + e.getMessage());
            }
        }
        return result;
    }

    /**
     * 卸载 App
     *
     * @param packageName 应用程序包名
     */
    public static void uninstallApp(Context context, @NonNull String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 打开 App
     *
     * @param packageName 应用程序包名
     */
    public static void launchApp(Context context, @NonNull String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }

    /**
     * 获取 App 的签名信息
     *
     * @param packageName 应用程序包名
     * @return App 签名
     */
    public static Signature[] getAppSignature(Context context, @NonNull String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            @SuppressLint("PackageManagerGetSignatures")
            PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return pi == null ? null : pi.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取应用签名的的 SHA1 值
     *
     * @return SHA1
     */
    public static String getAppSignatureSHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i]).toUpperCase(Locale.US);
                if (appendString.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            String sha1 = result.substring(0, result.length() - 1);
            //LogUtil.i(TAG, String.format("AppSHA1 ========> SHA1 is %s", sha1));
            return sha1;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据包名跳转到系统自带的应用程序信息界面
     */
    public static void openAppSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

    /**
     * 获取 App 信息
     *
     * @return 当前应用的 AppInfo
     */
    public static AppInfo getAppInfo(Context context) {
        return getAppInfo(context, context.getPackageName());
    }

    /**
     * 获取 App 信息
     *
     * @param packageName 应用程序包名
     * @return 应用的 AppInfo
     */
    public static AppInfo getAppInfo(Context context, @NonNull String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return getAppInfo(pm, pi);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取所有已安装 App 的信息
     *
     * @return 所有已安装App的 AppInfo 列表
     */
    public static List<AppInfo> getAppsInfo(Context context) {
        List<AppInfo> list = new ArrayList<AppInfo>();
        PackageManager pm = context.getPackageManager();
        // 获取系统中安装的所有软件信息
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
        for (PackageInfo pi : installedPackages) {
            AppInfo ai = getAppInfo(pm, pi);
            if (ai != null) {
                list.add(ai);
            }
        }
        return list;
    }

    /**
     * 获取 App 信息
     *
     * @param pm 应用程序包的管理类
     * @param pi 应用程序包的信息类
     * @return AppInfo 类
     */
    private static AppInfo getAppInfo(PackageManager pm, PackageInfo pi) {
        if (pm == null || pi == null) {
            return null;
        }
        ApplicationInfo ai = pi.applicationInfo;
        String packageName = pi.packageName;
        String name = ai.loadLabel(pm).toString();
//        Drawable icon = ai.loadIcon(pm);
        String packagePath = ai.sourceDir;
        String versionName = pi.versionName;
        int versionCode = pi.versionCode;
        boolean isSystemApp = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != 0;
        return new AppInfo(name, null, packageName, packagePath, versionName, versionCode, isSystemApp);
    }

    /**
     * 获取 uid
     * <p>uid 是应用在安装时系统分配给应用的唯一标识，一个应用只有一个 uid，但是可以有多个 pid，
     * 在应用卸载重装后，系统重新给应用分配 uid</p>
     * <p>注：应用覆盖安装升级时，是不会改变 uid 的，在应用升级时，新应用会读取旧应用的 uid</p>
     *
     * @return uid
     */
    public static int getUid() {
        return android.os.Process.myUid();
    }

    /**
     * 根据应用包名获取 uid
     *
     * @param pkgName 应用程序包名
     * @return uid
     */
    public static int getUid(Context context, String pkgName) {
        try {
            PackageManager pm = context.getPackageManager();
            @SuppressLint("WrongConstant")
            ApplicationInfo ai = pm.getApplicationInfo(pkgName, PackageManager.GET_ACTIVITIES);
            return ai.uid;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取所有已安装应用的 uid
     *
     * @return uid 列表
     */
    public static List getUids(Context context) {
        List<Integer> uidList = new ArrayList<Integer>();
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packinfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES
                | PackageManager.GET_PERMISSIONS);
        for (PackageInfo info : packinfos) {
            String[] premissions = info.requestedPermissions;
            if (premissions != null && premissions.length > 0) {
                for (String premission : premissions) {
                    if ("android.permission.INTERNET".equals(premission)) {
                        int uid = info.applicationInfo.uid;
                        uidList.add(uid);
                    }
                }
            }
        }
        return uidList;
    }

    /**
     * 服务是否正在运行
     *
     * @param context
     * @param serviceName
     * @return
     */
    public static boolean isServiceRunning(Context context, String serviceName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(200);
        if (runningServiceInfos.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo serviceInfo : runningServiceInfos) {
            if (serviceInfo.service.getClassName().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * App 信息封装类
     */
    public final static class AppInfo {
        // App 的名称
        public String name;
        // App 的图标
        public Drawable icon;
        // App 的包名
        public String packageName;
        // App 的包路径
        public String packagePath;
        // App 的版本名
        public String versionName;
        // App 的版本号
        public int versionCode;
        // 是否系统应用
        public boolean isSystemApp;

        public AppInfo(String name, Drawable icon, String packageName, String packagePath,
                       String versionName, int versionCode, boolean isSystemApp) {
            this.name = name;
            this.icon = icon;
            this.packageName = packageName;
            this.packagePath = packagePath;
            this.versionName = versionName;
            this.versionCode = versionCode;
            this.isSystemApp = isSystemApp;
        }
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


}
