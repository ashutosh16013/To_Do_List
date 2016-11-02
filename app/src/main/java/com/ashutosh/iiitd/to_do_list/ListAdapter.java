package com.ashutosh.iiitd.to_do_list;

/**
 * Created by Ashutosh on 31-10-2016.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import helperClasses.ListItem;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{

    private ArrayList<ListItem> itemList;
    public TextView title;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView details;
        public TextView date;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.todo_title);
            details = (TextView)view.findViewById(R.id.todo_details);
            date = (TextView)view.findViewById(R.id.todo_date);
        }
    }

    public ListAdapter(ArrayList<ListItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListItem item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.details.setText(item.getDetails());
        holder.date.setText(item.getDate());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
