package com.suqareapps.Shopping_Cart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CategoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Creating view corresponding to the fragment
        View v = inflater.inflate(R.layout.category_fragment, container, false);

        return v;
    }
}
