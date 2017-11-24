package com.example.tyz.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.tyz.latte.delegate.Lattedelegate;
import com.example.tyz.latte.ec.R;
import com.example.tyz.latte.ec.R2;
import com.example.tyz.latte.net.RestClient;
import com.example.tyz.latte.net.callback.ISucces;

import java.util.List;

import butterknife.BindView;

/**
 * Created by TYZ on 2017/10/18.
 */

public class ContentDelegate extends Lattedelegate {
    private static final String APP_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;
    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(APP_CONTENT_ID, contentId);
        final ContentDelegate contentDelegate = new ContentDelegate();
        contentDelegate.setArguments(args);
        return contentDelegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(APP_CONTENT_ID);
        }
    }

    //初始化recyclerview
    private void initRecyclerView() {
        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }


    private void initData() {
        RestClient.builder()
                .url( "sort_content_list.php?contentId=" + mContentId)
                .succes(new ISucces() {
                    @Override
                    public void onSuccess(String response) {
                        mData = new SectionDataConverter().convert(response);
                        final SectionAdapter sectionAdapter =
                                new SectionAdapter(R.layout.item_section_content,
                                        R.layout.item_section_header, mData);
                        mRecyclerView.setAdapter(sectionAdapter);

//                        Toast.makeText(Latte.getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {
        initRecyclerView();
        initData();
    }
}
