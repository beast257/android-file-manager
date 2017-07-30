package com.beastopia.manager.utils;

import android.util.SparseArray;

public enum MenuOption {
    REFRESH(40, "Refresh"),
    ADD_DIRECTORY(46, "Add directory"),
    ADD_FILE(47, "Add file"),
    SORT_BY_NAME(51, "Sort alphabetically", true),
    SETTINGS(59, "Settings");

    private static final SparseArray<MenuOption> options = new SparseArray<>();

    static {
        for (MenuOption opt : MenuOption.values()) {
            options.put(opt.id, opt);
        }
    }

    public final int id;
    public final String title;
    public final boolean isSorting;

    MenuOption(int id, String title) {
        this(id, title, false);
    }

    MenuOption(int id, String title, boolean isSorting) {
        this.id = id;
        this.title = title;
        this.isSorting = isSorting;
    }

    public static MenuOption optionFromId(int id) {
        return options.get(id);
    }
}
