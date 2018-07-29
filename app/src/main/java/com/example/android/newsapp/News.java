package com.example.android.newsapp;

/**News
 * An {@link News} object contains information related to a single earthquake.
 */
public class News {

    /**
     * Date of news  (mMagnitude)
     */
    private String mNewsDate;

    /**
     * Section of news  (mLocation)
     */
    private String mSection;

    /**
     * Time of the earthquake(mTimeInMilliseconds)
     */
    private String mTitle;

    /**
     * Website URL of the earthquake (mUrl)
     */
    private String mImageUrl;

    private String mContributor;

    /**
     * Constructs a new {@link News} object.
     *
//     * @param newsDate          is the date of news
//     * @param section           is the location where the earthquake happened
//     * @param title             is the title of news item
//     * @param imageUrl          is the website URL to the new thumbnail
//     * @param contributor
     */
//    public News(String newsDate, String section, String title, String imageUrl, String contributor) {
//        mNewsDate = newsDate;
//        mSection = section;
//        mTitle = title;
//        mImageUrl = imageUrl;
//        mContributor = contributor;
//    }

//    public News(String title, String section, String date, String contributor, String imageUrl) {
//        mTitle = title;
//        mSection = section;
//        mNewsDate = date;
//        mContributor = contributor;
//        mImageUrl = imageUrl;
//    }

    public News(String title, String section, String date, String contributor) {
        mTitle = title;
        mSection = section;
        mNewsDate = date;
        mContributor = contributor;
    }

    /**
     * Returns the date of news.
     */
    public String getNewsDate() {
        return mNewsDate;
    }

    /**
     * Returns the news section.
     */
    public String getSection() {
        return mSection;
    }

    /**
     * Returns the time of the earthquake.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Returns the website URL to find more information about image.
     */
    public String getImageUrl() {
        return mImageUrl;
    }

    public String getContributor(){ return mContributor;}
}