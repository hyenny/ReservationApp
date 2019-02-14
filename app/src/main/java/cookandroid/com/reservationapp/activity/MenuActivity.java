package cookandroid.com.reservationapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.List;

import cookandroid.com.reservationapp.LoadDataMenu;
import cookandroid.com.reservationapp.R;
import cookandroid.com.reservationapp.adapter.MenuAdapter;
import cookandroid.com.reservationapp.model.MenuItem;

public class MenuActivity extends AppCompatActivity {
    private static final String URL_MENU_ALL = "http://simssbook9.cafe24.com/FoodList.php";
    private static final String URL_COFFEE = "http://simssbook9.cafe24.com/CoffeeList.php";
    private static final String URL_DESSERT = "http://simssbook9.cafe24.com/DessertList.php";

    Toolbar myToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);

        getSupportActionBar().setTitle("MENU");

        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1);
        tabHost1.setup();

        // 첫 번째 Tab.
        final TabHost.TabSpec ts1 = tabHost1.newTabSpec("TapSpec1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("전체메뉴");
        tabHost1.addTab(ts1);


        // 두 번째 Tab.
        final TabHost.TabSpec ts2 = tabHost1.newTabSpec("TapSpec2");
        ts2.setContent(R.id.bevTap);
        ts2.setIndicator("음료");
        tabHost1.addTab(ts2);


        // 세 번째 Tab.
        final TabHost.TabSpec ts3 = tabHost1.newTabSpec("TapSpec3");
        ts3.setContent(R.id.foodTap);
        ts3.setIndicator("푸드");
        tabHost1.addTab(ts3);


        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.b_recyclerView);
        RecyclerView recyclerView3 = (RecyclerView) findViewById(R.id.f_recyclerView);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);

        new LoadDataMenu(MenuActivity.this, recyclerView1, URL_MENU_ALL);
        new LoadDataMenu(MenuActivity.this, recyclerView2, URL_COFFEE);
        new LoadDataMenu(MenuActivity.this, recyclerView3, URL_DESSERT);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.menu_search:
                Toast.makeText(this, "검색", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
