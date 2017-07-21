package com.binaryic.beinsync.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.models.DrawerModel;

import java.util.ArrayList;


/**
 * Created by Asd on 10-10-2016.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    public ArrayList<DrawerModel> list;
    Context context;

    public DrawerAdapter(Activity context, ArrayList<DrawerModel> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_Name.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_Name;

        public ViewHolder(View view) {
            super(view);
            tv_Name = (TextView) view.findViewById(R.id.tv_Name);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, list.get(getPosition()).getTitle(), Toast.LENGTH_SHORT).show();
                    /*Intent intent = new Intent(context, StoryViewActivity.class);
                    intent.putExtra("title", list.get(getPosition()).getTitle());
                    intent.putExtra("image", list.get(getPosition()).getImage());
                    intent.putExtra("content", list.get(getPosition()).getContent());
                    intent.putExtra("url", list.get(getPosition()).getUrl());
                    context.startActivity(intent);*/
                }
            });
        }


    }
}
