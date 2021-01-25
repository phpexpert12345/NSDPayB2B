package com.example.nsd.pay.helper;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.nsd.pay.R;

import pl.droidsonroids.gif.GifImageView;

public class LoadingDialog {

    public static AlertDialog alertDialog;

    public static void callLoadingDialog(Context context, Activity activity, int drawable, String message) {
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_progress_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.NewDialog);
        builder.setCancelable(false);
        builder.setView(dialogView);
        alertDialog = builder.create();

        GifImageView gif_image = dialogView.findViewById(R.id.gif_image);
        TextView tv_dialog_message = dialogView.findViewById(R.id.tv_dialog_message);

        gif_image.setBackgroundResource(drawable);
        tv_dialog_message.setText(message);

        alertDialog.show();
    }

    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}