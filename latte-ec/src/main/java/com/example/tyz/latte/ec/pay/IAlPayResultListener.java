package com.example.tyz.latte.ec.pay;

/**
 * Created by TYZ on 2017/10/24.
 */

public interface IAlPayResultListener {
    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
