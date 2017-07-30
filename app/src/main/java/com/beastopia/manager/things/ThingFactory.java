package com.beastopia.manager.things;

import com.beastopia.manager.utils.FileHelper;
import java.io.File;

public class ThingFactory {

    public static AbstractFileThing fromFile(File file) {
        if (!FileHelper.isESReadable() || !file.exists()) {
            return null;
        } else if (file.isDirectory()) {
            return new DirectoryEntry(file);
        } else {
            return new FileEntry(file);
        }
    }

}
