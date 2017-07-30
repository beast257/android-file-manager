package com.beastopia.manager.things;

import android.app.Activity;
import android.content.Context;
import com.beastopia.manager.utils.Actions;
import java.io.File;

public class FileEntry extends AbstractFileThing {
    FileEntry(File file) {
        super(file);
    }

    @Override
    public void clicked(Activity origin, Context ctx) {
        Actions.toast(origin, "clicked: " + getPath());
        // TODO: something more than notify user of click
    }
}
