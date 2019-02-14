package cookandroid.com.reservationapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cookandroid.com.reservationapp.R;
import cookandroid.com.reservationapp.model.MenuItem;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    Context context;

    List<MenuItem> menuList;
    OnItemClickListener listener;

    public static interface OnItemClickListener {
        public void onItemClick(ViewHolder holder, View view, int position);
    }

    public MenuAdapter(Context context, List<MenuItem> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.menu_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem item = menuList.get(position);

        Glide.with(context)
                .load(item.getImg())
                .into(holder.imageView);

        holder.setItem(item);
        holder.setOnItemClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public void addItem(MenuItem item) {
        menuList.add(item);
    }

    public void addItems(ArrayList<MenuItem> items) {
        this.menuList = items;
    }

    public MenuItem getItem(int position) {
        return menuList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // 뷰홀더 : 각각의 아이템을 위한 뷰를 담아두기 위한 용도
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView textView2;
        ImageView imageView;

        OnItemClickListener listener;

        public ViewHolder(View itemView) {
            super(itemView);


            imageView = (ImageView) itemView.findViewById(R.id.bevImg);
            textView = (TextView) itemView.findViewById(R.id.bevName);
            textView2 = (TextView) itemView.findViewById(R.id.bevPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);                   }
                }
            });
        }

        public void setItem(MenuItem item) {

            textView.setText(item.getName());
            textView2.setText(item.getPrice());

        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }
}
