package com.presishorts16gmail.presi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;


/**
 * Created by vishn on 4/23/2017.
 */

public class ViewPagerAdapter extends PagerAdapter
{
    Activity activity;
    ImageLoader imageLoader;
    String[] images;
    LayoutInflater inflater;
    private DisplayImageOptions options;


    ViewPagerAdapter(Context context) {
        inflater = LayoutInflater.from(context);

         options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_logo1)
                .showImageOnFail(R.drawable.ic_logo1)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
    }

    public ViewPagerAdapter(Activity activity, String[] images, ImageLoader imageLoader) {
        this.activity = activity;
        this.images = images;
        this.imageLoader = imageLoader;
    }

    public int getCount() {
        return this.images.length;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview = inflater.inflate(R.layout.viewpager_item,container,false);


        ImageView imageView= (ImageView)itemview.findViewById(R.id.imageView);
        imageLoader.displayImage(images[position],imageView);
        container.addView(itemview);
        return itemview;
    }

    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
