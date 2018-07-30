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

// Based on ud843-Quake Report - Udemy Android Basics Application

/**
 * An {@link NewsAdapter} knows how to create a list item layout for each news item
 * in the data source (a list of {@link News} objects).
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsAdapter extends ArrayAdapter<News> {


    //create new java.util.Date object
    Date date = new Date();

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context  of the app
     * @param newsList is the list of news items, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<News> newsList) {
        super(context, 0, newsList);
    }

    /**
     * Returns a list item view that displays information about the news items  at the given position
     * in the list of news items.
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

        // Display the image of the current news item in the ImageView
        ImageView imageView = listItemView.findViewById(R.id.thumbnail);
        String formattedImage = currentNews.getImageUrl();
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


        TextView sectionView = listItemView.findViewById(R.id.section);
        String formattedSection = currentNews.getSection();
        // Display the section of the current news item in that TextView
        sectionView.setText(formattedSection);

        TextView titleView = listItemView.findViewById(R.id.title);
        String formattedTitle = currentNews.getTitle();
        // Display the title of the current news item in that TextView
        titleView.setText(formattedTitle);

        TextView contributorView = listItemView.findViewById(R.id.contributordate);
        String formattedContributor = currentNews.getContributor();
        // Display the contributor and date of the current news item in that TextView
        String date = currentNews.getNewsDate();

        // Format date string
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

        //Concatenate contributor and date if contributor exists
        if (formattedContributor != null && !formattedContributor.isEmpty()) {
            String authordate = ("by " + formattedContributor + "  /  " + formattedNewsdate);
            contributorView.setText(authordate);
        } else {
            contributorView.setText(formattedNewsdate);
        }

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}