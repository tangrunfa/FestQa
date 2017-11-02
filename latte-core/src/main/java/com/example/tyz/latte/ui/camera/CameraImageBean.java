package com.example.tyz.latte.ui.camera;

import android.net.Uri;

/**
 * Created by TYZ on 2017/10/26.
 */

public final class CameraImageBean {
    public Uri getPath() {
        return mPath;
    }

    public void setmPath(Uri mPath) {
        this.mPath = mPath;
    }
    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }
    private Uri mPath = null;
}
