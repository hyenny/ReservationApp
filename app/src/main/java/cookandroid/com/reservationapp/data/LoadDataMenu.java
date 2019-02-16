package cookandroid.com.reservationapp.data;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cookandroid.com.reservationapp.activity.CoffeeOrderActivity;
import cookandroid.com.reservationapp.activity.DessertOrderActivity;
import cookandroid.com.reservationapp.adapter.MenuAdapter;
import cookandroid.com.reservationapp.model.MenuItem;

public class LoadDataMenu extends AppCompatActivity {

    Context context;
    String URL;
    RecyclerView recyclerView;
    MenuAdapter adapter;
    List<MenuItem> menuList;

    public LoadDataMenu(Context context, RecyclerView recyclerView, String URL) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.URL = URL;
        loadData();

    }


    private void loadData() {

        menuList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject menu = array.getJSONObject(i);

                                //adding the product to product list
                                menuList.add(new MenuItem(
                                        menu.getString("foodImage"),
                                        menu.getString("foodName"),
                                        menu.getString("foodPrice"),
                                        menu.getString("foodType")
                                ));

                            }

                            // 어댑터 객체를 생성하고 이를 리사이클러뷰에 set한다.
                            adapter = new MenuAdapter(context, menuList);
                            recyclerView.setAdapter(adapter);

                            //click
                            adapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(MenuAdapter.ViewHolder holder, View view, int position) {
                                    MenuItem item = adapter.getItem(position);

                                    //
                                    if(item.getType().equals("커피")) {
                                        Intent intent = new Intent(context, CoffeeOrderActivity.class);
                                        intent.putExtra("menu", item);
                                        context.startActivity(intent);

                                    } else if(item.getType().equals("디저트")) {
                                        Intent intent = new Intent(context, DessertOrderActivity.class);
                                        intent.putExtra("menu", item);
                                        context.startActivity(intent);

                                    }

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


        Volley.newRequestQueue(context).add(stringRequest);


    }
}

