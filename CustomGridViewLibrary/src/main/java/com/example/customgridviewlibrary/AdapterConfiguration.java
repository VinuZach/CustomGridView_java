package com.example.customgridviewlibrary;

public interface AdapterConfiguration<T> {
    int getLayoutResourceID();
    void onDataBind(T dataItem,int position,ItemViewHolder itemViewHolder);
    default void onItemClick(T dataItem)
    {}
}
