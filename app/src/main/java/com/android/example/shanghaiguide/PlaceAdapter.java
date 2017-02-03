package com.android.example.shanghaiguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * PlaceAdapter - extend Array Adapter to provide array elements to a list view
 *
 * By overiding getView() we can provide the list items at position for a scrolling list view and
 * pick out their individual elements for the list_item layout
 */

public class PlaceAdapter extends ArrayAdapter<Place> {

    // Private colour resource to set list item category background color
    private int mColorResourceId;

    /** Constructor
     * Note we zero out "resource" as this isn't used (normally it points to a layout but
     * this layout is limited to a standard 2 item layout), and we override with getView() method
     * @param context an android context
     * @param objects place array
     */
    public PlaceAdapter(Context context, List<Place> objects, int color_resource_id) {
        super(context, 0, objects);
        mColorResourceId = color_resource_id;
    }

    /**
     * GetView
     * Here we over ride array adapters default getView to provide the list item object at position,
     * this object then provides place data to populate list view
     * @param position see {@link ArrayAdapter}
     * @param convertView see {@link ArrayAdapter}
     * @param parent see {@link ArrayAdapter}
     * @return listItemView the list item view populated with place object data
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        // parent in this case should be the listview ViewGroup
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Place} object located at this position in the list
        final Place currentPlace = getItem(position);

        // Find the view in the list_item.xml layout with the Id
        TextView placeDescriptionBriefTextView = (TextView) listItemView.findViewById(R.id.description_brief_text);

        // Get the resource from current object
        // set this resource on the listItemView
        placeDescriptionBriefTextView.setText(currentPlace.getPlaceDescriptionBrief());

        // Find the view in the list_item.xml layout with the Id
        TextView placeRating = (TextView) listItemView.findViewById(R.id.rating_text);

        // Get the resource from current object
        // set this resource on the listItemView
        // TODO: Convert rating to star image rating
        placeRating.setText("Rating: " + currentPlace.getRating());

        // Find the view in the list_item.xml layout with the Id
        TextView placeLocation = (TextView) listItemView.findViewById(R.id.location_text);

        // Get the resource from current object
        // set this resource on the listItemView
        // TODO: Convert location data to Location view or distance from current location
        placeLocation.setText("Location: " + currentPlace.getLocationResourceId());

        // Find the view in the list_item.xml layout with the Id
        ImageView placeThumb = (ImageView) listItemView.findViewById(R.id.place_thumb_image);

        // Get the resource from current object
        // set this resource on the listItemView
        placeThumb.setImageResource(currentPlace.getImageThumbResourceId());;

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.list_item_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
