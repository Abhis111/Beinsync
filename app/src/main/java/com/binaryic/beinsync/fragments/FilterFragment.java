package com.binaryic.beinsync.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.activities.MainActivity;
import com.binaryic.beinsync.adapters.TagAdapter;
import com.binaryic.beinsync.controllers.DashboardController;
import com.binaryic.beinsync.models.TagModel;

import java.util.ArrayList;

import static android.R.id.list;
import static com.binaryic.beinsync.activities.MainActivity.ll_textFormatOptions;


/**
 * Created by admin on 4/11/2016.
 */
public class FilterFragment extends Fragment implements View.OnClickListener {
    private String str_Language = "";
    public static String strTag = "", strFilterTitle;
    private RecyclerView recyclerview;
    private TextView tv_Language_Header;
    private TextView tv_Tags_Header;
    private ImageView ta_Refresh;
    private ImageView ta_Close;
    private ImageView ta_LanguageHindi;
    private ImageView ta_LanguageEnglish;
    private ImageView ta_blankLanguageHindi;
    private ImageView ta_blankLanguageEnglish;
    private LinearLayout ll_LanguageHindi;
    private LinearLayout ll_LanguageEnglish;
    private int boolean_LanguageHindi = 0;
    private int boolean_LanguageEnglish = 0;
    private Button bt_apply;
    private Fragment fragment;
    TagAdapter adapter;

