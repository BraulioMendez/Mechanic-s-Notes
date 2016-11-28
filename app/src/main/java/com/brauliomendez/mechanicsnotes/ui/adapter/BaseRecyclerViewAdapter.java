package com.brauliomendez.mechanicsnotes.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.brauliomendez.mechanicsnotes.ui.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Braulio Méndez Jiménez
 * @since 25/08/16
 */
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, E> extends RecyclerView.Adapter<VH> {

    protected List<E> mItemsAdapter;

    protected OnItemClickListener<E> onItemClickListener;

    public BaseRecyclerViewAdapter() {
        mItemsAdapter = new ArrayList<>();
    }

    @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return createHolder(parent, viewType);
    }

    @Override public void onBindViewHolder(VH holder, int position) {
        bindView(holder, mItemsAdapter.get(position));
        bindViewWithPosition(holder, mItemsAdapter.get(position), position);
    }

    public void addItem(E item) {
        mItemsAdapter.add(item);
        notifyDataSetChanged();
    }

    public void addItems(ArrayList<E> items) {
        if (items != null && !items.isEmpty()) {
            mItemsAdapter.clear();
            mItemsAdapter.addAll(items);
            notifyDataSetChanged();
        }
    }

    public abstract VH createHolder(ViewGroup parent, int viewType);

    public void bindView(VH holder, E item) { }

    public void bindViewWithPosition(VH holder, E item, int position) { }

    @Override public int getItemCount() {
        return mItemsAdapter != null ? mItemsAdapter.size() : 0;
    }

    List<E> getItemsAdapter() {
        return mItemsAdapter;
    }

    public void setOnItemClickListener(OnItemClickListener<E> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void removeItem(int position){
        mItemsAdapter.remove(position);
        notifyDataSetChanged();
    }

    public void removeItems(){
        mItemsAdapter.clear();
        notifyDataSetChanged();
    }

    public List<E> getItems(){
        return mItemsAdapter;
    }
}
