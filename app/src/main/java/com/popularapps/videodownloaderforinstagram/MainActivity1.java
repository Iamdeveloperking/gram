package com.popularapps.videodownloaderforinstagram;


import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity1 extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageView bt;
    ImageView bt2;
    private int STORAGE_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main2);

        MobileAds.initialize(this, "ca-app-pub-6410525103222610~4328151967");

        AdView adView = findViewById(R.id.adView112);

        AdRequest adRequest1 = new AdRequest.Builder().build();
        adView.loadAd(adRequest1);











        File myDirectory = new File(Environment.getExternalStorageDirectory(), "InstantInsta");

        if(!myDirectory.exists()) {
            myDirectory.mkdirs();
        }else{
            Log.e("directory", "folder already exists");
        }



        viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        bt =  findViewById(R.id.Button2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "Insta Downloader can make better experience ## https://play.google.com/store/apps/details?id=com.popularapps.videodownloaderforinstagram ## put 5 Star and Review";
                String shareSUb = "code";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareBody);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);

                startActivity(Intent.createChooser(myIntent,"Share Via"));
            }
        });

        RateUs.app_launched(this);

//        if (!checkPermission()) {
//            openActivity();
//        } else {
//            if (checkPermission()) {
//                requestPermissionAndContinue();
//
//            } else {
//                openActivity();
//            }
//        }

//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "You have already granted this permission!",
//                    Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        } else {
//            requestStoragePermission();
//        }




    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setCancelable(true);
            alertBuilder.setTitle(getString(R.string.permission_necessary));
            alertBuilder.setMessage(R.string.storage_permission_is_encessary_to_wrote_event);
            alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(MainActivity1.this, new String[]{WRITE_EXTERNAL_STORAGE
                            , READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                }
            });
            AlertDialog alert = alertBuilder.create();
            alert.show();
            Log.e("", "permission denied, show dialog");

        } else {
            ActivityCompat.requestPermissions(MainActivity1.this, new String[]{WRITE_EXTERNAL_STORAGE,
                    READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        adapter.addFragment(new downloadFragment1(), "SAVED IMAGES");
        adapter.addFragment(new downloadVideoFragment1(), "SAVED VIDEOS");



        viewPager.setAdapter(adapter);
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


//    private void requestStoragePermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)) {
//
//            new android.app.AlertDialog.Builder(MainActivity.this)
//                    .setTitle("Permission needed")
//                    .setMessage("This permission is needed because of this and that")
//                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            ActivityCompat.requestPermissions(MainActivity.this,
//                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
//                        }
//                    })
//                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
//                    .create().show();
//
//        } else {
//            ActivityCompat.requestPermissions(MainActivity.this,
//                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == STORAGE_PERMISSION_CODE)  {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//            } else {
//                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

//    private static final int PERMISSION_REQUEST_CODE = 200;
//    private boolean checkPermission() {
//
//        return ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
//    }
//
//
//    private void requestPermissionAndContinue() {
//        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)
//                    && ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
//                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
//                alertBuilder.setCancelable(true);
//                alertBuilder.setTitle(getString(R.string.permission_necessary));
//                alertBuilder.setMessage(R.string.storage_permission_is_encessary_to_wrote_event);
//                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//                    public void onClick(DialogInterface dialog, int which) {
//                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE
//                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
//                    }
//                });
//                AlertDialog alert = alertBuilder.create();
//                alert.show();
//                Log.e("", "permission denied, show dialog");
//            } else {
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE,
//                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
//
//            }
//        } else {
//
//            openActivity();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (permissions.length > 0 && grantResults.length > 0) {
//
//                boolean flag = true;
//                for (int i = 0; i < grantResults.length; i++) {
//                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                        flag = false;
//                    }
//                }
//                if (flag) {
//                    openActivity();
//                } else {
//                    finish();
//                }
//
//            } else {
//                finish();
//            }
//        } else {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }
//
//    private void openActivity() {
//        //add your further process after giving permission or to download images from remote server.
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }

//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//            onRequestPermissionsResultCallback.onRequestPermissionsResult(requestCode, permissions,
//                    grantResults);


    }


}
