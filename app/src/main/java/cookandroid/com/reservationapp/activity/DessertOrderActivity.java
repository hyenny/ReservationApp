package cookandroid.com.reservationapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cookandroid.com.reservationapp.R;
import cookandroid.com.reservationapp.model.MenuItem;

public class DessertOrderActivity extends AppCompatActivity {

    String dessertName;
    int dessertPrice;
    int dessertNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert_order);

        MenuItem item = (MenuItem)getIntent().getSerializableExtra("menu");

        final ImageView orderImg = (ImageView) findViewById(R.id.d_orderImg);
        final TextView orderName = (TextView) findViewById(R.id.d_orderName);
        final TextView orderPrice = (TextView) findViewById(R.id.d_orderPrice);

        Glide.with(this)
                .load(item.getImg())
                .into(orderImg);

        orderName.setText(item.getName());
        orderPrice.setText(item.getPrice());



        final TextView orderNum = (TextView) findViewById(R.id.d_orderNum);
        final ImageButton plusButton = (ImageButton) findViewById(R.id.d_plusButton);
        final ImageButton minusButton = (ImageButton) findViewById(R.id.d_minusButton);

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(orderNum.getText().toString());
                count++;
                orderNum.setText(String.valueOf(count));
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(orderNum.getText().toString());
                count--;
                orderNum.setText(String.valueOf(count));
            }
        });


        final Button orderButton = (Button) findViewById(R.id.d_orderButton);
        final Button putButton = (Button) findViewById(R.id.d_putButton);

        // 주문하기
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dessertName = orderName.getText().toString();
                dessertPrice = Integer.parseInt(orderPrice.getText().toString());
                dessertNum = Integer.parseInt(orderNum.getText().toString());
                // coffeeTemp = orderTemp.getText().toString();

                Intent intent = new Intent(DessertOrderActivity.this, ReservationActivity.class);

                DessertOrderActivity.this.startActivity(intent);

            }
        });
    }
}
