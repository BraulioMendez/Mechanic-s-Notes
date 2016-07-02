package com.brauliomendez.mechanicsnotes.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.brauliomendez.mechanicsnotes.R;
import com.brauliomendez.mechanicsnotes.ui.fragment.ServiceFragment;

/**
 * Created by Braulio on 28/06/2016.
 */
public class ServiceActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment();
    }

    private void setFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_fragment);
        if (fragment == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.container_fragment, new ServiceFragment())
                    .commit();
        }
    }
}
