package com.binaryic.beinsync;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * Created by Asd on 10-10-2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public ArrayList<HomeModel> list;
    Context context;
    ClickListener clickListener;

    public HomeAdapter(Context context, ArrayList<HomeModel> list) {
        this.context = context;
        this.list = list;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder(View view) {
            super(view);

        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.itemClicked(v, getPosition());
        }
    }
}
