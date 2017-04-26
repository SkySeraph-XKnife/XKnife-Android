package com.skyseraph.xknife.c2_4;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;

import com.skyseraph.xknife.lib.utils.nomal.NotProguardUtils;

/**
 * Created by SkySeraph on 2015/4/26.
 */
public abstract class RomEntity {

    private final String propertyKey;

    /**
     * Instantiates a new Rom entity.
     *
     * @param key the key
     */
    public RomEntity(String key) {
        propertyKey = key;
    }

    /**
     * 打开当前Rom的权限设置Activity。
     *
     * @param context the context
     */
    public abstract void showPermissionActivity(Context context);

    /**
     * 是否有浮窗权限。
     *
     * @param context the context
     * @return boolean
     */
    public boolean hasFloatPermission(Context context) {
        final int version = Build.VERSION.SDK_INT;
        if (version < Build.VERSION_CODES.KITKAT) {
            return true;
        }
        return NotProguardUtils.checkOp(context, 24);
    }

    private String getPropertyValue() {
        if (!TextUtils.isEmpty(propertyKey)) {
            return NotProguardUtils.getSystemProperty(propertyKey);
        } else {
            return null;
        }
    }

    /**
     * 此RomEntity是否与当前系统Rom匹配
     *
     * @return boolean
     */
    boolean isThisRom() {
        String value = getPropertyValue();
        return !TextUtils.isEmpty(value);
    }

    /**
     * MIUI rom
     */
    static class MiuiRom extends RomEntity {

        /**
         * Instantiates a new Miui rom.
         */
        public MiuiRom() {
            super("ro.miui.ui.version.name");
        }

        @Override
        public void showPermissionActivity(Context context) {
            showMiuiInstalledAppDetails(context);
        }

        @Override
        public boolean hasFloatPermission(Context context) {
            return isMiuiFloatWindowOpAllowed(context);
        }

        private boolean isMiuiFloatWindowOpAllowed(Context context) {
            final int version = Build.VERSION.SDK_INT;
            if (version >= 19) {
                return NotProguardUtils.checkOp(context, 24);
            } else {
                return NotProguardUtils.floatingWindowHasOpened(context);
            }
        }

        private void showMiuiInstalledAppDetails(Context context) {
            String rom = super.getPropertyValue();
            String pkg = context.getPackageName();
            if ("V6".equals(rom)) {
                Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                intent.putExtra("extra_pkgname", pkg);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    context.startActivity(intent);
                } catch (Exception e) {
                }

//            Activity activity = PageSwitcher.peekActivityStack();
//            if (Util.isIntentAvailable(activity, intent)) {
//                activity.startActivityForResult(intent, 2);
//            } else {
//                L.e("Intent is not available!");
//            }
            } else {
                NotProguardUtils.showInstalledAppDetails(context, pkg);
            }
        }
    }
}
