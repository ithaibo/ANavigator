package com.andy.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andy.navigator.ANavigator;
import com.andy.navigator.ResultConsumer;

public class FrgImport extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_import, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_import_frag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navWithXNavigator(v);
            }

        });
    }

    private void navWithXNavigator(View v) {
        ANavigator.getInstance()
                .from(v.getContext())
                .to(new Intent(FrgImport.this.getContext(), JustResultOkAty.class))
                .onResult(new ResultConsumer() {
                    @Override
                    public void onResult(int resultCode, @Nullable Intent dataResult) {
                        if (resultCode == Activity.RESULT_OK) {
                            Toast.makeText(FrgImport.this.getContext(), "ANavigator start from FrgImport, get result", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .apply();
    }
}
