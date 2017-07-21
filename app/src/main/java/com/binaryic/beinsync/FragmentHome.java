package com.binaryic.beinsync;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * Created by Asd on 27-09-2016.
 */
public class FragmentHome extends Fragment {

    private RecyclerView rv_Home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        view = inflater.inflate(R.layout.fragment_home, container, false);
        rv_Home = (RecyclerView) view.findViewById(R.id.rv_Home);
        rv_Home.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rv_Home.setAdapter(new HomeAdapter(getActivity(), new ArrayList<HomeModel>()));
        return view;
    }


}
