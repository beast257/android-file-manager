package com.beastopia.manager.activities;

import com.beastopia.manager.utils.FileHelper;

public class RootActivity extends FileListActivity {
    @Override
    public void resetBase() {
        if (FileHelper.isESReadable()) {
            basePath = FileHelper.getESRoot();
        }
    }
}
