package com.beastopia.manager.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.beastopia.manager.utils.Actions;
import com.beastopia.manager.R;
import com.beastopia.manager.things.AbstractFileThing;
import com.beastopia.manager.things.Thing;
import com.beastopia.manager.things.ThingFactory;
import com.beastopia.manager.utils.FileHelper;
import com.beastopia.manager.utils.MenuOption;
import com.beastopia.manager.utils.Sorting;
import java.io.File;
import java.util.ArrayList;

public class FileListActivity extends AppCompatActivity {
    Activity self;
    // for the list view
    ArrayList<Thing> things = new ArrayList<>();
    ArrayAdapter<Thing> thingAdapter;
    // state
    File basePath = null;
    MenuOption lastSortType = MenuOption.SORT_BY_NAME;
    int lastSortDirection = 1;

    /**
     * Item click listener for the list view
     * Defers actions to the things themselves
     */
    public AdapterView.OnItemClickListener fileListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Object target = parent.getItemAtPosition(position);
            if (target instanceof Thing) {
                Thing thing = (Thing) target;
                thing.clicked(self, getApplicationContext());
            }
        }
    };

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        self = this;

        // try to initialise the activity based on input data
        parseIntentData(getIntent());
        if (getBase() == null) {
            // no data available and not the root: warn the user and close
            Actions.toast(this, "unable to read destination");
            finish();
        }
        setTitle(getBase().getName());

        // setup the toolbar
        setContentView(R.layout.list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        if (!(this instanceof RootActivity) && getSupportActionBar() != null) {
            // root toolbar should not have an "up" button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // setup the collection and its adapter for the list view
        thingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, things);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(thingAdapter);
        listView.setOnItemClickListener(fileListener);

        // get the list of files for the current directory
        scan();
    }

    /**
     * Do custom setup for the options menu
     * @param menu to populate
     * @return true to have the menu appear
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        for (MenuOption opt : MenuOption.values()) {
            menu.add(Menu.NONE, opt.id, Menu.NONE, opt.title);
        }
        return true;
    }

    /**
     * Handle an options menu / action bar item being selected
     * Home / Up buttons handled automatically if a parent activity is specified in the manifest
     * @param item that was selected
     * @return whether to stop the action propagating further
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MenuOption option = MenuOption.optionFromId(id);
        if (option != null) {
            switch (option) {
                case REFRESH:
                    thingAdapter.notifyDataSetChanged();
                    return true;
                case ADD_DIRECTORY:
                    // TODO: add directory creation
                    return true;
                case ADD_FILE:
                    // TODO: add file creation
                    return true;
                case SORT_BY_NAME:
                    sort(option);
                    return true;
                case SETTINGS:
                    // TODO: add settings activity
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Public getter for the base path
     * @return base path or null
     */
    public File getBase() {
        return basePath;
    }

    /**
     * Determines the base filepath for the current activity
     * @param intent that opened this activity
     */
    protected void parseIntentData(Intent intent) {
        if (intent == null || !FileHelper.isESReadable()) {
            return;
        }
        Uri data = intent.getData();
        if (data == null) {
            resetBase();
            return;
        }
        try {
            File file = new File(data.toString());
            if (file.exists()) {
                basePath = file;
            }
        } catch (Exception e) {
            // swallow
        }
    }

    /**
     * Overridable hook to reset the base path to a suitable dataless value, or to null
     */
    public void resetBase() {
        basePath = null;
    }

    /**
     * Assuming a valid base path is set, gets a directory listing and adds all files (that exist)
     *  to the list view
     */
    private void scan() {
        if (FileHelper.isESReadable()) {
            for (File file : FileHelper.dir(getBase())) {
                AbstractFileThing thing = ThingFactory.fromFile(file);
                if (thing != null) {
                    things.add(thing);
                }
            }
            touch();
        }
    }

    /**
     * Sorts the list view based on last selected or default criteria
     * Does not force an update if already sorted
     */
    private void sort() {
        Sorting sorting = Sorting.getSortingForOption(lastSortType);
        if (sorting != null) {
            thingAdapter.sort(sorting.getComparatorForDirection(lastSortDirection));
        }
    }

    /**
     * Apply a change to the current sorting criterion, then sort the view with it
     * Will change the direction of the sort if reapplied
     * @param option that was selected
     * // TODO: swap menu option for sorting
     */
    private void sort(MenuOption option) {
        if (option == null) {
            return;
        }
        if (option.equals(lastSortType) && lastSortDirection == 1) {
            // same sort type: change direction from ascending to descending
            lastSortDirection = -1;
        } else if (option.isSorting) {
            lastSortType = option;
            lastSortDirection = 1;
        }
        sort();
    }

    /**
     * Force an update and re-sorting of the current list without any change to sorting criteria
     */
    private void touch() {
        // the sort may not trigger updates if already sorted, so force it here
        thingAdapter.notifyDataSetChanged();
        sort();
    }
}
