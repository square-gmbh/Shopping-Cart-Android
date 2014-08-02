package com.suqareapps.Shopping_Cart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dan on 03/08/14.
 */
public class CustomSubcategoryList extends ArrayAdapter<String> {

    private final Activity context;
    private static ArrayList<String> title;

    public CustomSubcategoryList(Activity context, ArrayList<String> subCategories) {
        super(context, R.layout.category_item, subCategories);
        this.context = context;
        CustomSubcategoryList.title = subCategories;
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
        txtDesc.setText("320 produse gasite");

        img.setImageDrawable(this.context.getResources().getDrawable(R.drawable.ic_default));

        return rowView;
    }
}
