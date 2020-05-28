package com.doda.project555.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.doda.project555.R;

public class NewsFragment extends Fragment {

    public static View root;
    static LinearLayout news = root.findViewById(R.id.news_layout);

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_news, container, false);

        return  root;
    }

    public static void createNews (String title, String pubData, String text, Context context) {
        TextView titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTextColor(Color.parseColor("#ffffff"));
        titleView.setTextSize(21);
        titleView.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded));
        NewsFragment.news.addView(titleView);
    }
}
