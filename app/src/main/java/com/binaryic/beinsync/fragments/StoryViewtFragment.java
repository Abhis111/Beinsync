package com.binaryic.beinsync.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.bumptech.glide.Glide;

import static com.binaryic.beinsync.common.Constants.COLUMN_BACKGROUND_COLOR;
import static com.binaryic.beinsync.common.Constants.COLUMN_FONT_NAME;
import static com.binaryic.beinsync.common.Constants.COLUMN_LINE_SPACING;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_ALIGNMENT;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_COLOR;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_MODE;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_SIZE;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_STYLE;
import static com.binaryic.beinsync.common.Constants.CONTENT_SETTING;

/**
 * Created by minakshi on 21/7/17.
 */

public class StoryViewtFragment extends Fragment {
    private RelativeLayout rl_MainLayout;
    private TextView tv_Story_Name;
    private ImageView iv_StoryImage;
    private WebView webview;
    private FloatingActionButton fab_Share;
    private String url;
    private String content = "";

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
            content = bundle.getString("content");
            applySetting();
            //  webview.loadDataWithBaseURL(null, bundle.getString("content"), "text/html", "utf-8", null);


/*
 webview.loadDataWithBaseURL(null, "<html><body><head><link href='https://fonts.googleapis.com/css?' rel='stylesheet' type='text/css'><style>div {text-align: justify;text-justify: inter-word;color:#7f7f7f;font-family: font-family: 'proxima_nova_rgregular', Arial, Helvetica, sans-serif;, sans-serif;font-size:"
                    + "18" + ";margin:0 0 5px;padding:0;}</style></head><div>"
                    + bundle.getString("content")+ "</div></br></body></html>", "text/html", "utf-8", null);
*/

 /* webview.loadDataWithBaseURL(null, "<html><body><head><link href='https://fonts.googleapis.com/css?family=" + font_Name + "' rel='stylesheet' type='text/css'><style>div {text-align: justify;text-justify: inter-word;color:#7f7f7f;font-family: '" + font_Name + "', sans-serif;font-size:"
                    + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_SIZE)) + ";margin:0 0 5px;padding:0;}</style></head><div>"
                    + bundle.getString("content")+ "</div></br></body></html>", "text/html", "utf-8", null);*/

        }

    }

    private void applySetting() {
        ColorDrawable colorDrawable = null;
        String strLineSpacing = "", strMode = "", strColor = "", strBgcolor = "", font_Name = "";
//
        Cursor cursorSetFont = getActivity().getContentResolver().query(CONTENT_SETTING, null, null, null, null);
        String open_Tag = "";
        String close_Tag = "";
        if (cursorSetFont.getCount() > 0) {
            cursorSetFont.moveToLast();
            if (cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_FONT_NAME)) != null) {
                font_Name = cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_FONT_NAME));
            }
            if (cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_SIZE)) != null) {

                String data = "<html><body><head><link href='https://fonts.googleapis.com/css?family=" + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_FONT_NAME)) + "' rel='stylesheet' type='text/css'><style>div {text-align: justify;text-justify: inter-word;color:#7f7f7f;font-family: '" + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_FONT_NAME)) + "', sans-serif;font-size:"+ cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_SIZE)) + ";margin:0 0 5px;padding:0;}.sharedaddy.sd-sharing-enabled{display:none;}</style></head><div>"+content +"</div></br></body></html>";
                webview.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);

                if (cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_STYLE)) != null) {

                    switch (cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_STYLE))) {

                        case "bold":
                            open_Tag = "<b>";
                            close_Tag = "<\b>";
                            break;
                        case "italic":
                            open_Tag = "<i>";
                            close_Tag = "<\\i>";
                            break;
                        case "bold_italic":
                            open_Tag = "<b><i>";
                            close_Tag = "<\b><\\i>";
                            break;
                        case "normal":
                            open_Tag = "";
                            close_Tag = "";
                            break;
                        case "boldnormal":
                            open_Tag = "<i>";
                            close_Tag = "<\\i>";
                            break;
                        case "italicnormal":
                            open_Tag = "<b>";
                            close_Tag = "<\b>";
                            break;
                    }
                    String data1 = "<html><body><head><link href='https://fonts.googleapis.com/css?family=\" + font_Name + \"' rel='stylesheet' type='text/css'><style>div {text-align: justify;text-justify: inter-word;font-family: '\" + font_Name + \"',font-style: '"+ cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_STYLE)) + "',font-weight:"+ cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_STYLE)) + ",sans-serif;font-size:"+ cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_SIZE)) + ";margin:0 0 5px;padding:0;}.sharedaddy.sd-sharing-enabled{display:none;}</style></head><div>"+content +"</div></br></body></html>";
                    webview.loadDataWithBaseURL(null, data1, "text/html", "utf-8", null);

                    if (cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_ALIGNMENT)) != null ||
                            cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_LINE_SPACING)) != null) {
                        strLineSpacing = cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_LINE_SPACING));
                        if (cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_MODE)) != null) {
                            strMode = cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_MODE));
                            if (strMode.matches("day")) {
                                strColor = cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_COLOR));
                                strBgcolor = cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_BACKGROUND_COLOR));
                                colorDrawable = new ColorDrawable(Color.parseColor(cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_BACKGROUND_COLOR))));
                            } else {
                                strBgcolor = cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_COLOR));
                                strColor = cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_BACKGROUND_COLOR));
                                colorDrawable = new ColorDrawable(Color.parseColor(cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_COLOR))));
                            }
                        }


                        Log.e("font_Name", "==" + font_Name);
                        Log.e("strLineSpacing", "==" + strLineSpacing);
                        Log.e("strBgcolor", "==" + strBgcolor);
                        Log.e("strColor", "==" + strColor);
                        Log.e("open_Tag", "==" + open_Tag);
                        rl_MainLayout.setBackground(colorDrawable);
                        String data2 = "<html><body><head><link href='https://fonts.googleapis.com/css?family=" + font_Name + "' rel='stylesheet' type='text/css'><style>div {text-align: "
                                + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_ALIGNMENT)) + ";text-justify: inter-word; color: " + strColor + "; background-color: " + strBgcolor + ";font-family: '" + font_Name + "',font-style: '"
                                + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_STYLE)) + "' ,font-weight: "
                                + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_STYLE)) + ",sans-serif;font-size:"
                                + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_SIZE)) + "; line-height: " + strLineSpacing + ";margin:0 0 5px;padding:0;} body {background-color: " + strBgcolor + ";}.sharedaddy.sd-sharing-enabled{display:none;}</style></head><div>"
                                + open_Tag +content + close_Tag + "</div></br></body></html>";
                        webview.loadDataWithBaseURL(null, data2, "text/html", "utf-8", null);

                    }
                }
            } else {
            }
        }
        cursorSetFont.close();


    }

    private void init(View view) {
        rl_MainLayout = (RelativeLayout) view.findViewById(R.id.rl_MainLayout);
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
