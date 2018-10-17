package com.company.mhmd.digitalatlas;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class PatientListFragment extends Fragment {
    RecyclerView patientRecyclerView ;
    RecyclerView.Adapter myadapter;
    RecyclerView.LayoutManager layoutManager;
    View view;
    ArrayList<ApproveList> approveLists;
    public PatientListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_patient_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        patientRecyclerView = view.findViewById(R.id.approvelist);
        patientRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        patientRecyclerView.setLayoutManager(layoutManager);
        approveLists = new ArrayList<ApproveList>();
        approveLists.add(new ApproveList("Mhmd"));
        approveLists.add(new ApproveList("ahmd"));
        approveLists.add(new ApproveList("Ali"));
        approveLists.add(new ApproveList("Omar"));
        approveLists.add(new ApproveList("Fucker"));
        approveLists.add(new ApproveList("Ramos"));
        approveLists.add(new ApproveList("Messi"));
        approveLists.add(new ApproveList("Mhmd"));
        approveLists.add(new ApproveList("Cr7"));
        approveLists.add(new ApproveList("Kaka"));
        approveLists.add(new ApproveList("Hassan"));
        myadapter = new ApproveListAdapter(approveLists,this.getActivity());
        patientRecyclerView.setAdapter(myadapter);
    }
}
