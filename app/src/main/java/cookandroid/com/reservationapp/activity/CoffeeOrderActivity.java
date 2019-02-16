package cookandroid.com.reservationapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cookandroid.com.reservationapp.R;
import cookandroid.com.reservationapp.model.MenuItem;

public class CoffeeOrderActivity extends AppCompatActivity {

    String coffeeName;
    int coffeePrice;
    int coffeeNum;
    String coffeeTemp;
    String coffeeSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);

        MenuItem item = (MenuItem)getIntent().getSerializableExtra("menu");


        final ImageView orderImg = (ImageView) findViewById(R.id.orderImg);
        final TextView orderName = (TextView) findViewById(R.id.orderName);
        final TextView orderPrice = (TextView) findViewById(R.id.orderPrice);

        Glide.with(this)
                .load(item.getImg())
                .into(orderImg);

        orderName.setText(item.getName());
        orderPrice.setText(item.getPrice());



        final TextView orderNum = (TextView) findViewById(R.id.orderNum);
        final ImageButton plusButton = (ImageButton) findViewById(R.id.plusButton);
        final ImageButton minusButton = (ImageButton) findViewById(R.id.minusButton);

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



       final RadioGroup sizeGroup = (RadioGroup) findViewById(R.id.sizeGroup);
       final int sizeGroupID = sizeGroup.getCheckedRadioButtonId();
        coffeeSize = ((RadioButton) findViewById(sizeGroupID)).getText().toString();

        sizeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton genderButton = (RadioButton) findViewById(i);
                coffeeSize = genderButton.getText().toString();

            }
        });



        final Button orderButton = (Button) findViewById(R.id.c_orderButton);
        final Button putButton = (Button) findViewById(R.id.c_putButton);

        // 주문하기
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coffeeName = orderName.getText().toString();
                coffeePrice = Integer.parseInt(orderPrice.getText().toString());
                coffeeNum = Integer.parseInt(orderNum.getText().toString());
                // coffeeTemp = orderTemp.getText().toString();

               Intent intent = new Intent(CoffeeOrderActivity.this, ReservationActivity.class);

               intent.putExtra("coffeeSize",coffeeSize);
               CoffeeOrderActivity.this.startActivity(intent);

            }
        });






    }
}
