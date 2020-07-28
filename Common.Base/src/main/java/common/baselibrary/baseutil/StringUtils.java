package common.baselibrary.baseutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    // private final static SimpleDateFormat dateFormater = new
    // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // private final static SimpleDateFormat dateFormater2 = new
    // SimpleDateFormat("yyyy-MM-dd");

    public static boolean isMobileNumber(String number) {
        Pattern p = Pattern.compile("^\\+?(86)?\\d{11}$");

        Matcher m = p.matcher(number);

        return m.matches();

    }

    public static String formatStringToHtml(String s) {
        if (s != null) {
            return s.replaceAll("\r\n", "<br/>").replaceAll("\n\r", "<br/>").replaceAll("\r", "<br/>").replaceAll("\n", "<br/>");
        } else {
            return "";
        }
    }


    public static String formatStringToPhone(String s) {
        if (s != null) {
            return s.replaceAll("\n\r", "\n").replaceAll("\r\n", "\n").replaceAll("\r", "\n").replaceAll("<br/>", "\n");
        } else {
            return "";
        }
    }


    public static String getMobileNumber(String number) {

        Pattern p = Pattern.compile("^\\+?(86)?(\\d{11})$");

        Matcher m = p.matcher(number);

        return m.find() ? m.group(2) : null;

    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return !(email == null || email.trim().length() == 0) && emailer.matcher(email).matches();
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception ignored) {

        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null) {
            return 0;
        }
        return toInt(obj.toString(), 0);
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception ignored) {

        }
        return 0;
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception ignored) {

        }
        return false;
    }

    /**
     * 将一个InputStream流转换成字符串
     *
     * @param is
     * @return
     */
    public static String toConvertString(InputStream is) throws IOException {
        StringBuffer res = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader read = new BufferedReader(isr);

        String line;
        line = read.readLine();
        while (line != null) {
            res.append(line);
            line = read.readLine();
        }

        if (null != isr) {
            isr.close();
        }
        if (null != read) {
            read.close();
            read = null;
        }
        if (null != is) {
            is.close();
            is = null;
        }

        return res.toString();
    }

    public static String join(String separator, List<String> values, boolean exceptNull) {

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < values.size(); i++) {
            if (isEmpty(values.get(i)) && exceptNull) {

                continue;
            }
            buf.append(values.get(i));

            buf.append(separator);
        }

        return buf.length() > 0 ? buf.substring(0, buf.length() - 1) : "";
    }

    public static String join(String separator, List<String> values) {

        return join(separator, values, false);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }


}
