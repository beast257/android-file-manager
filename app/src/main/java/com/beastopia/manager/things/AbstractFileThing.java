package com.beastopia.manager.things;

import java.io.File;

public abstract class AbstractFileThing extends AbstractThing {
    protected File file;

    protected AbstractFileThing(File file) {
        super(file.getName());
        this.file = file;
    }

    public String getPath() {
        return file.getAbsolutePath();
    }
}
