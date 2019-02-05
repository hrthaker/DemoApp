package hrt.android.artofprogramming;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class DisplayImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String s = getIntent().getExtras().getString("img");
        ImageView iv = new ImageView(getApplicationContext());
        Bitmap bitmap = BitmapFactory.decodeFile(s);
        iv.setImageBitmap(bitmap);
        setContentView(iv);
    }
}
