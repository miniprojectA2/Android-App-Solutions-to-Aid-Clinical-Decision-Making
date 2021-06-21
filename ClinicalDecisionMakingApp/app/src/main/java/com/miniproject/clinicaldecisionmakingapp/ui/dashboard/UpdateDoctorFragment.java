package com.miniproject.clinicaldecisionmakingapp.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.miniproject.clinicaldecisionmakingapp.R;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentUpdateDoctorBinding;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentUpdatePatientBinding;
import com.miniproject.clinicaldecisionmakingapp.model.Doctor;
import com.miniproject.clinicaldecisionmakingapp.model.Patient;

public class UpdateDoctorFragment extends Fragment {

    FragmentUpdateDoctorBinding binding;
    FirebaseDatabase database;
    DatabaseReference reference;

    ArrayAdapter<CharSequence> adapter;

    public UpdateDoctorFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String patientEmail="";
        if(user != null) {
            patientEmail = user.getEmail();
            System.out.println(user.getUid());
        }
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Doctor");


        showAllDetails(patientEmail);

        binding.updateDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String doctorName = binding.doctorName.getText().toString();
                final String doctorEmail = binding.doctorEmail.getText().toString();
                final String doctorPhone = binding.phone1.getText().toString();
                final String doctorAge = binding.doctorAge.getText().toString();
                final String doctorDepartment = binding.doctorDepartment.getText().toString();
                final String doctorSex = binding.doctorSex.getSelectedItem().toString();

                Doctor doctor = new Doctor();
                doctor.setDoctorName(doctorName);
                doctor.setDoctorEmail(doctorEmail);
                doctor.setDepartment(doctorDepartment);
                doctor.setDoctorAge(doctorAge);
                doctor.setDoctorSex(doctorSex);
                doctor.setDoctorPhone(doctorPhone);

                binding.progressBar2.setVisibility(View.VISIBLE);

                reference.child(user.getUid()).setValue(doctor);

                Fragment fragment = new UpdateDoctorFragment();
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
        final String[] age = new String[1];
        final String[] demail = new String[1];
        final String[] phone = new String[1];
        final String[] sex = new String[1];
        final String[] department = new String[1];
        reference.orderByChild("doctorEmail").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datas : snapshot.getChildren()) {
                    name[0] = datas.child("doctorName").getValue().toString();
                    age[0] = datas.child("doctorAge").getValue().toString();
                    demail[0] = datas.child("doctorEmail").getValue().toString();
                    phone[0] = datas.child("doctorPhone").getValue().toString();
                    sex[0] = datas.child("doctorSex").getValue().toString();
                    department[0] = datas.child("department").getValue().toString();
                }


                adapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_sex, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                binding.doctorSex.setAdapter(adapter);

                binding.doctorName.setText(name[0]);
                binding.doctorAge.setText(age[0]);
                binding.doctorEmail.setText(email);

                int pos = adapter.getPosition(sex[0]);
                binding.doctorSex.setSelection(pos);

                binding.doctorDepartment.setText(department[0]);
                binding.phone1.setText(phone[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateDoctorBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return binding.getRoot();
    }
}