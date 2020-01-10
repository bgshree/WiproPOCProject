package com.bhagyashree.wipropocproject.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhagyashree.wipropocproject.R;
import com.bhagyashree.wipropocproject.adapter.DetailListAdapter;
import com.bhagyashree.wipropocproject.model.DetailModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView.View {

    private MainActivityPresenter mMainActivityPresenter;
    private List<DetailModel> mNewsDetailModelList;

    @BindView(R.id.rv_list)
    RecyclerView mList;

    @BindView(R.id.tv_header)
    TextView mHeader;

    private DetailListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mMainActivityPresenter = new MainActivityPresenter(this, this);
        mAdapter = new DetailListAdapter(this);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mMainActivityPresenter.callListAPI();
    }

    @Override
    public void showToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTitleBar(String title) {
        mHeader.setText(title);
    }

    @Override
    public void setList(List<DetailModel> rows) {
        mNewsDetailModelList = rows;

        mAdapter.setRowList(rows);
        mAdapter.notifyDataSetChanged();
    }
}
