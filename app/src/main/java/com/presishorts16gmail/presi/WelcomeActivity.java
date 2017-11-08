package com.presishorts16gmail.presi;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private Button btnNext;
    private Button btnSkip;
    private TextView[] dots;
    private LinearLayout dotsLayout;
    private int[] layouts;
    private MyViewPagerAdapter myViewPagerAdapter;
    private PrefManager prefManager;
    private ViewPager viewPager;
    OnPageChangeListener viewPagerPageChangeListener = new OnPageChangeListener() {
        public void onPageSelected(int position) {
            WelcomeActivity.this.addBottomDots(position);
            if (position == WelcomeActivity.this.layouts.length - 1) {
                WelcomeActivity.this.btnNext.setText(WelcomeActivity.this.getString(R.string.start));
                WelcomeActivity.this.btnSkip.setVisibility(8);
                return;
            }
            WelcomeActivity.this.btnNext.setText(WelcomeActivity.this.getString(R.string.next));
            WelcomeActivity.this.btnSkip.setVisibility(0);
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }
    };

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public Object instantiateItem(ViewGroup container, int position) {
            this.layoutInflater = (LayoutInflater) WelcomeActivity.this.getSystemService("layout_inflater");
            View view = this.layoutInflater.inflate(WelcomeActivity.this.layouts[position], container, false);
            container.addView(view);
            return view;
        }

        public int getCount() {
            return WelcomeActivity.this.layouts.length;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.prefManager = new PrefManager(this);
        if (!this.prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
        if (VERSION.SDK_INT >= 22) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        setContentView(R.layout.activity_welcome);
        this.viewPager = (ViewPager) findViewById(R.id.view_pager);
        this.dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        this.btnSkip = (Button) findViewById(R.id.btn_skip);
        this.btnNext = (Button) findViewById(R.id.btn_next);
        this.layouts = new int[]{R.layout.welcome_slide1, R.layout.welcome_slide2, R.layout.welcome_slide3, R.layout.welcome_slide4};
        addBottomDots(0);
        changeStatusBarColor();
        this.myViewPagerAdapter = new MyViewPagerAdapter();
        this.viewPager.setAdapter(this.myViewPagerAdapter);
        this.viewPager.addOnPageChangeListener(this.viewPagerPageChangeListener);
        this.btnSkip.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                WelcomeActivity.this.startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        });
        this.btnNext.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int current = WelcomeActivity.this.getItem(1);
                if (current < WelcomeActivity.this.layouts.length) {
                    WelcomeActivity.this.viewPager.setCurrentItem(current);
                    return;
                }
                WelcomeActivity.this.startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                WelcomeActivity.this.launchHomeScreen();
            }
        });
    }

    private void addBottomDots(int currentPage) {
        this.dots = new TextView[this.layouts.length];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
        this.dotsLayout.removeAllViews();
        for (int i = 0; i < this.dots.length; i++) {
            this.dots[i] = new TextView(this);
            this.dots[i].setText(Html.fromHtml("&#8226;"));
            this.dots[i].setTextSize(35.0f);
            this.dots[i].setTextColor(colorsInactive[currentPage]);
            this.dotsLayout.addView(this.dots[i]);
        }
        if (this.dots.length > 0) {
            this.dots[currentPage].setTextColor(colorsActive[currentPage]);
        }
    }

    private int getItem(int i) {
        return this.viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        this.prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void changeStatusBarColor() {
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        }
    }
}
