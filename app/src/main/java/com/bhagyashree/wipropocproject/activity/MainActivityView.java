package com.bhagyashree.wipropocproject.activity;

import com.bhagyashree.wipropocproject.model.DetailModel;

import java.util.List;

public class MainActivityView {
    public interface View {

        void setTitleBar(String title);

        void setList(List<DetailModel> rows);

        void stopRefreshing();

        void unableToLoad();
    }

    public interface Action {

        void callListAPI(boolean isRefreshing);
    }
}
