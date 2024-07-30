package com.example.customgridviewlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * custom adapter to display items in recyclerview
 * uses generics to avoid making multiple adapters for different data
 * data is set in the calling activity with the help of AdapterConfiguration {@link AdapterConfiguration#onDataBind(Object, int, ItemViewHolder)}  }
 * layout resource is specified using {@link AdapterConfiguration#getLayoutResourceID()}  }
 * @param <T>
 */
public abstract class CustomAdapter<T> extends RecyclerView.Adapter<ItemViewHolder> implements AdapterConfiguration<T> {

    Context context;
    List<T> itemList;

    public CustomAdapter(Context context, List<T> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }

    public List<T> getItemList() {
        return itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getLayoutResourceID(), parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(itemList.get(holder.getAdapterPosition()));
            }
        });
        onDataBind(itemList.get(position), position, holder);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

