package com.example.tyz.festqa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.tyz.latte.delegate.Lattedelegate;
import com.example.tyz.latte.net.RestClient;
import com.example.tyz.latte.net.callback.IError;
import com.example.tyz.latte.net.callback.IFailure;
import com.example.tyz.latte.net.callback.ISucces;

/**
 * Created by Administrator on 2017/9/22.
 */

public class exampleDelegate extends Lattedelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://www.gomemyc.com/")
                .params("","")
                .succes(new ISucces() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })

                .build().get();
    }


}
