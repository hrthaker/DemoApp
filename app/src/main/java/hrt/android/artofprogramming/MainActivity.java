package hrt.android.artofprogramming;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Toolbar tb;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl = findViewById(R.id.rl);
        tb = findViewById(R.id.toolbar);
        tb.setTitle("ToolBar Demo...");
        setSupportActionBar(tb);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_red:
                rl.setBackgroundColor(Color.RED);
                break;
            case R.id.item_green:
                rl.setBackgroundColor(Color.GREEN);
                break;
            case R.id.item_blue:
                rl.setBackgroundColor(Color.BLUE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
