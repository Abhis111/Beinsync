package com.binaryic.beinsync.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.adapters.HomeAdapter;
import com.binaryic.beinsync.common.ApiCallBack;
import com.binaryic.beinsync.controllers.DashboardController;
import com.binaryic.beinsync.models.HomeModel;

import java.util.ArrayList;

/**
 * Created by Asd on 27-09-2016.
 */
public class FragmentNews extends Fragment {

    private RecyclerView rv_Home;
    private SwipeRefreshLayout swipeContainer;
    private String link = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        view = inflater.inflate(R.layout.fragment_home, container, false);
        rv_Home = (RecyclerView) view.findViewById(R.id.rv_Home);

        rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            link = (bundle.getString("link")) + "?json=1";
        }
       /* ArrayList<HomeModel> array_Data = new ArrayList<>();
        array_Data = getDashboardDataFromDatabase(getActivity());
        rv_Home.setAdapter(new HomeAdapter(getActivity(), array_Data));*/

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
        DashboardController.getNewsApiCall(getActivity(), link, new ApiCallBack() {
            @Override
            public void onSuccess(Object success) {
                ArrayList<HomeModel> array_Data = (ArrayList<HomeModel>) (success);

                swipeContainer.setRefreshing(false);
                rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                rv_Home.setAdapter(new HomeAdapter(getActivity(), array_Data));
            }

            @Override
            public void onError(String error) {
                ArrayList<HomeModel> array_Data = new ArrayList<>();


                swipeContainer.setRefreshing(false);
                rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                rv_Home.setAdapter(new HomeAdapter(getActivity(), array_Data));
            }
        });
    }


}
