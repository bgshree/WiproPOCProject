package com.bhagyashree.wipropocproject.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppUtil {
    private static final String TAG = AppUtil.class.getSimpleName();
    private static String sAndroidId;

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

    public static String getAndroidId(Context context) {
        if (TextUtils.isEmpty(sAndroidId)) {
            sAndroidId = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        }
        return sAndroidId;
    }

    public static String getPackageId(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
        return packageInfo.packageName;
    }

    public static String getPackageHash(Context context) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
        String keyHash = "";
        if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
            Signature signature = packageInfo.signatures[0];
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(signature.toByteArray());
            keyHash = new String(Base64.encode(messageDigest.digest(), Base64.DEFAULT)).trim();
        }
        return keyHash;
    }
}
