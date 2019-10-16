package com.popularapps.videodownloaderforinstagram.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.popularapps.videodownloaderforinstagram.R;

import java.util.ArrayList;

public class StatusImageAdapter extends RecyclerView.Adapter<StatusImageAdapter.MyViewHolder> {

    private ArrayList<String> images;
    private String path;
    private Activity activity;


    public StatusImageAdapter(ArrayList<String> images, String path){
        this.images=images;
        this.path=path;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_item, null);
        MyViewHolder viewHolder = new MyViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
//        File currentStatus = images.get(i);
//
//        myViewHolder.imageStatusDownload.setOnClickListener(downloadMedia(currentStatus));


        BitmapFactory.Options options= new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeFile(path+"/"+images.get(i), options);
        myViewHolder.imageView.setImageBitmap(bitmap);
    }

//    private View.OnClickListener downloadMedia(final File currentFile) {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new Runnable(){
//                    @Override
//                    public void run() {
//                        try {
//                            Toast.makeText(activity, "Saving ...", Toast.LENGTH_SHORT).show();
//                            saveFile(currentFile, new File(LOCATION_TO_SAVE_TO + currentFile.getName()));
//                        } catch (IOException e) {
//                            Toast.makeText(activity, "Error! Could not save file.", Toast.LENGTH_SHORT).show();
//                        } finally {
//                            Toast.makeText(activity, "Done saving in Gallery", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }.run();
//            }
//        };
//    }
//
//
//    private void saveFile(File source, File destination) throws IOException {
//        if (!destination.getParentFile().exists())
//            destination.getParentFile().mkdirs();
//
//        if (!destination.exists())
//            destination.createNewFile();
//
//        FileChannel sourceChannel = null;
//        FileChannel destinationChannel = null;
//
//        try {
//            sourceChannel = new FileInputStream(source).getChannel();
//            destinationChannel = new FileOutputStream(destination).getChannel();
//            destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
//        } finally {
//            if (sourceChannel != null)
//                sourceChannel.close();
//
//            if (destinationChannel != null)
//                destinationChannel.close();
//        }
//    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        ImageView imageStatusDownload;
        CardView imageStatusCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.statusItem_imageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){

        }
    }
}
