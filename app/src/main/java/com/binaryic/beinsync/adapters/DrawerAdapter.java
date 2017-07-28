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
    RecyclerView rv_sub;

    public DrawerAdapter(Activity context, ArrayList<DrawerModel> list, RecyclerView rv_sub) {
        this.context = context;
        this.list = list;
        this.rv_sub = rv_sub;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_Name.setText(list.get(position).getTitle());
        if (list.get(position).isHeader()) {
            holder.iv_expand.setVisibility(View.VISIBLE);
            if (list.get(position).isOpen()) {
                holder.iv_expand.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_remove_black_24dp));
                rv_sub.setVisibility(View.VISIBLE);
                holder.iv_arrow.setVisibility(View.GONE);
            } else {
                holder.iv_expand.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_add));
                rv_sub.setVisibility(View.GONE);
                holder.iv_arrow.setVisibility(View.GONE);
            }
        } else {
            holder.iv_expand.setVisibility(View.GONE);
            holder.iv_arrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_Name;
        ImageView iv_expand;
        ImageView iv_arrow;
        LinearLayout rl_DrawerItem;

        public ViewHolder(View view) {
            super(view);
            tv_Name = (TextView) view.findViewById(R.id.tv_Name);
            iv_expand = (ImageView) view.findViewById(R.id.iv_expand);
            iv_arrow = (ImageView) view.findViewById(R.id.iv_arrow);
            rl_DrawerItem = (LinearLayout) view.findViewById(R.id.rl_DrawerItem);

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
                        intent.putExtra("category",list.get(getPosition()).getTitle());
                        intent.putExtra("link", Constants.URL + list.get(getPosition()).getId());
                        context.startActivity(intent);

                    }
                }
            });
        }


    }
}
