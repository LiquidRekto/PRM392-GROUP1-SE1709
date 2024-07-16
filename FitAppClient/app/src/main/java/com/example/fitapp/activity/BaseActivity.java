package com.example.fitapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fitapp.R;
import com.example.fitapp.databinding.BaseLayoutBinding;
import com.example.fitapp.fragment.HomeFragment;
import com.example.fitapp.fragment.SettingsFragment;
import com.example.fitapp.fragment.UserProfileFragment;

public class BaseActivity extends AppCompatActivity {
    Toolbar topNavBar, botNavBar;

    ImageButton backButton;
    TextView fragTitle;
    BaseLayoutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = BaseLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.baseRoot);

        replaceFragment(new HomeFragment());
        fragTitle = (TextView) findViewById(R.id.app_bar_title);
        backButton = (ImageButton) findViewById(R.id.btn_back);
        backButton.setVisibility(View.INVISIBLE);
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.profile) {
                replaceFragment(new UserProfileFragment());
            } else if (itemId == R.id.settings) {
                replaceFragment(new SettingsFragment());
            }
            return true;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
                super.onFragmentCreated(fm, f, savedInstanceState);

            }

            @Override
            public void onFragmentStarted(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentStarted(fm, f);

                if (fragmentManager.getBackStackEntryCount() > 0) {
                    backButton.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFragmentDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentDestroyed(fm, f);
            }

            @Override
            public void onFragmentDetached(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentDetached(fm, f);
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    backButton.setVisibility(View.VISIBLE);
                }
            }
        }, true);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.app_frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void changeTitle(String title) {
        fragTitle.setText(title);
    }

}
