package com.andy.navigator.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.andy.navigator.ResultConsumer;

public interface Navigator<T> {
    void launch(T from, @NonNull Intent intent, int requestCode, @Nullable ResultConsumer resulthandler);
}
