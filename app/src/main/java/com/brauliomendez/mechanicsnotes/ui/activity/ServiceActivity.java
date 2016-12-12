package com.brauliomendez.mechanicsnotes.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.brauliomendez.mechanicsnotes.R;
import com.brauliomendez.mechanicsnotes.ui.fragment.ServiceFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Braulio Méndez Jiménez
 * @since 28/06/16
 */
public class ServiceActivity extends AppCompatActivity {

    @Bind(R.id.service_toolbar) Toolbar toolbar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
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
