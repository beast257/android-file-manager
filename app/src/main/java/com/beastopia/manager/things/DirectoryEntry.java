package com.beastopia.manager.things;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.beastopia.manager.activities.FileListActivity;
import java.io.File;

public class DirectoryEntry extends AbstractFileThing {
    DirectoryEntry(File dir) {
        super(dir);
    }

    @Override
    public void clicked(Activity origin, Context ctx) {
        Uri uri = Uri.parse(getPath());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri, ctx, FileListActivity.class);
        origin.startActivity(intent);
    }
}
