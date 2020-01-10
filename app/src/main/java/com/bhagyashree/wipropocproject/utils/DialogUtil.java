package com.bhagyashree.wipropocproject.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class DialogUtil {

    private static AlertDialog.Builder mBuilder;
    private static ProgressDialog mProgressDialog;

    private DialogUtil() {

    }

    public static ProgressDialog showProgressDialog(Context context, String title, String message, DialogInterface.OnCancelListener cancelListener) {
        mProgressDialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
        mProgressDialog.setCancelable(cancelListener != null);
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
        return mProgressDialog;
    }

    public static void showDialog(Context context, String title, String message,
                                  String positiveButtonText, DialogInterface.OnClickListener positiveButtonListener) {
        showDialog(context, title, message, positiveButtonText, null, null, positiveButtonListener, null, null, null);
    }

    public static void showDialog(Context context, String title, String message,
                                  String positiveButtonText, String negativeButtonText,
                                  DialogInterface.OnClickListener positiveButtonListener,
                                  DialogInterface.OnClickListener negativeButtonListener) {
        showDialog(context, title, message, positiveButtonText, negativeButtonText, null, positiveButtonListener, negativeButtonListener, null, null);
    }

    public static void showDialog(Context context, String title, String message,
                                  String positiveButtonText, String negativeButtonText, String neutralButtonText,
                                  DialogInterface.OnClickListener positiveButtonListener,
                                  DialogInterface.OnClickListener negativeButtonListener,
                                  DialogInterface.OnClickListener neutralButtonListener, View customView) {
        mBuilder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
        if (!TextUtils.isEmpty(title)) {
            mBuilder.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            mBuilder.setMessage(message);
        }
        mBuilder.setCancelable(false);

        if (!TextUtils.isEmpty(positiveButtonText)) {
            mBuilder.setPositiveButton(positiveButtonText, positiveButtonListener);
        }
        if (!TextUtils.isEmpty(negativeButtonText)) {
            mBuilder.setNegativeButton(negativeButtonText, negativeButtonListener);
        }
        if (!TextUtils.isEmpty(neutralButtonText)) {
            mBuilder.setNeutralButton(neutralButtonText, neutralButtonListener);
        }
        if (customView != null) {
            mBuilder.setView(customView);
        }
        mBuilder.create().show();
    }

    public static void dialogDismiss() {
        if (mBuilder != null) {
            mBuilder.create().dismiss();
        }

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public static void progressDialogDismiss() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public static void showNumberPickerDialog(Context context, String title, String message,
                                              String positiveButtonText, String negativeButtonText,
                                              DialogInterface.OnClickListener positiveButtonListener,
                                              DialogInterface.OnClickListener negativeButtonListener,
                                              EditText editText) {
        showDialog(context, title, message, positiveButtonText, negativeButtonText, null, positiveButtonListener, negativeButtonListener, null, editText);
    }
}
