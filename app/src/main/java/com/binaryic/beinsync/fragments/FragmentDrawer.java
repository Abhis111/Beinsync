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

        ArrayList<DrawerModel> array_Data = new ArrayList<DrawerModel>();


        for (int i = 0; i < 3; i++) {
            DrawerModel model = new DrawerModel();
            switch (i) {
                case 0:
                    model.setId("category/marketing/");
                    model.setTitle("Marketing");
                    break;
                case 1:
                    model.setId("category/design-development/");
                    model.setTitle("Design and Development");
                    break;
                case 2:
                    model.setId("category/news/");
                    model.setTitle("News");
                    break;
                case 3:
                    model.setId("learning-lab/events/");
                    model.setTitle("Events");
                    break;
                case 4:
                    model.setId("learning-lab/webinar/");
                    model.setTitle("Webinar");
                    break;


            }

            array_Data.add(model);
        }


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
