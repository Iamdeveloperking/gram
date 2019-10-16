package com.popularapps.videodownloaderforinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.popularapps.videodownloaderforinstagram.adapter.StatusImageAdapter;
import com.popularapps.videodownloaderforinstagram.listener.RecyclerTouchListener;
import com.popularapps.videodownloaderforinstagram.utill.HelpperMethods;
import com.popularapps.videodownloaderforinstagram.utill.Loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class downloadFragment1 extends Fragment{

    RecyclerView recyclerView;

    public downloadFragment1() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);



        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_image, container, false);

            setupView(root);
            return  root;
        }

    public void setupView(ViewGroup root) {
        recyclerView =  root.findViewById(R.id.recyclerViewImage);
        setUPList();
    }

    private void setUPList() {

        List<Integer> images = new ArrayList<Integer>(0);

        Log.i("LOAD", "------------- " + Environment.getExternalStorageDirectory());
        Log.i("LOAD", "------------- " + Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.i("LOAD", "------------- " + Environment.getExternalStorageDirectory().getAbsolutePath() + "/InstantInsta/");

        final File hiddenpath = new File(Environment.getExternalStorageDirectory() + "/InstantInsta/");

        boolean success= true;
        if (!hiddenpath.exists()) {
            success= hiddenpath.mkdirs();
        }

            if(success){
            Log.i("LOAD", "------------- " + hiddenpath);
            String[] fileName = hiddenpath.list();

            final ArrayList<String> imageFiles = new ArrayList<>();
            for (String f : fileName) {
                Log.i("LOAD", "------------- " + f);
                if (HelpperMethods.isImage(f)) {
                    imageFiles.add(f);
                }
            }


//        java.lang.NullPointerException: Attempt to get length of null array
//        at avd.downloader.downloadFragment.setUPList(downloadFragment.java:68)
//        at avd.downloader.downloadFragment.setupView(downloadFragment.java:52)
//        at avd.downloader.downloadFragment.onCreateView(downloadFragment.java:46)

            Log.i("LOAD", "------------- ? " + hiddenpath.canRead());


            new Loader().loadImages(Environment.getExternalStorageState());

            for (int i = 0; i < 20; i++)
                images.add(i);

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
            StatusImageAdapter statusImageAdapter = new StatusImageAdapter(imageFiles, hiddenpath.getAbsolutePath());
            recyclerView.setAdapter(statusImageAdapter);
            recyclerView.setLayoutManager(gridLayoutManager);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {

                    Intent intent = new Intent(getActivity(), downloadViewActivity1.class);
                    intent.putExtra("path", hiddenpath.getAbsolutePath() + "/" + imageFiles.get(position));

                    startActivity(intent);

                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));

        }

        else
        {

            Toast.makeText(getActivity(), "Files does not Exist", Toast.LENGTH_SHORT).show();

        }
    }

}
