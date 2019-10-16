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

import com.popularapps.videodownloaderforinstagram.adapter.StatusVideoAdapter;
import com.popularapps.videodownloaderforinstagram.listener.RecyclerTouchListener;
import com.popularapps.videodownloaderforinstagram.utill.HelpperMethods;
import com.popularapps.videodownloaderforinstagram.utill.Loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class downloadVideoFragment1 extends Fragment {

    private RecyclerView recyclerView;

    public downloadVideoFragment1() {
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
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_video, container, false);

        setupView(root);
        return  root;
    }

    private void setupView(ViewGroup root) {
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewVideo);
        setUPList();
    }

    private void setUPList() {List<Integer> images = new ArrayList<Integer>();

        Log.i("LOAD","------------- "+Environment.getExternalStorageDirectory());

        final File hiddenpath = new File(Environment.getExternalStorageDirectory() + "/InstantInsta/");

        boolean success= true;
        if (!hiddenpath.exists()) {
            success= hiddenpath.mkdirs();
        }

        if(success){

        Log.i("LOAD","------------- "+hiddenpath.getAbsolutePath());
        String[] fileName = hiddenpath.list();
        final ArrayList<String> videoFiles = new ArrayList<>();
        for(String f : fileName){
            Log.i("LOAD","------------- "+f);
            if(HelpperMethods.isVideo(f)){
                videoFiles.add(f);
            }
        }

        Log.i("LOAD","------------- ? "+hiddenpath.canRead());

        new Loader().loadImages(Environment.getExternalStorageState());


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        StatusVideoAdapter statusImageAdapter = new StatusVideoAdapter(videoFiles,hiddenpath.getAbsolutePath(),getContext());

        recyclerView.setAdapter(statusImageAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent =new Intent(getActivity(),downloadViewActivity1.class);
                intent.putExtra("path",hiddenpath.getAbsolutePath()+"/"+videoFiles.get(position));

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
