package com.doda.project555.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.doda.project555.DatabaseHelper;
import com.doda.project555.R;

public class PayFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pay, container, false);
                DatabaseHelper databaseHelper;
        SQLiteDatabase db;
        Cursor userCursor;
        SimpleCursorAdapter userAdapter;

        databaseHelper = new DatabaseHelper(getContext());
        // создаем базу данных
        databaseHelper.create_db(getContext());
        // открываем подключение
        db = databaseHelper.open(getContext());
        //получаем данные из бд в виде курсора
        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[] {DatabaseHelper.Num1, DatabaseHelper.Num2, DatabaseHelper.Num3,
                DatabaseHelper.name, DatabaseHelper.certificate, DatabaseHelper.typeOfReceipt,
                DatabaseHelper.math, DatabaseHelper.russian, DatabaseHelper.phys,
                DatabaseHelper.essay, DatabaseHelper.IA, DatabaseHelper.sum};
        // создаем адаптер, передаем в него курсор
        userAdapter = new SimpleCursorAdapter(getContext(), R.layout.grid,
                userCursor, headers, new int[]{R.id.text1, R.id.text2, R.id.text3, R.id.text4,
                R.id.text5, R.id.text6, R.id.text7, R.id.text8, R.id.text9, R.id.text10,
                R.id.text11, R.id.text12}, 0);
        GridView userGrid = root.findViewById(R.id.gridView);
        userGrid.setAdapter(userAdapter);
        return root;
    }

}
