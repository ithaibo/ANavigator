package com.andy.navigator;

import android.app.Activity;
import android.app.Fragment;
import android.app.ResultInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.andy.navigator.core.HandlerCallbackHook;
import com.andy.navigator.core.Initializer;

import java.util.List;

public class ANavigator {
    private Activity ctxFrom;
    private Intent intent;
    private int reqCode = 1000;
    @Nullable
    private ResultConsumer resultConsumer;

    private Navigator navigator = new NavigatorFromActivity();


    private ANavigator() {
    }

    private static final class Holder {
        private static final ANavigator instance = new ANavigator();
    }

    public static ANavigator getInstance() {
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

    private static final class NavigatorFromActivity implements Navigator<Activity> {
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

    private static final class InnerCallback extends HandlerCallbackHook {
        @Override
        protected boolean onDispatchResult(List<ResultInfo> results) {
            for (ResultInfo resultInfo : results) {
                ResultConsumer resultConsumer = ResultConsumerWarehouse.get(resultInfo.mRequestCode);
                if (null == resultConsumer) {
                    continue;
                }
                resultConsumer.onResult(resultInfo.mResultCode, resultInfo.mData);
                // clear
                ResultConsumerWarehouse.remove(resultInfo.mRequestCode);
                return true;
            }

            return false;
        }
    }

    private static final class InnerFrg  extends Fragment {

        public void startActivityForResult(int requestCode, ResultConsumer resultConsumer, Intent intent) {
            ResultConsumerWarehouse.add(requestCode, resultConsumer);
            startActivityForResult(intent, requestCode);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            ResultConsumer consumer = ResultConsumerWarehouse.get(requestCode);
            if (null != consumer) {
                consumer.onResult(resultCode, data);
                ResultConsumerWarehouse.remove(requestCode);
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }
    }
}
