package com.beastopia.manager.utils;

import com.beastopia.manager.things.Thing;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public enum Sorting {
    NAME(Comparators.nameComparator, Comparators.nameComparatorReversed);

    private static Map<MenuOption, Sorting> optionToSorting = new HashMap<>();

    static {
        optionToSorting.put(MenuOption.SORT_BY_NAME, NAME);
    }

    public Comparator<Thing> ascending;
    public Comparator<Thing> descending;

    Sorting(Comparator<Thing> asc, Comparator<Thing> desc) {
        this.ascending = asc;
        this.descending = desc;
    }

    public static Sorting getSortingForOption(MenuOption option) {
        return optionToSorting.get(option);
    }

    public Comparator<Thing> getComparatorForDirection(int d) {
        if (d < 0) {
            return descending;
        }
        return ascending;
    }
}
