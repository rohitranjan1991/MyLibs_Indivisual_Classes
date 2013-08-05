package com.example.mediafiretry;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/*
 * MediaFire URLS :- 
 *  	"FILE_COLLABORATE" => "http://www.mediafire.com/api/file/collaborate.php",
 "FILE_CONFIGURE_ONE_TIME_DOWNLOAD" => "http://www.mediafire.com/api/file/configure_one_time_download.php",
 "FILE_COPY" => "http://www.mediafire.com/api/file/copy.php",
 "FILE_DELETE" => "http://www.mediafire.com/api/file/delete.php",
 "FILE_GET_INFO" => "http://www.mediafire.com/api/file/get_info.php",
 "FILE_GET_LINKS" => "http://www.mediafire.com/api/file/get_links.php",
 "FILE_MOVE" => "http://www.mediafire.com/api/file/move.php",
 "FILE_ONE_TIME_DOWNLOAD" => "http://www.mediafire.com/api/file/one_time_download.php",
 "FILE_UPDATE" => "http://www.mediafire.com/api/file/update.php",
 "FILE_UPDATE_FILE" => "http://www.mediafire.com/api/file/update_file.php",
 "FILE_UPDATE_PASSWORD" => "http://www.mediafire.com/api/file/update_password.php",
 "FILE_UPLOAD" => "http://www.mediafire.com/api/upload/upload.php",
 "FILE_UPLOAD_CONFIG" => "http://www.mediafire.com/basicapi/uploaderconfiguration.php",
 "FILE_UPLOAD_GETTYPE" => "http://www.mediafire.com/basicapi/getfiletype.php",
 "FILE_UPLOAD_POLL" => "http://www.mediafire.com/api/upload/poll_upload.php",
 "FOLDER_ATTACH_FOREIGN" => "http://www.mediafire.com/api/folder/attach_foreign.php",
 "FOLDER_CREATE" => "http://www.mediafire.com/api/folder/create.php",
 "FOLDER_DELETE" => "http://www.mediafire.com/api/folder/delete.php",
 "FOLDER_DETACH_FOREIGN" => "http://www.mediafire.com/api/folder/detach_foreign.php",
 "FOLDER_GET_CONTENT" => "http://www.mediafire.com/api/folder/get_content.php",
 "FOLDER_GET_DEPTH" => "http://www.mediafire.com/api/folder/get_depth.php",
 "FOLDER_GET_INFO" => "http://www.mediafire.com/api/folder/get_info.php",
 "FOLDER_GET_REVISION" => "http://www.mediafire.com/api/folder/get_revision.php",
 "FOLDER_GET_SIBLINGS" => "http://www.mediafire.com/api/folder/get_siblings.php",
 "FOLDER_MOVE" => "http://www.mediafire.com/api/folder/move.php",
 "FOLDER_SEARCH" => "http://www.mediafire.com/api/folder/search.php",
 "FOLDER_UPDATE" => "http://www.mediafire.com/api/folder/update.php",
 "SYSTEM_GET_EDITABLE_MEDIA" => "http://www.mediafire.com/api/system/get_editable_media.php",
 "SYSTEM_GET_INFO" => "http://www.mediafire.com/api/system/get_info.php",
 "SYSTEM_GET_MIME_TYPES" => "http://www.mediafire.com/api/system/get_mime_types.php",
 "SYSTEM_GET_SUPPORTED_MEDIA" => "http://www.mediafire.com/api/system/get_supported_media.php",
 "SYSTEM_GET_VERSION" => "http://www.mediafire.com/api/system/get_version.php",
 "USER_ACCEPT_TOS" => "http://www.mediafire.com/api/user/accept_tos.php",
 "USER_FETCH_TOS" => "http://www.mediafire.com/api/user/fetch_tos.php",
 "USER_GET_INFO" => "http://www.mediafire.com/api/user/get_info.php",
 "USER_GET_LOGIN_TOKEN" => "https://www.mediafire.com/api/user/get_login_token.php",
 "USER_GET_SESSION_TOKEN" => "https://www.mediafire.com/api/user/get_session_token.php",
 "USER_LOGIN" => "http://www.mediafire.com/api/user/login_with_token.php",
 "USER_MYFILES" => "http://www.mediafire.com/api/user/myfiles.php",
 "USER_MYFILES_REVISION" => "http://www.mediafire.com/api/user/myfiles_revision.php",
 "USER_REGISTER" => "https://www.mediafire.com/api/user/register.php",
 "USER_RENEW_SESSION_TOKEN" => "http://www.mediafire.com/api/user/renew_session_token.php",
 "USER_UPDATE" => "http://www.mediafire.com/api/user/update.php",
 "MEDIA_CONVERSION" => "http://www.mediafire.com/conversion_server.php"
 */

