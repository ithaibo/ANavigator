package com.andy.navigator.core;

import android.app.ResultInfo;
import android.os.Handler;
import android.os.Message;

import com.andy.utils.ReflectUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class HandlerCallbackHook implements Handler.Callback {

    @Override
    public boolean handleMessage(Message msg) {
        boolean consumed = false;
        if (msg.what == 108) {
            Object obj = msg.obj;
            try {
                ArrayList<ResultInfo> results = ReflectUtils.getField(obj, "results");
                if (null != results && !results.isEmpty()) {
                    consumed = onDispatchResult(results);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return consumed;
    }

    protected abstract boolean onDispatchResult(List<ResultInfo> results);
}
