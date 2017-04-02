package com.skyseraph.xknife.lib.utils.image;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by SkySeraph on 2016/3/15.
 */
public class BitmapUtils {

    /**
     * 从Uri中获取Bitmap
     *
     * @param context the context
     * @param uri     图片url链接
     * @return Bitmap bitmap
     */
    public static Bitmap decodeUriAsBitmap(Context context, Uri uri) {
        if (context == null || uri == null) {
            return null;
        }

        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /***
     * 图片的缩放方法
     *
     * @param bgimage ：源图片资源
     * @param newWidth ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return bitmap bitmap
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

    /**
     * Decode sampled bitmap from resource bitmap.
     *
     * @param res       the res
     * @param resId     the res id
     * @param reqWidth  the req width
     * @param reqHeight the req height
     * @return the bitmap
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * Calculate in sample size int.
     *
     * @param options   the options
     * @param reqWidth  the req width
     * @param reqHeight the req height
     * @return the int
     */
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    /**
     * Blur bitmap bitmap.
     *
     * @param applicationContext the application context
     * @param bitmap             the bitmap
     * @param radius             the radius
     * @return the bitmap
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    /**
     * 利用RenderScript进行模糊处理
     * 模糊效果的处理是比较耗时的(~250ms)，开线程预先处理好*/
    public static Bitmap blurBitmap(Context applicationContext, Bitmap bitmap, float radius) {

        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(applicationContext);

        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);
        //Set the radius of the blur
        blurScript.setRadius(radius);
        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        bitmap.recycle();

        //After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;
    }

    /**
     * Drawable 2 bitmap bitmap.
     *
     * @param drawable  the drawable
     * @param defaultWH the default wh
     * @return the bitmap
     */
    public static Bitmap drawable2Bitmap(Drawable drawable, int... defaultWH) {
        if (drawable == null)
            return null;
        if (drawable instanceof BitmapDrawable)
            return ((BitmapDrawable) drawable).getBitmap();
        try {
            Bitmap bitmap;
            if (drawable instanceof ColorDrawable)
                bitmap = Bitmap.createBitmap(defaultWH[0], defaultWH[1], Bitmap.Config.ARGB_8888);
            else
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                        Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Drawable convert to bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable != null) {
            int w = drawable.getIntrinsicWidth();
            int h = drawable.getIntrinsicHeight();
            Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                    : Bitmap.Config.RGB_565;
            Bitmap bitmap = Bitmap.createBitmap(w, h, config);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, w, h);
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }

    /**
     * Bitmap convert to drawable
     *
     * @param bitmap
     * @return
     */
    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        if (bitmap != null) {
            return new BitmapDrawable(bitmap);
        } else {
            return null;
        }
    }

    /**
     * Stream convert to bitmap
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static Bitmap inputStreamToBitmap(InputStream inputStream)
            throws Exception {
        if (inputStream != null) {
            return BitmapFactory.decodeStream(inputStream);
        } else {
            return null;
        }
    }

    /**
     * Bytes convert to bitmap
     *
     * @param byteArray
     * @return
     */
    public static Bitmap bytesToBitmap(byte[] byteArray) {
        if (byteArray != null && byteArray.length != 0) {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } else {
            return null;
        }
    }

    /**
     * Bytes convert to drawable
     *
     * @param byteArray
     * @return
     */
    public static Drawable byteToDrawable(byte[] byteArray) {
        if (byteArray != null && byteArray.length > 0) {
            ByteArrayInputStream ins = new ByteArrayInputStream(byteArray);
            return Drawable.createFromStream(ins, null);
        } else {
            return null;
        }

    }

    /**
     * Bitmap convert to bytes
     *
     * @param byteArray
     * @return
     */
    public static byte[] bitmapToBytes(Bitmap bm) {
        if (bm != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } else {
            return null;
        }

    }

    /**
     * Drawable convert to bytes
     *
     * @param drawable
     * @return
     */
    public static byte[] drawableToBytes(Drawable drawable) {
        if (drawable != null) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            byte[] bytes = bitmapToBytes(bitmap);
            ;
            return bytes;
        } else {
            return null;
        }

    }

    /**
     * Get reflection image from bitmap
     *
     * @param bitmap
     * @return
     */
    public static Bitmap getReflectionImageWithBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            final int reflectionGap = 4;
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            Matrix matrix = new Matrix();
            matrix.preScale(1, -1);
            Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, h / 2, w,
                    h / 2, matrix, false);

            Bitmap bitmapWithReflection = Bitmap.createBitmap(w, (h + h / 2),
                    Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmapWithReflection);
            canvas.drawBitmap(bitmap, 0, 0, null);
            Paint deafalutPaint = new Paint();
            canvas.drawRect(0, h, w, h + reflectionGap, deafalutPaint);

            canvas.drawBitmap(reflectionImage, 0, h + reflectionGap, null);

            Paint paint = new Paint();
            LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
                    bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
                    0x00ffffff, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            // Set the Transfer mode to be porter duff and destination in
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            // Draw a rectangle using the paint with our linear gradient
            canvas.drawRect(0, h, w, bitmapWithReflection.getHeight()
                    + reflectionGap, paint);
            return bitmapWithReflection;
        } else {
            return null;
        }
    }

    /**
     * Get rounded corner image
     *
     * @param bitmap
     * @param roundPx 5 10
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        if (bitmap != null) {
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, w, h);
            final RectF rectF = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return output;
        } else {
            return null;
        }
    }


    /**
     * Resize the drawable
     *
     * @param drawable
     * @param w
     * @param h
     * @return
     */
    public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
        if (drawable != null) {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            Bitmap oldbmp = drawableToBitmap(drawable);
            Matrix matrix = new Matrix();
            float sx = ((float) w / width);
            float sy = ((float) h / height);
            matrix.postScale(sx, sy);
            Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                    matrix, true);
            return new BitmapDrawable(newbmp);
        } else {
            return null;
        }
    }
}
