package com.bhagyashree.wipropocproject.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bhagyashree.wipropocproject.R;
import com.bhagyashree.wipropocproject.adapter.DetailListAdapter;
import com.bhagyashree.wipropocproject.model.DetailModel;
import com.bhagyashree.wipropocproject.utils.DialogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView mList;
    @BindView(R.id.tv_data_not_load)
    TextView mDataNotLoad;

    private MainActivityPresenter mMainActivityPresenter;
    private List<DetailModel> mNewsDetailModelList;
    private DetailListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mRefreshLayout.setOnRefreshListener(this);
        mMainActivityPresenter = new MainActivityPresenter(this, this);
        mAdapter = new DetailListAdapter(this);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataNotLoad.setVisibility(View.GONE);
        DialogUtil.showProgressDialog(this, getString(R.string.please_wait), getString(R.string.data_loading), null);
        mMainActivityPresenter.callListAPI(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DialogUtil.dialogDismiss();
    }

    @Override
    public void setTitleBar(String title) {
        setTitle(title);
    }

    @Override
    public void setList(List<DetailModel> rows) {
        mNewsDetailModelList = rows;
        mAdapter.setRowList(rows);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void stopRefreshing() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void unableToLoad() {
        mDataNotLoad.setVisibility(View.VISIBLE);
    }


    @Override
    public void onRefresh() {
        mDataNotLoad.setVisibility(View.GONE);
        mAdapter.setRowList(null);
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(true);
        mMainActivityPresenter.callListAPI(true);
    }
}
