package com.skyseraph.xknife.lib.module.log;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by SkySeraph on 2016/10/25.
 */

public class L {

    private enum Type {
        V, D, I, W, E, A, JSON
    }

    private static final int MAX_LENGTH = 4000;

    private static final String SUFFIX = ".java";
    private static final String TAG_DEFAULT = "L";
    public static final String NULL_TIPS = "Log with null object";
    private static final String PARAM = "Param";
    private static final String NULL = "null";
    private static final int STACK_TRACE_INDEX_5 = 5;
    private static final String DEFAULT_MESSAGE = "execute";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final int JSON_INDENT = 4;

    private static String mGlobalTag;
    private static boolean mIsGlobalTagEmpty = true;
    private static boolean IS_SHOW_LOG = true;

    public static void init(boolean isShowLog) {
        IS_SHOW_LOG = isShowLog;
    }

    public static void init(boolean isShowLog, @Nullable String tag) {
        IS_SHOW_LOG = isShowLog;
        mGlobalTag = tag;
        mIsGlobalTagEmpty = TextUtils.isEmpty(mGlobalTag);
    }

    public static void v() {
        printLog(Type.V, null, DEFAULT_MESSAGE);
    }

    public static void v(Object msg) {
        printLog(Type.V, null, msg);
    }

    public static void v(String tag, Object... objects) {
        printLog(Type.V, tag, objects);
    }

    public static void d() {
        printLog(Type.D, null, DEFAULT_MESSAGE);
    }

    public static void d(Object msg) {
        printLog(Type.D, null, msg);
    }

    public static void d(String tag, Object... objects) {
        printLog(Type.D, tag, objects);
    }

    public static void i() {
        printLog(Type.I, null, DEFAULT_MESSAGE);
    }

    public static void i(Object msg) {
        printLog(Type.I, null, msg);
    }

    public static void i(String tag, Object... objects) {
        printLog(Type.I, tag, objects);
    }

    public static void w() {
        printLog(Type.W, null, DEFAULT_MESSAGE);
    }

    public static void w(Object msg) {
        printLog(Type.W, null, msg);
    }

    public static void w(String tag, Object... objects) {
        printLog(Type.W, tag, objects);
    }

    public static void e() {
        printLog(Type.E, null, DEFAULT_MESSAGE);
    }

    public static void e(Object msg) {
        printLog(Type.E, null, msg);
    }

    public static void e(String tag, Object... objects) {
        printLog(Type.E, tag, objects);
    }

    public static void json(String jsonFormat) {
        printLog(Type.JSON, null, jsonFormat);
    }

    public static void json(String tag, String jsonFormat) {
        printLog(Type.JSON, tag, jsonFormat);
    }

    private static void printLog(Type type, String tagStr, Object... objects) {

        if (!IS_SHOW_LOG) {
            return;
        }

        String[] contents = wrapperContent(STACK_TRACE_INDEX_5, tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        switch (type) {
            case V:
            case D:
            case I:
            case W:
            case E:
            case A:
                printDefault(type, tag, headString + msg);
                break;
            case JSON:
                printJson(tag, msg, headString);
                break;
        }
    }

    private static String[] wrapperContent(int stackTraceIndex, String tagStr, Object... objects) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        StackTraceElement targetElement = stackTrace[stackTraceIndex];
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
        }

        if (className.contains("$")) {
            className = className.split("\\$")[0] + SUFFIX;
        }

        String methodName = targetElement.getMethodName();
        int lineNumber = targetElement.getLineNumber();

        if (lineNumber < 0) {
            lineNumber = 0;
        }

        String tag = (tagStr == null ? className : tagStr);

        if (mIsGlobalTagEmpty && TextUtils.isEmpty(tag)) {
            tag = TAG_DEFAULT;
        } else if (!mIsGlobalTagEmpty) {
            tag = mGlobalTag;
        }

        String msg = (objects == null) ? NULL_TIPS : getObjectsString(objects);
        String headString = "[ (" + className + ":" + lineNumber + ")#" + methodName + " ] ";

        return new String[]{tag, msg, headString};
    }

    private static String getObjectsString(Object... objects) {

        if (objects.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object == null) {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(NULL).append("\n");
                } else {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
                }
            }
            return stringBuilder.toString();
        } else {
            Object object = objects[0];
            return object == null ? NULL : object.toString();
        }
    }

    public static void printJson(String tag, String msg, String headString) {
        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        printLine(tag, true);
        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        printLine(tag, false);
    }

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            Log.d(tag, "╚-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    private static void printDefault(Type type, String tag, String msg) {

        int index = 0;
        int length = msg.length();
        int countOfSub = length / MAX_LENGTH;

        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + MAX_LENGTH);
                printSub(type, tag, sub);
                index += MAX_LENGTH;
            }
            printSub(type, tag, msg.substring(index, length));
        } else {
            printSub(type, tag, msg);
        }
    }

    private static void printSub(Type type, String tag, String sub) {
        switch (type) {
            case V:
                Log.v(tag, sub);
                break;
            case D:
                Log.d(tag, sub);
                break;
            case I:
                Log.i(tag, sub);
                break;
            case W:
                Log.w(tag, sub);
                break;
            case E:
                Log.e(tag, sub);
                break;
            case A:
                Log.wtf(tag, sub);
                break;
        }
    }
}
