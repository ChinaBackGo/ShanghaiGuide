package com.android.example.shanghaiguide;

/**
 * Place
 * This object contains data associated with a place
 */

public class Place {
    //Place brief description
    private String mPlaceDescriptionBrief = null;
    //Place detailed description
    private String mPlaceDescriptionDetailed = null;
    //Private int place image thumbnail id
    private int mImageThumbResourceId = NO_RESOURCE_PROVIDED;
    //Private int place image detailed resource id
    private int mImageDetailedResourceId = NO_RESOURCE_PROVIDED;;
    //Private int place rating
    private int mRating = NO_RESOURCE_PROVIDED;;
    //Private int location resource id
    private int mLocationResourceId = NO_RESOURCE_PROVIDED;;
    //Private static int final no resource state
    private static final int NO_RESOURCE_PROVIDED = -1;

    /**
     * Public Constructor
     * @param place_description_brief a brief description of the place
     * @param image_thumb_resource_id a thumbnail resource id
     * @param rating a rating of the place out of 10
     * @param location a location uri
     */
    public Place(String place_description_brief, int image_thumb_resource_id, int rating,
                int location) {
        mPlaceDescriptionBrief = place_description_brief;
        mImageThumbResourceId = image_thumb_resource_id;
        mRating = rating;
        mLocationResourceId = location;
    }

    /**
     * Get Method - Place description Brief
     * @return String Brief Description
     */
    public String getPlaceDescriptionBrief() {
        return mPlaceDescriptionBrief;
    }

    /**
     * Get Method - Place description detailed
     * @return String description detailed
     */
    public String getPlaceDescriptionDetailed() {
        return mPlaceDescriptionDetailed;
    }

    /**
     * Get Method - Place thumbnail
     * @return thumbnail image resource id
     */
    public int getImageThumbResourceId() {
        return mImageThumbResourceId;
    }

    /**
     * Get Method - Image detailed resource id
     * @return int image detailed id
     */
    public int getImageDetailedResourceId() {
        return mImageDetailedResourceId;
    }

    /**
     * Get Method - Place rating
     * @return int rating
     */
    public int getRating() {
        return mRating;
    }

    /**
     * Get Method - Location resource id
     * @return int location resource id
     */
    public int getLocationResourceId() {
        return mLocationResourceId;
    }

    /**
     * has Image - returns true/false if the place has an image thumbnail resource id associated
     * @return has image
     */
    public boolean hasThumbImage() {
        return mImageThumbResourceId != NO_RESOURCE_PROVIDED;
    }

    @Override
    public String toString() {
        return "Place{" +
                "mPlaceDescriptionBrief='" + mPlaceDescriptionBrief + '\'' +
                ", mPlaceDescriptionDetailed='" + mPlaceDescriptionDetailed + '\'' +
                ", mImageThumbResourceId=" + mImageThumbResourceId +
                ", mImageDetailedResourceId=" + mImageDetailedResourceId +
                ", mRating=" + mRating +
                ", mLocationResourceId=" + mLocationResourceId +
                '}';
    }
}
