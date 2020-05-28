package com.doda.project555;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.doda.project555.ui.HomeFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class NewsBlock extends AppCompatActivity {
    public String title;
    private String description;
    private String pubDate;
    private String[] subStr;
    private String str;
    private String link;
    public String fullText;
    private boolean flag = true;

    private LinearLayout linear = HomeFragment.root.findViewById(R.id.linear);

    private void main(String result, int num) {
        subStr = result.split("title: ");
        str = subStr[num].replace("\t", "");
        subStr = str.split("description: ");
        title = subStr[0]+".";
        str = subStr[1];
        subStr = str.split("link: ");
        description = subStr[0]+".";
        str = subStr[1];
        subStr = str.split("pubDate: ");
        link = subStr[0];
        pubDate = "(" + subStr[1] + ")";
        new Parser().execute();
    }

    public void createNewsBlock (String result, int num, FrameLayout.LayoutParams params, final Context context){
        FrameLayout newsBlock = new FrameLayout(context);
        main(result, num);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView titleView = createParagraph(title, context);
        titleView.setTextColor(Color.parseColor("#ffffff"));
        titleView.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_up));
        titleView.setTextSize(21);
        layout.addView(titleView);

        final FrameLayout frame = new FrameLayout(context);
        final TextView descriptionView = createParagraph(description, context);
        descriptionView.setTextColor(Color.parseColor("#e6e6e6"));
        descriptionView.setPadding(0,20,0,20);
        descriptionView.setTextSize(17);
        frame.addView(descriptionView);
        layout.addView(frame);

        TextView pubDateView = createParagraph(pubDate, context);
        pubDateView.setTextColor(Color.parseColor("#ffffff"));
        pubDateView.setGravity(Gravity.CENTER);
        pubDateView.setTextSize(13);
        layout.addView(pubDateView);

        Button button = new Button(context);
        OnClickListener click = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    frame.removeAllViews();
                    TextView text = new TextView(context);
                    text.setPadding(0,20,0,20);
                    text.setTextColor(Color.parseColor("#e6e6e6"));
                    text.setText(fullText);
                    text.setTextSize(17);
                    frame.addView(text);
                    flag = false;
                } else {
                    frame.removeAllViews();
                    frame.addView(descriptionView);
                    flag = true;
                }
            }
        };
        button.setOnClickListener(click);
        button.setBackground(null);

        newsBlock.addView(layout);
        newsBlock.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded));
        newsBlock.setPadding(30,30,30,30);
        newsBlock.setLayoutParams(params);
        newsBlock.addView(button);
        linear.addView(newsBlock);
    }

    private TextView createParagraph (String text, Context context) {
        TextView paragraph = new TextView(context);
        paragraph.setText(text);
        paragraph.setTextColor(Color.parseColor("#ffffff"));
        paragraph.setTextSize(15);
        return paragraph;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    class Parser extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            StringBuilder text = new StringBuilder();
            try {
                Document doc = Jsoup.connect(link).get();
                for (Element paragraph : doc.getElementsByTag("p")) {
                    if(!paragraph.toString().contains("<p class")) {
                        text.append(paragraph);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return text.toString().replace("<p>", "").replace("</p>", "\n\n");
        }

        @Override
        protected void onPostExecute(String result) {
            fullText = result;
            Log.d("text", fullText);
        }
    }
}