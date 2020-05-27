package com.doda.project555;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.doda.project555.ui.HomeFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;



public class NewsBlock extends HomeFragment {
    private String title;
    private String description;
    private String pubDate;
    private String link;
    private String fullText;

    private LinearLayout linear = HomeFragment.root.findViewById(R.id.linear);

    private void main(String result, int num) {
        String[] subStr = result.split("title: ");
        String str = subStr[num].replace("\t", "");
        subStr = str.split("description: ");
        title = subStr[0];
        str = subStr[1];
        subStr = str.split("link: ");
        description = subStr[0];
        str = subStr[1];
        subStr = str.split("pubDate: ");
        link = subStr[0];
        pubDate = subStr[1];
        new Parser().execute();
    }

    public void createNewsBlock (String result, int num, LinearLayout.LayoutParams params, final Context context){
        LinearLayout newsBlock = new LinearLayout(context);
        main(result, num);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView titleView = createParagraph(title, context);
        titleView.setTextColor(Color.parseColor("#ffffff"));
        titleView.setTextSize(22);
        layout.addView(titleView);

        TextView descriptionView = createParagraph(description, context);
        descriptionView.setTextColor(Color.parseColor("#dedede"));
        descriptionView.setPadding(0,20,0,20);
        descriptionView.setTextSize(18);
        layout.addView(descriptionView);

        TextView pubDateView = createParagraph(pubDate, context);
        pubDateView.setTextColor(Color.parseColor("#ffffff"));
        pubDateView.setTextSize(13);
        LinearLayout buttonLayout = new LinearLayout(context);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        Button button = new Button(context);
        OnClickListener gotoFrag = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Не тыкай, работает", Toast.LENGTH_LONG).show();
            }
        };
        button.setOnClickListener(gotoFrag);
        button.setText("Далее");
        button.setTextColor(Color.parseColor("#ffffff"));
        buttonLayout.addView(pubDateView);
        buttonLayout.addView(button);
        layout.addView(buttonLayout);

        newsBlock.addView(layout);
        newsBlock.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded));
        newsBlock.setPadding(30,30,30,30);
        newsBlock.setLayoutParams(params);
        linear.addView(newsBlock);
    }

    private TextView createParagraph (String text, Context context) {
        TextView paragraph = new TextView(context);
        paragraph.setText(text);
        paragraph.setTextColor(Color.parseColor("#ffffff"));
        paragraph.setTextSize(15);
        return paragraph;
    }
    class Parser extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String text ="Ошибка при получении текста новости";
            try {
                text = Jsoup.connect(link).get().text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return text;
        }

        @Override
        protected void onPostExecute(String result) {
             fullText = result;
        }
    }
}