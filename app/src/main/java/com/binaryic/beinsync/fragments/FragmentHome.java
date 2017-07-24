package com.binaryic.beinsync.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.adapters.HomeAdapter;
import com.binaryic.beinsync.common.ApiCallBack;
import com.binaryic.beinsync.controllers.DashboardController;
import com.binaryic.beinsync.models.HomeModel;

import java.util.ArrayList;

import static com.binaryic.beinsync.controllers.DashboardController.getDashboardDataFromDatabase;

/**
 * Created by Asd on 27-09-2016.
 */
public class FragmentHome extends Fragment {

    private RecyclerView rv_Home;
    private SwipeRefreshLayout swipeContainer;
    private String link = "";
    private TextView tv_No_Data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        view = inflater.inflate(R.layout.fragment_home, container, false);
        rv_Home = (RecyclerView) view.findViewById(R.id.rv_Home);
        tv_No_Data = (TextView) view.findViewById(R.id.tv_No_Data);

        rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            link = (bundle.getString("link"));
        }
        ArrayList<HomeModel> array_Data = new ArrayList<>();
        array_Data = getDashboardDataFromDatabase(getActivity());
        rv_Home.setAdapter(new HomeAdapter(getActivity(), array_Data));
        swipeContainer.setRefreshing(false);
        getDashboardData();
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDashboardData();
            }
        });

        return view;
    }

    private void getDashboardData() {
        DashboardController.getDashboardApiCall(getActivity(), link, new ApiCallBack() {
            @Override
            public void onSuccess(Object success) {
                ArrayList<HomeModel> array_Data = new ArrayList<>();
                array_Data = getDashboardDataFromDatabase(getActivity());
                if (array_Data.size() > 0) {
                    tv_No_Data.setVisibility(View.GONE);
                    swipeContainer.setVisibility(View.VISIBLE);
                    swipeContainer.setRefreshing(false);
                    rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    rv_Home.setAdapter(new HomeAdapter(getActivity(), array_Data));
                } else {
                    tv_No_Data.setVisibility(View.VISIBLE);
                    swipeContainer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String error) {
                ArrayList<HomeModel> array_Data = new ArrayList<>();
                tv_No_Data.setVisibility(View.VISIBLE);
                swipeContainer.setVisibility(View.GONE);
                array_Data = getDashboardDataFromDatabase(getActivity());

                swipeContainer.setRefreshing(false);
                rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                rv_Home.setAdapter(new HomeAdapter(getActivity(), array_Data));
            }
        });
    }

    public void search(String search_text){
        ArrayList<HomeModel> array_data = new ArrayList<>();
        array_data = DashboardController.search(getActivity(),search_text);
        if (array_data.size() > 0) {
            tv_No_Data.setVisibility(View.GONE);
            swipeContainer.setVisibility(View.VISIBLE);
            swipeContainer.setRefreshing(false);
            rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            rv_Home.setAdapter(new HomeAdapter(getActivity(), array_data));
        }else {
            tv_No_Data.setVisibility(View.VISIBLE);
            swipeContainer.setVisibility(View.GONE);
        }

    }


}
