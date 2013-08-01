package com.example.xmlpullparser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.AsyncTask;
import android.util.Xml;

public class Xml_Pull_Universal_Parser extends
		AsyncTask<String, Integer, String> {

	// ArrayList<Xml_Class> XmlList = new ArrayList<Xml_Class>();

	XmlPullParserFactory factory = null;
	XmlPullParser parser = null;
	InputStream is;
	int event = 0;
	Boolean endwhile = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		try {

			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			parser = factory.newPullParser();

			// int eventType = parser.getEventType();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			is = new ByteArrayInputStream(data.getBytes());
			parser.setInput(is, null);
			parser.next();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return data;

	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getText(String tag_to_find, int pos) {

		String text = null;
		try {

			// parser.setInput(is, null);

			int eventType = parser.getEventType();
			Boolean tag_found = false, loop = true;
			int i = 1;
			while (eventType != XmlPullParser.END_DOCUMENT && loop) {
				String tagname = parser.getName();

				switch (eventType) {
				/*
				 * case XmlPullParser.START_DOCUMENT: break;
				 */
				case XmlPullParser.START_TAG:
					if (tagname.equalsIgnoreCase(tag_to_find)) {
						if (pos == i)
							tag_found = true;
						else
							i++;
					}
					break;

				case XmlPullParser.TEXT:
					if (tag_found) {
						text = parser.getText();
						loop = false;
					}

					break;

				}
				eventType = parser.next();
			}

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return text;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getAttribute(String tag_name, String AttrName, int pos) {
		String text = null;
		try {
			int eventType = parser.getEventType();
			Boolean loop = true;
			int i = 0;
			while (eventType != XmlPullParser.END_DOCUMENT && loop) {
				String tagname = parser.getName();

				switch (eventType) {
				/*
				 * case XmlPullParser.START_DOCUMENT: break;
				 */
				case XmlPullParser.START_TAG:
					if (tagname.equalsIgnoreCase(tag_name)) {
						if (pos == i) {
							text = parser.getAttributeValue(null, AttrName);
						} else
							i++;
					} else
						break;

				}
				eventType = parser.next();
			}

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return text;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String getWhere(String block, String tagValueToFind,
			String whereTag, String whereValue) {
		String text = null;

		try {
			int eventType = parser.getEventType();
			Boolean loop = true, insideBlock = false, captureToFindText = false, captureWhereValue = false, wheretag = false, wherevalue = false;

			Boolean tagtofind = false;

			while (eventType != XmlPullParser.END_DOCUMENT && loop) {
				String tagname = parser.getName();

				switch (eventType) {
				/*
				 * case XmlPullParser.START_DOCUMENT: break;
				 */
				case XmlPullParser.START_TAG:
					if (tagname.equalsIgnoreCase(block)) {
						insideBlock = true;
					}
					if (insideBlock) {
						if (tagname.equalsIgnoreCase(tagValueToFind)) {
							captureToFindText = true;
							tagtofind = true;

						} else if (tagname.equalsIgnoreCase(whereTag)) {
							captureWhereValue = true;
							wheretag = true;
						}

					}
					break;
				case XmlPullParser.TEXT:
					if (captureToFindText) {
						text = parser.getText();
						captureToFindText = false;
					} else if (captureWhereValue) {
						captureWhereValue = false;
						if (parser.getText().equalsIgnoreCase(whereValue))
							wherevalue = true;
					}

					break;

				case XmlPullParser.END_TAG:
					if (tagname.equalsIgnoreCase(block)) {
						if (wherevalue && tagtofind && wheretag)

							return text;
						else
							wherevalue = tagtofind = wheretag = wherevalue = false;
					}
					break;

				}
				eventType = parser.next();
			}

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	int eventType;

	public Xml_Class getTag(String tagName) {
		Xml_Class xml = new Xml_Class();
		
		HashMap<String, String> attr = new HashMap<String, String>();
		ArrayList<Xml_Class> tagList = new ArrayList<Xml_Class>();
		try {
			eventType = parser.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tagname = parser.getName();

				if (eventType == XmlPullParser.START_TAG
						&& tagname.equalsIgnoreCase(tagName)) {
					// getting details for the tag
					xml.setTagName(tagname);
					for (int i = 0; i < parser.getAttributeCount() - 1; i++) {
						attr.put(parser.getAttributeName(i),
								parser.getAttributeValue(i));
					}
					eventType = parser.next();
					xml.setText(parser.getText());
					eventType = parser.next();
					// checking for sub block
					while (eventType != XmlPullParser.END_TAG
							&& eventType == XmlPullParser.START_TAG) {
						tagList.add(getTag(parser.getName()));
						// get this tag
						// and store it

					}
					eventType = parser.next();
					eventType = parser.next();
					xml.addBlockList(tagList);
					return xml;
				}
				eventType = parser.next();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	ArrayList<Xml_Class> tagValueList = new ArrayList<Xml_Class>();

	public int getTagValueList(Xml_Class xml1) {

		tagValueList.add(xml1);
		for (int i = 0; i < xml1.tagList.size(); i++) {
			getTagValueList(xml1.tagList.get(i));
		}

		return tagValueList.size();

	}

	public ArrayList<Xml_Class> getTagValueList() {
		return tagValueList;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Xml_Class getBlockWhere(String BlockTag, String whereTag,
			String whereValue) {
		Xml_Class xml; 
		reset();
		while((xml= getTag(BlockTag))!=null){
		
		getTagValueList(xml);
		for(int i=0;i<tagValueList.size();i++)
		{
			if(tagValueList.get(i).getTagName().equalsIgnoreCase(whereTag) && tagValueList.get(i).getText().equalsIgnoreCase(whereValue))
				return xml;
		}
		}
		tagValueList.clear();
		return null;

	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public void reset()
	{
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			parser = factory.newPullParser();
			parser.setInput(is, null);
			parser.next();
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
