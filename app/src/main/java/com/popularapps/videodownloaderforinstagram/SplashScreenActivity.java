package com.popularapps.videodownloaderforinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_spalsh);

    Handler handler=new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        startActivities(new Intent[]{new Intent(SplashScreenActivity.this, MainActivity.class)});
        finish();
      }
    },1000);



  }
}
