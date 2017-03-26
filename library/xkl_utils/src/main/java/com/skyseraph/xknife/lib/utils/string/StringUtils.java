package com.skyseraph.xknife.lib.utils.string;

import android.os.Build;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by SkySeraph on 2016/3/2.
 * 字符串操作工具类(部分依赖RegexUtils)
 */
public class StringUtils {

    private StringUtils() {
        throw new AssertionError();
    }

    /**
     * Is empty boolean.
     *
     * @param input the input
     * @return the boolean
     */
    public static boolean isEmpty(CharSequence input) {
        return (input == null || input.length() == 0);
    }

    /**
     * Is empty boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || "null".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param obj 对象
     * @return {@code true}: 为空<br>{@code false}: 不为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String && obj.toString().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is empty zero boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isEmptyZero(String str) {
        if (str == null || "".equals(str) || "null".equals(str) || "0".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Is empty no limited boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isEmptyNoLimited(String str) {
        if (str == null || "".equals(str) || "null".equals(str) || "不限".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 空白串包括由空格、制表符、回车符、换行符组成
     *
     * @param input the input
     * @return the boolean
     */
    public static boolean isEmptyEx(CharSequence input) {
        if (input == null || "".equals(input) || input.length() == 0)
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
     * Is equals boolean.
     *
     * @param actual   the actual
     * @param expected the expected
     * @return the boolean
     */
    public static boolean isEquals(String actual, String expected) {
        return (actual == expected) || (actual == null ? expected == null : actual.equals(expected));
    }

    /**
     * Is equals boolean.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public static boolean isEquals(CharSequence a, CharSequence b) {
        if (a == b) {
            return true;
        }
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Is equals ignore case boolean.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public static boolean isEqualsIgnoreCase(String a, String b) {
        return (a == b) || (b != null) && (a.length() == b.length()) && a.regionMatches(true, 0, b, 0, b.length());
    }

    /**
     * Is space boolean.
     *
     * @param s the s
     * @return the boolean
     */
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * Gets length.
     *
     * @param str the str
     * @return the length
     */
    public static int getLength(CharSequence str) {
        return str == null ? 0 : str.length();
    }

    /**
     * Replace blank space string.
     *
     * @param str the str
     * @return the string
     */
    public static String replaceBlankSpace(String str) {
        return RegexUtils.replaceBlankSpace(str);
    }

    /**
     * Upper first character string.
     *
     * @param s the s
     * @return the string
     */
    public static String upperFirstCharacter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) return s;
        char c = s.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? s : new StringBuilder(s.length())
                .append(Character.toUpperCase(c)).append(s.substring(1)).toString();

        // return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * Lower first character string.
     *
     * @param s the s
     * @return the string
     */
    public static String lowerFirstCharacter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * Null str to empty string.
     *
     * @param str the str
     * @return the string
     */
    public static String nullStrToEmpty(Object str) {
        return (str == null ? "" : (str instanceof String ? (String) str : str.toString()));
    }

    /**
     * Utf 8 encode string.
     *
     * @param str the str
     * @return the string
     */
    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * Utf 8 encode string.
     *
     * @param str          the str
     * @param defultReturn the defult return
     * @return the string
     */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }

    /**
     * byte[]数组转换为16进制的字符串。
     *
     * @param data 要转换的字节数组。
     * @return 转换后的结果 。
     */
    public static final String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }

    /**
     * 16进制表示的字符串转换为字节数组。
     *
     * @param s 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return d;
    }

    /**
     * To int int.
     *
     * @param str      the str
     * @param defValue the def value
     * @return the int
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * Remove duplicate character string.
     *
     * @param value the value
     * @return a String without character duplicated
     */
    public static String removeDuplicateCharacter(String value) {
        if (value == null) {
            throw new IllegalArgumentException("parameter cannot be null");
        }
        return removeDuplicate(value);
    }

    private static String removeDuplicate(String value) {
        CharSequence charValue = value;
        List<Character> valueRoute = new ArrayList<>();
        int positionIndex = 1;
        for (int i = positionIndex; i < value.length(); i++) {
            if (i >= positionIndex && charValue.charAt(i) != charValue.charAt(i - positionIndex)) {
                valueRoute.add(charValue.charAt(i));
            }
        }
        String result = value.substring(0, 1);
        for (Character character : valueRoute) {
            result += character.toString();
        }
        return result;
    }

    /**
     * 反转字符串
     *
     * @param s the s
     * @return the string
     */
    public static String reverse(String s) {
        int len = getLength(s);
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * To int int.
     *
     * @param obj the obj
     * @return the int
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * To long long.
     *
     * @param obj the obj
     * @return the long
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * To double double.
     *
     * @param obj the obj
     * @return the double
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception e) {
        }
        return 0D;
    }

    /**
     * To bool boolean.
     *
     * @param b the b
     * @return the boolean
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 转化为半角字符
     *
     * @param s the s
     * @return the string
     */
    public static String toHalfWidth(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s the s
     * @return the string
     */
    public static String toFullWidth(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * Html escape chars to string string.
     *
     * @param source the source
     * @return the string
     */
    public static String htmlEscapeCharsToString(String source) {
        return StringUtils.isEmpty(source) ? source : source.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
                .replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    /**
     * Gets href inner html.
     *
     * @param href the href
     * @return the href inner html
     */
    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        }
        return RegexUtils.delHtmlHref(href);
    }
}
