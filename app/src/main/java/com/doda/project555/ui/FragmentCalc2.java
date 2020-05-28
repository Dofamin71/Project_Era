package com.doda.project555.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import com.doda.project555.DatabaseHelper;
import com.doda.project555.R;

import static com.doda.project555.MainActivity.APP_PREFERENCES;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class FragmentCalc2 extends Fragment {

    private LinearLayout linear = HomeFragment.root.findViewById(R.id.linear);
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final SharedPreferences mySettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        View root = inflater.inflate(R.layout.fragment_calc_2, container, false);
        Button backButton = root.findViewById(R.id.back);
        backButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_calc_2_to_nav_calc_1));

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        Button backbtn = root.findViewById(R.id.back);
        backbtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_calc_2_to_nav_calc_1));
        DatabaseHelper databaseHelper;
        SQLiteDatabase db;
        Cursor userCursor;
        SimpleCursorAdapter userAdapter;

        databaseHelper = new DatabaseHelper(getContext());
        // создаем базу данных
        databaseHelper.create_db();
        // открываем подключение
        db = databaseHelper.open();
        //получаем данные из бд в виде курсора
        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[] {DatabaseHelper.Num1, DatabaseHelper.Num2, DatabaseHelper.Num3,
                DatabaseHelper.name, DatabaseHelper.certificate, DatabaseHelper.typeOfReceipt,
                DatabaseHelper.math, DatabaseHelper.russian, DatabaseHelper.phys,
                DatabaseHelper.essay, DatabaseHelper.IA, DatabaseHelper.num};
        // создаем адаптер, передаем в него курсор
        /*userAdapter = new SimpleCursorAdapter(getContext(), android.R.layout.two_line_list_item,
                userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        userList.setAdapter(userAdapter);*/

        return root;
    }
}
