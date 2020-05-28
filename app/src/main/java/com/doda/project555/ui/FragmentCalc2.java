package com.doda.project555.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.doda.project555.R;

import static com.doda.project555.MainActivity.APP_PREFERENCES;

public class FragmentCalc2 extends Fragment {

    private LinearLayout linear = HomeFragment.root.findViewById(R.id.linear);
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final SharedPreferences mySettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        View root = inflater.inflate(R.layout.fragment_calc_2, container, false);
        Button backButton = root.findViewById(R.id.back);
        backButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_calc_2_to_nav_calc_1));

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        backButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_calc_2_to_nav_calc_1));


        return root;
    }
}
