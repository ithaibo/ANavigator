package com.andy.navigator;

import android.app.ResultInfo;

import com.andy.navigator.core.HandlerCallbackHook;

import java.util.List;

class InnerCallback extends HandlerCallbackHook {
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
