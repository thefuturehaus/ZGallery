package com.mzelzoghbi.zgallery.activities;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mzelzoghbi.zgallery.Constants;
import com.mzelzoghbi.zgallery.R;
import com.mzelzoghbi.zgallery.ZImage;

import java.util.ArrayList;

/**
 * Created by mohamedzakaria on 8/11/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    protected ArrayList<ZImage> images;
    protected int toolbarTitleColor;
    protected int toolbarColor;
    protected boolean showHorizontalList;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayoutId());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        // get values
        images = (ArrayList<ZImage>) getIntent().getSerializableExtra(Constants.IntentPassingParams.IMAGES);
        toolbarColor = getIntent().getIntExtra(Constants.IntentPassingParams.TOOLBAR_COLOR, Color.BLACK);
        title = getIntent().getStringExtra(Constants.IntentPassingParams.TITLE);
        toolbarTitleColor = getIntent().getIntExtra(Constants.IntentPassingParams.TOOLBAR_TITLE_COLOR, Color.WHITE);
        showHorizontalList = getIntent().getBooleanExtra(Constants.IntentPassingParams.HORIZONTAL_LIST, true);

        if (getSupportActionBar() == null) {
            setSupportActionBar(mToolbar);
            mToolbar.setVisibility(View.VISIBLE);

            mToolbar.setTitleTextColor(toolbarTitleColor);

            BitmapDrawable bmpDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.tinted_arrow_back, getTheme());
            bmpDrawable.setTint(toolbarTitleColor);
            getSupportActionBar().setHomeAsUpIndicator(bmpDrawable);

            mToolbar.setBackgroundColor(toolbarColor);

            if (title != null) {
                getSupportActionBar().setTitle(title);
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        } else {
            mToolbar.setVisibility(View.GONE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        afterInflation();
    }


    protected abstract int getResourceLayoutId();

    protected abstract void afterInflation();
}
