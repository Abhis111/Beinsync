package com.binaryic.beinsync.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.adapters.HomeAdapter;
import com.binaryic.beinsync.adapters.NewsAdapter;
import com.binaryic.beinsync.common.ApiCallBack;
import com.binaryic.beinsync.common.Constants;
import com.binaryic.beinsync.controllers.DashboardController;
import com.binaryic.beinsync.models.HomeModel;

import java.util.ArrayList;

import static com.binaryic.beinsync.R.id.swipeContainer;

/**
 * Created by Asd on 27-09-2016.
 */
public class FragmentNews extends Fragment implements NewsAdapter.ScrollListener {

    private RecyclerView rv_Home;
    private String link = "";
    String id = "";
    String max_count = "1";
    int page_no = 1;
    ArrayList<HomeModel> array_Data = new ArrayList<>();
    NewsAdapter adapter;
    LinearLayout ll_progress;
    TextView tv_No_Data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        view = inflater.inflate(R.layout.fragment_news, container, false);
        rv_Home = (RecyclerView) view.findViewById(R.id.rv_Home);
        ll_progress = (LinearLayout) view.findViewById(R.id.ll_progress);
        tv_No_Data = (TextView) view.findViewById(R.id.tv_No_Data);
        rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = (bundle.getString("id"));
            max_count = (bundle.getString("page_count"));

        }
        getDashboardData();


        return view;
    }

    private void getDashboardData() {
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
                rv_Home.setAdapter(new HomeAdapter(getActivity(), array_Data, array_Data.get(0).getTitle()));
            }
        });
    }

    private void setUpRecyclerView(ArrayList<HomeModel> list){
        //
        for(HomeModel homeModel : list){
            array_Data.add(homeModel);
        }
        if(list.size() > 0) {
            tv_No_Data.setVisibility(View.GONE);
            if (page_no == 1) {
                rv_Home.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new NewsAdapter(getActivity(), array_Data);
                adapter.setScrollListener(FragmentNews.this);
                rv_Home.setAdapter(adapter);
            } else {
                adapter.list = array_Data;
                adapter.notifyDataSetChanged();
            }
        }else{
            tv_No_Data.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void Scrolled() {
        page_no++;
        if(page_no <= Integer.parseInt(max_count)){
            getDashboardData();
        }
    }
}
