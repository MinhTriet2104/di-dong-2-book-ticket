package mt.movieticketbooking.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView imageView;

    public DownloadImageTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urlOfImage = urls[0];
        Bitmap imageMovie = null;

        try{
            InputStream is = new URL(urlOfImage).openStream();
            imageMovie = BitmapFactory.decodeStream(is);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return imageMovie;

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
