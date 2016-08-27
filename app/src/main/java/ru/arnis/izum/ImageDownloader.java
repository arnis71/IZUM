package ru.arnis.izum;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by arnis on 24/08/16.
 */
//Класс для загрузки картинок на фоне
public class ImageDownloader {

    private ArrayList<Bitmap> cachedImages;
    public ImageDownloader() {
        this.cachedImages = new ArrayList<>();
    }

    //скачиваем фото на фоне
    public void downloadImages(final ArrayList<Image> images){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Image image:images){
                    try {
                        URL urlConnection = new URL(image.getUrl());
                        cachedImages.add(BitmapFactory.decodeStream(urlConnection.openStream()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //устанавливаем фото по завершению загрузки
    public void requestImageFor(final ImageView imageView, final int index){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (cachedImages.size()<=index){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(cachedImages.get(index));
                    }
                });
            }
        }).start();
    }

    // чистим кэш
    public void clearCache(){
        cachedImages.clear();
    }

}
