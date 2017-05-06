package com.skyseraph.xknife.c13_6;

import com.skyseraph.xknife.lib.module.log.L;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by skyseraph on 2016/5/5.
 */
@Aspect
public class AspectTest {

    /**
     * Log for main activity. 切入点，代码注入位置
     */
    @Pointcut("execution(* com.skyseraph.xknife.MainActivity.onCreate(..)) ||"
            + "execution(* com.skyseraph.xknife.MainActivity.onStart(..)) ||"
            + "execution(* com.skyseraph.xknife.MainActivity.onResume(..)) ||"
            + "execution(* com.skyseraph.xknife.MainActivity.onStop(..)) ||"
            + "execution(* com.skyseraph.xknife.MainActivity.onDestroy(..)) ||"
            + "execution(* com.skyseraph.xknife.MainActivity.onPause(..))"
    )
    public void logForMainActivity() {
    }

    /**
     * Log.
     *
     * @param joinPoint the join point
     */
    @Before("logForMainActivity()")
    public void log(JoinPoint joinPoint) {
        L.e("log=" + joinPoint.toShortString());
    }

    /**
     * On click event. 切入点，代码注入位置
     */
    @Pointcut("execution(* android.view.View.OnClickListener.onClick(..))"
    )
    public void onClickEvent() {
    }

    /**
     * Log click event.
     *
     * @param joinPoint the join point
     */
    @Before("onClickEvent()")
    public void logClickEvent(JoinPoint joinPoint) {
        L.e("logClickEvent=" + joinPoint.toShortString());
    }
}
