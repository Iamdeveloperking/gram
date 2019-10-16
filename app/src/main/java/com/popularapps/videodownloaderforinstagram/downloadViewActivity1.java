package com.popularapps.videodownloaderforinstagram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import uk.co.senab.photoview.PhotoViewAttacher;

public class downloadViewActivity1 extends AppCompatActivity {

    ImageView imageView;
    ImageView floatingActionButton3;
    ImageView bt;
    VideoView videoView;
    CardView imageCardView,VideoCardView;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image1);


        MobileAds.initialize(this, "ca-app-pub-6410525103222610~4328151967");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6410525103222610/8526363372");
        AdRequest adRequest = new AdRequest.Builder().build();
//        AdRequest adRequest = new AdRequest.Builder().addTestDevice("533109D10C201C22272F458C2875819E").build();
        mInterstitialAd.loadAd(adRequest);




        AdView adView = findViewById(R.id.adView);

        AdRequest adRequest1 = new AdRequest.Builder().build();
        adView.loadAd(adRequest1);

        if (Build.VERSION.SDK_INT >= 28) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        imageCardView =  findViewById(R.id.imageCardView);
        VideoCardView =  findViewById(R.id.VideoCardView);

        imageView =findViewById(R.id.detail_imageViews);
        PhotoViewAttacher pAttacher;
        pAttacher = new PhotoViewAttacher(imageView);
        pAttacher.update();

        final Intent intent = getIntent();
        final String imageUrl =intent.getStringExtra("path");


        if (imageUrl.endsWith(".mp4")) {


            VideoCardView.setVisibility(View.VISIBLE);
            imageCardView.setVisibility(View.GONE);
            videoView = findViewById(R.id.detail_imageViews1);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            Uri videoUri = Uri.parse(imageUrl);
            videoView.setVideoURI(videoUri);
            videoView.setMediaController(mediaController);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(false);
                    mp.setScreenOnWhilePlaying(true);
                    mp.getDuration();


                            videoView.start();

                }
            });

            ImageView capture_share =   findViewById(R.id.imageView10);
            capture_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Uri videoUri = Uri.parse(imageUrl);

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("video/mp4");
                    intent.putExtra(Intent.EXTRA_STREAM, videoUri);
                    startActivity(Intent.createChooser(intent, "Share"));

                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }

                }
            });





        }
        else {
            final Bitmap bitmap = BitmapFactory.decodeFile(imageUrl);
            imageView.setImageBitmap(bitmap);
            imageCardView.setVisibility(View.VISIBLE);

            ImageView capture_share =   findViewById(R.id.imageView9);
            capture_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Uri bitmapUri = Uri.parse(imageUrl);

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
                    startActivity(Intent.createChooser(intent, "Share"));

                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }

                }
            });
        }









        bt =  findViewById(R.id.Button2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "Instadownloader can make better experience ## https://play.google.com/store/apps/details?id=com.popularapps.videodownloaderforinstagram ## put 5 Star and Review";
                String shareSUb = "code";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareBody);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);

                startActivity(Intent.createChooser(myIntent,"Share Via"));
            }
        });
    }









}
