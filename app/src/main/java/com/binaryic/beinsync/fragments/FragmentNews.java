package com.binaryic.beinsync.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.adapters.HomeAdapter;
import com.binaryic.beinsync.adapters.NewsAdapter;
import com.binaryic.beinsync.common.ApiCallBack;
import com.binaryic.beinsync.common.Constants;
import com.binaryic.beinsync.common.InternetConnectionDetector;
import com.binaryic.beinsync.common.MyApplication;
import com.binaryic.beinsync.controllers.DashboardController;
import com.binaryic.beinsync.models.HomeModel;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;

/**
 * Created by Asd on 27-09-2016.
 */
public class FragmentNews extends Fragment implements NewsAdapter.ScrollListener {

    private RelativeLayout ll_MainLayout;
    private RecyclerView rv_Home;
    private TextView tv_CategoryName;
    private String link = "";
    String id = "";
    String max_count = "1";
    int page_no = 1;
    ArrayList<HomeModel> array_Data = new ArrayList<>();
    NewsAdapter adapter;
    LinearLayout ll_progress;
    TextView tv_No_Data;
    String Category = "";
    private static Tracker mTracker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        MyApplication application = (MyApplication) getActivity().getApplication();
        mTracker = application.getDefaultTracker();
        view = inflater.inflate(R.layout.fragment_news, container, false);
        ll_MainLayout = (RelativeLayout) view.findViewById(R.id.ll_MainLayout);
        tv_CategoryName = (TextView) view.findViewById(R.id.tv_CategoryName);
        rv_Home = (RecyclerView) view.findViewById(R.id.rv_Home);
        ll_progress = (LinearLayout) view.findViewById(R.id.ll_progress);
        tv_No_Data = (TextView) view.findViewById(R.id.tv_No_Data);
        rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            tv_CategoryName.setText((bundle.getString("category")));
            Category = (bundle.getString("category"));
            id = (bundle.getString("id"));
            max_count = (bundle.getString("page_count"));
            mTracker.setScreenName(bundle.getString("category"));
            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
        getDashboardData();
        return view;
    }

    private void getDashboardData() {
        if (InternetConnectionDetector.isInternetWorking()) {
            link = Constants.URL + "?json=get_category_posts&id=" + id + "&count=10&status=publish&page=" + page_no;
            ll_progress.setVisibility(View.VISIBLE);
            DashboardController.getNewsApiCall(getActivity(), link, new ApiCallBack() {
                @Override
                public void onSuccess(Object success) {
                    ll_progress.setVisibility(View.GONE);
                    ArrayList<HomeModel> list = (ArrayList<HomeModel>) (success);
                    setUpRecyclerView(list);
                }

                @Override
                public void onError(String error) {
                    ll_progress.setVisibility(View.GONE);
                    ArrayList<HomeModel> array_Data = new ArrayList<>();
                    rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    rv_Home.setAdapter(new HomeAdapter(getActivity(), array_Data, Category));
                }
            });
        } else {
            ll_progress.setVisibility(View.GONE);

            tv_No_Data.setVisibility(View.VISIBLE);
            rv_Home.setVisibility(View.GONE);
        }
    }

    private void setUpRecyclerView(ArrayList<HomeModel> list) {
        Log.e("FragmentNews", "array_size==" + list.size());
        for (HomeModel homeModel : list) {
            array_Data.add(homeModel);
        }
        if (array_Data.size() > 0) {
            Log.e("FragmentNews", "array_size==" + array_Data.size());

            tv_No_Data.setVisibility(View.GONE);
            rv_Home.setVisibility(View.VISIBLE);
            if (page_no == 1) {
                rv_Home.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new NewsAdapter(getActivity(), array_Data);
                adapter.setScrollListener(FragmentNews.this);
                rv_Home.setAdapter(adapter);
            } else {
                adapter.list = array_Data;
                adapter.notifyDataSetChanged();
            }
        } else {
            tv_No_Data.setVisibility(View.VISIBLE);
            rv_Home.setVisibility(View.GONE);

        }
    }


    @Override
    public void Scrolled() {
        page_no++;
        if (page_no <= Integer.parseInt(max_count)) {
            getDashboardData();
        }
    }
}
