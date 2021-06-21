package com.miniproject.clinicaldecisionmakingapp.ui.doctors;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.miniproject.clinicaldecisionmakingapp.R;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentCrudPatientBinding;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentUpdatePatientBinding;
import com.miniproject.clinicaldecisionmakingapp.model.DoctorPatients;
import com.miniproject.clinicaldecisionmakingapp.ui.dashboard.PatientsListsFragment;
import com.miniproject.clinicaldecisionmakingapp.ui.dashboard.UpdateDoctorFragment;

import java.lang.reflect.Array;


public class CrudPatientFragment extends Fragment {

    FragmentCrudPatientBinding binding;
    private FirebaseFirestore firestore;

    ArrayAdapter<CharSequence> adapter;

    public CrudPatientFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCrudPatientBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DoctorPatients doctorPatients = (DoctorPatients) getArguments().getSerializable("patients");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();

        firestore = FirebaseFirestore.getInstance();

        adapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        binding.patientSex.setAdapter(adapter);

        binding.patientName.setText(doctorPatients.getPatientName());
        binding.patientAge.setText(doctorPatients.getPatientAge());
        binding.patientEmail.setText(doctorPatients.getPatientEmail());

        int pos = adapter.getPosition(doctorPatients.getPatientSex());
        binding.patientSex.setSelection(pos);

        binding.patientWeight.setText(doctorPatients.getPatientWeight());
        binding.phone.setText(doctorPatients.getPatientPhone());

        binding.updatePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String patientName = binding.patientName.getText().toString();
                final String patientEmail = binding.patientEmail.getText().toString();
                final String phone = binding.phone.getText().toString();
                final String age = binding.patientAge.getText().toString();
                final String weight = binding.patientWeight.getText().toString();
                final String sex = binding.patientSex.getSelectedItem().toString();

                DoctorPatients doctorPatients1 = new DoctorPatients();
                doctorPatients1.setDoctorName(email);
                doctorPatients1.setPatientName(patientName);
                doctorPatients1.setPatientEmail(patientEmail);
                doctorPatients1.setPatientPhone(phone);
                doctorPatients1.setPatientAge(age);
                doctorPatients1.setPatientSex(sex);
                doctorPatients1.setPatientWeight(weight);

                firestore.collection(email)
                        .document(doctorPatients.getId())
                        .set(doctorPatients1)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(), "Patient has been updated..", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Fail to update the data..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        binding.deletePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firestore.collection(email)
                        .document(doctorPatients.getId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Patient has been deleted from Databse.", Toast.LENGTH_SHORT).show();
                                    Fragment fragment = new PatientsListsFragment();
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();
                                } else {
                                    Toast.makeText(getContext(), "Failed to delete", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}