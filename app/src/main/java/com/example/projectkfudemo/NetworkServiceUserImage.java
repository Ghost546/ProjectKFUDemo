package com.example.projectkfudemo;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.projectkfudemo.ui.RetrofitGetUserImage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static io.fabric.sdk.android.Fabric.TAG;

public class NetworkServiceUserImage extends Thread {
    private static NetworkServiceUserImage mInstance;
    private static final String BASE_URL = "https://portal-dis.kpfu.ru/"; //основной адрес
    private Retrofit mRetrofit;
    public String url;
    Bitmap bmWithPicture = null;

    private NetworkServiceUserImage() {
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void run() {

    }

    public Bitmap getBmWithPicture() {
        if(bmWithPicture != null) {
            return bmWithPicture;
        } else {
            System.out.println("Изображение не дошло");
        }
        return null;
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "Error getting bitmap", e);
        }
        return bm;
    }

}
