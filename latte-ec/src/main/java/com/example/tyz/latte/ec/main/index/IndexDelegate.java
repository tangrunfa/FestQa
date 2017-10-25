package com.example.tyz.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.latte.ui.recycler.BaseDecoration;
import com.example.administrator.latte.ui.refresh.RefreshHandler;
import com.example.tyz.latte.delegate.bottom.BottomItemDelegate;
import com.example.tyz.latte.ec.R;
import com.example.tyz.latte.ec.R2;
import com.example.tyz.latte.ec.main.EcBottomDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/10/13.
 */

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

    //初始化recyclerview
    private void initRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(
                BaseDecoration.create(ContextCompat.getColor(getContext(),R.color.app_background),5));
        final EcBottomDelegate ecBottomDelegate=getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
//        RestClient.builder()
//                .url("http://117.48.205.138/RestServer/api/" + "index.php")
//                .succes(new ISucces() {
//                    @Override
//                    public void onSuccess(String response) {
//                        final IndexDataConverter converter = new IndexDataConverter();
//                        converter.setmJsonData(response);
//                        final ArrayList<MultipleItemEntity> arrayList = converter.convert();
//                        final String imag = arrayList.get(1).getField(MultipleFields.IMAGE_URL);
//                        Toast.makeText(getContext(), imag, Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .build()
//                .get();
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.fristPage("index.php");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


}
