package cookandroid.com.reservationapp;

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

import cookandroid.com.reservationapp.activity.OrderActivity;
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
                                        menu.getString("foodPrice")
                                ));

                            }

                            //creating adapter object and setting it to recyclerview
                            adapter = new MenuAdapter(context, menuList);
                            recyclerView.setAdapter(adapter);

                            //click
                            adapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(MenuAdapter.ViewHolder holder, View view, int position) {
                                    MenuItem item = adapter.getItem(position);

                                    Intent intent = new Intent(context, OrderActivity.class);
                                    intent.putExtra("menu", item);
                                    context.startActivity(intent);

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

        //adding our stringrequest to queue
        Volley.newRequestQueue(context).add(stringRequest);


    }
}

