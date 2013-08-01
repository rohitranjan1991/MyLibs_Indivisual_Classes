package com.example.trydb;

import android.app.Activity;
import android.content.Context;

import com.dropbox.sync.android.DbxAccount;
import com.dropbox.sync.android.DbxAccountManager;
import com.dropbox.sync.android.DbxException;
import com.dropbox.sync.android.DbxFile;
import com.dropbox.sync.android.DbxFileSystem;
import com.dropbox.sync.android.DbxPath;

public class DropBox {

////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private int REQUEST_LINK_TO_DBX;
	private DbxAccountManager mDbxAcctMgr;
	DbxFileSystem dbxFs;
	Context con;
	Activity act;
	String App_key, App_Secret;
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public DropBox(int rEQUEST_LINK_TO_DBX, Context con, Activity act,
			String App_Key, String App_Secret) {
		super();
		this.App_key = App_Key;
		this.App_Secret = App_Secret;
		this.act = act;
		REQUEST_LINK_TO_DBX = rEQUEST_LINK_TO_DBX;
		this.con = con;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String connectToDropBox() {
		
		String ret = null;
		try {
			mDbxAcctMgr = DbxAccountManager.getInstance(con, App_key,
					App_Secret);
			mDbxAcctMgr.unlink();
			(mDbxAcctMgr).startLink(act, REQUEST_LINK_TO_DBX);
		} catch (Exception e) {
			ret = e.getMessage();
		}
		return ret;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public DbxAccount getAccount() {
		if (mDbxAcctMgr.getLinkedAccount() == null)
			return null;
		else
			return mDbxAcctMgr.getLinkedAccount();

	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * @Override public void onActivityResult(int requestCode, int resultCode,
	 * Intent data) { if (requestCode == REQUEST_LINK_TO_DBX) { if (resultCode
	 * == Activity.RESULT_OK) {
	 * 
	 * } else { // ... Link failed or was cancelled by the user. } } else { //
	 * super.onActivityResult(requestCode, resultCode, data); } }
	 */
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public DbxPath writefile(String FileName, String Content) {
		DbxFile testFile = null;
		DbxPath path = null;
		try {
			dbxFs = DbxFileSystem.forAccount(((DbxAccountManager) mDbxAcctMgr)
					.getLinkedAccount());
			DbxPath dbPath=new DbxPath(FileName);
			testFile = dbxFs.create(dbPath);
			testFile.writeString(Content);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		//	path = testFile.getPath();
			testFile.close();
			
		}
		try {
			dbxFs.syncNowAndWait();
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;

	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String readfile(String FileName) {
		DbxFile testFile = null;
		String contents = null;
		try {
			testFile = dbxFs.open(new DbxPath(FileName));
			contents = testFile.readString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			testFile.close();
		}
		return contents;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
