package com.compubase.mhmd.digitalatlas;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AdminMain extends AppCompatActivity {
    ImageButton add, patientlisst, approve, notific;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        final AddFinfing1 addFinfing1 = new AddFinfing1();
        final AdminApproveList adminApproveList = new AdminApproveList();
        final AdminNotification adminNotification = new AdminNotification();
        final PaientList paientList = new PaientList();
        approve = findViewById(R.id.approve);
        //final AddPatientFragment addPatientFragment = new AddPatientFragment();
        //final PatientListAdmin patientListAdmin = new  PatientListAdmin();
        // PatientNotification patientNotification = new PatientNotification();
        // final NotificationAdmin notificationAdmin = new NotificationAdmin();
        //FragmentManager fragmentManager = getFragmentManager();
        // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.add(R.id.cotainer , frag);
        //fragmentTransaction.commit();
        add = findViewById(R.id.addfinfingadmin);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.cotaineradmin,addFinfing1 );
                fragmentTransaction.commit();
            }
        });
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.cotaineradmin,adminApproveList );
                fragmentTransaction.commit();
            }
        });
        notific = findViewById(R.id.notificAdmin);
        notific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.cotaineradmin,adminNotification );
                fragmentTransaction.commit();

            }
        });
        patientlisst = findViewById(R.id.paierntlistadmin);
        patientlisst .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.cotaineradmin,paientList );
                fragmentTransaction.commit();

            }
        });
    }
}






