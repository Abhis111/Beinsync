package com.binaryic.beinsync.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.adapters.DrawerAdapter;
import com.binaryic.beinsync.models.DrawerModel;

import java.util.ArrayList;


/**
 * Created by Asd on 27-09-2016.
 */
public class FragmentDrawer extends Fragment {

    private RecyclerView rv_Drawer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        view = inflater.inflate(R.layout.fragment_drawer, container, false);
        rv_Drawer = (RecyclerView) view.findViewById(R.id.rv_Drawer);

        rv_Drawer.hasFixedSize();
        rv_Drawer.setLayoutManager(new LinearLayoutManager(getActivity()));


        ArrayList<DrawerModel> array_Data = new ArrayList<>();
        DrawerModel model = new DrawerModel();
        model.setId("0");
        model.setTitle("Marketing");
        array_Data.add(model);

        DrawerModel model1 = new DrawerModel();
        model1.setId("1");
        model1.setTitle("News");
        array_Data.add(model1);

        DrawerModel model2 = new DrawerModel();
        model2.setId("2");
        model2.setTitle("Learning lab");
        array_Data.add(model2);

        DrawerModel model3 = new DrawerModel();
        model3.setId("3");
        model3.setTitle("Design and Development");
        array_Data.add(model3);


        rv_Drawer.setAdapter(new DrawerAdapter(getActivity(), array_Data));
        return view;
    }

/*
    private void getDashboardData() {
        DashboardController.getDashboardApiCall(getActivity(), new ApiCallBack() {
            @Override
            public void onSuccess(Object success) {
                ArrayList<HomeModel> array_Data = new ArrayList<>();

                array_Data = getDashboardDataFromDatabase(getActivity());
                swipeContainer.setRefreshing(false);
                rv_Drawer.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                rv_Drawer.setAdapter(new HomeAdapter(getActivity(), array_Data));
            }

            @Override
            public void onError(String error) {
                ArrayList<HomeModel> array_Data = new ArrayList<>();

                array_Data = getDashboardDataFromDatabase(getActivity());

                swipeContainer.setRefreshing(false);
                rv_Drawer.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                rv_Drawer.setAdapter(new HomeAdapter(getActivity(), array_Data));
            }
        });
    }
*/


}
