package com.brauliomendez.mechanicsnotes.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brauliomendez.mechanicsnotes.R;
import com.brauliomendez.mechanicsnotes.model.ServiceCollection;
import com.brauliomendez.mechanicsnotes.ui.adapter.ServiceAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Braulio on 28/06/2016.
 */
public class ServiceFragment extends Fragment {

    @Bind(R.id.main_recycler) RecyclerView mRecyclerView;

    private ServiceAdapter mServiceAdapter;
    private ServiceCollection mServiceCollection;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        if (mServiceCollection != null){
        mServiceAdapter = new ServiceAdapter(getContext(), mServiceCollection.mServices);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mServiceAdapter);
        }
    }


    @OnClick(R.id.fab) public void setService() {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_fragment, new DetailFragment())
                .addToBackStack("detail_fragment")
                .commit();

    }

}
