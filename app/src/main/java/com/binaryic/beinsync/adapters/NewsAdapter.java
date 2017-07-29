package com.binaryic.beinsync.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.activities.StoryViewActivity;
import com.binaryic.beinsync.models.HomeModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.R.attr.category;


/**
 * Created by HP on 28-Jul-17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    public ArrayList<HomeModel> list;
    Activity context;

    public NewsAdapter(Activity context, ArrayList<HomeModel> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_layout_new, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if ((list.size() - 1) == position) {
            if (scrollListener != null)
                scrollListener.Scrolled();
        }
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
                    Intent intent = new Intent(context, StoryViewActivity.class);
                    intent.putExtra("category","");
                    intent.putExtra("id", list.get(getPosition()).getId());
                    intent.putExtra("title", list.get(getPosition()).getTitle());
                    intent.putExtra("image", list.get(getPosition()).getImage());
                    intent.putExtra("content", list.get(getPosition()).getContent());
                    intent.putExtra("url", list.get(getPosition()).getUrl());
                    context.startActivity(intent);
                }
            });
        }
    }

    private ScrollListener scrollListener;
    public void setScrollListener(ScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }
    public interface ScrollListener {
        public void Scrolled();
    }
}
