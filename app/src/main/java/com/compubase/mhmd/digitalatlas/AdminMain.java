package com.compubase.mhmd.digitalatlas;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AdminMain extends AppCompatActivity {
    ImageButton add , patientlist , approve , notific;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_patient_list_admin);
        AdminFragment frag = new AdminFragment();
        final AddPatientFragment addPatientFragment = new AddPatientFragment();
        final PatientListAdmin patientListAdmin = new  PatientListAdmin();
        final PatientNotification patientNotification = new PatientNotification();
        final NotificationAdmin notificationAdmin = new NotificationAdmin();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       fragmentTransaction.add(R.id.cotainer , frag);
        fragmentTransaction.commit();
        add = findViewById(R.id.adds);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.cotainer, addPatientFragment);
                fragmentTransaction.commit();
            }
        });
        patientlist = findViewById(R.id.lists);
        patientlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.cotainer, patientListAdmin);
                fragmentTransaction.commit();
            }
        });
        notific =findViewById(R.id.notific);
        notific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.cotainer, notificationAdmin);
                fragmentTransaction.commit();
            }
        });
        approve = findViewById(R.id.approve);
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.cotainer, patientNotification);
                fragmentTransaction.commit();
            }
        });
    }
}
