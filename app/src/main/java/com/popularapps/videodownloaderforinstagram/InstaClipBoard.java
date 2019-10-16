package com.popularapps.videodownloaderforinstagram;

import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;


public class InstaClipBoard extends Service {

    private ClipboardManager mClipboardManager;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mClipboardManager =
                (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        assert mClipboardManager != null;
        mClipboardManager.addPrimaryClipChangedListener(
                mOnPrimaryClipChangedListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private ClipboardManager.OnPrimaryClipChangedListener mOnPrimaryClipChangedListener =
            new ClipboardManager.OnPrimaryClipChangedListener() {
                @Override
                public void onPrimaryClipChanged() {



                    String str = InstaClipBoard.this.mClipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
                    if (str.matches("https://www.instagram.com/p/(.*)") || str.matches("https://www.instagram.com/(.*)/p/(.*)")) {
                                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(getApplicationContext().getPackageName());
                                if (launchIntent != null) {
                                    try {
                                        InstaClipBoard.this.startActivity(launchIntent);
                                    } catch (ActivityNotFoundException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }


                };


}