package com.beastopia.manager.things;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public interface Thing {
    void clicked(Activity origin, Context ctx);
    String getName();
    String toString();
}
