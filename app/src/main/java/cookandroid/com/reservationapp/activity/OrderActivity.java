package cookandroid.com.reservationapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cookandroid.com.reservationapp.R;
import cookandroid.com.reservationapp.model.MenuItem;

public class OrderActivity extends AppCompatActivity {
    ImageView img;
    TextView name, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        MenuItem item = (MenuItem)getIntent().getSerializableExtra("menu");


        img = (ImageView) findViewById(R.id.orderImg);
        name = (TextView) findViewById(R.id.orderName);
        price = (TextView) findViewById(R.id.orderPrice);

        Glide.with(this)
                .load(item.getImg())
                .into(img);

        name.setText(item.getName());
        price.setText(item.getPrice());

    }
}
