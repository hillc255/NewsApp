package com.example.android.newsapp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * An {@link NewsAdapter} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link News} objects).
 * <p>
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    //  private static final String LOCATION_SEPARATOR = " of ";

    //create new java.util.Date object
    Date date = new Date();
    Date date1;

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context  of the app
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

        // Find the ImageView with view
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
        String date = currentNews.getNewsDate();

        //Format string input
        String input = date.substring(0, 10);
        SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat sdfOut = new SimpleDateFormat("mm-dd-yyyy");
        Date date1 = null;
        try {
            date1 = sdfIn.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedNewsdate = sdfOut.format(date1);

        if (formattedContributor != null && !formattedContributor.isEmpty()) {
            String authordate = ("by " + formattedContributor + "  /  " + formattedNewsdate);
            contributorView.setText(authordate);
        } else {
            String authordate = (formattedNewsdate);
            contributorView.setText(authordate);
        }


        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }


}