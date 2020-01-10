package com.bhagyashree.wipropocproject.activity;

import com.bhagyashree.wipropocproject.model.DetailModel;

import java.util.List;

public class MainActivityView {
    public interface View {
        void showToast(String toastMessage);

        void setTitleBar(String title);

        void setList(List<DetailModel> rows);
    }

    public interface Action {

        void callListAPI();
    }
}
