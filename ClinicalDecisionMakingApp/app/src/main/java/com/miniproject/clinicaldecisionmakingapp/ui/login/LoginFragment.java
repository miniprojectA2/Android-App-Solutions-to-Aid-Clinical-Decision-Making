package com.miniproject.clinicaldecisionmakingapp.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.miniproject.clinicaldecisionmakingapp.R;
import com.miniproject.clinicaldecisionmakingapp.databinding.FragmentLoginBinding;
import com.miniproject.clinicaldecisionmakingapp.ui.dashboard.PatientDashboard;
import com.miniproject.clinicaldecisionmakingapp.ui.register.RegisterFragment;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();


        binding.loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.patientemail.getText().toString().trim();
                String password = binding.patientpassword.getText().toString().trim();

                if(email.isEmpty()) {
                    binding.patientemail.setError("Enter your Username");
                    binding.patientemail.requestFocus();
                } else if(password.isEmpty()) {
                    binding.patientpassword.setError("Enter your Password");
                    binding.patientpassword.requestFocus();
                } else if(email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(getActivity(), "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if(!email.isEmpty() && !password.isEmpty()) {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getContext(), "Login Failure", Toast.LENGTH_SHORT).show();
                                binding.progressBar.setVisibility(View.GONE);
                            } else {
                                Toast.makeText(getContext(), "You are Logged in", Toast.LENGTH_SHORT).show();
                                binding.progressBar.setVisibility(View.GONE);
                                Fragment fragment = new PatientDashboard();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                        }
                    });
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ForgotPasswordFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        binding.textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new RegisterFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}