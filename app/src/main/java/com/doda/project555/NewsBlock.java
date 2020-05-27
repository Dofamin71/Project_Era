package com.doda.project555;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.doda.project555.ui.HomeFragment;

public class NewsBlock extends AppCompatActivity {
    private String title;
    private String description;
    private String link;
    private String pubDate;
    private String[] subStr;
    private String str;

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
        link = subStr[0]+".";
        pubDate = "("+subStr[1]+")";
    }

    public void createNewsBlock (String result, int num, FrameLayout.LayoutParams params, final Context context){
        FrameLayout newsBlock = new FrameLayout(context);
        main(result, num);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView titleView = createParagraph(title, context);
        titleView.setTextColor(Color.parseColor("#ffffff"));
        titleView.setTextSize(21);
        layout.addView(titleView);

        TextView descriptionView = createParagraph(description, context);
        descriptionView.setTextColor(Color.parseColor("#e6e6e6"));
        descriptionView.setPadding(0,20,0,20);
        descriptionView.setTextSize(17);
        layout.addView(descriptionView);

        TextView pubDateView = createParagraph(pubDate, context);
        pubDateView.setTextColor(Color.parseColor("#ffffff"));
        pubDateView.setTextSize(13);
        pubDateView.setGravity(Gravity.CENTER);
        layout.addView(pubDateView);

        Button button = new Button(context);
        OnClickListener gotoFrag = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Не тыкай, работает", Toast.LENGTH_LONG).show();
            }
        };
        button.setOnClickListener(gotoFrag);
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
}
