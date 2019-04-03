package com.andy.navigator;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.support.annotation.Nullable;

public abstract class ResultConsumer implements LifecycleObserver {
    int requestCode;

    protected abstract void onResult(int resultCode, @Nullable Intent dataResult);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void onRelease() {
        ANavigator.getInstance().cancel(requestCode);
    }
}
