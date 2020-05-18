package com.doda.project555.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.doda.project555.R;

import static com.doda.project555.MainActivity.APP_PREFERENCES;

public class FragmentCalc1 extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final SharedPreferences mySettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        View root = inflater.inflate(R.layout.fragment_calc_1, container, false);
        Button furtherbtn = root.findViewById(R.id.further);
        furtherbtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_calc_1_to_nav_calc_2));

        CheckBox cb_russian = root.findViewById(R.id.russian);
        CheckBox cb_math_base = root.findViewById(R.id.math_base);
        CheckBox cb_math_pro = root.findViewById(R.id.math_pro);
        CheckBox cb_physics = root.findViewById(R.id.physics);
        CheckBox cb_chemistry = root.findViewById(R.id.chemistry);
        CheckBox cb_computer_science = root.findViewById(R.id.computer_science);
        CheckBox cb_biology = root.findViewById(R.id.biology);
        CheckBox cb_geography = root.findViewById(R.id.geography);
        CheckBox cb_history = root.findViewById(R.id.history);
        CheckBox cb_social_science = root.findViewById(R.id.social_science);
        CheckBox cb_literature = root.findViewById(R.id.literature);
        CheckBox cb_english = root.findViewById(R.id.english);
        CheckBox cb_german = root.findViewById(R.id.german);
        CheckBox cb_french = root.findViewById(R.id.french);
        CheckBox cb_spanish = root.findViewById(R.id.spanish);
        CheckBox cb_chinese = root.findViewById(R.id.chinese);

        mySettings.edit().putBoolean("russian", cb_russian.isChecked());
        mySettings.edit().putBoolean("math_base", cb_math_base.isChecked());
        mySettings.edit().putBoolean("math_pro", cb_math_pro.isChecked());
        mySettings.edit().putBoolean("physics", cb_physics.isChecked());
        mySettings.edit().putBoolean("chemistry", cb_chemistry.isChecked());
        mySettings.edit().putBoolean("computer_science", cb_computer_science.isChecked());
        mySettings.edit().putBoolean("biology", cb_biology.isChecked());
        mySettings.edit().putBoolean("geography", cb_geography.isChecked());
        mySettings.edit().putBoolean("history", cb_history.isChecked());
        mySettings.edit().putBoolean("social_science", cb_social_science.isChecked());
        mySettings.edit().putBoolean("literature", cb_literature.isChecked());
        mySettings.edit().putBoolean("english", cb_english.isChecked());
        mySettings.edit().putBoolean("german", cb_german.isChecked());
        mySettings.edit().putBoolean("french", cb_french.isChecked());
        mySettings.edit().putBoolean("spanish", cb_spanish.isChecked());
        mySettings.edit().putBoolean("chinese", cb_chinese.isChecked());

        return root;
    }
}
