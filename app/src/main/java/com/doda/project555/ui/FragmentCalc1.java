package com.doda.project555.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
        Button furtherButton = root.findViewById(R.id.further);

        final CheckBox cb_russian = root.findViewById(R.id.russian);
        final CheckBox cb_math_base = root.findViewById(R.id.math_base);
        final CheckBox cb_math_pro = root.findViewById(R.id.math_pro);
        final CheckBox cb_physics = root.findViewById(R.id.physics);
        final CheckBox cb_chemistry = root.findViewById(R.id.chemistry);
        final CheckBox cb_computer_science = root.findViewById(R.id.computer_science);
        final CheckBox cb_biology = root.findViewById(R.id.biology);
        final CheckBox cb_geography = root.findViewById(R.id.geography);
        final CheckBox cb_history = root.findViewById(R.id.history);
        final CheckBox cb_social_science = root.findViewById(R.id.social_science);
        final CheckBox cb_literature = root.findViewById(R.id.literature);
        final CheckBox cb_english = root.findViewById(R.id.english);
        final CheckBox cb_german = root.findViewById(R.id.german);
        final CheckBox cb_french = root.findViewById(R.id.french);
        final CheckBox cb_spanish = root.findViewById(R.id.spanish);
        final CheckBox cb_chinese = root.findViewById(R.id.chinese);

        cb_russian.setChecked(mySettings.getBoolean("russian", true));
        cb_math_base.setChecked(mySettings.getBoolean("math_base", false));
        cb_math_pro.setChecked(mySettings.getBoolean("math_pro", false));
        cb_physics.setChecked(mySettings.getBoolean("physics", false));
        cb_chemistry.setChecked(mySettings.getBoolean("chemistry", false));
        cb_computer_science.setChecked(mySettings.getBoolean("computer_science", false));
        cb_biology.setChecked(mySettings.getBoolean("biology", false));
        cb_geography.setChecked(mySettings.getBoolean("geography", false));
        cb_history.setChecked(mySettings.getBoolean("history", false));
        cb_social_science.setChecked(mySettings.getBoolean("social_science", false));
        cb_literature.setChecked(mySettings.getBoolean("literature", false));
        cb_english.setChecked(mySettings.getBoolean("english", false));
        cb_german.setChecked(mySettings.getBoolean("german", false));
        cb_french.setChecked(mySettings.getBoolean("french", false));
        cb_spanish.setChecked(mySettings.getBoolean("spanish", false));
        cb_chinese.setChecked(mySettings.getBoolean("chinese", false));

        furtherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Editor ed = mySettings.edit();
                ed.putBoolean("russian", cb_russian.isChecked());
                ed.putBoolean("math_base", cb_math_base.isChecked());
                ed.putBoolean("math_pro", cb_math_pro.isChecked());
                ed.putBoolean("physics", cb_physics.isChecked());
                ed.putBoolean("chemistry", cb_chemistry.isChecked());
                ed.putBoolean("computer_science", cb_computer_science.isChecked());
                ed.putBoolean("biology", cb_biology.isChecked());
                ed.putBoolean("geography", cb_geography.isChecked());
                ed.putBoolean("history", cb_history.isChecked());
                ed.putBoolean("social_science", cb_social_science.isChecked());
                ed.putBoolean("literature", cb_literature.isChecked());
                ed.putBoolean("english", cb_english.isChecked());
                ed.putBoolean("german", cb_german.isChecked());
                ed.putBoolean("french", cb_french.isChecked());
                ed.putBoolean("spanish", cb_spanish.isChecked());
                ed.putBoolean("chinese", cb_chinese.isChecked());
                ed.apply();
                Navigation.findNavController(v).navigate(R.id.action_nav_calc_1_to_nav_calc_2);
            }
        });
        return root;
    }
}
