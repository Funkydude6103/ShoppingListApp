package com.example.shoppinglistapp;
import android.app.Dialog;
import android.content.Context;

public class LottieLoader {
    private Dialog dialog;

    public LottieLoader(Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_loader);
        dialog.setCancelable(false); // Prevent dismissal by back button
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
    public void show() {
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
    public void dismiss() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
