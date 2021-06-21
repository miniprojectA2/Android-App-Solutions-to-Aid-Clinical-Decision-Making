package com.miniproject.clinicaldecisionmakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.miniproject.clinicaldecisionmakingapp.ui.dashboard.AddPatientFragment;
import com.miniproject.clinicaldecisionmakingapp.ui.dashboard.DoctorDashboard;
import com.miniproject.clinicaldecisionmakingapp.ui.dashboard.PatientDashboard;
import com.miniproject.clinicaldecisionmakingapp.ui.dashboard.PatientsListsFragment;
import com.miniproject.clinicaldecisionmakingapp.ui.dashboard.UpdateDoctorFragment;
import com.miniproject.clinicaldecisionmakingapp.ui.dashboard.UpdatePatientFragment;
import com.miniproject.clinicaldecisionmakingapp.ui.home.HomeFragment;
import com.miniproject.clinicaldecisionmakingapp.ui.login.LoginFragment;
import com.miniproject.clinicaldecisionmakingapp.ui.register.RegisterFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private DrawerLayout drawer;
    private NavigationView navigationView;

    private static final String COMMON_TAG = "Orientation Change";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_register, R.id.nav_login)
//                .setOpenableLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
//        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                    }
                };
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new HomeFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_register:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new RegisterFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_login:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new LoginFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_update:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new UpdatePatientFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_dashboard:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new PatientDashboard()).addToBackStack(null).commit();
                break;
            case R.id.nav_ddashboard:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new DoctorDashboard()).addToBackStack(null).commit();
                break;
            case R.id.nav_dupdate:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new UpdateDoctorFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_addpatient:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new AddPatientFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_showpatients:
                getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, new PatientsListsFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_logout:
                logout();
                break;
            default:
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.i(COMMON_TAG, "landscape");
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.i(COMMON_TAG, "potrait");
        }
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}