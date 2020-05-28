package com.doda.project555.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.doda.project555.NewsBlock;
import com.doda.project555.R;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static com.doda.project555.MainActivity.APP_PREFERENCES;

public class HomeFragment extends Fragment {

    private static String rssResult = "";
    private static boolean item = false;
    public static View root;
    private SharedPreferences mySettings;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        mySettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if(Calendar.getInstance().getTimeInMillis() - mySettings.getLong("LastRSSUpdate", 0) < 18000000) {
            RSSTask rssT = new RSSTask();
            try {
                rssT.execute("https://edu.ru/news/egegia/feed.rss").get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            String result = mySettings.getString("RSS", "");
            if(!result.equals("")) {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 25, 0, 25);
                for (int i = 1; i <= 20; ++i) {
                    NewsBlock newsBlock = new NewsBlock();
                    newsBlock.createNewsBlock(result, i, params, getContext());
                }
            }
        }
        final SwipeRefreshLayout mSwipeRefreshLayout = root.findViewById(R.id.fragment_home);
        SwipeRefreshLayout.OnRefreshListener listener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        RSSTask rssT = new RSSTask();
                        try {
                            rssT.execute("https://edu.ru/news/egegia/feed.rss").get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, 1500);
            }
        };
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4499ff"));
        mSwipeRefreshLayout.setOnRefreshListener(listener);
        return root;
    }

    private static String readRSS(String urlAddress) {
        try{
            URL rssUrl = new URL(urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser;
            saxParser = factory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            RSSHandler rssHandler = new RSSHandler();
            xmlReader.setContentHandler(rssHandler);
            InputSource inputSource = new InputSource(rssUrl.openStream());
            xmlReader.parse(inputSource);
            in.close();
            return rssResult;
        } catch (MalformedURLException ue) {
            Log.d("1234", "Малформед юрл");
        } catch (IOException ioe) {
            Log.d("1234", "Второй");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return "Program Failed";
    }

    private static class RSSHandler extends DefaultHandler {

        public void startElement(String uri, String localName, String qName,
                                 Attributes attrs) {
            if (localName.equals("item"))
                item = true;

            if (!localName.equals("item") && item)
                rssResult = rssResult + localName + ": ";

        }

        public void endElement(String namespaceURI, String localName,
                               String qName) {
        }

        public void characters(char[] ch, int start, int length) {
            String cdata = new String(ch, start, length);
            if (item)
                rssResult = rssResult +(cdata.trim()).replaceAll("\\s+", " ")+"\t";
        }
    }

    private class RSSTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return readRSS(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 25, 0, 25);
            Context context = getContext();
            if(result.split("title: ").length != 1){
                SharedPreferences.Editor ed = mySettings.edit();
                ed.putString("RSS", result);
                ed.putLong("LastRSSUpdate", Calendar.getInstance().getTimeInMillis());
                ed.apply();
                for (int i = 1; i <= 20; ++i) {
                    NewsBlock newsBlock = new NewsBlock();
                    newsBlock.createNewsBlock(result, i, params, context);
                }
            }else {
                if(!mySettings.getString("RSS", "-1").equals("-1")){
                    result = mySettings.getString("RSS", "");
                    for (int i = 1; i <= 20; ++i) {
                        NewsBlock newsBlock = new NewsBlock();
                        newsBlock.createNewsBlock(result, i, params, context);
                    }
                }
            }
        }
    }
}