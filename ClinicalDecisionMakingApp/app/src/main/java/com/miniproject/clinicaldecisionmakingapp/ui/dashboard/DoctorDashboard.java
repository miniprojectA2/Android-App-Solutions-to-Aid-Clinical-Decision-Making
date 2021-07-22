package com.miniproject.clinicaldecisionmakingapp.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miniproject.clinicaldecisionmakingapp.R;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.miniproject.clinicaldecisionmakingapp.R;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentDoctorDashboardBinding;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentPatientDashboardBinding;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentRegisterBinding;
import com.miniproject.clinicaldecisionmakingapp.ui.doctors.CrudPatientFragment;

public class DoctorDashboard extends Fragment {

    FragmentDoctorDashboardBinding binding;
    DatabaseReference reference;
    FirebaseDatabase database;


    public DoctorDashboard() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDoctorDashboardBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_patient_dashboard, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String doctorEmail="";
        if(user != null) {
            doctorEmail = user.getEmail();
        }
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Doctor");

        showAllDetails(doctorEmail);

        binding.uDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new UpdateDoctorFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        
        binding.addPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AddPatientFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        binding.myPatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PatientsListsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }
    public void showAllDetails(String email) {
        final String[] name = new String[1];
//        final String[] age = new String[1];
        final String[] demail = new String[1];
//        final String[] phone = new String[1];
//        final String[] sex = new String[1];
//        final String[] department = new String[1];
        reference.orderByChild("doctorEmail").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datas : snapshot.getChildren()) {
                    name[0] = datas.child("doctorName").getValue().toString();
//                    age[0] = datas.child("doctorAge").getValue().toString();
                    demail[0] = datas.child("doctorEmail").getValue().toString();
//                    phone[0] = datas.child("doctorPhone").getValue().toString();
//                    sex[0] = datas.child("doctorSex").getValue().toString();
//                    department[0] = datas.child("department").getValue().toString();
                }

                updateHeader(name[0]);
                updateNavigationBarDoctor();

                binding.dName.setText(name[0]);
//                binding.dAge.setText("Age: " + age[0]);
//                binding.dEmail.setText("Email: " + email);
//                binding.dSex.setText("Sex: " + sex[0]);
//                binding.dDepartment.setText("Specialization: " + department[0]);
//                binding.dPhone.setText("Phone: " + phone[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateHeader(String email) {
        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.nav_view);
        View navHeader = navigationView.getHeaderView(0);
        TextView username = (TextView) navHeader.findViewById(R.id.username);
        username.setText(email);
    }

    public void updateNavigationBarDoctor() {
        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.nav_register).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_home).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_ddashboard).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_dupdate).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_addpatient).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_showpatients).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);

    }
}