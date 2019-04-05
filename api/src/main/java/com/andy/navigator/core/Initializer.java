package com.andy.navigator.core;

import android.app.ActivityThread;
import android.os.Handler;

import com.andy.navigator.utils.ReflectUtils;

import java.lang.reflect.Field;

public class Initializer {
    private static boolean success;

    public static void init(HandlerCallbackHook callbackHook) {
        if (success) {
            return;
        }
        if (null == callbackHook) {
            throw new NullPointerException("callbackHook cannot be null");
        }

        try {
            /**
             * hook filed mH in android.app.ActivityThread
             */
            ActivityThread activityThread = ActivityThread.currentActivityThread();
            Handler handler = ReflectUtils.getField(activityThread, "mH");
            Field mCallBackField = Handler.class.getDeclaredField("mCallback");
            mCallBackField.setAccessible(true);
            mCallBackField.set(handler, callbackHook);
            success = true;
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
    }

    public static boolean initialized() {
        return success;
    }
}
