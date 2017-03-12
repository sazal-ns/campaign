/*
 * Copyright By Noor Nabiul Alam Siddiqui  (c) 2017.
 *  www.fb.com/sazal.ns
 */

package com.rtsoftbd.siddiqui.campaign.helpingHand;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.rtsoftbd.siddiqui.campaign.R;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by RTsoftBD_Siddiqui on 2017-03-12.
 */

public class DownImageTask extends AsyncTask<String,Void, Bitmap> {

    ImageView imageView;
    public static   Bitmap logo = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_face_white_48dp);

    public static Bitmap getLogo() {
        return logo;
    }

    public DownImageTask(ImageView imageView){
        this.imageView = imageView;
    }

    /*
        doInBackground(Params... params)
            Override this method to perform a computation on a background thread.
     */
    protected Bitmap doInBackground(String...urls){
        String urlOfImage = urls[0];
        logo = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_face_white_48dp);
        try{
            InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
            logo = BitmapFactory.decodeStream(is);
        }catch(Exception e){ // Catch the download exception
            e.printStackTrace();
        }
        return logo;
    }

    /*
        onPostExecute(Result result)
            Runs on the UI thread after doInBackground(Params...).
     */
    protected void onPostExecute(Bitmap result){
        imageView.setImageBitmap(result);
    }
}
