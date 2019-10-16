package com.popularapps.videodownloaderforinstagram.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.popularapps.videodownloaderforinstagram.R;

import java.util.ArrayList;

public class StatusVideoAdapter extends RecyclerView.Adapter<StatusVideoAdapter.MyViewHolder> {


    private ArrayList<String> videos;
    private String path;
    private Context context;

    public StatusVideoAdapter(ArrayList<String> videos, String path, Context context){
        this.videos=videos;
        this.path=path;
        this.context=context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item, null);
        MyViewHolder viewHolder = new MyViewHolder(itemLayoutView);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {



        Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(path+"/"+videos.get(i), MediaStore.Images.Thumbnails.MINI_KIND);

        BitmapDrawable bitmapD = new BitmapDrawable(thumbnail);
        myViewHolder.videoView.setBackground(bitmapD);

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        VideoView videoView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView=itemView.findViewById(R.id.statusItem_videoView);



        }



    }
}
