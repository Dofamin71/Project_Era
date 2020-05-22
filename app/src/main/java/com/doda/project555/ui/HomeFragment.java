package com.doda.project555.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class HomeFragment extends Fragment {

    private static String rssResult = "";
    private static boolean item = false;
    private static TextView rss;
    private LinearLayout mainLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mainLayout = root.findViewById(R.id.mainLayout);

        //rss = root.findViewById(R.id.rss);
        RSSTask rssT = new RSSTask();
        try {
            rssT.execute("https://edu.ru/news/egegia/feed.rss").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
    private static class RSSTask extends AsyncTask<String, Void, String> {

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
            result = result.replace("title: ", "\n/title ")
                    .replace("description: ", "\n/desc ")
                    .replace("link: ", "\n/link ")
                    .replace("pubDate: ", "\n/pubDate ");
            rssResult = result;
        }
    }
}