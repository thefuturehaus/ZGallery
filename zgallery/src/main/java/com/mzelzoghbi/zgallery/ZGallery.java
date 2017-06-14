package com.mzelzoghbi.zgallery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;

import com.mzelzoghbi.zgallery.activities.ZGalleryActivity;

import java.util.ArrayList;

/**
 * Created by mohamedzakaria on 8/11/16.
 */
public class ZGallery {
    private Activity mActivity;
    private ArrayList<ZImage> images;
    private String title;
    private int spanCount = 2;
    private int toolbarColor = Color.BLACK;
    private int imgPlaceHolderResId = -1;
    private int color;
    private int selectedImgPosition;
    private int backgroundColor;
    private int captionColor;
    private boolean showHorizontalList = true;

    private ZGallery() {
    }

    /**
     * @param activity   Refrence from current activity
     * @param images Images to be displayed
     */
    public static ZGallery with(Activity activity, ArrayList<ZImage> images) {
        return new ZGallery(activity, images);
    }


    private ZGallery(Activity activity, ArrayList<ZImage> images) {
        this.images = images;
        this.mActivity = activity;
    }

    /**
     * Set toolbar title
     *
     * @param title
     * @return
     */
    public ZGallery setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Setting toolbar {@link android.graphics.Color}
     *
     * @param color
     * @return
     */
    public ZGallery setToolbarColor(int color) {
        this.toolbarColor = color;
        return this;
    }

    /**
     * Setting toolbar {@link android.graphics.Color}
     *
     * @param color
     * @return
     */
    public ZGallery setToolbarTitleColor(int color) {
        this.color = color;
        return this;
    }

    /**
     * Setting starting position
     *
     * @param selectedImgPosition
     * @return
     */
    public ZGallery setSelectedImgPosition(int selectedImgPosition) {
        this.selectedImgPosition = selectedImgPosition;
        return this;
    }

    public ZGallery setGalleryBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public ZGallery setGalleryCaptionColor(int color) {
        this.captionColor = color;
        return this;
    }

    /**
     * Sets whether the horizontal list of images is shown
     *
     * @param value
     * @return
     */
    public ZGallery setShowHorizontalList(boolean value) {
        this.showHorizontalList = value;
        return this;
    }

    /**
     * Start the gallery activity with builder settings
     */
    public void show() {
        Intent gridActivity = new Intent(mActivity, ZGalleryActivity.class);
        gridActivity.putExtra(Constants.IntentPassingParams.IMAGES, images);
        gridActivity.putExtra(Constants.IntentPassingParams.TITLE, title);
        gridActivity.putExtra(Constants.IntentPassingParams.TOOLBAR_COLOR, toolbarColor);
        gridActivity.putExtra(Constants.IntentPassingParams.CAPTION_COLOR, captionColor);
        gridActivity.putExtra(Constants.IntentPassingParams.TOOLBAR_TITLE_COLOR, color);
        gridActivity.putExtra(Constants.IntentPassingParams.SELECTED_IMG_POS, selectedImgPosition);
        gridActivity.putExtra(Constants.IntentPassingParams.BG_COLOR, backgroundColor);
        gridActivity.putExtra(Constants.IntentPassingParams.HORIZONTAL_LIST, showHorizontalList);
        mActivity.startActivity(gridActivity);
    }
}
