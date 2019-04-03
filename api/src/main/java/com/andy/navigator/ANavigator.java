package com.andy.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.andy.navigator.core.Initializer;
import com.andy.navigator.navigator.Navigator;
import com.andy.navigator.navigator.NavigatorFromActivity;

public class ANavigator {
    private Activity ctxFrom;
    private Intent intent;
    @Nullable
    private ResultConsumer resultConsumer;

    private Navigator navigator = new NavigatorFromActivity();
    private ResultConsumerWarehouse consumerWarehouse;

    private int reqCode = 1000;


    private ANavigator() {
    }

    private static final class Holder {
        private static final ANavigator instance = new ANavigator();
    }

    public static final ANavigator getInstance() {
        return Holder.instance;
    }

    public ANavigator from(@NonNull Context cxt) {
        if (!(cxt instanceof Activity)) {
            throw new IllegalArgumentException("context must be Activity!");
        }
        this.ctxFrom = (Activity) cxt;
        return this;
    }

    public ANavigator to(@NonNull Intent intent) {
        this.intent = intent;
        return this;
    }

    public ANavigator onResult(@Nullable ResultConsumer resultConsumer) {
        this.resultConsumer = resultConsumer;
        return this;
    }

    public final void apply() {
        if (null == resultConsumer) {
            navigateWithoutResult();
            return;
        }

        ++reqCode;
        if (reqCode > 2000) {
            reqCode = 1;
        }

        resultConsumer.requestCode = reqCode;
        ResultConsumerWarehouse.add(reqCode, resultConsumer);

        if (Build.VERSION.SDK_INT >= 16) {
            // check navigator
            if (null == navigator) {
                throw new IllegalArgumentException("no Navigator instance exists!");
            }
            //noinspection unchecked
            this.navigator.launch(ctxFrom, intent, reqCode, resultConsumer);
        } else {
            checkInit();
            ctxFrom.startActivityForResult(intent, reqCode);
        }

        // clear
        this.ctxFrom = null;
        this.resultConsumer = null;
        this.intent = null;
    }

    private void checkInit() {
        if (Initializer.initialized()) {
            return;
        }
        Initializer.init(new InnerCallback());
    }

    public void cancel(int requestCode) {
        ResultConsumerWarehouse.remove(requestCode);
    }

    private void navigateWithoutResult() {
        ctxFrom.startActivity(intent);
    }
}
