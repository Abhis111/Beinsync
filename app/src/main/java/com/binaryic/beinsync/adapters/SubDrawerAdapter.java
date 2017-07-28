package com.binaryic.beinsync.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.activities.NewsActivity;
import com.binaryic.beinsync.common.Constants;
import com.binaryic.beinsync.models.DrawerModel;
import com.binaryic.beinsync.models.TopicModel;

import java.util.ArrayList;

import static com.binaryic.beinsync.activities.MainActivity.drawer;

/**
 * Created by HP on 26-Jul-17.
 */

public class SubDrawerAdapter extends RecyclerView.Adapter<SubDrawerAdapter.ViewHolder> {
    public ArrayList<TopicModel> list;
    Context context;

    public SubDrawerAdapter(Activity context, ArrayList<TopicModel> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_drawer_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_child.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  tv_child;
        RelativeLayout rl_child_item;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_child = (TextView) itemView.findViewById(R.id.tv_child);
            rl_child_item = (RelativeLayout) itemView.findViewById(R.id.rl_child_item);
            rl_child_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.closeDrawer(Gravity.LEFT);
                    // Toast.makeText(context, list.get(getPosition()).getTitle(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, NewsActivity.class);
                    //intent.putExtra("link", Constants.URL + list.get(getPosition()).getId());
                    intent.putExtra("id",list.get(getPosition()).getId());
                    intent.putExtra("page_count",list.get(getPosition()).getPost_count());
                    context.startActivity(intent);
                }
            });
        }
    }
}
