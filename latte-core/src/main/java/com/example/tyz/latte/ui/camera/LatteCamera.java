package com.example.tyz.latte.ui.camera;

import android.net.Uri;

import com.example.tyz.latte.delegate.PermissionCheckerDelegate;
import com.example.tyz.latte.util.file.FileUtil;

/**
 * Created by TYZ on 2017/10/26.
 */

public class LatteCamera {

    public static Uri createCropFile(){
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG","jpg")).getPath());
    }
    public static void start(PermissionCheckerDelegate delegate){
        new CameraHandler(delegate).beginCameraDialog();
    }
}
