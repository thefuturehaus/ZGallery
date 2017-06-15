package com.mzelzoghbi.zgallery.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mzelzoghbi.zgallery.Constants;
import com.mzelzoghbi.zgallery.CustomViewPager;
import com.mzelzoghbi.zgallery.OnImgClick;
import com.mzelzoghbi.zgallery.R;
import com.mzelzoghbi.zgallery.ZImage;
import com.mzelzoghbi.zgallery.adapters.HorizontalListAdapters;
import com.mzelzoghbi.zgallery.adapters.ViewPagerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mohamedzakaria on 8/11/16.
 */
public class ZGalleryActivity extends BaseActivity {
    private RelativeLayout mainLayout;

    CustomViewPager mViewPager;
    ViewPagerAdapter adapter;
    RecyclerView imagesHorizontalList;
    LinearLayoutManager mLayoutManager;
    HorizontalListAdapters hAdapter;
    private int currentPos;
    private int bgColor;
    private int captionColor;
    private boolean canShare;
    private SimpleTarget loadtarget;
    private MenuItem shareMenuItem;


    @Override
    protected int getResourceLayoutId() {
        return R.layout.activity_gallery;
    }

    @Override
    protected void afterInflation() {
        // init layouts
        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        mViewPager = (CustomViewPager) findViewById(R.id.pager);
        imagesHorizontalList = (RecyclerView) findViewById(R.id.imagesHorizontalList);

        // get intent data
        currentPos = getIntent().getIntExtra(Constants.IntentPassingParams.SELECTED_IMG_POS, 0);
        bgColor = getIntent().getIntExtra(Constants.IntentPassingParams.BG_COLOR, Color.BLACK);
        captionColor = getIntent().getIntExtra(Constants.IntentPassingParams.CAPTION_COLOR, Color.WHITE);
        canShare = getIntent().getBooleanExtra(Constants.IntentPassingParams.CAN_SHARE, false);

        mainLayout.setBackgroundColor(bgColor);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        // pager adapter
        adapter = new ViewPagerAdapter(this, images, mToolbar, imagesHorizontalList, captionColor);
        mViewPager.setAdapter(adapter);
        // horizontal list adaapter
        hAdapter = new HorizontalListAdapters(this, images, new OnImgClick() {
            @Override
            public void onClick(int pos) {
                mViewPager.setCurrentItem(pos, true);
            }
        });
        imagesHorizontalList.setLayoutManager(mLayoutManager);
        imagesHorizontalList.setAdapter(hAdapter);
        hAdapter.notifyDataSetChanged();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                imagesHorizontalList.smoothScrollToPosition(position);
                hAdapter.setSelectedItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        hAdapter.setSelectedItem(currentPos);
        mViewPager.setCurrentItem(currentPos);

        imagesHorizontalList.setVisibility(showHorizontalList ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_gallery, menu);

        shareMenuItem = menu.findItem(R.id.menu_share);
        shareMenuItem.getIcon().setColorFilter(toolbarTitleColor, PorterDuff.Mode.SRC_IN);
        if (!canShare) {
            menu.removeItem(shareMenuItem.getItemId());
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }
        if (itemId == R.id.menu_share) {
            share();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void share() {
        ProgressBar progress = new ProgressBar(this);
        progress.getIndeterminateDrawable().setColorFilter(toolbarTitleColor, PorterDuff.Mode.SRC_IN);
        shareMenuItem.setActionView(progress);

        int currentItem = mViewPager.getCurrentItem();
        ZImage image = images.get(currentItem);
        loadAndShare(image);
    }

    private void loadAndShare(final ZImage image) {
        if (loadtarget == null) {
            loadtarget = new SimpleTarget<GlideBitmapDrawable>() {
                @Override
                public void onResourceReady(GlideBitmapDrawable resource, GlideAnimation<? super GlideBitmapDrawable> glideAnimation) {
                    Uri fileUri = null;
                    try {
                        fileUri = handleLoadedBitmap(resource.getBitmap());
                    } catch (IOException e) {
                    }

                    showShareIntent(image.getCaption(), fileUri);
                }
            };
        }

        Glide.with(this).load(image.getUrl()).into(loadtarget);
    }

    public Uri handleLoadedBitmap(Bitmap b) throws IOException {
        // file download temporary
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");

        if (null != b) {
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            b.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.close();
        }

        return Uri.fromFile(file);
    }

    private void showShareIntent(String caption, Uri data) {
        shareMenuItem.setActionView(null);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpeg");
        shareIntent.putExtra(Intent.EXTRA_TEXT, caption);
        if (data != null) {
            shareIntent.putExtra(Intent.EXTRA_STREAM, data);
        }
        startActivity(Intent.createChooser(shareIntent, "Share"));
    }
}
