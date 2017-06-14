package com.mzelzoghbi.zgallery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;

import com.mzelzoghbi.zgallery.activities.ZGridActivity;

import java.util.ArrayList;

/**
 * Created by mohamedzakaria on 8/7/16.
 */
public class ZGrid {
    private Activity mActivity;
    private ArrayList<ZImage> images;
    private String title;
    private int spanCount = 2;
    private int toolbarColor = Color.BLACK;
    private int imgPlaceHolderResId = -1;
    private int color;

    private ZGrid() {
    }

    /**
     * @param activity   Refrence from current activity
     * @param images Image URLs to be displayed
     */
    public static ZGrid with(Activity activity, ArrayList<ZImage> images) {
        return new ZGrid(activity, images);
    }


    private ZGrid(Activity activity, ArrayList<ZImage> images) {
        this.images = images;
        this.mActivity = activity;
    }

    /**
     * Set toolbar title
     *
     * @param title
     * @return
     */
    public ZGrid setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Set grid layout colums count (default: 2)
     *
     * @param count integer number for colum count
     * @return
     */
    public ZGrid setSpanCount(int count) {
        this.spanCount = count;
        return this;
    }

    /**
     * Setting toolbar {@link Color}
     *
     * @param color
     * @return
     */
    public ZGrid setToolbarColor(int color) {
        this.toolbarColor = color;
        return this;
    }

    /**
     * Set placeholder image for images in the grid
     * @param imgPlaceHolderResId
     * @return
     */
    public ZGrid setGridImgPlaceHolder(int imgPlaceHolderResId) {
        this.imgPlaceHolderResId = imgPlaceHolderResId;
        return this;
    }

    /**
     * Setting toolbar color
     *
     * @param color enum color may be black or white
     * @return
     */
    public ZGrid setToolbarTitleColor(int color) {
        this.color = color;
        return this;
    }
    /**
     * Start the grid activity with builder settings
     */
    public void show() {
        Intent gridActivity = new Intent(mActivity, ZGridActivity.class);
        gridActivity.putExtra(Constants.IntentPassingParams.IMAGES, images);
        gridActivity.putExtra(Constants.IntentPassingParams.COUNT, spanCount);
        gridActivity.putExtra(Constants.IntentPassingParams.TITLE, title);
        gridActivity.putExtra(Constants.IntentPassingParams.TOOLBAR_COLOR, toolbarColor);
        gridActivity.putExtra(Constants.IntentPassingParams.IMG_PLACEHOLDER, imgPlaceHolderResId);
        gridActivity.putExtra(Constants.IntentPassingParams.TOOLBAR_TITLE_COLOR, color);
        mActivity.startActivity(gridActivity);
    }
}
