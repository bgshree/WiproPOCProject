package com.bhagyashree.wipropocproject.activity;

import android.content.Context;

import com.bhagyashree.wipropocproject.R;
import com.bhagyashree.wipropocproject.constant.NetworkConstant;
import com.bhagyashree.wipropocproject.model.PlaceModel;
import com.bhagyashree.wipropocproject.retrofit.ApiManager;
import com.bhagyashree.wipropocproject.utils.AppUtil;
import com.bhagyashree.wipropocproject.utils.DialogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter implements MainActivityView.Action {
    private final Context mContext;
    private final MainActivityView.View mView;

    public MainActivityPresenter(Context context, MainActivityView.View view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void callListAPI(boolean isRefreshing) {
        if (!AppUtil.isNetworkAvailable(mContext)) {
            DialogUtil.showDialog(mContext, mContext.getString(R.string.info),
                    mContext.getString(R.string.internet_unvailable), mContext.getString(R.string.ok), null);
            return;
        }
        ApiManager.getInstance().getListAPI(new Callback<PlaceModel>() {
            @Override
            public void onResponse(Call<PlaceModel> call, Response<PlaceModel> response) {
                DialogUtil.progressDialogDismiss();
                if (isRefreshing) {
                    mView.stopRefreshing();
                }
                if (response.code() == NetworkConstant.INTERNAL_SERVER_ERROR) {
                    mView.unableToLoad();
                    return;
                }
                if (response.isSuccessful()) {
                    PlaceModel news = response.body();
                    if (news != null) {

                        mView.setTitleBar(news.getTitle());
                        mView.setList(news.getRows());
                    }
                }
            }

            @Override
            public void onFailure(Call<PlaceModel> call, Throwable t) {
                mView.unableToLoad();
                DialogUtil.progressDialogDismiss();
                DialogUtil.showDialog(mContext, mContext.getString(R.string.info),
                        mContext.getString(R.string.unable_to_load), mContext.getString(R.string.ok), null);
            }
        });
    }
}
