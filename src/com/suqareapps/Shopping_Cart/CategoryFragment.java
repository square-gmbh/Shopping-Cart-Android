package com.suqareapps.Shopping_Cart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CategoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Creating view corresponding to the fragment
        View v = inflater.inflate(R.layout.category_fragment, container, false);

        // Override strictmode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //JSONArray mappings = null;

        // get the mappings
        JSONObject params = new JSONObject();
        JSONArray mappings = null;
        try {
            params.put("", "");
            mappings = new JSONArray(ServerConnection.makeHTTPCall(MainActivity.SERVER_URL+ "/getMappings", params.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final ArrayList<String> categories;
        final ArrayList<String> category_id;

        final ListView categoryList = (ListView) v.findViewById(R.id.categoryList);
        final TextView connectionWarning = (TextView) v.findViewById(R.id.noConnectionAlert);


        if (mappings == null) {
            // show the connection warning and hide the list
            categoryList.setVisibility(v.GONE);
            connectionWarning.setVisibility(v.VISIBLE);
        } else {
            // hide the connection warning and show the list
            categoryList.setVisibility(v.VISIBLE);
            connectionWarning.setVisibility(v.GONE);

            categories = new ArrayList<String>();
            category_id = new ArrayList<String>();

            for (int i = 0; i < mappings.length(); ++ i) {
                try {
                    categories.add(mappings.getJSONObject(i).getString("name"));
                    category_id.add(mappings.getJSONObject(i).getString("class_id"));

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            categoryList.setAdapter(new CustomCategoryList(getActivity(), categories, category_id, mappings));
        }
        return v;
    }
}
