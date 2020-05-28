package com.doda.project555.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.doda.project555.R;

import static com.doda.project555.MainActivity.APP_PREFERENCES;

public class FragmentCalc2 extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final SharedPreferences mySettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        View root = inflater.inflate(R.layout.fragment_calc_2, container, false);
        LinearLayout linear = root.findViewById(R.id.linear_calc);
        Button backButton = root.findViewById(R.id.back);
        backButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_calc_2_to_nav_calc_1));

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        backButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_calc_2_to_nav_calc_1));

        if (mySettings.getBoolean("russian", false))createEdit("RUS", "Русский язык", linear, mySettings);
        if (mySettings.getBoolean("math_base", false))createEdit("MAB", "Математика (база)", linear, mySettings);
        if (mySettings.getBoolean("math_pro", false))createEdit("MAP", "Математика (профиль)", linear, mySettings);
        if (mySettings.getBoolean("physics", false))createEdit("PHY", "Физика", linear, mySettings);
        if (mySettings.getBoolean("chemistry", false))createEdit("CHE", "Химия", linear, mySettings);
        if (mySettings.getBoolean("computer_science", false))createEdit("COM", "Информатика и ИКТ", linear, mySettings);
        if (mySettings.getBoolean("biology", false))createEdit("BIO", "Биология", linear, mySettings);
        if (mySettings.getBoolean("geography", false))createEdit("GEO", "География", linear, mySettings);
        if (mySettings.getBoolean("history", false))createEdit("HIS", "История", linear, mySettings);
        if (mySettings.getBoolean("social_science", false))createEdit("SOC", "Обществознание", linear, mySettings);
        if (mySettings.getBoolean("literature", false))createEdit("LIT", "Литература", linear, mySettings);
        if (mySettings.getBoolean("english", false))createEdit("ENG", "Английский язык", linear, mySettings);
        if (mySettings.getBoolean("german", false))createEdit("GER", "Немецкий язык", linear, mySettings);
        if (mySettings.getBoolean("french", false))createEdit("FRE", "Французский язык", linear, mySettings);
        if (mySettings.getBoolean("spanish", false))createEdit("SPA", "Испанский язык", linear, mySettings);
        if (mySettings.getBoolean("chinese", false))createEdit("CHI", "Китайский язык", linear, mySettings);

        return root;
    }

    private void createEdit (final String key, String hint, LinearLayout linear, final SharedPreferences mySettings) {
        final EditText edit = new EditText(getContext());
        edit.setHint(hint);
        edit.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit.setText(mySettings.getString(key, null));
        linear.addView(edit);
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                mySettings.edit().putString(key, String.valueOf(edit.getText())).apply();
            }
        });
    }
}
