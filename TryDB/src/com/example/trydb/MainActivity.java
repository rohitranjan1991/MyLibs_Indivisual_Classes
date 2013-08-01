package com.example.trydb;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.dropbox.sync.android.DbxAccountManager;

import com.dropbox.sync.android.DbxFile;
import com.dropbox.sync.android.DbxFileSystem;
import com.dropbox.sync.android.DbxPath;


public class MainActivity extends Activity implements OnClickListener {


	private Button bDownload;
	private Button bLink;
	private Button bUpload;
	private Button doStuff;
	private int REQUEST_LINK_TO_DBX;
	DbxFileSystem dbxFs ;
	DropBox db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
		//mDbxAcctMgr = DbxAccountManager.getInstance(getApplicationContext(),"ut3grlqbuz2299s", "3c9pbszuaqhmvtr");
		db=new DropBox(0,getApplicationContext(),MainActivity.this,"ut3grlqbuz2299s","3c9pbszuaqhmvtr");
		if(db.connectToDropBox()==null)
		{
			Toast.makeText(MainActivity.this, "Could not Connect To DropBox Account", Toast.LENGTH_SHORT).show();
		}
		
	}

	private void initialize() {

		bDownload = (Button) findViewById(R.id.bDownload);
		bLink = (Button) findViewById(R.id.bLink);
		bUpload = (Button) findViewById(R.id.bUpload);
		doStuff= (Button) findViewById(R.id.bDoStuff);
		doStuff.setOnClickListener(this);
		bDownload.setOnClickListener(this);
		bLink.setOnClickListener(this);
		bUpload.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bLink:
		
			break;
			
			
		case R.id.bDoStuff:
			db.writefile("asdasd.txt", "Hello FileSystem!!");
			break;

		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_LINK_TO_DBX) {
			if (resultCode == Activity.RESULT_OK) {
				Toast.makeText(MainActivity.this, "Connected to the User Account", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(MainActivity.this, "Could not connect to the User Account!! Try Again Later!!", Toast.LENGTH_SHORT).show();
			}
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

}
