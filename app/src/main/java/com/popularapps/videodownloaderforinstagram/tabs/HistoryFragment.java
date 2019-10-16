package com.popularapps.videodownloaderforinstagram.tabs;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.popularapps.videodownloaderforinstagram.MainActivity;
import com.popularapps.videodownloaderforinstagram.MainActivity1;
import com.popularapps.videodownloaderforinstagram.R;
import com.popularapps.videodownloaderforinstagram.adaptor.ImageRecyclerAdaptor;
import com.popularapps.videodownloaderforinstagram.database.DBController;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class HistoryFragment extends Fragment implements MainActivity.FragmentRefresh {

	ImageView ivSettings;
	private FragmentActivity mContext;
	private RecyclerView rvInsta;
	private Activity activity;
	FloatingActionButton bt2;
	private InterstitialAd mInterstitialAd;
	//DB
	private DBController dbcon;
	private int STORAGE_PERMISSION_CODE = 1;
	private ImageRecyclerAdaptor imageRecyclerAdaptor;

	public static HistoryFragment newInstance() {
		//Bundle args = new Bundle();
		//args.putString(ARG_PAGE, title);
		HistoryFragment fragment = new HistoryFragment();
		//fragment.setRetainInstance(true);
		//fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("Tag1","MoviesFrag");
mContext = getActivity();
		MobileAds.initialize(getActivity(), "ca-app-pub-6410525103222610~4328151967");

		mInterstitialAd = new InterstitialAd(mContext);
		mInterstitialAd.setAdUnitId("ca-app-pub-6410525103222610/8526363372");
		AdRequest adRequest = new AdRequest.Builder().build();
		mInterstitialAd.loadAd(adRequest);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {



		View rootView = inflater.inflate(R.layout.fragment_history, container, false);
		mContext =getActivity();
		//DB
		dbcon = new DBController(mContext);

		rvInsta= (RecyclerView) rootView.findViewById(R.id.rvInstaImages);
		imageRecyclerAdaptor = new ImageRecyclerAdaptor(mContext);
		rvInsta.setAdapter(imageRecyclerAdaptor);
		rvInsta.setLayoutManager(new LinearLayoutManager(mContext));
		bt2 = rootView.findViewById(R.id.imageView6);
		rvInsta.setHasFixedSize(true);
		rvInsta.setItemViewCacheSize(20);
		rvInsta.setDrawingCacheEnabled(true);
		rvInsta.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
		//rvInsta.s/

		imageRecyclerAdaptor.notifyDataSetChanged();


		bt2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (ContextCompat.checkSelfPermission(mContext,
						Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

					Intent intent = new Intent(mContext, MainActivity1.class);
					mContext.startActivity(intent);
				} else {
					requestStoragePermission();
				}
			}
		});




		return rootView;
	}

	private void requestStoragePermission() {
		if (ContextCompat.checkSelfPermission(mContext, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
				&& ContextCompat.checkSelfPermission(mContext, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

			android.support.v7.app.AlertDialog.Builder alertBuilder = new android.support.v7.app.AlertDialog.Builder(mContext);
			alertBuilder.setCancelable(true);
			alertBuilder.setTitle(getString(R.string.permission_necessary));
			alertBuilder.setMessage(R.string.storage_permission_is_encessary_to_wrote_event);
			alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
				@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
				public void onClick(DialogInterface dialog, int which) {
					ActivityCompat.requestPermissions(mContext, new String[]{WRITE_EXTERNAL_STORAGE
							, READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
				}
			});
			android.support.v7.app.AlertDialog alert = alertBuilder.create();
			alert.show();
			Log.e("", "permission denied, show dialog");

		} else {
			ActivityCompat.requestPermissions(mContext, new String[]{WRITE_EXTERNAL_STORAGE,
					READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
		}
	}

	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MobileAds.initialize(getActivity(), "ca-app-pub-6410525103222610~4328151967");
		AdView adView = mContext.findViewById(R.id.adViewcompletedownload);

//.addTestDevice("88D4207EA8C91C8238E5C2C879724669")
		AdRequest adRequest1 = new AdRequest.Builder().build();
		adView.loadAd(adRequest1);



	}

	@Override public void refresh() {
		if (imageRecyclerAdaptor!=null) {
			imageRecyclerAdaptor.onRefreshh();
		}
	}
}