package com.beastopia.manager.utils;

import android.content.Context;
import android.widget.Toast;

public class Actions {
    public static void toast(Context ctx, String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }
}
