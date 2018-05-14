package com.example.nickeldering.itunesimage;

import java.io.Serializable;

/**
 * Created by timbuchalka on 5/08/2016.
 */

class Photo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String mTitle;
    private String mTags;
    private String mImage;

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmTags(String mTags) {
        this.mTags = mTags;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    String getmTitle() {
        return mTitle;
    }

    String getmTags() {
        return mTags;
    }

    String getmImage() {
        return mImage;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "mTitle='" + mTitle + '\'' +
                ", mTags='" + mTags + '\'' +
                ", mImage='" + mImage + '\'' +
                '}';
    }
}
