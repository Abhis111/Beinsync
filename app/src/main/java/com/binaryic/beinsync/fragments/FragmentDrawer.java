package com.binaryic.beinsync.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.adapters.DrawerAdapter;
import com.binaryic.beinsync.adapters.SubDrawerAdapter;
import com.binaryic.beinsync.controllers.DashboardController;
import com.binaryic.beinsync.models.DrawerModel;
import com.binaryic.beinsync.models.TopicModel;

import java.util.ArrayList;

import static com.binaryic.beinsync.activities.MainActivity.drawer;


/**
 * Created by Asd on 27-09-2016.
 */
public class FragmentDrawer extends Fragment {

    private RecyclerView rv_Drawer, rv_sub_drawer;
    private ImageView tvi_Home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        view = inflater.inflate(R.layout.fragment_drawer, container, false);
        tvi_Home = (ImageView) view.findViewById(R.id.tvi_Home);
        rv_Drawer = (RecyclerView) view.findViewById(R.id.rv_Drawer);
        rv_sub_drawer = (RecyclerView) view.findViewById(R.id.rv_sub_drawer);

        tvi_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(Gravity.LEFT);
            }
        });

        ArrayList<DrawerModel> array_Data = new ArrayList<DrawerModel>();

        for (int i = 0; i < 6; i++) {
            switch (i) {
                case 0:
                    array_Data.add(new DrawerModel("category/marketing/", "Marketing", false, false, false));
                    break;
                case 1:
                    array_Data.add(new DrawerModel("category/design-development/", "Design and Development", false, false, false));
                    break;
                case 2:
                    array_Data.add(new DrawerModel("category/news/", "News", false, false, false));
                    break;
                case 3:
                    array_Data.add(new DrawerModel("learning-lab/events/", "Events", false, false, false));
                    break;
                case 4:
                    array_Data.add(new DrawerModel("learning-lab/webinar/", "Webinar", false, false, false));
                    break;
                case 5:
                    array_Data.add(new DrawerModel("learning-lab/Topics/", "Topics", false, true, false));
                    break;

            }

        }
        //ArrayList<DrawerModel> array_data_sub = new ArrayList<DrawerModel>();

        ArrayList<TopicModel> array_data_sub = DashboardController.getTopics(getActivity());


        rv_Drawer.hasFixedSize();
        rv_Drawer.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_Drawer.setAdapter(new DrawerAdapter(getActivity(), array_Data, rv_sub_drawer));
        rv_sub_drawer.hasFixedSize();
        rv_sub_drawer.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_sub_drawer.setAdapter(new SubDrawerAdapter(getActivity(), array_data_sub));
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
