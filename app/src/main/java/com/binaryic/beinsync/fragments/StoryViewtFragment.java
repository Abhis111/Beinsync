package com.binaryic.beinsync.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.bumptech.glide.Glide;

/**
 * Created by minakshi on 21/7/17.
 */

public class  StoryViewtFragment extends Fragment {
    private TextView tv_Story_Name;
    private ImageView iv_StoryImage;
    private WebView webview;
    private FloatingActionButton fab_Share;
    private String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_story_view, container, false);
        init(view);
        getExtra();
        return view;
    }

    private void getExtra() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            tv_Story_Name.setText(bundle.getString("title"));
            url = (bundle.getString("url"));
            Glide.with(this)
                    .load(bundle.getString("image"))
                    .into(iv_StoryImage);
          //  webview.loadDataWithBaseURL(null, bundle.getString("content"), "text/html", "utf-8", null);


         webview.loadDataWithBaseURL(null, "<html><body><head><link href='https://fonts.googleapis.com/css?' rel='stylesheet' type='text/css'><style>div {text-align: justify;text-justify: inter-word;color:#7f7f7f;font-family: font-family: 'proxima_nova_rgregular', Arial, Helvetica, sans-serif;, sans-serif;font-size:"
                    + "18" + ";margin:0 0 5px;padding:0;}</style></head><div>"
                    + bundle.getString("content")+ "</div></br></body></html>", "text/html", "utf-8", null);

 /* webview.loadDataWithBaseURL(null, "<html><body><head><link href='https://fonts.googleapis.com/css?family=" + font_Name + "' rel='stylesheet' type='text/css'><style>div {text-align: justify;text-justify: inter-word;color:#7f7f7f;font-family: '" + font_Name + "', sans-serif;font-size:"
                    + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_SIZE)) + ";margin:0 0 5px;padding:0;}</style></head><div>"
                    + bundle.getString("content")+ "</div></br></body></html>", "text/html", "utf-8", null);*/

        }

    }

    private void init(View view) {
        tv_Story_Name = (TextView) view.findViewById(R.id.tv_Story_Name);
        iv_StoryImage = (ImageView) view.findViewById(R.id.iv_StoryImage);
        webview = (WebView) view.findViewById(R.id.webview);
        fab_Share = (FloatingActionButton) view.findViewById(R.id.fab_Share);
        fab_Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
    }

    private void share() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
            String sAux = url;
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            Log.e("StoryViewFragment", "error==" + e.getMessage());
        }
    }
}
