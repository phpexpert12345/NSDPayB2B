package com.example.nsd.pay.interfaces;

import android.view.View;

import com.example.nsd.pay.enums.ButtonActions;

public interface SnackBarActionClickListener {
    void onPositiveClick(View view, ButtonActions action);
}
