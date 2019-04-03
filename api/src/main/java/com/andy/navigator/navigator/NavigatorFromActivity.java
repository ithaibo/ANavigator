package com.andy.navigator.navigator;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.andy.navigator.ResultConsumer;
import com.andy.navigator.InnerFrg;

public class NavigatorFromActivity implements Navigator<Activity> {
    private final String TAG = "NavigatorFrg";

    @Override
    public void launch(Activity from, @NonNull Intent intent, int requestCode, @Nullable ResultConsumer resulthandler) {
        InnerFrg frg = (InnerFrg) from.getFragmentManager().findFragmentByTag(TAG);
        if (frg == null) {
            frg = new InnerFrg();
            from.getFragmentManager().beginTransaction().add(frg, TAG).commit();
            from.getFragmentManager().executePendingTransactions();
        }
        frg.startActivityForResult(requestCode, resulthandler, intent);
    }
}
