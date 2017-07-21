package com.binaryic.beinsync.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
        holder. tv_Content.loadDataWithBaseURL(null, "<html><body><head><link href='https://fonts.googleapis.com/css?' rel='stylesheet' type='text/css'><style>div {text-align: justify;text-justify: inter-word;color:#7f7f7f;font-family: font-family: 'proxima_nova_rgregular', Arial, Helvetica, sans-serif;, sans-serif;font-size:"
                + "16" + ";margin:0 0 0px;padding:0;}</style></head><div>"
                +list.get(position).getContent() + "</div></br></body></html>", "text/html", "utf-8", null);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_Name;
        private WebView tv_Content;
        private ImageView iv_Image;

        public ViewHolder(View view) {
            super(view);
            tv_Content = (WebView) view.findViewById(R.id.webview);
            tv_Name = (TextView) view.findViewById(R.id.tv_Name);
            iv_Image = (ImageView) view.findViewById(R.id.iv_Image);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, StoryViewActivity.class);
                    intent.putExtra("title", list.get(getPosition()).getTitle());
                    intent.putExtra("image", list.get(getPosition()).getImage());
                    intent.putExtra("content", list.get(getPosition()).getContent());
                    intent.putExtra("url", list.get(getPosition()).getUrl());
                    context.startActivity(intent);
                }
            });
        }


    }
}
