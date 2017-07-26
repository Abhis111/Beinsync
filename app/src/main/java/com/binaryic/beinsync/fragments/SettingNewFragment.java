package com.binaryic.beinsync.fragments;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.activities.MainActivity;
import com.binaryic.beinsync.adapters.TestSizeAdapter;
import com.binaryic.beinsync.common.Constants;

import java.util.ArrayList;

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
 * Created by admin on 4/11/2016.
 */
public class SettingNewFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_POSITION = "position";
    private Button bt_apply;
    private int booleanBold = 0, booleanItalic = 0;
    private ClickListener clickListener;


    private EditText et_LineSpacing;
    private TextView tv_NightFontColor;
    private TextView tv_DayFontColor;
    private TextView tv_Size_Header;
    private TextView tv_Size16;
    private TextView tv_Size18;
    private TextView tv_Size20;
    private TextView tv_Size22;
    private TextView tv_Size24;
    private TextView tv_Size26;
    private TextView tv_Fonts_Header;
    private TextView tv_Reading_Mode_Header;
    private LinearLayout ll_FontRobotaCondensed;
    private LinearLayout ll_Reading_NightMode;
    private TextView tv_Aligments_Header;
    private LinearLayout ll_AligmentLeft;
    private LinearLayout ll_AligmentCenter;
    private LinearLayout ll_AligmentRight;
    private LinearLayout ll_AligmentJustify;
    private TextView tv_Style_Header;
    private TextView tv_StyleBold;
    private TextView tv_StyleItalic;
    private TextView tv_Line_Spacing_Header;
    private TextView tv_Color_Header;
    private TextView tv_ColorGreen;
    private TextView tv_ColorRed;
    private TextView tv_ColorBlue;
    private TextView tv_ColorLightGreen;
    private TextView tv_ColorPink;
    private TextView tv_ColorWhite;
    private TextView tv_ColorLightYellow;
    private TextView tv_ColorLightBlue;
    private TextView tv_ColorLighterGreen;
    private TextView tv_ColorLightPink;
    private LinearLayout ll_FontRobota;
    private LinearLayout ll_FontPTSans;
    private LinearLayout ll_Reading_DayMode;
    private LinearLayout ll_FontDroidSans;
    private ImageView ta_FontRobota;
    private ImageView ta_FontPTSans;
    private ImageView ta_FontRobotaCondensed;
    private ImageView ta_FontDroidSans;
    private ImageView ta_Reading_NightMode;
    private ImageView ta_AligmentJustify;
    private ImageView ta_AligmentLeft;
    private ImageView ta_AligmentCenter;
    private ImageView ta_AligmentRight;
    private ImageView ta_Reading_DayMode;
    private ImageView ta_blankFontRobota;
    private ImageView ta_blankFontPTSans;
    private ImageView ta_blankFontRobotaCondensed;
    private ImageView ta_blankFontDroidSans;
    private ImageView ta_blankReading_NightMode;
    private ImageView ta_blankAligmentJustify;
    private ImageView ta_blankAligmentLeft;
    private ImageView ta_blankAligmentCenter;
    private ImageView ta_blankAligmentRight;
    private ImageView ta_blankReading_DayMode;
    private ImageView ta_close;
    private Fragment fragment;
    private ArrayList<String> list;
    private Spinner spinner_LineSpacing;

    public static SettingNewFragment newInstance() {

        Bundle args = new Bundle();
        SettingNewFragment fragment = new SettingNewFragment();


        fragment.setArguments(args);
        return fragment;
    }

    public static SettingNewFragment newInstance(String s) {

        Bundle args = new Bundle();
        SettingNewFragment fragment = new SettingNewFragment();


        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_setting, container, false);

        et_LineSpacing = (EditText) view.findViewById(R.id.et_LineSpacing);
        bt_apply = (Button) view.findViewById(R.id.bt_apply);
        tv_NightFontColor = (TextView) view.findViewById(R.id.tv_NightFontColor);
        tv_DayFontColor = (TextView) view.findViewById(R.id.tv_DayFontColor);
        tv_Size_Header = (TextView) view.findViewById(R.id.tv_Size_Header);
        tv_Size16 = (TextView) view.findViewById(R.id.tv_Size16);
        tv_Size18 = (TextView) view.findViewById(R.id.tv_Size18);
        tv_Size20 = (TextView) view.findViewById(R.id.tv_Size20);
        tv_Size22 = (TextView) view.findViewById(R.id.tv_Size22);
        tv_Size24 = (TextView) view.findViewById(R.id.tv_Size24);
        tv_Size26 = (TextView) view.findViewById(R.id.tv_Size26);
        tv_Fonts_Header = (TextView) view.findViewById(R.id.tv_Fonts_Header);
        ll_FontRobota = (LinearLayout) view.findViewById(R.id.ll_FontRobota);
        ll_FontPTSans = (LinearLayout) view.findViewById(R.id.ll_FontPTSans);
        ll_FontRobotaCondensed = (LinearLayout) view.findViewById(R.id.ll_FontRobotaCondensed);
        ll_FontDroidSans = (LinearLayout) view.findViewById(R.id.ll_FontDroidSans);

        ta_AligmentLeft = (ImageView) view.findViewById(R.id.ta_AligmentLeft);
        ta_AligmentCenter = (ImageView) view.findViewById(R.id.ta_AligmentCenter);
        ta_AligmentRight = (ImageView) view.findViewById(R.id.ta_AligmentRight);
        ta_AligmentJustify = (ImageView) view.findViewById(R.id.ta_AligmentJustify);
        ta_Reading_NightMode = (ImageView) view.findViewById(R.id.ta_Reading_NightMode);
        ta_Reading_DayMode = (ImageView) view.findViewById(R.id.ta_Reading_DayMode);
        ta_FontRobota = (ImageView) view.findViewById(R.id.ta_FontRobota);
        ta_FontPTSans = (ImageView) view.findViewById(R.id.ta_FontPTSans);
        ta_FontRobotaCondensed = (ImageView) view.findViewById(R.id.ta_FontRobotaCondensed);
        ta_FontDroidSans = (ImageView) view.findViewById(R.id.ta_FontDroidSans);
        ta_blankAligmentLeft = (ImageView) view.findViewById(R.id.ta_blankAligmentLeft);
        ta_blankAligmentCenter = (ImageView) view.findViewById(R.id.ta_blankAligmentCenter);
        ta_blankAligmentRight = (ImageView) view.findViewById(R.id.ta_blankAligmentRight);
        ta_blankAligmentJustify = (ImageView) view.findViewById(R.id.ta_blankAligmentJustify);
        ta_blankReading_NightMode = (ImageView) view.findViewById(R.id.ta_blankReading_NightMode);
        ta_blankReading_DayMode = (ImageView) view.findViewById(R.id.ta_blankReading_DayMode);
        ta_blankFontRobota = (ImageView) view.findViewById(R.id.ta_blankFontRobota);
        ta_blankFontPTSans = (ImageView) view.findViewById(R.id.ta_blankFontPTSans);
        ta_blankFontRobotaCondensed = (ImageView) view.findViewById(R.id.ta_blankFontRobotaCondensed);
        ta_blankFontDroidSans = (ImageView) view.findViewById(R.id.ta_blankFontDroidSans);

        ta_close = (ImageView) view.findViewById(R.id.ta_close);
        tv_Aligments_Header = (TextView) view.findViewById(R.id.tv_Aligments_Header);
        ll_AligmentLeft = (LinearLayout) view.findViewById(R.id.ll_AligmentLeft);
        ll_AligmentCenter = (LinearLayout) view.findViewById(R.id.ll_AligmentCenter);
        ll_AligmentRight = (LinearLayout) view.findViewById(R.id.ll_AligmentRight);
        ll_AligmentJustify = (LinearLayout) view.findViewById(R.id.ll_AligmentJustify);
        spinner_LineSpacing = (Spinner) view.findViewById(R.id.spinner_LineSpacing);
        tv_Style_Header = (TextView) view.findViewById(R.id.tv_Style_Header);
        tv_StyleBold = (TextView) view.findViewById(R.id.tv_StyleBold);
        tv_StyleItalic = (TextView) view.findViewById(R.id.tv_StyleItalic);
        tv_Color_Header = (TextView) view.findViewById(R.id.tv_Color_Header);
        tv_ColorGreen = (TextView) view.findViewById(R.id.tv_ColorGreen);
        tv_ColorRed = (TextView) view.findViewById(R.id.tv_ColorRed);
        tv_ColorBlue = (TextView) view.findViewById(R.id.tv_ColorBlue);
        tv_ColorLightGreen = (TextView) view.findViewById(R.id.tv_ColorLightGreen);
        tv_ColorPink = (TextView) view.findViewById(R.id.tv_ColorPink);
        tv_ColorWhite = (TextView) view.findViewById(R.id.tv_ColorWhite);
        tv_ColorLightYellow = (TextView) view.findViewById(R.id.tv_ColorLightYellow);
        tv_ColorLightBlue = (TextView) view.findViewById(R.id.tv_ColorLightBlue);
        tv_ColorLighterGreen = (TextView) view.findViewById(R.id.tv_ColorLighterGreen);
        tv_ColorLightPink = (TextView) view.findViewById(R.id.tv_ColorLightPink);
        tv_Line_Spacing_Header = (TextView) view.findViewById(R.id.tv_Line_Spacing_Header);
        tv_Reading_Mode_Header = (TextView) view.findViewById(R.id.tv_Reading_Mode_Header);
        ll_Reading_DayMode = (LinearLayout) view.findViewById(R.id.ll_Reading_DayMode);
        ll_Reading_NightMode = (LinearLayout) view.findViewById(R.id.ll_Reading_NightMode);

        underLinetheWords(tv_Size_Header, "Size");
        underLinetheWords(tv_Fonts_Header, "Fonts");
        underLinetheWords(tv_Aligments_Header, "Alignments");
        underLinetheWords(tv_Style_Header, "Style");
        underLinetheWords(tv_Line_Spacing_Header, "Line Spacing");
        underLinetheWords(tv_Color_Header, "Color");
        underLinetheWords(tv_Reading_Mode_Header, "Reading Mode");

        WebView tv_FontRobota = (WebView) view.findViewById(R.id.tv_FontRobota);
        WebView tv_FontPTSans = (WebView) view.findViewById(R.id.tv_FontPTSans);
        WebView tv_FontRobotaCondensed = (WebView) view.findViewById(R.id.tv_FontRobotaCondensed);
        WebView tv_FontDroidSans = (WebView) view.findViewById(R.id.tv_FontDroidSans);
        MainActivity.ll_textFormatOptions.setVisibility(View.GONE);

        tv_FontRobota.loadDataWithBaseURL(null, "<html><body><head><link href='https://fonts.googleapis.com/css?family=" + "Roboto" + "' rel='stylesheet' type='text/css'><style>div {text-align: justify;text-justify: inter-word;color:#686868;font-family: '" + "Roboto" + "', sans-serif;font-size:15px; line-height: 0.3 ;margin:0 0 5px;padding:0;}</style></head><div>" + "Roboto" + "</div></body></html>", "text/html", "utf-8", null);
        tv_FontPTSans.loadDataWithBaseURL(null, "<html><body><head><link href='https://fonts.googleapis.com/css?family=" + "PT Sans" + "' rel='stylesheet' type='text/css'><style>div {text-align: justify;text-justify: inter-word;color:#686868;font-family: '" + "PT Sans" + "', sans-serif;font-size:15px; line-height: 0.3 ;margin:0 0 5px;padding:0;}</style></head><div>" + "PT Sans" + "</div></body></html>", "text/html", "utf-8", null);
        tv_FontRobotaCondensed.loadDataWithBaseURL(null, "<html><body><head><link href='https://fonts.googleapis.com/css?family=" + "Roboto Condensed" + "' rel='stylesheet' type='text/css'><style>div {text-align: justify;text-justify: inter-word;color:#686868;font-family: '" + "Roboto Condensed" + "', sans-serif;font-size:15px; line-height: 0.3 ;margin:0 0 5px;padding:0;}</style></head><div>" + "Roboto Condensed" + "</div></body></html>", "text/html", "utf-8", null);
        tv_FontDroidSans.loadDataWithBaseURL(null, "<html><body><head><link href='https://fonts.googleapis.com/css?family=" + "Droid Sans" + "' rel='stylesheet' type='text/css'><style>div {text-align: justify;text-justify: inter-word;color:#686868;font-family: '" + "Droid Sans" + "', sans-serif;font-size:15px; line-height: 0.3 ;margin:0 0 5px;padding:0;}</style></head><div>" + "Droid Sans" + "</div></body></html>", "text/html", "utf-8", null);

        list = new ArrayList<String>();
        list.add("0.50");
        list.add("0.75");
        list.add("1.00");
        list.add("1.25");
        list.add("1.50");
        list.add("1.75");
        list.add("2.00");
        list.add("2.50");
        list.add("3.00");
        TestSizeAdapter dataAdapter = new TestSizeAdapter(getActivity(), R.layout.text_size_list_view, list);
        spinner_LineSpacing.setAdapter(dataAdapter);


        spinner_LineSpacing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Log.e("SettingFragment", "line==" + list.get(position));
                    addData(COLUMN_LINE_SPACING, list.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lastChanges();
        bt_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentHome();
                Bundle bundle = new Bundle();
                bundle.putString("link", Constants.URL_DASHBOARD);
                fragment.setArguments(bundle);

                replaceFragmentBack(MainActivity.fl_Main.getId(), fragment);


            }
        });

        ta_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSize();
                et_LineSpacing.getText().clear();
                clearMode();
                clearFont();
                clearStyle();
                clearAlignments();
                addData(COLUMN_TEXT_SIZE, "16");
                addData(COLUMN_LINE_SPACING, "1.5");
                addData(COLUMN_TEXT_STYLE, "normal");
                addData(COLUMN_TEXT_MODE, "day");
                addData(COLUMN_TEXT_ALIGNMENT, "justify");
                addData(COLUMN_FONT_NAME, "DroidSans");
                addData(COLUMN_TEXT_COLOR, "" + "#2D292B");
                addData(COLUMN_BACKGROUND_COLOR, "" + "#EEEAF0");
               /* if (!TextUtils.isEmpty(strSettingComeFrom)) {
                    if (strSettingComeFrom.matches("MainActivity")) {
                        replaceFragmentBack(MainActivity.fl_Main.getId(), fragment);
                    } else {
                        replaceFragmentBack(ShowStoryActivity.fragment_container.getId(), fragment);
                    }
                }*/
            }
        });
        ll_FontRobota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFont();
                ta_FontRobota.setVisibility(View.VISIBLE);
                ta_blankFontRobota.setVisibility(View.GONE);
                addData(COLUMN_FONT_NAME, "Roboto");
            }
        });
        ll_FontPTSans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFont();
                ta_FontPTSans.setVisibility(View.VISIBLE);
                ta_blankFontPTSans.setVisibility(View.GONE);
                addData(COLUMN_FONT_NAME, "PT Sans");
            }
        });
        ll_FontRobotaCondensed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFont();
                ta_FontRobotaCondensed.setVisibility(View.VISIBLE);
                ta_blankFontRobotaCondensed.setVisibility(View.GONE);
                addData(COLUMN_FONT_NAME, "Roboto Condensed");
            }
        });
        ll_FontDroidSans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFont();
                ta_FontDroidSans.setVisibility(View.VISIBLE);
                ta_blankFontDroidSans.setVisibility(View.GONE);
                addData(COLUMN_FONT_NAME, "Droid Sans");
            }
        });
        ll_AligmentLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAlignments();
                ta_AligmentLeft.setVisibility(View.VISIBLE);
                ta_blankAligmentLeft.setVisibility(View.GONE);
                addData(COLUMN_TEXT_ALIGNMENT, "left");
            }
        });
        ll_AligmentRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAlignments();
                ta_AligmentRight.setVisibility(View.VISIBLE);
                ta_blankAligmentRight.setVisibility(View.GONE);
                addData(COLUMN_TEXT_ALIGNMENT, "right");
            }
        });
        ll_AligmentCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAlignments();
                ta_AligmentCenter.setVisibility(View.VISIBLE);
                ta_blankAligmentCenter.setVisibility(View.GONE);
                addData(COLUMN_TEXT_ALIGNMENT, "center");
            }
        });
        ll_AligmentJustify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAlignments();
                ta_AligmentJustify.setVisibility(View.VISIBLE);
                ta_blankAligmentJustify.setVisibility(View.GONE);
                addData(COLUMN_TEXT_ALIGNMENT, "justify");
            }
        });
        tv_StyleBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (booleanBold == 0) {
                    booleanBold = 1;
                    tv_StyleBold.setTextColor(getResources().getColor(R.color.white));
                    tv_StyleBold.setBackground(getResources().getDrawable(R.drawable.orange_rounded_background));
                    addData(COLUMN_TEXT_STYLE, "bold");
                } else {
                    booleanBold = 0;
                    tv_StyleBold.setTextColor(getResources().getColor(R.color.darkgrey));
                    tv_StyleBold.setBackground(getResources().getDrawable(R.drawable.grey_background));
                    addData(COLUMN_TEXT_STYLE, "boldnormal");
                }
                setStyle();

            }
        });
        tv_StyleItalic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (booleanItalic == 0) {
                    booleanItalic = 1;
                    tv_StyleItalic.setTextColor(getResources().getColor(R.color.white));
                    tv_StyleItalic.setBackground(getResources().getDrawable(R.drawable.orange_rounded_background));
                    addData(COLUMN_TEXT_STYLE, "italic");
                } else {
                    booleanItalic = 0;
                    tv_StyleItalic.setTextColor(getResources().getColor(R.color.darkgrey));
                    tv_StyleItalic.setBackground(getResources().getDrawable(R.drawable.grey_background));
                    addData(COLUMN_TEXT_STYLE, "italicnormal");
                }
                setStyle();

            }
        });
        ll_Reading_DayMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayMode();
                clearMode();
                ta_Reading_DayMode.setVisibility(View.VISIBLE);
                ta_blankReading_DayMode.setVisibility(View.GONE);
                addData(COLUMN_TEXT_MODE, "day");
            }
        });
        ll_Reading_NightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nightMode();
                clearMode();
                ta_Reading_NightMode.setVisibility(View.VISIBLE);
                ta_blankReading_NightMode.setVisibility(View.GONE);
                addData(COLUMN_TEXT_MODE, "night");
            }
        });
        tv_Size16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSize();
                tv_Size16.setTextColor(getResources().getColor(R.color.orange));
                addData(COLUMN_TEXT_SIZE, "16");
            }
        });
        tv_Size18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSize();
                tv_Size18.setTextColor(getResources().getColor(R.color.orange));
                addData(COLUMN_TEXT_SIZE, "18");
            }
        });
        tv_Size20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSize();
                tv_Size20.setTextColor(getResources().getColor(R.color.orange));
                addData(COLUMN_TEXT_SIZE, "20");
            }
        });
        tv_Size22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSize();
                tv_Size22.setTextColor(getResources().getColor(R.color.orange));
                addData(COLUMN_TEXT_SIZE, "22");
            }
        });
        tv_Size24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSize();
                tv_Size24.setTextColor(getResources().getColor(R.color.orange));
                addData(COLUMN_TEXT_SIZE, "24");
            }
        });
        tv_Size26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSize();
                tv_Size26.setTextColor(getResources().getColor(R.color.orange));
                addData(COLUMN_TEXT_SIZE, "26");
            }
        });
        tv_ColorGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeTextColor();
                tv_ColorGreen.setBackground(getResources().getDrawable(R.drawable.green_background_red_border));
                addData(COLUMN_TEXT_COLOR, "" + "#2D292B");
            }
        });
        tv_ColorRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeTextColor();
                tv_ColorRed.setBackground(getResources().getDrawable(R.drawable.red_background_red_background));

                addData(COLUMN_TEXT_COLOR, "" + "#641214");
            }
        });
        tv_ColorBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeTextColor();
                tv_ColorBlue.setBackground(getResources().getDrawable(R.drawable.blue_background_red_border));

                addData(COLUMN_TEXT_COLOR, "" + "#025178");
            }
        });
        tv_ColorPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeTextColor();
                tv_ColorPink.setBackground(getResources().getDrawable(R.drawable.pink_background_red_border));

                addData(COLUMN_TEXT_COLOR, "" + "#6E0054");
            }
        });
        tv_ColorLightGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeTextColor();
                tv_ColorLightGreen.setBackground(getResources().getDrawable(R.drawable.light_green_background_red_border));

                addData(COLUMN_TEXT_COLOR, "" + "#023710");
            }
        });
        tv_ColorLighterGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBackground();
                tv_ColorLighterGreen.setBackground(getResources().getDrawable(R.drawable.lighter_green_background_red_border));

                addData(COLUMN_BACKGROUND_COLOR, "" + "#CAEAD2");
            }
        });
        tv_ColorLightBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBackground();
                tv_ColorLightBlue.setBackground(getResources().getDrawable(R.drawable.light_blue_background_red_border));

                addData(COLUMN_BACKGROUND_COLOR, "" + "#CBD9E6");
            }
        });
        tv_ColorLightPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBackground();
                tv_ColorLightPink.setBackground(getResources().getDrawable(R.drawable.light_pink_background_red_background));

                addData(COLUMN_BACKGROUND_COLOR, "" + "#E3D1DD");
            }
        });
        tv_ColorLightYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBackground();
                tv_ColorLightYellow.setBackground(getResources().getDrawable(R.drawable.light_yellow_background_red_border));

                addData(COLUMN_BACKGROUND_COLOR, "" + "#E3D5BB");
            }
        });
        tv_ColorWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBackground();
                tv_ColorWhite.setBackground(getResources().getDrawable(R.drawable.white_background_red_border));

                addData(COLUMN_BACKGROUND_COLOR, "" + "#EEEAF0");
            }
        });
        return view;
    }

    private void setStyle() {
        if (booleanBold == 1 && booleanItalic == 1) {
            addData(COLUMN_TEXT_STYLE, "bold_italic");

        } else if (booleanBold == 0 && booleanItalic == 0) {
            addData(COLUMN_TEXT_STYLE, "normal");

        }
    }

    private void removeFragment(int fragment_Id) {
        getActivity().getSupportFragmentManager().beginTransaction().
                remove(getActivity().getSupportFragmentManager().findFragmentById(fragment_Id)).commit();
    }

    private void clearMode() {
        ta_Reading_NightMode.setVisibility(View.GONE);
        ta_Reading_DayMode.setVisibility(View.GONE);
        ta_blankReading_NightMode.setVisibility(View.VISIBLE);
        ta_blankReading_DayMode.setVisibility(View.VISIBLE);
    }


    private void lastChanges() {
        Cursor cursor = getActivity().getContentResolver().query(CONTENT_SETTING, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            if (cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_SIZE)) != null) {
                switch (cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_SIZE))) {
                    case "16":
                        tv_Size16.setTextColor(getResources().getColor(R.color.orange));
                        break;
                    case "18":
                        tv_Size18.setTextColor(getResources().getColor(R.color.orange));
                        break;
                    case "20":
                        tv_Size20.setTextColor(getResources().getColor(R.color.orange));
                        break;
                    case "22":
                        tv_Size22.setTextColor(getResources().getColor(R.color.orange));
                        break;
                    case "24":
                        tv_Size24.setTextColor(getResources().getColor(R.color.orange));
                        break;
                    case "26":
                        tv_Size26.setTextColor(getResources().getColor(R.color.orange));
                        break;
                }
            } else {
                addData(COLUMN_TEXT_SIZE, "16");
            }
            if (cursor.getString(cursor.getColumnIndex(COLUMN_LINE_SPACING)) != null) {
                spinner_LineSpacing.setSelection(list.indexOf(cursor.getString(cursor.getColumnIndex(COLUMN_LINE_SPACING))));
            } else {
                addData(COLUMN_LINE_SPACING, "1.5");
                spinner_LineSpacing.setSelection(5);

            }


            if (cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_STYLE)) != null) {
                switch ((cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_STYLE)))) {

                    case "bold":
                        booleanBold = 1;

                        tv_StyleBold.setTextColor(getResources().getColor(R.color.white));
                        tv_StyleBold.setBackground(getResources().getDrawable(R.drawable.orange_rounded_background));
                        break;
                    case "italic":
                        booleanItalic = 1;

                        tv_StyleItalic.setTextColor(getResources().getColor(R.color.white));
                        tv_StyleItalic.setBackground(getResources().getDrawable(R.drawable.orange_rounded_background));
                        break;
                    case "boldnormal":
                        booleanBold = 0;
                        tv_StyleBold.setTextColor(getResources().getColor(R.color.darkgrey));
                        tv_StyleBold.setBackground(getResources().getDrawable(R.drawable.grey_background));
                        tv_StyleItalic.setTextColor(getResources().getColor(R.color.white));
                        tv_StyleItalic.setBackground(getResources().getDrawable(R.drawable.orange_rounded_background));

                        break;
                    case "italicnormal":
                        booleanItalic = 0;
                        tv_StyleItalic.setTextColor(getResources().getColor(R.color.darkgrey));
                        tv_StyleItalic.setBackground(getResources().getDrawable(R.drawable.grey_background));
                        tv_StyleBold.setTextColor(getResources().getColor(R.color.white));
                        tv_StyleBold.setBackground(getResources().getDrawable(R.drawable.orange_rounded_background));

                        break;
                    case "bold_italic":
                        booleanBold = 1;
                        booleanItalic = 1;
                        tv_StyleBold.setTextColor(getResources().getColor(R.color.white));
                        tv_StyleBold.setBackground(getResources().getDrawable(R.drawable.orange_rounded_background));
                        tv_StyleItalic.setTextColor(getResources().getColor(R.color.white));
                        tv_StyleItalic.setBackground(getResources().getDrawable(R.drawable.orange_rounded_background));
                        break;
                }
            } else {
                tv_StyleBold.setTextColor(getResources().getColor(R.color.darkgrey));
                tv_StyleBold.setBackground(getResources().getDrawable(R.drawable.grey_background));
                tv_StyleItalic.setTextColor(getResources().getColor(R.color.darkgrey));
                tv_StyleItalic.setBackground(getResources().getDrawable(R.drawable.grey_background));

                addData(COLUMN_TEXT_STYLE, "normal");
            }
            if (cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_MODE)) != null) {
                switch ((cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_MODE)))) {

                    case "day":
                        dayMode();
                        ta_Reading_DayMode.setVisibility(View.VISIBLE);
                        ta_blankReading_DayMode.setVisibility(View.GONE);
                        break;
                    case "night":
                        nightMode();
                        ta_Reading_NightMode.setVisibility(View.VISIBLE);
                        ta_blankReading_NightMode.setVisibility(View.GONE);
                        break;
                }
            } else {
                addData(COLUMN_TEXT_MODE, "day");
            }
            if (cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_ALIGNMENT)) != null) {
                switch (cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_ALIGNMENT))) {
                    case "left":
                        ta_AligmentLeft.setVisibility(View.VISIBLE);
                        ta_blankAligmentLeft.setVisibility(View.GONE);
                        break;
                    case "right":
                        ta_AligmentRight.setVisibility(View.VISIBLE);
                        ta_blankAligmentRight.setVisibility(View.GONE);
                        break;
                    case "center":
                        ta_AligmentCenter.setVisibility(View.VISIBLE);
                        ta_blankAligmentCenter.setVisibility(View.GONE);
                        break;
                    case "justify":
                        ta_AligmentJustify.setVisibility(View.VISIBLE);
                        ta_blankAligmentJustify.setVisibility(View.GONE);
                        break;
                }
            } else {
                addData(COLUMN_TEXT_ALIGNMENT, "justify");
            }
            if (cursor.getString(cursor.getColumnIndex(COLUMN_FONT_NAME)) != null) {
                switch (cursor.getString(cursor.getColumnIndex(COLUMN_FONT_NAME))) {
                    case "Droid Sans":
                        ta_FontDroidSans.setVisibility(View.VISIBLE);
                        ta_blankFontDroidSans.setVisibility(View.GONE);
                        break;
                    case "PT Sans":
                        ta_FontPTSans.setVisibility(View.VISIBLE);
                        ta_blankFontPTSans.setVisibility(View.GONE);
                        break;
                    case "Roboto":
                        ta_FontRobota.setVisibility(View.VISIBLE);
                        ta_blankFontRobota.setVisibility(View.GONE);
                        break;
                    case "Roboto Condensed":
                        ta_FontRobotaCondensed.setVisibility(View.VISIBLE);
                        ta_blankFontRobotaCondensed.setVisibility(View.GONE);
                        break;
                }
            } else {
                addData(COLUMN_FONT_NAME, "Roboto");
            }
            if (cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_COLOR)) != null) {
                switch (cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_COLOR))) {
                    case "#2D292B":
                        tv_ColorGreen.setBackground(getResources().getDrawable(R.drawable.green_background_red_border));

                        break;
                    case "#025178":
                        tv_ColorBlue.setBackground(getResources().getDrawable(R.drawable.blue_background_red_border));

                        break;
                    case "#023710":
                        tv_ColorLightGreen.setBackground(getResources().getDrawable(R.drawable.light_green_background_red_border));

                        break;
                    case "#641214":
                        tv_ColorRed.setBackground(getResources().getDrawable(R.drawable.red_background_red_background));

                        break;
                    case "#6E0054":
                        tv_ColorPink.setBackground(getResources().getDrawable(R.drawable.pink_background_red_border));

                        break;
                }
            } else {
                addData(COLUMN_TEXT_COLOR, "" + "#2D292B");
            }
            if (cursor.getString(cursor.getColumnIndex(COLUMN_BACKGROUND_COLOR)) != null) {
                switch (cursor.getString(cursor.getColumnIndex(COLUMN_BACKGROUND_COLOR))) {

                    case "#E3D1DD":
                        tv_ColorLightPink.setBackground(getResources().getDrawable(R.drawable.light_pink_background_red_background));

                        break;
                    case "#CBD9E6":
                        tv_ColorLightBlue.setBackground(getResources().getDrawable(R.drawable.light_blue_background_red_border));

                        break;
                    case "#CAEAD2":
                        tv_ColorLighterGreen.setBackground(getResources().getDrawable(R.drawable.lighter_green_background_red_border));

                        break;
                    case "#E3D5BB":
                        tv_ColorLightYellow.setBackground(getResources().getDrawable(R.drawable.light_yellow_background_red_border));

                        break;
                    case "#EEEAF0":
                        tv_ColorWhite.setBackground(getResources().getDrawable(R.drawable.white_background_red_border));

                        break;
                }
            } else {
                addData(COLUMN_BACKGROUND_COLOR, "" + "#EEEAF0");
            }
        }
        cursor.close();
    }

    private void addData(final String COLUMN_NAME, String value) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, value.trim());
        Cursor cursor = getContext().getContentResolver().query(CONTENT_SETTING, null, null, null, null);
        if (cursor.getCount() > 0) {
            getContext().getContentResolver().update(CONTENT_SETTING, values, null, null);
        } else {
            getContext().getContentResolver().insert(CONTENT_SETTING, values);
        }

        cursor.moveToLast();
        cursor.close();
    }

    private void removeTextColor() {
        tv_ColorGreen.setBackground(getResources().getDrawable(R.drawable.green_background));
        tv_ColorRed.setBackground(getResources().getDrawable(R.drawable.red_background));
        tv_ColorBlue.setBackground(getResources().getDrawable(R.drawable.blue_background));
        tv_ColorLightGreen.setBackground(getResources().getDrawable(R.drawable.light_green_background));
        tv_ColorPink.setBackground(getResources().getDrawable(R.drawable.pink_background));
    }

    private void removeBackground() {
        tv_ColorWhite.setBackground(getResources().getDrawable(R.drawable.white_background));
        tv_ColorLightYellow.setBackground(getResources().getDrawable(R.drawable.light_yellow_background));
        tv_ColorLightBlue.setBackground(getResources().getDrawable(R.drawable.light_blue_background));
        tv_ColorLighterGreen.setBackground(getResources().getDrawable(R.drawable.lighter_green_background));
        tv_ColorLightPink.setBackground(getResources().getDrawable(R.drawable.light_pink_background));
    }

    private void nightMode() {
        tv_NightFontColor.setText("Font Color");
        tv_DayFontColor.setText("Background Color");
    }

    private void clearSize() {
        tv_Size16.setTextColor(getResources().getColor(R.color.darkgrey));
        tv_Size18.setTextColor(getResources().getColor(R.color.darkgrey));
        tv_Size20.setTextColor(getResources().getColor(R.color.darkgrey));
        tv_Size22.setTextColor(getResources().getColor(R.color.darkgrey));
        tv_Size24.setTextColor(getResources().getColor(R.color.darkgrey));
        tv_Size26.setTextColor(getResources().getColor(R.color.darkgrey));
    }

    private void dayMode() {
        tv_NightFontColor.setText("Background Color");
        tv_DayFontColor.setText("Font Color");
    }

    private void clearStyle() {

    }

    private void clearFont() {
        ta_FontRobota.setVisibility(View.GONE);
        ta_FontPTSans.setVisibility(View.GONE);
        ta_FontRobotaCondensed.setVisibility(View.GONE);
        ta_FontDroidSans.setVisibility(View.GONE);
        ta_blankFontRobota.setVisibility(View.VISIBLE);
        ta_blankFontPTSans.setVisibility(View.VISIBLE);
        ta_blankFontRobotaCondensed.setVisibility(View.VISIBLE);
        ta_blankFontDroidSans.setVisibility(View.VISIBLE);
    }

    private void clearAlignments() {
        ta_AligmentJustify.setVisibility(View.GONE);
        ta_AligmentRight.setVisibility(View.GONE);
        ta_AligmentCenter.setVisibility(View.GONE);
        ta_AligmentLeft.setVisibility(View.GONE);
        ta_blankAligmentJustify.setVisibility(View.VISIBLE);
        ta_blankAligmentRight.setVisibility(View.VISIBLE);
        ta_blankAligmentCenter.setVisibility(View.VISIBLE);
        ta_blankAligmentLeft.setVisibility(View.VISIBLE);
    }

    private void underLinetheWords(TextView view, String text) {
        SpannableString content_Size = new SpannableString(text);
        content_Size.setSpan(new UnderlineSpan(), 0, content_Size.length(), 0);
        view.setText(content_Size);
    }

    public void replaceFragmentBack(int containerId, Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(containerId, fragment).addToBackStack("").commit();
        }
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onClick(View v) {
        if (clickListener != null) {
            clickListener.ItemClicked(v);
        }
    }

    public interface ClickListener {
        public void ItemClicked(View view);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Tracking the screen view
        // MyApplication.getInstance().trackScreenView("SettingFragment");
    }
}
