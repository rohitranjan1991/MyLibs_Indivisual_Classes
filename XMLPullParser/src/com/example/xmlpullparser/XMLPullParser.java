package com.example.xmlpullparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.AsyncTask;

public class XMLPullParser extends AsyncTask<String,Integer,String>{
	ArrayList<songs> songsList=new ArrayList<songs>();
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		
		super.onPreExecute();
	}
	@Override
	protected String doInBackground(String... params) {
		String data = "";
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(params[0]);

			HttpResponse httpResponse = httpClient.execute(httpget);
			HttpEntity httpEntity = httpResponse.getEntity();
			data = EntityUtils.toString(httpEntity);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return data;
		
	}
	
	
	//main xmlpullparser
	 public ArrayList<songs> parsing(InputStream is) {
	        XmlPullParserFactory factory = null;
	        XmlPullParser parser = null;
	        songs song=null;
	        String text=null;
	        try {
	            factory = XmlPullParserFactory.newInstance();
	            factory.setNamespaceAware(true);
	            parser = factory.newPullParser();
	 
	            parser.setInput(is, null);
	 
	            int eventType = parser.getEventType();
	            while (eventType != XmlPullParser.END_DOCUMENT) {
	                String tagname = parser.getName();
	                
	                switch (eventType) {
	               /* case XmlPullParser.START_DOCUMENT:
	                	break;*/
	                case XmlPullParser.START_TAG:
	                    if (tagname.equalsIgnoreCase("song")) {
	                        // create a new instance of employee
	                        song = new songs();
	                    }
	                    break;
	 
	                case XmlPullParser.TEXT:
	                    text = parser.getText();
	                    break;
	 
	                case XmlPullParser.END_TAG:
	                    if (tagname.equalsIgnoreCase("song")) {
	                        // add employee object to list
	                       songsList.add(song);
	                    } else if (tagname.equalsIgnoreCase("id")) {
	                        song.setId(text);
	                    } else if (tagname.equalsIgnoreCase("title")) {
	                        song.setTitle(text);
	                    } else if (tagname.equalsIgnoreCase("artist")) {
	                        song.setArtist(text);
	                    } else if (tagname.equalsIgnoreCase("duration")) {
	                    	song.setDuration(text);
	                    } else if (tagname.equalsIgnoreCase("thumb_url")) {
	                    	song.setThumb_url(text);
	                    }
	                    break;
	                case XmlPullParser.END_DOCUMENT:
	                	break;
	                }
	                eventType = parser.next();
	            }
	 
	        } catch (XmlPullParserException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 
	        return songsList;
	    }
	

	

}
