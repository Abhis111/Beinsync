package com.binaryic.beinsync.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.activities.NewsActivity;
import com.binaryic.beinsync.common.Constants;
import com.binaryic.beinsync.models.DrawerModel;

import java.util.ArrayList;

import static com.binaryic.beinsync.activities.MainActivity.drawer;


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
        if (list.get(position).isHeader()) {
            holder.rl_child_item.setVisibility(View.GONE);
            holder.rl_DrawerItem.setVisibility(View.VISIBLE);
            if (list.get(position).isOpen()) {
                holder.iv_expand.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_remove_black_24dp));
            } else
                holder.iv_expand.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_add));
            holder.tv_Name.setText(list.get(position).getTitle());
        } else if (list.get(position).isChild()) {
            if (list.get(5).isOpen())
                holder.rl_child_item.setVisibility(View.VISIBLE);
            else
                holder.rl_child_item.setVisibility(View.GONE);
            holder.rl_DrawerItem.setVisibility(View.GONE);
            holder.tv_child.setText(list.get(position).getTitle());
        } else {
            holder.rl_child_item.setVisibility(View.GONE);
            holder.rl_DrawerItem.setVisibility(View.VISIBLE);
            holder.iv_expand.setVisibility(View.GONE);
            holder.tv_Name.setText(list.get(position).getTitle());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_Name, tv_child;
        ImageView iv_expand;
        LinearLayout rl_DrawerItem, rl_child_item;

        public ViewHolder(View view) {
            super(view);
            tv_Name = (TextView) view.findViewById(R.id.tv_Name);
            tv_child = (TextView) view.findViewById(R.id.tv_child);
            iv_expand = (ImageView) view.findViewById(R.id.iv_expand);
            rl_DrawerItem = (LinearLayout) view.findViewById(R.id.rl_DrawerItem);
            rl_child_item = (LinearLayout) view.findViewById(R.id.rl_child_item);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (list.get(getPosition()).isHeader()) {
                        list.get(getPosition()).setOpen(!list.get(getPosition()).isOpen());
                        notifyDataSetChanged();
                    } else {

                        drawer.closeDrawer(Gravity.LEFT);
                        // Toast.makeText(context, list.get(getPosition()).getTitle(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, NewsActivity.class);
                        intent.putExtra("link", Constants.URL + list.get(getPosition()).getId());
                        context.startActivity(intent);
                    }
                }
            });
        }


    }
}
