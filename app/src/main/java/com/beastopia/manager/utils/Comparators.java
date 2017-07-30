package com.beastopia.manager.utils;

import com.beastopia.manager.things.Thing;
import java.util.Comparator;

public class Comparators {
    public static final Comparator<Thing> nameComparator = new Comparator<Thing>() {
        @Override
        public int compare(Thing lhs, Thing rhs) {
            if (lhs == null) {
                return -1;
            }
            if (rhs == null) {
                return 1;
            }
            return lhs.toString().toLowerCase().compareTo(rhs.toString().toLowerCase());
        }
    };

    public static final Comparator<Thing> nameComparatorReversed = new Comparator<Thing>() {
        @Override
        public int compare(Thing lhs, Thing rhs) {
            return nameComparator.compare(rhs, lhs);
        }
    };
}