public class MainActivity extends Activity implements OnClickListener {

	Button bDoStuff;
	Xml_Pull_Universal_Parser parser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initialize();

	}

	private void initialize() {
		bDoStuff = (Button) findViewById(R.id.button1);
		bDoStuff.setOnClickListener(this);
		parser = new Xml_Pull_Universal_Parser();
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
		case R.id.button1:

			/*
			 * Example: email: name@domain.com password: 123456 application id:
			 * 1200 api key : c1d62nzqu7z4rig15axe72jvczib53682kapu7lk
			 * 
			 * the signature would be: SHA1(name@domain.
			 * com1234561200c1d62nzqu7z4rig15axe72jvczib53682kapu7lk) which is:
			 * f772724acc14117f3f69784a2f505d1560afaf64
			 * f772724acc14117f3f69784a2f505d1560afaf64
			 */
			String result = null,
			data = null;
			String sessionToken = null,
			loginToken = null,
			userInfo = null,
			noIdea = null;
			try {
				String ret = "91.rohit@gmail.comgMy:*~9DEK36464tdf1slp1hssjtjw4706k0yukc2xz01jz44qccck2";
				result = makeSHA1Hash(ret);

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				data = parser
						.execute(
								new String[] { "https://www.mediafire.com/api/user/get_session_token.php?email=91.rohit@gmail.com&password=gMy:*~9DEK&application_id=36464&signature="
										+ result + "&version=1" }).get();
				sessionToken = parser.getText("session_token", 1);
				if (sessionToken != null) {
					Xml_Pull_Universal_Parser login = new Xml_Pull_Universal_Parser();
					loginToken = login
							.execute(
									new String[] { "https://www.mediafire.com/api/user/get_login_token.php?email=91.rohit@gmail.com&password=gMy:*~9DEK&application_id=36464&signature="
											+ result + "&version=1" }).get();
					loginToken = login.getText("login_token", 1);
					login = null;
					System.gc();

				}

				if (loginToken != null) {
					Xml_Pull_Universal_Parser getInfo = new Xml_Pull_Universal_Parser();
					// http://www.mediafire.com/api/user/get_info.php?session_token=123456789012345678901234567890123456789012345678901234567890&version=1
					userInfo = getInfo
							.execute(
									new String[] { "http://www.mediafire.com/api/user/get_info.php?session_token="
											+ sessionToken + "&version=1" })
							.get();
					userInfo = getInfo.getText("first_name", 1);
					getInfo = null;
					System.gc();

				}

				if (userInfo != null) {
					Xml_Pull_Universal_Parser login_with_token = new Xml_Pull_Universal_Parser();
					noIdea = login_with_token
							.execute(
									new String[] { "http://www.mediafire.com/api/user/login_with_token.php?login_token="
											+ loginToken }).get();
					noIdea = login_with_token.getText("first_name", 1);
					login_with_token = null;
					System.gc();
				}

				Xml_Pull_Universal_Parser waste = new Xml_Pull_Universal_Parser();
				waste = new Xml_Pull_Universal_Parser();
				noIdea = waste
						.execute(
								new String[] { "http://www.mediafire.com/api/user/myfiles.php?session_token="
										+ sessionToken + "&version=1" }).get();
				noIdea = waste.getText("first_name", 1);
				waste = null;
				System.gc();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Toast.makeText(MainActivity.this, "asndkas", Toast.LENGTH_SHORT)
					.show();
			break;

		default:
			break;
		}

	}

	public String makeSHA1Hash(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA1");
		md.reset();
		byte[] buffer = input.getBytes();
		md.update(buffer);
		byte[] digest = md.digest();

		String hexStr = "";
		for (int i = 0; i < digest.length; i++) {
			hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16)
					.substring(1);
		}
		return hexStr;
	}



}
