package com.binaryic.beinsync.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.models.TagModel;

import java.util.ArrayList;

/**
 * Created by HP on 28-Jul-17.
 */

public class TagAdapter  extends RecyclerView.Adapter<TagAdapter.ViewHolder> {
    Context context;
    public ArrayList<TagModel> list;
    public TagAdapter(Context context, ArrayList<TagModel> tagModels){
        this.context = context;
        this.list = tagModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_tag.setText(list.get(position).getTag());
        if(list.get(position).isSelect())
            holder.iv_check.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check_box_black_24dp));
        else
            holder.iv_check.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check_box_outline_blank_black_24dp));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_check;
        TextView tv_tag;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_check = (ImageView) itemView.findViewById(R.id.iv_check);
            tv_tag = (TextView) itemView.findViewById(R.id.tv_tag);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(getPosition()).setSelect(!list.get(getPosition()).isSelect());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
