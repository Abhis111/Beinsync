package com.binaryic.beinsync.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.models.HomeModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


/**
 * Created by Asd on 10-10-2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public ArrayList<HomeModel> list;
    Context context;

    public HomeAdapter(Activity context, ArrayList<HomeModel> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImage()).into(holder.iv_Image);
        holder.tv_Name.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_Name;
        private ImageView iv_Image;

        public ViewHolder(View view) {
            super(view);
            tv_Name = (TextView) view.findViewById(R.id.tv_Name);
            iv_Image = (ImageView) view.findViewById(R.id.iv_Image);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  Intent intent = new Intent(context, StoryViewActivity.class);
                   // intent.putExtra("title", list.get(getPosition()).getTitle());
                  //  intent.putExtra("image", list.get(getPosition()).getImage());
                   // intent.putExtra("content", list.get(getPosition()).getContent());
                }
            });
        }


    }
}
