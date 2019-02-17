package cookandroid.com.reservationapp.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.sql.Date;

import cookandroid.com.reservationapp.R;

// 주문 날짜, 시간 설정

public class ReservationActivity extends AppCompatActivity {

    RadioButton calButton, timeButton;
    CalendarView calendar;
    TimePicker time;
    Button finishButton;
    TextView year, month, day, hour, minute;
    int selectYear, selectMonth, selectDay, selectHour, selectMinute;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        year = (TextView) findViewById(R.id.tvYear);
        month = (TextView) findViewById(R.id.tvMonth);
        day = (TextView) findViewById(R.id.tvDay);
        hour = (TextView) findViewById(R.id.tvHour);
        minute = (TextView) findViewById(R.id.tvMin);



        calButton = (RadioButton) findViewById(R.id.rdoCal);
        timeButton = (RadioButton) findViewById(R.id.rdoTime);
        calendar = (CalendarView) findViewById(R.id.calendarView1);
        time = (TimePicker) findViewById(R.id.timePicker1);
        time.setVisibility(View.INVISIBLE);
        calendar.setVisibility(View.INVISIBLE);

        calButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                time.setVisibility(View.INVISIBLE);
                calendar.setVisibility(View.VISIBLE);

                calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        selectYear = year;
                        selectMonth = month+1;
                        selectDay = dayOfMonth;


                    }
                });


            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                time.setVisibility(View.VISIBLE);
                calendar.setVisibility(View.INVISIBLE);

                time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        selectHour = hourOfDay;
                        selectMinute = minute;
                    }
                });
            }
        });




        finishButton = (Button) findViewById(R.id.btnEnd) ;
        finishButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                year.setText(selectYear);
                month.setText(selectMonth);
                day.setText(selectDay);
                hour.setText(selectHour);
                minute.setText(selectMinute);

                String date = String.valueOf(selectYear)+"-"+String.valueOf(selectMonth)+"-"+String.valueOf(selectDay);
                Date orderDate = Date.valueOf(date);

                String orderTime = String.valueOf(selectHour)+String.valueOf(selectMinute);


                AlertDialog.Builder builder = new AlertDialog.Builder(ReservationActivity.this);
                dialog = builder.setMessage("주문이 완료되었습니다.")
                        .setPositiveButton("확인",null)
                        .create();
                dialog.show();
                

            }


        });
    } //onCreate()

}

