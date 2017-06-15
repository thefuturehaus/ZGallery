package com.mzelzoghbi.zgallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.mzelzoghbi.zgallery.activities.ZGalleryActivity;

import java.util.ArrayList;

/**
 * Created by mohamedzakaria on 8/11/16.
 */
public class ZGallery {
    private Context context;
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
    private boolean canShare = false;

    private ZGallery() {
    }

    /**
     * @param context   Refrence from current context
     * @param images Images to be displayed
     */
    public static ZGallery with(Context context, ArrayList<ZImage> images) {
        return new ZGallery(context, images);
    }


    private ZGallery(Context context, ArrayList<ZImage> images) {
        this.images = images;
        this.context = context;
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
     * Sets whether the images can be shared
     *
     * @param value
     * @return
     */
    public ZGallery setCanShare(boolean value) {
        this.canShare = value;
        return this;
    }

    /**
     * Start the gallery activity with builder settings
     */
    public void show() {
        Intent activity = new Intent(context, ZGalleryActivity.class);
        activity.putExtra(Constants.IntentPassingParams.IMAGES, images);
        activity.putExtra(Constants.IntentPassingParams.TITLE, title);
        activity.putExtra(Constants.IntentPassingParams.TOOLBAR_COLOR, toolbarColor);
        activity.putExtra(Constants.IntentPassingParams.CAPTION_COLOR, captionColor);
        activity.putExtra(Constants.IntentPassingParams.TOOLBAR_TITLE_COLOR, color);
        activity.putExtra(Constants.IntentPassingParams.SELECTED_IMG_POS, selectedImgPosition);
        activity.putExtra(Constants.IntentPassingParams.BG_COLOR, backgroundColor);
        activity.putExtra(Constants.IntentPassingParams.HORIZONTAL_LIST, showHorizontalList);
        activity.putExtra(Constants.IntentPassingParams.CAN_SHARE, canShare);
        context.startActivity(activity);
    }
}
