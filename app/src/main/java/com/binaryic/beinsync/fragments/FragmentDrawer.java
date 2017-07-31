package com.binaryic.beinsync.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.adapters.DrawerAdapter;
import com.binaryic.beinsync.adapters.SubDrawerAdapter;
import com.binaryic.beinsync.common.ApiCallBack;
import com.binaryic.beinsync.common.Utils;
import com.binaryic.beinsync.controllers.DashboardController;
import com.binaryic.beinsync.models.TopicModel;

import java.util.ArrayList;

import static com.binaryic.beinsync.activities.MainActivity.drawer;


/**
 * Created by Asd on 27-09-2016.
 */
public class FragmentDrawer extends Fragment {

    private LinearLayout ll_NoData;
    private RecyclerView rv_Drawer, rv_sub_drawer;
    private ImageView tvi_Home;
    private TextView tv_No_Data;
    private TextView tv_Sync;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        view = inflater.inflate(R.layout.fragment_drawer, container, false);
        tvi_Home = (ImageView) view.findViewById(R.id.tvi_Home);
        rv_Drawer = (RecyclerView) view.findViewById(R.id.rv_Drawer);
        rv_sub_drawer = (RecyclerView) view.findViewById(R.id.rv_sub_drawer);
        tv_No_Data = (TextView) view.findViewById(R.id.tv_No_Data);
        tv_Sync = (TextView) view.findViewById(R.id.tv_Sync);
        ll_NoData = (LinearLayout) view.findViewById(R.id.ll_NoData);

        tvi_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(Gravity.LEFT);
            }
        });
        tv_Sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.downloading_Dialog.show();
                getTopics();
            }
        });
        Utils.createDialog(getActivity());
        setDrawerData();

        return view;
    }

    private void setDrawerData() {
        ArrayList<TopicModel> array_Data = new ArrayList<TopicModel>();
        TopicModel topicModel = null;
        ArrayList<TopicModel> array_data = DashboardController.getTopics(getActivity());
        Log.e("FragmentDrawer", "array_data==" + array_data.size());
        if (array_data.size() > 0) {
            ll_NoData.setVisibility(View.GONE);
            rv_Drawer.setVisibility(View.VISIBLE);
            for (int i = 0; i < 6; i++) {
                switch (i) {
                    case 0:
                        topicModel = DashboardController.getTopicId(getActivity(), "Marketing");
                        if (topicModel != null)
                            array_Data.add(topicModel);
                        break;
                    case 1:
                        topicModel = DashboardController.getTopicId(getActivity(), "Design &amp; Development");
                        topicModel.setTitle("Design & Development");
                        if (topicModel != null)
                            array_Data.add(topicModel);
                        break;
                    case 2:
                        topicModel = DashboardController.getTopicId(getActivity(), "News");
                        if (topicModel != null)
                            array_Data.add(topicModel);
                        break;
                    case 3:
                        topicModel = DashboardController.getTopicId(getActivity(), "Events");
                        if (topicModel != null)
                            array_Data.add(topicModel);
                        break;
                    case 4:
                        topicModel = DashboardController.getTopicId(getActivity(), "Webinar");
                        if (topicModel != null)
                            array_Data.add(topicModel);
                        break;
                    case 5:
                        topicModel = new TopicModel();
                        topicModel.setTitle("Topics");
                        array_Data.add(topicModel);
                        break;

                }

            }
        } else {
            Log.e("FragmentDrawer", "else");
            ll_NoData.setVisibility(View.VISIBLE);
            rv_Drawer.setVisibility(View.GONE);
        }
        //ArrayList<DrawerModel> array_data_sub = new ArrayList<DrawerModel>();

        ArrayList<TopicModel> array_data_sub = DashboardController.getTopics(getActivity());


        rv_Drawer.hasFixedSize();
        rv_Drawer.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_Drawer.setAdapter(new DrawerAdapter(getActivity(), array_Data, rv_sub_drawer));
        rv_sub_drawer.hasFixedSize();
        rv_sub_drawer.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_sub_drawer.setAdapter(new SubDrawerAdapter(getActivity(), array_data_sub));
    }

    private void getTopics() {
        DashboardController.getTopicsApiCall(getActivity(), "http://www.beinsync.in/?json=get_category_index", new ApiCallBack() {
            @Override
            public void onSuccess(Object success) {
                setDrawerData();
                Utils.downloading_Dialog.show();

            }

            @Override
            public void onError(String error) {

                Log.e("FragmentDrawer", "error==" + error);
                setDrawerData();
                Utils.downloading_Dialog.show();

            }
        });
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
