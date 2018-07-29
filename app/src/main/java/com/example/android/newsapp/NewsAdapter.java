package com.example.android.newsapp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * An {@link NewsAdapter} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link News} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
  //  private static final String LOCATION_SEPARATOR = " of ";

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context of the app
     * @param newsList is the list of earthquakes, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<News> newsList) {
        super(context, 0, newsList);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        News currentNews = getItem(position);

        // Find the TextView with view ID magnitude
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.thumbnail);
        String formattedImage = currentNews.getImageUrl();
       // URL url = new URL(formattedImage);
        URL url = null;
        try {
            url = new URL(formattedImage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap bmp;
        if (url != null) {
            try {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                imageView.setImageBitmap(bmp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




        TextView sectionView = (TextView) listItemView.findViewById(R.id.section);
        String formattedSection = currentNews.getSection();
        // Display the magnitude of the current earthquake in that TextView
        sectionView.setText(formattedSection);

        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        String formattedTitle = currentNews.getTitle();
        // Display the magnitude of the current earthquake in that TextView
        titleView.setText(formattedTitle);

        TextView contributorView = (TextView) listItemView.findViewById(R.id.contributordate);
        String formattedContributor = currentNews.getContributor();
        String formattedNewsdate = currentNews.getNewsDate();

        if (formattedContributor != null && !formattedContributor.isEmpty()){
            String authordate = (formattedContributor + "          " + formattedNewsdate);
            contributorView.setText(authordate);
        }else {
            String authordate = (formattedNewsdate);
            contributorView.setText(authordate);
        }

//        TextView newsdateView = (TextView) listItemView.findViewById(R.id.newsdate);
//        String formattedNewsdate = currentNews.getNewsDate();
//        // Display the magnitude of the current earthquake in that TextView
//        newsdateView.setText(formattedNewsdate);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

}