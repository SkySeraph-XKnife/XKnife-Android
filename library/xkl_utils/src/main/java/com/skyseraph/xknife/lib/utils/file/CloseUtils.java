package com.skyseraph.xknife.lib.utils.file;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by SkySeraph on 2014/12/20 23:17
 */
public class CloseUtils {

    private CloseUtils() {
        throw new AssertionError();
    }

    /**
     * Close IO
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null || closeables.length <= 0) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Close io quietly.
     *
     * @param closeables the closeables
     */
    public static void closeIOQuietly(Closeable... closeables) {
        if (closeables == null || closeables.length <= 0) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
