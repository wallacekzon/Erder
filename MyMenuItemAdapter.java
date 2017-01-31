package com.example.walla.erder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zhang4all on 1/25/2017.
 */

public class MyMenuItemAdapter extends ArrayAdapter<MyMenuItem> {


    public MyMenuItemAdapter(Context context, ArrayList<MyMenuItem> menuItems) {
        super(context, 0, menuItems);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.menu_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        MyMenuItem currentMenuItem = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView itemNameTextView = (TextView) listItemView.findViewById(R.id.itemname_text_view);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        itemNameTextView.setText(currentMenuItem.getItemName());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView itemDescriptionTextView = (TextView) listItemView.findViewById(R.id.itemdescription_text_view);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        itemDescriptionTextView.setText(currentMenuItem.getItemDescription());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView itemPriceTextView = (TextView) listItemView.findViewById(R.id.price_text_view);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        itemPriceTextView.setText(currentMenuItem.getItemPriceString());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }

}
