package com.skyseraph.xknife.lib.xjni;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

/**
 * Created by SkySeraph on 2016/1/28.
 */

public class SignVerify {

    private static final String APP_SIGN = "xxxxxx";

    public static boolean verifySign(Context context) {
        return APP_SIGN.equals(getSignature(context));
    }

    private static String getSignature(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] signatures = packageInfo.signatures;
            StringBuilder builder = new StringBuilder();
            for (Signature signature : signatures) {
                builder.append(signature.toCharsString());
            }
            return builder.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
