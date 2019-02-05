package hrt.android.artofprogramming;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MediastoreImageDemoActivity extends AppCompatActivity {

    Toolbar tb1;
    GridView gv;
    int count=0;
    Cursor c = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediastore_image_demo);
        tb1 = findViewById(R.id.tb1);
        gv = findViewById(R.id.gv1);
        tb1.setTitle("Mediastore CP Demo");
        setSupportActionBar(tb1);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 111);
        } else {
            bindata();
        }

    }

    public void bindata() {
//        c = getContentResolver().query(MediaStore.Files.getContentUri("external"), null, null,
                //null);
        c = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Thumbnails.DATA},null,null);
        Toast.makeText(getApplicationContext(),""+c.getCount(),Toast.LENGTH_LONG).show();
        count = c.getCount();
        gv.setAdapter(new ImageAdapter(getApplicationContext()));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                c.moveToPosition(position);
                String s = c.getString(0);
                Intent i = new Intent(getApplicationContext(),DisplayImageActivity.class);
                i.putExtra("img", s);
                startActivity(i);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 111:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    bindata();
                }
                break;
            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private class ImageAdapter extends BaseAdapter {
        Context ct;
        public ImageAdapter(Context ct) {
            this.ct=ct;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d("tag","inside getview");
            ImageView iv = new ImageView(ct.getApplicationContext());
            c.moveToPosition(position);
            String s = c.getString(0);//c.getColumnIndex(MediaStore.Images.Thumbnails.DATA));
            Bitmap b = BitmapFactory.decodeFile(s);
            iv.setImageBitmap(b);
            iv.setPadding(2, 2, 2, 2);
            iv.setLayoutParams(new GridView.LayoutParams(300,300));
            return iv;
        }
    }
}














