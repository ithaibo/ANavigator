package com.andy.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class JustResultOkAty extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(RESULT_OK);
        finish();
    }
}
