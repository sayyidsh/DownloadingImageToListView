package pro.nanosystems.downloadingimagetolistview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sayyid on 01/04/2018.
 */

public class DownLoadImage extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = "DownLoadImage";
    private final WeakReference<ImageView> imageViewWeakReference;

    public DownLoadImage(ImageView imageView) {
        imageViewWeakReference = new WeakReference<>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return downloadImage(strings[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (isCancelled()) {
            bitmap = null;
        }
        if (imageViewWeakReference != null) {
            ImageView img = imageViewWeakReference.get();
            if (img != null) {
                if (bitmap != null) {
                    img.setImageBitmap(bitmap);
                } else {
                    Drawable placeHolder = img.getContext().getResources().getDrawable(R.drawable.placeholder);
                    img.setImageDrawable(placeHolder);
                }
            }

        }

    }

    private Bitmap downloadImage(String url) {
        HttpURLConnection conn = null;
        try {
            URL uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(1000);
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            if (inputStream != null) {
                Bitmap myImage = BitmapFactory.decodeStream(inputStream);
                return myImage;
            }

        } catch (Exception e) {
            if (conn != null) {
                conn.disconnect();
            }
            Log.w(TAG, "Error Download from : " + url);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }

        }
        return null;
    }
}
