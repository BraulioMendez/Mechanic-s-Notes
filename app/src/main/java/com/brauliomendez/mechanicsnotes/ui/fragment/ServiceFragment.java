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
import com.brauliomendez.mechanicsnotes.model.Service;
import com.brauliomendez.mechanicsnotes.ui.adapter.ServiceAdapter;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Braulio on 28/06/2016.
 */
public class ServiceFragment extends Fragment {

    @Bind(R.id.main_recycler) RecyclerView mRecyclerView;

    private final static String SAVED_ADAPTER_ITEMS = "SAVED_ADAPTER_ITEMS";
    private final static String SAVED_ADAPTER_KEYS = "SAVED_ADAPTER_KEYS";

    private Query mQuery;
    private ServiceAdapter mServiceAdapter;
    private ArrayList<Service> mAdapterItems;
    private ArrayList<String> mAdapterKeys;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleInstanceState(savedInstanceState);
        setupFirebase();
    }

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

    private void handleInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(SAVED_ADAPTER_ITEMS) &&
                savedInstanceState.containsKey(SAVED_ADAPTER_KEYS)) {
            mAdapterItems = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_ADAPTER_ITEMS));
            mAdapterKeys = savedInstanceState.getStringArrayList(SAVED_ADAPTER_KEYS);
        } else {
            mAdapterItems = new ArrayList<Service>();
            mAdapterKeys = new ArrayList<String>();
        }
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_ADAPTER_ITEMS, Parcels.wrap(mServiceAdapter.getItems()));
        outState.putStringArrayList(SAVED_ADAPTER_KEYS, mServiceAdapter.getKeys());
    }

    private void setupFirebase() {
        String firebaseLocation = getResources().getString(R.string.firebase_url);
        mQuery = new Firebase(firebaseLocation);
    }

    private void setUpRecyclerView() {
        mServiceAdapter = new ServiceAdapter(mQuery, Service.class, mAdapterItems, mAdapterKeys);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mServiceAdapter);
    }

    @OnClick(R.id.fab) public void setService() {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_fragment, new DetailFragment())
                .addToBackStack("detail_fragment")
                .commit();

    }

}
