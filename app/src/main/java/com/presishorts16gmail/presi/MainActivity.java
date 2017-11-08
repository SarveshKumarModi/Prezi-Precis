package com.presishorts16gmail.presi;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ViewPager viewPager;
    ViewPagerAdapter adapter;

    private String[] images = new String[]
            {"https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/juno%20copy.jpg?alt=media&token=d8cd1d00-3d54-42d4-aae1-14145061e78b",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/land%20copy.jpg?alt=media&token=56943343-6525-4b0e-be4f-c598d539b86d",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/lucid%20copy.jpg?alt=media&token=cba85e9b-71a2-4f15-bb6c-ef801f764ede",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/moskio%20copy.jpg?alt=media&token=d4651c52-3b8b-4d8a-a4dc-0e845cd34a3d",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/santa%20copy.jpg?alt=media&token=d0e00605-0deb-43ae-aa9f-6891af6dd667",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/bluetooth%20copy.jpg?alt=media&token=02111156-969f-440f-9492-641df40d8caf",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/bms%20copy.jpg?alt=media&token=bacaeb03-48cd-411a-aefc-eb6683e39531",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/capita%20copy.jpg?alt=media&token=37539837-6464-4262-a199-0697b531c4f3",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/cars%20copy.jpg?alt=media&token=95aeafca-75ab-4687-8302-2d4f603301e8",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/device%20copy.jpg?alt=media&token=74338561-1f14-46fe-97c2-9e32862ca2eb",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/digital%20tree%20copy.jpg?alt=media&token=3642cc58-3215-4a5a-81e4-6072e2afe9b4",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/facepass%20copy.jpg?alt=media&token=3a0786e7-fd2b-4cbc-ae9f-496ca3a4eb60",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/ftipass%20copy.jpg?alt=media&token=a911b304-790c-4fa5-8878-4f260a32e10c",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/gatebox%20ai%20copy.jpg?alt=media&token=00e9bc63-09a0-49d0-b9c5-afbe2c872d70",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/golden%20toys%20copy.jpg?alt=media&token=a577252a-ea9c-4e04-b64e-f9413164dc73",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/india%20copy.jpg?alt=media&token=8b0782d3-351b-4999-af08-f41d5780abdb",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/insulin%20copy.jpg?alt=media&token=d18d52f0-febd-4dd0-9fde-a19bda1db295",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/jio%20copy.jpg?alt=media&token=20106807-f7ba-493e-8c61-b0ee042c87a8",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/skype%20volvo%20copy.jpg?alt=media&token=fcc1ba3d-78eb-4b49-8cd3-9e750af81773",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/smart%20bed%20copy.jpg?alt=media&token=cf4dc3d5-b53b-4f06-aa76-d307fe88e162",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/smart%20fridge%20cam%20copy.jpg?alt=media&token=5b009d49-14ef-4f59-ba4e-1cadf450fd73",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/smart%20remote%20copy.jpg?alt=media&token=65e45cbb-90db-4ab2-9a5d-ef79d50d622f",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/solarroad%20copy.jpg?alt=media&token=8b9d946f-fc0b-4c99-83f0-af5bf3e98ccb",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/sos%20copy.jpg?alt=media&token=4fac13a9-8eb7-41b8-9f21-df17c81632a0",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/t%2B%20copy.jpg?alt=media&token=6113d27b-57b0-43c1-916b-832ef4ec59eb",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/tmobile%20copy.jpg?alt=media&token=eb787af6-1956-4235-b1ff-f50046200462",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/travel%20copy.jpg?alt=media&token=09ae97ce-fd69-4be6-8b53-683820fcc236",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/uber%20copy.jpg?alt=media&token=27434109-86e7-4a03-a45b-63e617e742b2",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/voice%20fridge%20copy.jpg?alt=media&token=5cad7beb-764d-417a-b493-7884d1c73e52",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/wair%20mask%20copy.jpg?alt=media&token=3a22fe9b-781b-4894-b45e-9b4fa6209ad5",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/wair%20mask%20copy.jpg?alt=media&token=3a22fe9b-781b-4894-b45e-9b4fa6209ad5",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/woke%20up%20smartfone%20%20copy.jpg?alt=media&token=2af928f8-fe93-41e0-af3b-c9dafcdc9227",
                    "https://firebasestorage.googleapis.com/v0/b/presishorts-b29c0.appspot.com/o/yesbank%20copy.jpg?alt=media&token=faf30e9e-118f-4442-b40a-5605cd186bf9"
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(this).memoryCacheSize(2097152).discCacheSize(314572800).defaultDisplayImageOptions(new DisplayImageOptions.Builder().cacheOnDisc(true).cacheInMemory(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT).displayer(new FadeInBitmapDisplayer(300)).build()).memoryCacheExtraOptions(480, 800).diskCacheExtraOptions(480, 800, null).build());

        viewPager=(ViewPager)findViewById(R.id.view_pager);

        adapter = new ViewPagerAdapter(MainActivity.this,images,ImageLoader.getInstance());
        viewPager.setAdapter(adapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_daysettings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_bookmark) {

        } else if (id == R.id.nav_logout) {


        }  else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
