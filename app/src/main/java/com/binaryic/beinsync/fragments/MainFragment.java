package com.binaryic.beinsync.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.common.Constants;
import com.binaryic.beinsync.controllers.DashboardController;
import com.binaryic.beinsync.models.HomeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 4/11/2016.
 */
public class MainFragment extends Fragment {
    private TabLayout tabs;
    private ViewPager viewpager;



    @Override
    public void onResume() {
        super.onResume();

        // Tracking the screen view
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_main_fragment, container, false);
        tabs = (TabLayout) view.findViewById(R.id.tabs);

        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        setFragment();

        tabs.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewpager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                       /* Log.e("positipositionon", "==" + tab.getPosition());
                        switch (tab.getPosition()) {
                            case 0:
                                MainActivity.searchItem.setVisible(true);
                                MainActivity.ll_Quates.setVisibility(View.GONE);
                                MainActivity.ll_TextAndContentFormatting.setVisibility(View.VISIBLE);

                                break;
                            case 1:
                                MainActivity.ll_Quates.setVisibility(View.VISIBLE);
                                MainActivity.searchItem.setVisible(false);
                                MainActivity.ll_TextAndContentFormatting.setVisibility(View.GONE);

                                break;
                        }*/
                        //   int tabIconColor = ContextCompat.getColor(getActivity(), R.color.blue);
                        //   tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        //  int tabIconColor = ContextCompat.getColor(getActivity(), R.color.black);
                        //  tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );

       /* tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/


        return view;
    }


    private void setFragment() {

        if (viewpager != null) {
            Adapter adapter = new Adapter(getChildFragmentManager());

            FragmentHome fragmentNews = new FragmentHome();
            Bundle bundle = new Bundle();
            bundle.putString("category", "News");
            fragmentNews.setArguments(bundle);

            FragmentHome fragmentMarketing = new FragmentHome();
            Bundle bundle1 = new Bundle();
            bundle1.putString("category", "Marketing");
            fragmentMarketing.setArguments(bundle1);

            FragmentHome fragmentDD = new FragmentHome();
            Bundle bundle2 = new Bundle();
            bundle2.putString("category", "Design &amp; Development");
            fragmentDD.setArguments(bundle2);

            FragmentHome fragmentEcommerce = new FragmentHome();
            Bundle bundle3 = new Bundle();
            bundle3.putString("category", "Ecommerce");
            fragmentEcommerce.setArguments(bundle3);

            FragmentHome fragmentEvent = new FragmentHome();
            Bundle bundle4 = new Bundle();
            bundle4.putString("category", "Event");
            fragmentEvent.setArguments(bundle4);

            adapter.addFragment(fragmentNews, "News");
            adapter.addFragment(fragmentMarketing, "Marketing");
            adapter.addFragment(fragmentDD, "Design & Development");
            adapter.addFragment(fragmentEcommerce, "Ecommerce");
            adapter.addFragment(fragmentEvent, "Event");

            viewpager.setAdapter(adapter);
        }
        tabs.setupWithViewPager(viewpager);

    }

    public class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);

        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    public void filter(String tags){
        Toast.makeText(getActivity(), tags, Toast.LENGTH_SHORT).show();
        ArrayList<HomeModel> array_data = new ArrayList<>();
        array_data = DashboardController.filter(getActivity(), tags);
    }

}
