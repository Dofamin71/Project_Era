package com.doda.project555.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.doda.project555.R;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class HomeFragment extends Fragment {

    private String rssResult = "";
    private boolean item = false;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TextView rss = root.findViewById(R.id.rss);
        try {
            URL rssUrl = new URL("http://www.ege.edu.ru/ru/news/News/rss/");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser;
            saxParser = factory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            RSSHandler rssHandler = new RSSHandler();
            xmlReader.setContentHandler(rssHandler);
            InputSource inputSource = new InputSource(rssUrl.openStream());
            xmlReader.parse(inputSource);
            rss.setText(rssResult);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    private class RSSHandler extends DefaultHandler {

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
}