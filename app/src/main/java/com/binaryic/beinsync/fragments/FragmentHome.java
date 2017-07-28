package com.binaryic.beinsync.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.activities.MainActivity;
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

    public static LinearLayout ll_MainLayout;
    private RecyclerView rv_Home;
    private SwipeRefreshLayout swipeContainer;
    private String link = "";
    private String category = "";
    private TextView tv_No_Data;
    ArrayList<HomeModel> array_Data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        //  MainActivity.ll_textFormatOptions.setVisibility(View.VISIBLE);

        view = inflater.inflate(R.layout.fragment_home, container, false);
        ll_MainLayout = (LinearLayout) view.findViewById(R.id.ll_MainLayout);
        rv_Home = (RecyclerView) view.findViewById(R.id.rv_Home);
        tv_No_Data = (TextView) view.findViewById(R.id.tv_No_Data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        rv_Home.setLayoutManager(linearLayoutManager);
        //rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            link = (bundle.getString("link"));
            category = (bundle.getString("category"));
        }

        array_Data = getDashboardDataFromDatabase(getActivity(), category);
        rv_Home.setAdapter(new HomeAdapter(getActivity(), array_Data, category));
        swipeContainer.setRefreshing(false);
        //    getDashboardData(array_Data);
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDashboardData(array_Data);
            }
        });

        return view;
    }

    private void getDashboardData(ArrayList<HomeModel> array_Data) {

        DashboardController.getDashboardApiCall(getActivity(), link, new ApiCallBack() {
            @Override
            public void onSuccess(Object success) {
                ArrayList<HomeModel> array_Data = new ArrayList<>();
                array_Data = getDashboardDataFromDatabase(getActivity(), category);
                if (array_Data.size() > 0) {
                    tv_No_Data.setVisibility(View.GONE);
                    swipeContainer.setVisibility(View.VISIBLE);
                    swipeContainer.setRefreshing(false);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    linearLayoutManager.setAutoMeasureEnabled(true);
                    rv_Home.setLayoutManager(linearLayoutManager);
                    //rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    rv_Home.setAdapter(new HomeAdapter(getActivity(), array_Data, category));
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
                array_Data = getDashboardDataFromDatabase(getActivity(), category);
                if (array_Data.size() > 0) {
                    tv_No_Data.setVisibility(View.GONE);
                    swipeContainer.setVisibility(View.VISIBLE);
                    swipeContainer.setRefreshing(false);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    linearLayoutManager.setAutoMeasureEnabled(true);
                    rv_Home.setLayoutManager(linearLayoutManager);
                    //rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    rv_Home.setAdapter(new HomeAdapter(getActivity(), array_Data, category));
                } else {
                    tv_No_Data.setVisibility(View.VISIBLE);
                    swipeContainer.setVisibility(View.GONE);
                }

            }
        });

    }

    public void search(String search_text) {
        ArrayList<HomeModel> array_data = new ArrayList<>();
        array_data = DashboardController.search(getActivity(), search_text);
        if (array_data.size() > 0) {
            tv_No_Data.setVisibility(View.GONE);
            swipeContainer.setVisibility(View.VISIBLE);
            swipeContainer.setRefreshing(false);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            linearLayoutManager.setAutoMeasureEnabled(true);
            rv_Home.setLayoutManager(linearLayoutManager);
            //rv_Home.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            rv_Home.setAdapter(new HomeAdapter(getActivity(), array_data, category));
        } else {
            tv_No_Data.setVisibility(View.VISIBLE);
            swipeContainer.setVisibility(View.GONE);
        }

    }




}
