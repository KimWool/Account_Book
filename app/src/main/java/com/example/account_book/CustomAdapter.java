package com.example.account_book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Data> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<Data> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.content_view.setText(arrayList.get(position).getContent());
        holder.category_view.setText(arrayList.get(position).getCategory());
        holder.fixed_data_view.setText(arrayList.get(position).getFixed_data());
        holder.amount_view.setText(String.valueOf(arrayList.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        //삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView content_view;
        TextView category_view;
        TextView fixed_data_view;
        TextView amount_view;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.content_view = itemView.findViewById(R.id.content_view);
            this.category_view = itemView.findViewById(R.id.category_view);
            this.fixed_data_view = itemView.findViewById(R.id.fixed_data_view);
            this.amount_view = itemView.findViewById(R.id.amount_view);
        }
    }
}