    public static FilterFragment newInstance() {
        Bundle args = new Bundle();
        FilterFragment fragment = new FilterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private CloseListner closeListner;

    public void setCloseListner(CloseListner closeListner) {
        this.closeListner = closeListner;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        recyclerview = (RecyclerView) view.findViewById(R.id.recycle_TagLIst);
        setupRecyclerView(recyclerview);
        tv_Language_Header = (TextView) view.findViewById(R.id.tv_Language_Header);
        tv_Tags_Header = (TextView) view.findViewById(R.id.tv_Tags_Header);
        ta_Refresh = (ImageView) view.findViewById(R.id.ta_Refresh);
        ta_Close = (ImageView) view.findViewById(R.id.ta_Close);
        ta_LanguageHindi = (ImageView) view.findViewById(R.id.ta_LanguageHindi);
        ta_LanguageEnglish = (ImageView) view.findViewById(R.id.ta_LanguageEnglish);
        ta_blankLanguageHindi = (ImageView) view.findViewById(R.id.ta_blankLanguageHindi);
        ta_blankLanguageEnglish = (ImageView) view.findViewById(R.id.ta_blankLanguageEnglish);
        ll_LanguageHindi = (LinearLayout) view.findViewById(R.id.ll_LanguageHindi);
        ll_LanguageEnglish = (LinearLayout) view.findViewById(R.id.ll_LanguageEnglish);
        bt_apply = (Button) view.findViewById(R.id.bt_apply);
        ll_textFormatOptions.setVisibility(View.GONE);
        underLinetheWords(tv_Language_Header, "Language");
        underLinetheWords(tv_Tags_Header, "Tags");
        lastChanges();
        ta_Refresh.setOnClickListener(this);
        ta_Close.setOnClickListener(this);
        bt_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.list.size() > 0){
                    String selected_tag = "";
                    for(TagModel tagModel : adapter.list){
                        if(tagModel.isSelect())
                            selected_tag = selected_tag + ",'" + tagModel.getTag() + "'";
                    }
                    if(!selected_tag.equals("")) {
                        selected_tag = selected_tag.substring(1);
                        if(closeListner != null)
                            closeListner.onClose(selected_tag);
                        ll_textFormatOptions.setVisibility(View.VISIBLE);
                    }
                }
                getActivity().onBackPressed();
                ll_textFormatOptions.setVisibility(View.VISIBLE);
            }



        });
        ll_LanguageHindi.setOnClickListener(this);
        ll_LanguageEnglish.setOnClickListener(this);
        return view;
    }

    public interface CloseListner{
        public void onClose(String tags);
    }

    private void setupRecyclerView(RecyclerView recyclerview) {
        ArrayList<TagModel> list = DashboardController.getDistinctTag(getActivity());
        adapter = new TagAdapter(getActivity(), list);
        recyclerview.setLayoutManager(new GridLayoutManager(recyclerview.getContext(), 1));
        recyclerview.setHasFixedSize(true);
        recyclerview.setAdapter(adapter);
    }

    private void clearLanguage() {
        ta_LanguageHindi.setVisibility(View.GONE);
        ta_blankLanguageHindi.setVisibility(View.VISIBLE);
        ta_LanguageEnglish.setVisibility(View.GONE);
        ta_blankLanguageEnglish.setVisibility(View.VISIBLE);
    }


    private void underLinetheWords(TextView view, String text) {
        SpannableString content_Size = new SpannableString(text);
        content_Size.setSpan(new UnderlineSpan(), 0, content_Size.length(), 0);
        view.setText(content_Size);
    }

    private void lastChanges() {
       /* Cursor cursor = getActivity().getContentResolver().query(CONTENT_FILTER, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            if (cursor.getString(cursor.getColumnIndex(COLUMN_STORY_LANGUAGE)) != null) {
                String[] array_LangString = cursor.getString(cursor.getColumnIndex(COLUMN_STORY_LANGUAGE)).split(",");
                for (int i = 0; i < array_LangString.length; i++) {
                    switch (array_LangString[i]) {
                        case "hi":
                            strTag = "";
                            boolean_LanguageHindi = 1;
                            ta_LanguageHindi.setVisibility(View.VISIBLE);
                            ta_blankLanguageHindi.setVisibility(View.GONE);
                            addData("language", str_Language, "hi");
                            recyclerview.setAdapter(new TagListAdapter(getActivity(), getTags(boolean_LanguageHindi, boolean_LanguageEnglish)));
                            break;
                        case "en":
                            strTag = "";
                            boolean_LanguageEnglish = 1;
                            ta_LanguageEnglish.setVisibility(View.VISIBLE);
                            ta_blankLanguageEnglish.setVisibility(View.GONE);
                            addData("language", str_Language, "en");
                            recyclerview.setAdapter(new TagListAdapter(getActivity(), getTags(boolean_LanguageHindi, boolean_LanguageEnglish)));
                            break;
                    }
                }
            }

        } else {
            strTag = "";
            boolean_LanguageHindi = 1;
            boolean_LanguageEnglish = 1;
            ta_LanguageHindi.setVisibility(View.VISIBLE);
            ta_blankLanguageHindi.setVisibility(View.GONE);
            ta_LanguageEnglish.setVisibility(View.VISIBLE);
            ta_blankLanguageEnglish.setVisibility(View.GONE);
            addData("language", str_Language, "hi");
            addData("language", str_Language, "en");
            recyclerview.setAdapter(new TagListAdapter(getActivity(), getTags(boolean_LanguageHindi, boolean_LanguageEnglish)));
        }
        cursor.close();*/
    }

    private void addData(String filterTitle, String strTitle, String value) {
        if (filterTitle.matches("language")) {
            if (!strTitle.matches("")) {
                if (strTitle.contains(",")) {
                    String[] array_LangString = strTitle.split(",");
                    strTitle = "";
                    for (int i = 0; i < array_LangString.length; i++) {
                        if (!array_LangString[i].matches(value)) {
                            str_Language = strTitle + "," + value;
                        }
                    }
                } else {
                    if (!str_Language.matches(value)) {
                        str_Language = strTitle + "," + value;
                    }
                }
            } else {
                str_Language = value;
            }
        }
    }

    private void removeData(String filterTitle, String strTitle, String value) {
        if (filterTitle.matches("language")) {
            if (!strTitle.matches("")) {
                if (strTitle.contains(",")) {
                    String[] separatedLanguage = strTitle.split(",");
                    strTitle = "";
                    for (int i = 0; i < separatedLanguage.length; i++) {
                        if (!separatedLanguage[i].matches(value)) {
                            if (TextUtils.isEmpty(strTitle)) {
                                str_Language = separatedLanguage[i];
                            } else {
                                str_Language = strTitle + "," + separatedLanguage[i];
                            }
                        }
                    }
                } else {
                    if (strTitle.matches(value)) {
                        str_Language = "";
                    }
                }
            } else {
                str_Language = "";
            }
        }
    }

    @Override
    public void onClick(View v) {}/*{
        switch (v.getId()) {
            case R.id.ll_LanguageHindi:
                if (boolean_LanguageHindi == 0) {
                    boolean_LanguageHindi = 1;
                    strTag = "";
                    ta_LanguageHindi.setVisibility(View.VISIBLE);
                    ta_blankLanguageHindi.setVisibility(View.GONE);
                    addData("language", str_Language, "hi");
                    recyclerview.setAdapter(new TagListAdapter(getActivity(), getTags(boolean_LanguageHindi, boolean_LanguageEnglish)));
                } else {
                    boolean_LanguageHindi = 0;
                    ta_LanguageHindi.setVisibility(View.GONE);
                    ta_blankLanguageHindi.setVisibility(View.VISIBLE);
                    recyclerview.setAdapter(new TagListAdapter(getActivity(), getTags(boolean_LanguageHindi, boolean_LanguageEnglish)));
                    removeData("language", str_Language, "hi");
                }
                break;
            case R.id.ll_LanguageEnglish:
                if (boolean_LanguageEnglish == 0) {
                    boolean_LanguageEnglish = 1;
                    strTag = "";
                    ta_LanguageEnglish.setVisibility(View.VISIBLE);
                    ta_blankLanguageEnglish.setVisibility(View.GONE);
                    addData("language", str_Language, "en");
                    recyclerview.setAdapter(new TagListAdapter(getActivity(), getTags(boolean_LanguageHindi, boolean_LanguageEnglish)));
                } else {
                    boolean_LanguageEnglish = 0;
                    ta_LanguageEnglish.setVisibility(View.GONE);
                    ta_blankLanguageEnglish.setVisibility(View.VISIBLE);
                    removeData("language", str_Language, "en");
                    recyclerview.setAdapter(new TagListAdapter(getActivity(), getTags(boolean_LanguageHindi, boolean_LanguageEnglish)));
                }
                break;
            case R.id.ta_Refresh:
                clearLanguage();
                boolean_LanguageEnglish = 1;
                ta_LanguageEnglish.setVisibility(View.VISIBLE);
                ta_blankLanguageEnglish.setVisibility(View.GONE);
                addData("language", str_Language, "en");
                addData("tag", strTag, "Spiritual");
                break;
            case R.id.ta_Close:
                fragment = AllStoryListFragment.newInstance();
                replaceFragmentBack(MainActivity.lay_main.getId(), fragment);
                clearLanguage();
                break;
            case R.id.bt_apply:
                ContentValues values = new ContentValues();
                values.put(COLUMN_STORY_LANGUAGE, str_Language);
                values.put(COLUMN_STORY_CATEGORY, strTag);
                getActivity().getContentResolver().insert(CONTENT_FILTER, values);
                Fragment fragment = MainFragment.newInstance();
                replaceFragmentBack(MainActivity.lay_main.getId(), fragment);
                break;
        }
    }*/

   /* private ArrayList<String> getTags(int boolean_LanguageHindi, int boolean_LanguageEnglish) {
        ArrayList<String> array_Tag = new ArrayList<>();
        String language;
        String selection = null;
        if (boolean_LanguageHindi == 1 && boolean_LanguageEnglish == 0) {
            language = "hi";
            selection = COLUMN_STORY_LANGUAGE + " = '" + language + "'";
        } else if (boolean_LanguageHindi == 0 && boolean_LanguageEnglish == 1) {
            language = "en";
            selection = COLUMN_STORY_LANGUAGE + " = '" + language + "'";
        } else if (boolean_LanguageHindi == 1 && boolean_LanguageEnglish == 1) {
            selection = null;
        } else if (boolean_LanguageHindi == 0 && boolean_LanguageEnglish == 0) {
            language = "nothing";
            selection = COLUMN_STORY_LANGUAGE + " = '" + language + "'";
        }

        Cursor cursor = getActivity().getContentResolver().query(CONTENT_STORY, null, selection, null, null);
        if (cursor.getCount() > 0) {
            HashSet<String> hashSet_Tag = new HashSet<String>();
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String[] tag = cursor.getString(cursor.getColumnIndex(COLUMN_STORY_CATEGORY)).trim().split(",");

                for (int j = 0; j < tag.length; j++) {
                    hashSet_Tag.add(tag[j]);
                }
            }
            array_Tag = new ArrayList<String>(hashSet_Tag);
        }
        cursor.close();
        return array_Tag;
    }
    @Override
    public void onResume() {
        super.onResume();

        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("FilterFragment");
    }*/
    public void replaceFragmentBack(int containerId, Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(containerId, fragment).addToBackStack("").commit();
        }
    }

}
