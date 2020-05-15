package com.doda.project555.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.doda.project555.R;

import static com.doda.project555.MainActivity.APP_PREFERENCES;

public class CalcFragment extends Fragment {

    @SuppressLint("CommitPrefEdits")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final SharedPreferences mySettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        final Fragment fragment_calc_1 = new Fragment(R.layout.fragment_calc_1);
        final Fragment fragment_calc_2 = new Fragment(R.layout.fragment_calc_2);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        View root = inflater.inflate(R.layout.calc_main, container, false);
        View fc1 = inflater.inflate(R.layout.fragment_calc_1, container, false);
        View fc2 = inflater.inflate(R.layout.fragment_calc_2, container, false);
        fragmentManager.beginTransaction()
                .add(R.id.layout_calc, fragment_calc_1)
                .commit();

        CheckBox cb_russian = fc1.findViewById(R.id.russian);
        CheckBox cb_math_base = fc1.findViewById(R.id.math_base);
        CheckBox cb_math_pro = fc1.findViewById(R.id.math_pro);
        CheckBox cb_physics = fc1.findViewById(R.id.physics);
        CheckBox cb_chemistry = fc1.findViewById(R.id.chemistry);
        CheckBox cb_computer_science = fc1.findViewById(R.id.computer_science);
        CheckBox cb_biology = fc1.findViewById(R.id.biology);
        CheckBox cb_geography = fc1.findViewById(R.id.geography);
        CheckBox cb_history = fc1.findViewById(R.id.history);
        CheckBox cb_social_science = fc1.findViewById(R.id.social_science);
        CheckBox cb_literature = fc1.findViewById(R.id.literature);
        CheckBox cb_english = fc1.findViewById(R.id.english);
        CheckBox cb_german = fc1.findViewById(R.id.german);
        CheckBox cb_french = fc1.findViewById(R.id.french);
        CheckBox cb_spanish = fc1.findViewById(R.id.spanish);
        CheckBox cb_chinese = fc1.findViewById(R.id.chinese);

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
