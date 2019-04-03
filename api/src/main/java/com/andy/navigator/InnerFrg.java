package com.andy.navigator;

import android.app.Fragment;
import android.content.Intent;

public class InnerFrg extends Fragment {

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
