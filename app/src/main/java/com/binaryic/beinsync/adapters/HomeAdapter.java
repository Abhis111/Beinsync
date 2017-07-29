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


/**
 * Created by Asd on 10-10-2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public ArrayList<HomeModel> list;
    Activity context;
    String category;



    public HomeAdapter(Activity context, ArrayList<HomeModel> list,String category) {
        this.context = context;
        this.list = list;
        this.category = category;
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
        //private WebView tv_Content;
        private ImageView iv_Image;
        //private LinearLayout ll_MainLayout;

        public ViewHolder(View view) {
            super(view);
          //  tv_Content = (WebView) view.findViewById(webview);
            tv_Name = (TextView) view.findViewById(R.id.tv_Name);
            iv_Image = (ImageView) view.findViewById(R.id.iv_Image);
            //ll_MainLayout = (LinearLayout) view.findViewById(R.id.ll_MainLayout);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, StoryViewActivity.class);
                    intent.putExtra("category",category);
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
