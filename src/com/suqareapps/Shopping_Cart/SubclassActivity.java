package com.suqareapps.Shopping_Cart;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dan on 03/08/14.
 */
public class SubclassActivity extends FragmentActivity {
    public static ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategory);

        // Override strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // action bar
        actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final ArrayList<String> subCategories = intent.getStringArrayListExtra("SUBCATEGORY");
        final String category = intent.getStringExtra("CATEGORY");
        final ArrayList<String> subCategoriesId = intent.getStringArrayListExtra("SUBCATEGORY_ID");

        // set the action bar title
        actionBar.setTitle(category);

        // custom list adapter
        final ListView subCategoryList = (ListView) findViewById(R.id.subcategoryList);
        subCategoryList.setAdapter(new CustomSubcategoryList(this, subCategories));

        // add click listener
        subCategoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String subcatgoryId = subCategoriesId.get(position);

                Log.v("lub", subcatgoryId);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println(item.getItemId());
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
