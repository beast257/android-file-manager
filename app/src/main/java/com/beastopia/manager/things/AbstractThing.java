package com.beastopia.manager.things;

public abstract class AbstractThing implements Thing {
    protected String name;

    AbstractThing(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Used by the default list view adapter to map things back to strings
     * @return String representation of this thing
     */
    public String toString() {
        return getName();
    }
}
