package com.binaryic.beinsync.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.adapters.RelatedStoriesAdapter;
import com.binaryic.beinsync.controllers.DashboardController;
import com.binaryic.beinsync.models.HomeModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

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
    private RecyclerView rv_RelatedStories;
    private TextView tv_Story_Name;
    private ImageView iv_StoryImage;
    private WebView webview;
    private FloatingActionButton fab_Share;
    private String url;
    private String content = "";
    private String category = "";
    private String id = "";
    private ArrayList<HomeModel> array_Related = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_story_view, container, false);
        init(view);
        return view;
    }

    private void getExtra() {
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            id = (bundle.getString("id"));
            category = (bundle.getString("category"));
            tv_Story_Name.setText(bundle.getString("title"));
            url = (bundle.getString("url"));
            Glide.with(this)
                    .load(bundle.getString("image"))
                    .into(iv_StoryImage);
            content = bundle.getString("content");

        }

    }

    private void applySetting() {
        ColorDrawable colorDrawable = null;
        String strLineSpacing = "", strMode = "", strColor = "", strBgcolor = "", font_Name = "";

        Cursor cursorSetFont = getActivity().getContentResolver().query(CONTENT_SETTING, null, null, null, null);
        String open_Tag = "";
        String close_Tag = "";
        if (cursorSetFont.getCount() > 0) {
            cursorSetFont.moveToLast();
            if (cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_FONT_NAME)) != null) {
                font_Name = cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_FONT_NAME));
            }
            if (cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_SIZE)) != null) {

                String data = "<html><body><head><link href='https://fonts.googleapis.com/css?family=" + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_FONT_NAME)) + "' rel='stylesheet' type='text/css'><style>div {text-align: justify;text-justify: inter-word;color:#7f7f7f;font-family: '" + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_FONT_NAME)) + "', sans-serif;font-size:" + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_SIZE)) + ";margin:0 0 5px;padding:0;}.sharedaddy.sd-sharing-enabled{display:none;}</style></head><div>" + content + "</div></br></body></html>";
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
                    String data1 = "<html><body><head><link href='https://fonts.googleapis.com/css?family=\" + font_Name + \"' rel='stylesheet' type='text/css'><style>div {text-align: justify;text-justify: inter-word;font-family: '\" + font_Name + \"',font-style: '" + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_STYLE)) + "',font-weight:" + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_STYLE)) + ",sans-serif;font-size:" + cursorSetFont.getString(cursorSetFont.getColumnIndex(COLUMN_TEXT_SIZE)) + ";margin:0 0 5px;padding:0;}.sharedaddy.sd-sharing-enabled{display:none;}</style></head><div>" + content + "</div></br></body></html>";
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
                                + open_Tag + content + close_Tag + "</div></br></body></html>";
                        webview.loadDataWithBaseURL(null, data2, "text/html", "utf-8", null);

                    }
                }
            } else {
            }
        }
        cursorSetFont.close();


    }

    private void init(View view) {
        rv_RelatedStories = (RecyclerView) view.findViewById(R.id.rv_RelatedStories);
        rl_MainLayout = (RelativeLayout) view.findViewById(R.id.rl_MainLayout);
        tv_Story_Name = (TextView) view.findViewById(R.id.tv_Story_Name);
        iv_StoryImage = (ImageView) view.findViewById(R.id.iv_StoryImage);
        webview = (WebView) view.findViewById(R.id.webview);
        fab_Share = (FloatingActionButton) view.findViewById(R.id.fab_Share);


        getExtra();
        applySetting();
        getRelatedArray();

        fab_Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });

    }

    private void getRelatedArray() {
        rv_RelatedStories.hasFixedSize();
        rv_RelatedStories.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        ArrayList<HomeModel> temp_array = DashboardController.getDashboardDataFromDatabase(getActivity(), category);

        rv_RelatedStories.setVisibility(View.VISIBLE);

        for (int i = 0; i < temp_array.size(); i++) {
            int j = new Random().nextInt(temp_array.size());

            if (!id.matches(temp_array.get(j).getId())) {
                if (!array_Related.contains(temp_array.get(j))) {
                    array_Related.add(temp_array.get(j));
                }
            }
        }
        if (array_Related.size() == 0) {
            rv_RelatedStories.setVisibility(View.GONE);
        } else {
            rv_RelatedStories.setVisibility(View.VISIBLE);
            if (array_Related.size() >= 3) {
                rv_RelatedStories.setAdapter(new RelatedStoriesAdapter(getActivity(), array_Related, category, 3));
            } else {
                rv_RelatedStories.setAdapter(new RelatedStoriesAdapter(getActivity(), array_Related, category, array_Related.size()));
            }
        }


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
