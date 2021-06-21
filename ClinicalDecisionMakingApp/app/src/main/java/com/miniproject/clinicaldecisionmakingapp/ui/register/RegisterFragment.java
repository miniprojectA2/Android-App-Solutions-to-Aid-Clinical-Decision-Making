package com.miniproject.clinicaldecisionmakingapp.ui.register;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.miniproject.clinicaldecisionmakingapp.R;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentRegisterBinding;
import com.miniproject.clinicaldecisionmakingapp.model.Doctor;
import com.miniproject.clinicaldecisionmakingapp.model.Patient;
import com.miniproject.clinicaldecisionmakingapp.ui.dashboard.DoctorDashboard;
import com.miniproject.clinicaldecisionmakingapp.ui.dashboard.PatientDashboard;
import com.miniproject.clinicaldecisionmakingapp.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class RegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    FragmentRegisterBinding binding;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;


    public RegisterFragment () {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner = (Spinner) binding.patientSex;
        firebaseAuth = FirebaseAuth.getInstance();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.spinner_sex, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        binding.Patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.type.setVisibility(View.GONE);
                binding.patientReg.setVisibility(View.VISIBLE);
            }
        });

        binding.Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.type.setVisibility(View.GONE);
                binding.doctorType.setVisibility(View.VISIBLE);
            }
        });

        binding.back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.type.setVisibility(View.VISIBLE);
                binding.patientReg.setVisibility(View.GONE);

            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.type.setVisibility(View.VISIBLE);
                binding.doctorType.setVisibility(View.GONE);
            }
        });

        binding.registerPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar1.setVisibility(View.VISIBLE);
                final String patientName = binding.patientName.getText().toString();
                final String patientEmail = binding.patientEmail.getText().toString();
                final String phone = binding.phone.getText().toString();
                final String age = binding.patientAge.getText().toString();
                final String weight = binding.patientWeight.getText().toString();
                final String sex = binding.patientSex.getSelectedItem().toString();
                final String password = binding.password.getText().toString();



                if(patientName.isEmpty()) {
                    binding.patientName.setError("Please enter your name");
                    binding.patientName.requestFocus();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(patientEmail.isEmpty()) {
                    binding.patientEmail.setError("Please enter your email");
                    binding.patientEmail.requestFocus();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(phone.isEmpty()) {
                    binding.phone.setError("Please enter contact");
                    binding.phone.requestFocus();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(age.isEmpty()) {
                    binding.patientAge.setError("Please enter age");
                    binding.patientAge.requestFocus();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(password.isEmpty()) {
                    binding.password.setError("Please enter password");
                    binding.password.requestFocus();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(weight.isEmpty()) {
                    binding.patientWeight.setError("Please enter your weight");
                    binding.patientWeight.requestFocus();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(sex.equals("--SEX--")) {
                    binding.patientWeight.setError("Please select your sex");
                    binding.patientSex.requestFocus();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(patientName.isEmpty() && patientEmail.isEmpty() && phone.isEmpty() && age.isEmpty() && password.isEmpty() && weight.isEmpty()) {
                    Toast.makeText(getContext(), "Fields are Empty!", Toast.LENGTH_SHORT).show();
                    binding.progressBar1.setVisibility(View.GONE);
                } else if(!patientName.isEmpty() && !patientEmail.isEmpty() && !phone.isEmpty() && !age.isEmpty() && !password.isEmpty() && !weight.isEmpty()) {
                    firebaseAuth.createUserWithEmailAndPassword(patientEmail, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(getContext(), "User already Exits", Toast.LENGTH_SHORT).show();
                            } else {
                                reference = FirebaseDatabase.getInstance().getReference("Patient");
                                String key = reference.push().getKey();

                                Patient patient = new Patient();
                                patient.setPatientName(patientName);
                                patient.setPatientEmail(patientEmail);
                                patient.setPatientPhone(phone);
                                patient.setPatientAge(age);
                                patient.setPatientPassword(password);
                                patient.setPatientSex(sex);
                                patient.setPatientWeight(weight);
                                reference.child(key).setValue(patient);

                                Toast.makeText(getContext(), "Registered", Toast.LENGTH_SHORT).show();
                                Fragment fragment = new PatientDashboard();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                        }
                    });

                }
            }
        });

        binding.registerDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar2.setVisibility(View.VISIBLE);
                final String doctorName = binding.doctorName.getText().toString();
                final String doctorEmail = binding.doctorEmail.getText().toString();
                final String doctorPhone = binding.phone1.getText().toString();
                final String doctorAge = binding.doctorAge.getText().toString();
                final String department = binding.doctorDepartment.getText().toString();
                final String doctorSex = binding.doctorSex.getSelectedItem().toString();
                final String doctorPassword = binding.password1.getText().toString();



                if(doctorName.isEmpty()) {
                    binding.doctorName.setError("Please enter your name");
                    binding.doctorName.requestFocus();
                    binding.progressBar2.setVisibility(View.GONE);
                } else if(doctorEmail.isEmpty()) {
                    binding.doctorEmail.setError("Please enter your email");
                    binding.doctorEmail.requestFocus();
                    binding.progressBar2.setVisibility(View.GONE);
                } else if(doctorPhone.isEmpty()) {
                    binding.phone1.setError("Please enter contact");
                    binding.phone1.requestFocus();
                    binding.progressBar2.setVisibility(View.GONE);
                } else if(doctorAge.isEmpty()) {
                    binding.doctorAge.setError("Please enter age");
                    binding.doctorAge.requestFocus();
                    binding.progressBar2.setVisibility(View.GONE);
                } else if(doctorPassword.isEmpty()) {
                    binding.password1.setError("Please enter password");
                    binding.password1.requestFocus();
                    binding.progressBar2.setVisibility(View.GONE);
                } else if(department.isEmpty()) {
                    binding.doctorDepartment.setError("Please enter your department");
                    binding.doctorDepartment.requestFocus();
                    binding.progressBar2.setVisibility(View.GONE);
                } else if(doctorSex.equals("--SEX--")) {
                    binding.patientSex.requestFocus();
                    binding.progressBar2.setVisibility(View.GONE);
                } else if(doctorName.isEmpty() && doctorEmail.isEmpty() && doctorAge.isEmpty() && doctorPassword.isEmpty() && department.isEmpty() && doctorPhone.isEmpty()) {
                    Toast.makeText(getContext(), "Fields are Empty!", Toast.LENGTH_SHORT).show();
                    binding.progressBar2.setVisibility(View.GONE);
                } else if(!doctorName.isEmpty() && !doctorEmail.isEmpty() && !doctorAge.isEmpty() && !doctorPassword.isEmpty() && !department.isEmpty() && !doctorPhone.isEmpty()) {
                    firebaseAuth.createUserWithEmailAndPassword(doctorEmail, doctorPassword).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                binding.progressBar2.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "User already Exits", Toast.LENGTH_SHORT).show();
                            } else {
                                reference = FirebaseDatabase.getInstance().getReference("Doctor");
                                String key = reference.push().getKey();

                                Doctor doctor = new Doctor();
                                doctor.setDoctorName(doctorName);
                                doctor.setDoctorEmail(doctorEmail);
                                doctor.setDepartment(department);
                                doctor.setDoctorAge(doctorAge);
                                doctor.setDoctorSex(doctorSex);
                                doctor.setDoctorPhone(doctorPhone);
                                doctor.setPassword(doctorPassword);

                                reference.child(key).setValue(doctor);

                                Toast.makeText(getContext(), "Registered", Toast.LENGTH_SHORT).show();
                                Fragment fragment = new DoctorDashboard();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                        }
                    });

                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}