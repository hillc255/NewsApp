package com.example.android.newsapp;

// Based on ud843-Quake Report - Udemy Android Basics Application

/**
 * News
 * An {@link News} object contains information related to a single news item.
 */
public class News {

    // Time of the news
    private String mTitle;

    // Section of news
    private String mSection;


    // Date of news
    private String mNewsDate;


    // Contributor for news
    private String mContributor;

    // Image for news
    private String mImageUrl;

    //Detail for news
    private String mUrl;


    /**
     * Constructs a new {@link News} object.
     *
     * @param title       is the title of news itemi
     * @param section     is the location where the earthquake happened
     * @param date        is the date of news
     * @param contributor is the contributor of the news
     * @param imageUrl    is the website URL to the news thumbnail
     * @param url         is the website URL to the news item details
     */
    public News(String title, String section, String date, String contributor, String imageUrl, String url) {
        mTitle = title;
        mSection = section;
        mNewsDate = date;
        mContributor = contributor;
        mImageUrl = imageUrl;
        mUrl = url;
    }

    // Returns the time of the earthquake.
    public String getTitle() {
        return mTitle;
    }


    // Returns the news section.
    public String getSection() {
        return mSection;
    }


    // Returns the date of news.
    public String getNewsDate() {
        return mNewsDate;
    }


    // Returns the contributor of the news item
    public String getContributor() {
        return mContributor;
    }


    // Returns the website URL to find more information about image.
    public String getImageUrl() {
        return mImageUrl;
    }

    //Returns detail news information
    public String getUrl() {
        return mUrl;
    }

}