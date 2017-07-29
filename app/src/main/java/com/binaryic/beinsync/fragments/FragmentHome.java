package com.binaryic.beinsync.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.adapters.HomeAdapter;
import com.binaryic.beinsync.common.ApiCallBack;
import com.binaryic.beinsync.common.Constants;
import com.binaryic.beinsync.controllers.DashboardController;
import com.binaryic.beinsync.models.HomeModel;
import com.binaryic.beinsync.models.TopicModel;

import java.util.ArrayList;

import static com.binaryic.beinsync.controllers.DashboardController.getDashboardDataFromDatabase;

/**
 * Created by Asd on 27-09-2016.
 */
public class FragmentHome extends Fragment implements HomeAdapter.ScrollListener {

    public static RelativeLayout ll_MainLayout;
    private RecyclerView rv_Home;

    private String link = "";
    private String category = "";
    private TextView tv_No_Data;
    ArrayList<HomeModel> array_Data = new ArrayList<>();
    String id = "";
    String max_count = "1";
    int page_no = 1;
    LinearLayout ll_progress;
    HomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ll_MainLayout = (RelativeLayout) view.findViewById(R.id.ll_MainLayout);
        rv_Home = (RecyclerView) view.findViewById(R.id.rv_Home);
        tv_No_Data = (TextView) view.findViewById(R.id.tv_No_Data);
        ll_progress = (LinearLayout) view.findViewById(R.id.ll_progress);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            category = (bundle.getString("category"));
        }
        generateLink();
        return view;
    }

    private void generateLink() {
        TopicModel topicModel = DashboardController.getTopicId(getActivity(), category);
        if (topicModel != null) {
            id = topicModel.getId();
            max_count = topicModel.getPost_count();
            page_no = 1;
            getDashboardData();
        }
    }

    private void getDashboardData() {
        link = Constants.URL + "?json=get_category_posts&id=" + id + "&count=10&status=publish&page=" + page_no;
        DashboardController.getDashboardApiCall(getActivity(), link,category, new ApiCallBack() {
            @Override
            public void onSuccess(Object success) {
                ArrayList<HomeModel> list = new ArrayList<>();
                list = getDashboardDataFromDatabase(getActivity(), category);
                setUpRecyclerView(list);
            }

            @Override
            public void onError(String error) {
                ArrayList<HomeModel> list = new ArrayList<>();
                list = getDashboardDataFromDatabase(getActivity(), category);
                setUpRecyclerView(list);

            }
        });

    }

    private void setUpRecyclerView(ArrayList<HomeModel> list) {
        ll_progress.setVisibility(View.GONE);
        if (list.size() > 0) {
            tv_No_Data.setVisibility(View.GONE);
            if (page_no == 1) {
                rv_Home.setVisibility(View.VISIBLE);
                rv_Home.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new HomeAdapter(getActivity(), list, category);
                adapter.setScrollListener(FragmentHome.this);
                rv_Home.setAdapter(adapter);
            } else {
                adapter.list = list;
                adapter.notifyDataSetChanged();
            }
        } else {
            tv_No_Data.setVisibility(View.VISIBLE);
        }
    }

    public void search(String search_text) {
        ArrayList<HomeModel> array_data = new ArrayList<>();
        array_data = DashboardController.search(getActivity(), search_text);
        setUpRecyclerView(array_data);
    }


    @Override
    public void Scrolled() {
        page_no++;
        if (page_no <= Integer.parseInt(max_count)) {
            getDashboardData();
        }
    }
}
