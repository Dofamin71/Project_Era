package com.doda.project555;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    public static final String APP_PREFERENCES = "mySettings";
    private AppBarConfiguration mAppBarConfiguration;
    Boolean swi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences mySettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6675273839004883~5892550117");

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        TextView header = navigationView.getHeaderView(0).findViewById(R.id.nav_user_name);
        header.setText(mySettings.getString("FIO", "ФИО"));
        TextView header1 = navigationView.getHeaderView(0).findViewById(R.id.nav_user_city);
        header1.setText(mySettings.getString("CITY", "Город"));
        TextView header2 = navigationView.getHeaderView(0).findViewById(R.id.nav_user_mail);
        header2.setText(mySettings.getString("MAIL", "Почта"));
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        swi = mySettings.getBoolean("SWITCH", true);

        MenuItem menuItem = navigationView.getMenu().findItem(R.id.nav_notifications);
        final Switch drawerSwitch = menuItem.getActionView().findViewById(R.id.notifications);
        drawerSwitch.setChecked(swi);
        drawerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                swi = isChecked;
                drawerSwitch.setChecked(swi);
                SharedPreferences.Editor ed = mySettings.edit();
                ed.putBoolean("SWITCH", swi);
                ed.apply();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout layout = findViewById(R.id.drawer_layout);
        if (layout.isDrawerOpen(GravityCompat.START)) {
            layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN) {
            if(resultCode == RESULT_OK) {
                String name = FirebaseAuth.getInstance()
                        .getCurrentUser()
                        .getDisplayName();
                if(name == null)name = "";
                Toast.makeText(this,
                        "Successfully signed in. Welcome " + name,
                        Toast.LENGTH_LONG)
                        .show();
            } else {
                Toast.makeText(this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Destroy", "onDestroy");
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("SWITCH", swi).apply();
    }
}