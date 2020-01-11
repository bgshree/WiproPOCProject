package com.bhagyashree.wipropocproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AppUtil {
    private static final String TAG = AppUtil.class.getSimpleName();

    private AppUtil() {

    }

    public static boolean isNetworkAvailable(Context context) {
        boolean isConnectedToNetwork = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            isConnectedToNetwork = networkInfo.isConnected();
        }
        return isConnectedToNetwork;
    }

}
