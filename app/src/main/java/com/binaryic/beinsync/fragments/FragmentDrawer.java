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


        for (int i = 0; i < 31; i++) {
            switch (i) {
                case 0:
                    array_Data.add(new DrawerModel("category/marketing/","Marketing",false,false,false));
                    break;
                case 1:
                    array_Data.add(new DrawerModel("category/design-development/","Design and Development",false,false,false));
                    break;
                case 2:
                    array_Data.add(new DrawerModel("category/news/","News",false,false,false));
                    break;
                case 3:
                    array_Data.add(new DrawerModel("learning-lab/events/","Events",false,false,false));
                    break;
                case 4:
                    array_Data.add(new DrawerModel("learning-lab/webinar/","Webinar",false,false,false));
                    break;
                case 5:
                    array_Data.add(new DrawerModel("learning-lab/Topics/","Topics",false,true,false));
                    break;
                case 6:
                    array_Data.add(new DrawerModel("category/marketing/advertising/","Advertising",false,false,true));
                    break;
                case 7:
                    array_Data.add(new DrawerModel("category/marketing/affiliate/","Affiliate",false,false,true));
                    break;
                case 8:
                    array_Data.add(new DrawerModel("category/marketing/analytics/","Analytics",false,false,true));
                    break;
                case 9:
                    array_Data.add(new DrawerModel("category/marketing/automation/","Automation",false,false,true));
                    break;
                case 10:
                    array_Data.add(new DrawerModel("category/marketing/blogging/","Blogging",false,false,true));
                    break;
                case 11:
                    array_Data.add(new DrawerModel("category/marketing/branding/","Branding",false,false,true));
                    break;
                case 12:
                    array_Data.add(new DrawerModel("category/marketing/content-marketing/","Content Marketing",false,false,true));
                    break;
                case 13:
                    array_Data.add(new DrawerModel("category/marketing/customer-experience/","Customer Experience",false,false,true));
                    break;
                case 14:
                    array_Data.add(new DrawerModel("category/marketing/design-development/","Design & Development",false,false,true));
                    break;
                case 15:
                    array_Data.add(new DrawerModel("category/marketing/digital-intelligence/","Digital Intelligence",false,false,true));
                    break;
                case 16:
                    array_Data.add(new DrawerModel("category/marketing/digital-strategy/","Digital Strategy",false,false,true));
                    break;
                case 17:
                    array_Data.add(new DrawerModel("category/marketing/ecommerce-marketing/","Ecommerce",false,false,true));
                    break;
                case 18:
                    array_Data.add(new DrawerModel("category/marketing/email-marketing/","Email Marketing",false,false,true));
                    break;
                case 19:
                    array_Data.add(new DrawerModel("category/marketing/holiday-marketing/","Holiday Marketing",false,false,true));
                    break;
                case 20:
                    array_Data.add(new DrawerModel("category/marketing/influencer-marketing/","Influencer Marketing",false,false,true));
                    break;
                case 21:
                    array_Data.add(new DrawerModel("category/marketing/insights/","Insights",false,false,true));
                    break;
                case 22:
                    array_Data.add(new DrawerModel("category/marketing/marketing/","Marketing",false,false,true));
                    break;
                case 23:
                    array_Data.add(new DrawerModel("category/marketing/marketing-automation/","Marketing Automation",false,false,true));
                    break;
                case 24:
                    array_Data.add(new DrawerModel("category/marketing/mobile-marketing/","Mobile Marketing",false,false,true));
                    break;
                case 25:
                    array_Data.add(new DrawerModel("category/marketing/news/","News",false,false,true));
                    break;
                case 26:
                    array_Data.add(new DrawerModel("category/marketing/ppc/","PPC",false,false,true));
                    break;
                case 27:
                    array_Data.add(new DrawerModel("category/marketing/search/","Search",false,false,true));
                    break;
                case 28:
                    array_Data.add(new DrawerModel("category/marketing/seo/","SEO",false,false,true));
                    break;
                case 29:
                    array_Data.add(new DrawerModel("category/marketing/slider/","Slider",false,false,true));
                    break;
                case 30:
                    array_Data.add(new DrawerModel("category/marketing/social-media/","Social Media",false,false,true));
                    break;
                case 31:
                    array_Data.add(new DrawerModel("category/marketing/video-marketing/","Video Marketing",false,false,true));
                    break;

            }


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
