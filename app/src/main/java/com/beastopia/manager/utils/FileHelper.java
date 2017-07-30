package com.beastopia.manager.utils;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FileHelper {
    /**
     * Obtain a collection of files and directories under the given file
     * Convenience that safety-checks the file, and returns a collection instead of an array
     * @param file to list contents of
     * @return collection of child files
     */
    public static Collection<File> dir(File file) {
        if (!file.exists() || !file.isDirectory()) {
            return new ArrayList<>();
        }
        return Arrays.asList(file.listFiles());
    }

    /**
     * Checks if external storage is available for read and write
     * Based on example code created and shared by the Android Open Source Project ([1])
     *   and used according to terms described in the Creative Commons 2.5 Attribution License ([2])
     * Specifically, this method is based on one taken from the Data Storage guide ([3])
     * Simplifications made as per Android Studio's recommendation
     * [1]: http://code.google.com/policies.html
     * [2]: http://creativecommons.org/licenses/by/2.5/
     * [3]: https://developer.android.com/guide/topics/data/data-storage.html#filesExternal
     */
    public static boolean isESWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /**
     * Checks if external storage is available for read and write
     * Based on example code created and shared by the Android Open Source Project ([1])
     *   and used according to terms described in the Creative Commons 2.5 Attribution License ([2])
     * Specifically, this method is based on one taken from the Data Storage guide ([3])
     * Simplifications made as per Android Studio's recommendation
     * [1]: http://code.google.com/policies.html
     * [2]: http://creativecommons.org/licenses/by/2.5/
     * [3]: https://developer.android.com/guide/topics/data/data-storage.html#filesExternal
     */
    public static boolean isESReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    /**
     * Get a file representing the root of the external storage
     * Convenience method that delegates to an equivalent external method
     * @return file representing the root of the external storage
     */
    public static File getESRoot() {
        return Environment.getExternalStorageDirectory();
    }
}
