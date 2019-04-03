package com.andy.navigator;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

class ResultConsumerWarehouse {
    private static Map<Integer, ResultConsumer> resultObservers = new HashMap<>(4);

    public static synchronized void add(@NonNull Integer key, @NonNull ResultConsumer consumer) {
        resultObservers.put(key, consumer);
    }

    public static synchronized void remove(@NonNull Integer key) {
        resultObservers.remove(key);
    }

    public static ResultConsumer get(@NonNull Integer key) {
        return resultObservers.size() <= 0 ? null : resultObservers.get(key);
    }
}