package com.example.tyz.latte.ec.main.personal.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.administrator.latte.ui.date.DateDialogUtil;
import com.example.tyz.latte.delegate.Lattedelegate;
import com.example.tyz.latte.ec.R;
import com.example.tyz.latte.ec.main.personal.list.ListBean;
import com.example.tyz.latte.net.RestClient;
import com.example.tyz.latte.net.callback.ISucces;
import com.example.tyz.latte.util.callback.CallbackManager;
import com.example.tyz.latte.util.callback.CallbackType;
import com.example.tyz.latte.util.callback.IGlobalCallback;
import com.example.tyz.latte.util.log.LatteLogger;

/**
 * Created by TYZ on 2017/10/26.
 */

public class UserProfileClickListener extends SimpleClickListener {
    private final UserProfileDelegate DELEGATE;
    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(UserProfileDelegate delegate) {
        DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {

        final ListBean listBean = (ListBean) adapter.getData().get(position);
        final int id = listBean.getId();
        switch (id) {
            case 1:
                //开始照相机或选择图片
                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(@Nullable Uri args) {
                        LatteLogger.d("ON_CROP", args);
                        final ImageView avatar = (ImageView) view.findViewById(R.id.img_arrow_avatar);
                        Glide.with(DELEGATE)
                                .load(args)
                                .into(avatar);

                        RestClient.builder()
                                .url(UploadConfig.UPLOAD_IMG)
                                .loader(DELEGATE.getContext())
                                .file(args.getPath())
                                .succes(new ISucces() {
                                    @Override
                                    public void onSuccess(String response) {
                                        LatteLogger.d("ON_CROP_UPLOAD", response);
                                        final String path = JSON.parseObject(response).getJSONObject("result")
                                                .getString("path");

                                        //通知服务器更新信息
                                        RestClient.builder()
                                                .url("user_profile.php")
                                                .params("avatar", path)
                                                .loader(DELEGATE.getContext())
                                                .succes(new ISucces() {
                                                    @Override
                                                    public void onSuccess(String response) {
                                                        //获取更新后的用户信息，然后更新本地数据库
                                                        //没有本地数据的APP，每次打开APP都请求API，获取信息
                                                    }
                                                })
                                                .build()
                                                .post();
                                    }
                                })
                                .build()
                                .upload();
                    }
                });

                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                final Lattedelegate namedelegate = listBean.getDelegate();
                DELEGATE.getSupportDelegate().start(namedelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView textView=view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
               final DateDialogUtil dateDialogUtil=new DateDialogUtil();
                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        TextView textView=view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;

        }
    }
    private void getGenderDialog(DialogInterface.OnClickListener listener){
        final AlertDialog.Builder builder=new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
    }
    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
