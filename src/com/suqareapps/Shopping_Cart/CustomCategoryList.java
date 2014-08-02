package com.suqareapps.Shopping_Cart;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;

public class CustomCategoryList extends ArrayAdapter<String> {

    private final Activity context;
    private static ArrayList<String> title;
    private static ArrayList<String> id;
    private JSONArray mappings = null;

    public CustomCategoryList(Activity context, ArrayList<String> categories, ArrayList<String> id, JSONArray mappings) {
        super(context, R.layout.category_item, categories);
        this.context = context;
        this.mappings = mappings;
        CustomCategoryList.id = id;
        CustomCategoryList.title = categories;
    }

    public View getView (int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.category_item, null, true);

        ImageView img = (ImageView) rowView.findViewById(R.id.categoryIcon);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.categoryTitle);
        TextView txtDesc = (TextView) rowView.findViewById(R.id.categoryDescription);

        /*try {
            Bitmap myImg = BitmapFactory.decodeStream((InputStream) new URL(MainActivity.SERVER_ADRESS + "/" + id.get(position)).getContent());
            img.setImageBitmap(myImg);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        txtTitle.setText(title.get(position));

        // build the description
        String description = "";
        try {
            for (int i = 0; i < mappings.getJSONObject(position).getJSONArray("subclass").length(); ++i) {
                String mySubclass = mappings.getJSONObject(position).getJSONArray("subclass").getString(i);
                description += ", " + mySubclass;
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        txtDesc.setText(description.substring(2));

        img.setImageDrawable(this.context.getResources().getDrawable(R.drawable.ic_default));

        return rowView;
    }

}
