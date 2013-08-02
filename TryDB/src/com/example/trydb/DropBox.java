package com.example.trydb;

import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.dropbox.sync.android.DbxAccount;
import com.dropbox.sync.android.DbxAccountManager;
import com.dropbox.sync.android.DbxException;
import com.dropbox.sync.android.DbxFileInfo;

import com.dropbox.sync.android.DbxFile;
import com.dropbox.sync.android.DbxFileSystem;
import com.dropbox.sync.android.DbxPath;

public class DropBox {

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////
	private int REQUEST_LINK_TO_DBX;
	private DbxAccountManager mDbxAcctMgr;
	DbxFileSystem dbxFs;
	Context con;
	Activity act;
	String App_key, App_Secret;
	String lastReadContent;
	DbxPath lastfilepath;

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////
	public DropBox(int rEQUEST_LINK_TO_DBX, Context con, Activity act,
			String App_Key, String App_Secret) {
		super();
		this.App_key = App_Key;
		this.App_Secret = App_Secret;
		this.act = act;
		REQUEST_LINK_TO_DBX = rEQUEST_LINK_TO_DBX;
		this.con = con;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////
	public DbxAccountManager connectToDropBox() {

	
		try {
			mDbxAcctMgr = DbxAccountManager.getInstance(con, App_key,
					App_Secret);
			mDbxAcctMgr.unlink();
			
			(mDbxAcctMgr).startLink(act, REQUEST_LINK_TO_DBX);
		} catch (Exception e) {
		
		}
		return mDbxAcctMgr;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public DbxAccount getAccount() {
		if (mDbxAcctMgr.getLinkedAccount() == null)
			return null;
		else
			return mDbxAcctMgr.getLinkedAccount();

	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * @Override public void onActivityResult(int requestCode, int resultCode,
	 * Intent data) { if (requestCode == REQUEST_LINK_TO_DBX) { if (resultCode
	 * == Activity.RESULT_OK) {
	 * 
	 * } else { // ... Link failed or was cancelled by the user. } } else { //
	 * super.onActivityResult(requestCode, resultCode, data); } }
	 */
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void writefile(String FileName, String Content) {

		try {
				
			new writeFileThread().execute(new String[]{FileName,Content});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public class writeFileThread extends AsyncTask<String,Void, DbxPath> {

		
		@Override
		protected void onPostExecute(DbxPath result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
		}

		protected DbxPath doInBackground(String... params) {


			DbxFile testFile = null;
			DbxPath dbPath = null;
			try {if(dbxFs==null)
				dbxFs = DbxFileSystem
						.forAccount(((DbxAccountManager) mDbxAcctMgr)
								.getLinkedAccount());
			
				dbPath = new DbxPath(params[0]);

				lastfilepath = dbPath;
				testFile = dbxFs.create(dbPath);
				testFile.writeString(params[1]);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				// path = testFile.getPath();
				testFile.close();

			}
			try {
				dbxFs.syncNowAndWait();
			} catch (DbxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			return dbPath;
		}


	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String readfile(String FileName) {
		
		try {
			return new ReadFileThread().execute(new String[]{FileName}).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	public class ReadFileThread extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			DbxFile testFile = null;
			String contents = null;
			try {
				if(dbxFs==null)
				dbxFs = DbxFileSystem
						.forAccount(((DbxAccountManager) mDbxAcctMgr)
								.getLinkedAccount());
				testFile = dbxFs.open(new DbxPath(params[0]));
				contents = testFile.readString();
				lastReadContent = contents;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				testFile.close();
			}

			return lastReadContent;
		}

	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public void createFolder(String path)
	{
		try {
			new createFolderThread().execute(new String[]{path});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public class createFolderThread extends AsyncTask<String , Void, String>{

		@Override
		protected String doInBackground(String... params) {
			try {if(dbxFs==null)
				dbxFs = DbxFileSystem
						.forAccount(((DbxAccountManager) mDbxAcctMgr)
								.getLinkedAccount());
				
				if(!dbxFs.isFolder(new DbxPath(params[0])))
				{
					dbxFs.createFolder(new DbxPath(params[0]));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void Delete(String path)
	{
		try {
			new DeleteThread().execute(new String[]{path});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public class DeleteThread extends AsyncTask<String , Void, String>{

		@Override
		protected String doInBackground(String... params) {
			try {
				if(dbxFs==null)
				dbxFs = DbxFileSystem
						.forAccount(((DbxAccountManager) mDbxAcctMgr)
								.getLinkedAccount());
				
				if(dbxFs.isFolder(new DbxPath(params[0])))
				{
					
					dbxFs.delete(new DbxPath(params[0]));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Boolean PathExists(String path)
	{
		try {
			return new PathExistsThread().execute(new String[]{path}).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	
	public class PathExistsThread extends AsyncTask<String , Void, Boolean>{

		@Override
		protected Boolean doInBackground(String... params) {
			try {
			if(dbxFs==null)
				dbxFs = DbxFileSystem
						.forAccount(((DbxAccountManager) mDbxAcctMgr)
								.getLinkedAccount());
				
				if(dbxFs.exists(new DbxPath(params[0])))
				{
					
						return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//example db.MovePath("a/b/c","q/w/e");
	//this will copy the contents of c in e be it a file or folder i.e renaming happens here
	
	public void MovePath(String from,String to)
	{
		try {
			new MovePathThread().execute(new String[]{from,to});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public class MovePathThread extends AsyncTask<String , Void, String>{

		@Override
		protected String doInBackground(String... params) {
			try {if(dbxFs==null)
				dbxFs = DbxFileSystem
						.forAccount(((DbxAccountManager) mDbxAcctMgr)
								.getLinkedAccount());
			if(dbxFs.exists(new DbxPath(params[0]))){
				dbxFs.move(new DbxPath(params[0]), new DbxPath(params[1]));
				
			}
			dbxFs.syncNowAndWait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void shutdown()
	{
		if(dbxFs!=null)
			dbxFs.shutDown();
		
			
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public List<DbxFileInfo>  listPath(String listPath)
	{
		try {
			return new listPathThread().execute(new String[]{listPath}).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public class listPathThread extends AsyncTask<String, Void, List<DbxFileInfo> >{

		@Override
		protected List<DbxFileInfo> doInBackground(String... params) {
			try {if(dbxFs==null)
				
				dbxFs = DbxFileSystem
						.forAccount(((DbxAccountManager) mDbxAcctMgr)
								.getLinkedAccount());
			dbxFs.syncNowAndWait();
			if(dbxFs.exists(new DbxPath(params[0]))){
			
				return dbxFs.listFolder(new DbxPath(params[0]));
				
			}
			dbxFs.syncNowAndWait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
