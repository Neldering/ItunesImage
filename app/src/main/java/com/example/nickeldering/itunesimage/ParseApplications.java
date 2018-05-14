package com.example.nickeldering.itunesimage;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by timbuchalka on 12/07/2016.
 */

public class ParseApplications {
    private static final String TAG = "ParseApplications";
    private ArrayList<Photo> mPhotoList;

    public ParseApplications() {
        this.mPhotoList = new ArrayList<>();
    }

    public ArrayList<Photo> getmPhotoList() {
        return mPhotoList;
    }

    public boolean parse(String xmlData) {
        boolean status = true;
        Photo currentRecord = null;
        boolean inEntry = false;
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "parse: Starting tag for " + tagName);
                        if ("entry".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentRecord = new Photo();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parse: Ending tag for " + tagName);
                        if (inEntry) {
                            if ("entry".equalsIgnoreCase(tagName)) {
                                mPhotoList.add(currentRecord);
                                inEntry = false;
                                Log.d(TAG, "parse: added an entry");
                            } else if ("title".equalsIgnoreCase(tagName)) {
                                    currentRecord.setmTitle(textValue);
                            } else if ("image".equalsIgnoreCase(tagName)) {
                                currentRecord.setmImage(textValue);
                            }
                        }
                        break;

                    default:
                        // Nothing else to do.
                }
                eventType = xpp.next();
            }
//            for (FeedEntry app: applications) {
//                Log.d(TAG, "******************");
//                Log.d(TAG, app.toString());
//            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }

        return status;
    }
}







