package com.popularapps.videodownloaderforinstagram;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.popularapps.videodownloaderforinstagram.adaptor.TabsPagerAdapter;
import com.popularapps.videodownloaderforinstagram.tabs.DownloadFragment;
import com.popularapps.videodownloaderforinstagram.tabs.HistoryFragment;
import com.popularapps.videodownloaderforinstagram.R;
public class MainActivity extends AppCompatActivity implements
    DownloadFragment.OnPostDownload{

  DrawerLayout androidDrawerLayout;
  ActionBarDrawerToggle actionBarDrawerToggle;
  NavigationView navigationView;
  Toolbar toolbar;
  TabLayout tabLayout;
  public ViewPager viewPager;
  private TabsPagerAdapter mAdapter;
  private WebView webView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initNavDrawerToggel();
    Utilities.getStoragePermission(MainActivity.this);

    MobileAds.initialize(this, "ca-app-pub-6410525103222610~4328151967");

//    AdView adView = findViewById(R.id.adView);
//
//    AdRequest adRequest1 = new AdRequest.Builder().build();
//    adView.loadAd(adRequest1);






  }

  private void initNavDrawerToggel() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    //toolbar.setTitle("InstaGrabber");

    // Initilization
    viewPager = (ViewPager) findViewById(R.id.pager);
    androidDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_design_support_layout);
    actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, androidDrawerLayout, R.string.app_name, R.string.app_name);


    mAdapter =  new TabsPagerAdapter(getSupportFragmentManager());
    viewPager.setAdapter(mAdapter);

    tabLayout = (TabLayout) findViewById(R.id.tab_layout);
    tabLayout.addTab(tabLayout.newTab().setText("Download"));
    tabLayout.addTab(tabLayout.newTab().setText("History"));

    tabLayout.setupWithViewPager(viewPager);



    androidDrawerLayout.addDrawerListener(actionBarDrawerToggle);
    navigationView = (NavigationView) findViewById(R.id.navigation_view);
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(MenuItem item) {

        //Checking if the item is in checked state or not, if not make it in checked state
        if (item.isChecked())
          item.setChecked(false);
        else
          item.setChecked(true);

        //Closing drawer on item click
        androidDrawerLayout.closeDrawers();

        switch (item.getItemId()) {
          case R.id.navDownload:
            TabLayout.Tab tab = tabLayout.getTabAt(0);
            tab.select();

            //Toast.makeText(MainActivity.this, "Item 1 Clicked", Toast.LENGTH_SHORT).show();
            break;
          case R.id.navHistory:

            TabLayout.Tab tab2 = tabLayout.getTabAt(1);
            tab2.select();
            //Toast.makeText(MainActivity.this, "Item 2 Clicked", Toast.LENGTH_SHORT).show();
            break;
          case R.id.navShare:

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);

            // todo enter app link here
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app at: https://play.google.com/store/apps/details?id=com.popularapps.videodownloaderforinstagram");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
            //Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
            break;
          default:
            break;
        }
        return true;
      }
    });


    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    actionBarDrawerToggle.syncState();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    actionBarDrawerToggle.onConfigurationChanged(newConfig);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    // This is required to make the drawer toggle work
    if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }

    /*
         * if you have other menu items in your activity/toolbar
         * handle them here and return true
     */

    switch (item.getItemId()) {
      case R.id.instalogo:

        callInstagram();

        return true;
      case R.id.share:

        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        String shareBody = "Insta downloader can make better experience ## https://play.google.com/store/apps/details?id=com.popularapps.videodownloaderforinstagram ## put 5 Star and Review";
        String shareSUb = "code";
        myIntent.putExtra(Intent.EXTRA_SUBJECT,shareBody);
        myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);

        startActivity(Intent.createChooser(myIntent,"Share Via"));

        return true;
      default:
        return super.onOptionsItemSelected(item);
    }

  }
  private void callInstagram() {
    String apppackage = "com.instagram.android";
    try {
      Intent i = getPackageManager().getLaunchIntentForPackage(apppackage);
      startActivity(i);
    } catch (Exception  e) {
      Toast.makeText(this, "Sorry, Instagram Apps Not Found", Toast.LENGTH_LONG).show();
    }

  }
  @Override public void refreshList() {
    Fragment fragment=mAdapter.getFragment(1);
    ((HistoryFragment) fragment).refresh();

  }

  public interface FragmentRefresh{
      void refresh();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }
}
