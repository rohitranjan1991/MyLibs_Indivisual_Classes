package com.example.xmlpullparser;


import java.util.ArrayList;

import com.androidquery.AQuery;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import android.widget.ExpandableListView;

import android.widget.Toast;

public class MainActivity extends Activity {
	ArrayList<songs> songsList = new ArrayList<songs>();
	ExpandableListView elv=null;
	AQuery aq1=new AQuery(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		//XMLPullParser parser = new XMLPullParser();
		Xml_Pull_Universal_Parser parse=new Xml_Pull_Universal_Parser();
		try {
			//String []abc=new String []{"http://api.androidhive.info/music/music.xml"};
			String data = parse.execute("http://api.androidhive.info/music/music.xml").get();
		
			//songsList = parser.parsing(new ByteArrayInputStream(data.getBytes()));
		//	ArrayList<Xml_Class> arrayList=parse.mainParser();
			//String a=parse.getText("id", 1);
			//String b=parse.getWhere("song", "artist", "id", "4");
		//	Xml_Class xml=parse.getTag("song");
			//Xml_Class xml1=parse.getTag("song");
		//	int i=parse.getTVL("music");
			Xml_Class abc=parse.getBlockWhere("song", "id", "1");
			
			Toast.makeText(MainActivity.this, "asas", Toast.LENGTH_SHORT).show();
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//populateListView();
		
		//Toast.makeText(this,songsList.get(0).getArtist().toString(), Toast.LENGTH_SHORT).show();
	}

	private void populateListView() {
		elv = (ExpandableListView) findViewById(R.id.expandableListView1);
		elv.setItemsCanFocus(true);
		
			
		ELA ela=new ELA(this,songsList,aq1);
		
		
		elv.setAdapter(ela);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
