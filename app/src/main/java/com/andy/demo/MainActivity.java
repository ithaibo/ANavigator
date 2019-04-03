package com.andy.demo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.andy.navigator.ResultConsumer;
import com.andy.navigator.ANavigator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_print_stack_trace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navWithXNavigator();
            }
        });

        initFrag();
    }

    private void navWithXNavigator() {
        ANavigator.getInstance()
                .from(MainActivity.this)
                .to(new Intent(MainActivity.this, JustResultOkAty.class))
                .onResult(new ResultConsumer() {
                    @Override
                    public void onResult(int resultCode, @Nullable Intent dataResult) {
                        if (resultCode == RESULT_OK) {
                            Toast.makeText(MainActivity.this, "ANavigator start from activity, get result", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .apply();
    }

    private void initFrag() {
        FragmentManager frgm = getSupportFragmentManager();
        frgm.beginTransaction()
                .replace(R.id.v_frg_stub, new FrgImport())
                .commit();
        frgm.executePendingTransactions();
    }
}
