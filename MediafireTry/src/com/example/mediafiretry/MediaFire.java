package com.example.mediafiretry;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class MediaFire {

	HashMap<String, String> mediafire_urls = new HashMap<String, String>();

	public MediaFire() {

		mediafire_urls.put("FILE_COLLABORATE",
				"http://www.mediafire.com/api/file/collaborate.php");
		mediafire_urls
				.put("FILE_CONFIGURE_ONE_TIME_DOWNLOAD",
						"http://www.mediafire.com/api/file/configure_one_time_download.php");
		mediafire_urls.put("FILE_COPY",
				"http://www.mediafire.com/api/file/copy.php");
		mediafire_urls.put("FILE_DELETE",
				"http://www.mediafire.com/api/file/delete.php");
		mediafire_urls.put("FILE_GET_INFO",
				"http://www.mediafire.com/api/file/get_info.php");
		mediafire_urls.put("FILE_GET_LINKS",
				"http://www.mediafire.com/api/file/get_links.php");
		mediafire_urls.put("FILE_MOVE",
				"http://www.mediafire.com/api/file/move.php");
		mediafire_urls.put("FILE_ONE_TIME_DOWNLOAD",
				"http://www.mediafire.com/api/file/one_time_download.php");
		mediafire_urls.put("FILE_UPDATE",
				"http://www.mediafire.com/api/file/update.php");
		mediafire_urls.put("FILE_UPDATE_FILE",
				"http://www.mediafire.com/api/file/update_file.php");
		mediafire_urls.put("FILE_UPDATE_PASSWORD",
				"http://www.mediafire.com/api/file/update_password.php");
		mediafire_urls.put("FILE_UPLOAD",
				"http://www.mediafire.com/api/upload/upload.php");
		mediafire_urls.put("FILE_UPLOAD_CONFIG",
				"http://www.mediafire.com/basicapi/uploaderconfiguration.php");
		mediafire_urls.put("FILE_UPLOAD_GETTYPE",
				"http://www.mediafire.com/basicapi/getfiletype.php");
		mediafire_urls.put("FILE_UPLOAD_POLL",
				"http://www.mediafire.com/api/upload/poll_upload.php");
		mediafire_urls.put("FOLDER_ATTACH_FOREIGN",
				"http://www.mediafire.com/api/folder/attach_foreign.php");
		mediafire_urls.put("FOLDER_CREATE",
				"http://www.mediafire.com/api/folder/create.php");
		mediafire_urls.put("FOLDER_DELETE",
				"http://www.mediafire.com/api/folder/delete.php");
		mediafire_urls.put("FOLDER_DETACH_FOREIGN",
				"http://www.mediafire.com/api/folder/detach_foreign.php");
		mediafire_urls.put("FOLDER_GET_CONTENT",
				"http://www.mediafire.com/api/folder/get_content.php");
		mediafire_urls.put("FOLDER_GET_DEPTH",
				"http://www.mediafire.com/api/folder/get_depth.php");
		mediafire_urls.put("FOLDER_GET_INFO",
				"http://www.mediafire.com/api/folder/get_info.php");
		mediafire_urls.put("FOLDER_GET_REVISION",
				"http://www.mediafire.com/api/folder/get_revision.php");
		mediafire_urls.put("FOLDER_GET_SIBLINGS",
				"http://www.mediafire.com/api/folder/get_siblings.php");
		mediafire_urls.put("FOLDER_MOVE",
				"http://www.mediafire.com/api/folder/move.php");
		mediafire_urls.put("FOLDER_SEARCH",
				"http://www.mediafire.com/api/folder/search.php");
		mediafire_urls.put("FOLDER_UPDATE",
				"http://www.mediafire.com/api/folder/update.php");
		mediafire_urls.put("SYSTEM_GET_EDITABLE_MEDIA",
				"http://www.mediafire.com/api/system/get_editable_media.php");
		mediafire_urls.put("SYSTEM_GET_INFO",
				"http://www.mediafire.com/api/system/get_info.php");
		mediafire_urls.put("SYSTEM_GET_MIME_TYPES",
				"http://www.mediafire.com/api/system/get_mime_types.php");
		mediafire_urls.put("SYSTEM_GET_SUPPORTED_MEDIA",
				"http://www.mediafire.com/api/system/get_supported_media.php");
		mediafire_urls.put("SYSTEM_GET_VERSION",
				"http://www.mediafire.com/api/system/get_version.php");
		mediafire_urls.put("USER_ACCEPT_TOS",
				"http://www.mediafire.com/api/user/accept_tos.php");
		mediafire_urls.put("USER_FETCH_TOS",
				"http://www.mediafire.com/api/user/fetch_tos.php");
		mediafire_urls.put("USER_GET_INFO",
				"http://www.mediafire.com/api/user/get_info.php");
		mediafire_urls.put("USER_GET_LOGIN_TOKEN",
				"https://www.mediafire.com/api/user/get_login_token.php");
		mediafire_urls.put("USER_GET_SESSION_TOKEN",
				"https://www.mediafire.com/api/user/get_session_token.php");
		mediafire_urls.put("USER_LOGIN",
				"http://www.mediafire.com/api/user/login_with_token.php");
		mediafire_urls.put("USER_MYFILES",
				"http://www.mediafire.com/api/user/myfiles.php");
		mediafire_urls.put("USER_MYFILES_REVISION",
				"http://www.mediafire.com/api/user/myfiles_revision.php");
		mediafire_urls.put("USER_REGISTER",
				"https://www.mediafire.com/api/user/register.php");
		mediafire_urls.put("USER_RENEW_SESSION_TOKEN",
				"http://www.mediafire.com/api/user/renew_session_token.php");
		mediafire_urls.put("USER_UPDATE",
				"http://www.mediafire.com/api/user/update.php");
		mediafire_urls.put("MEDIA_CONVERSION",
				"http://www.mediafire.com/conversion_server.php");

	}

	public InputStream toInputStream(String str)
	{
		return new ByteArrayInputStream(str.getBytes());
	}
	
	
	public class fetch_url extends AsyncTask<String, Void, String> {

		private InputStream is;

		@Override
		protected String doInBackground(String... params) {

			String data = "";
			try {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(params[0]);
				HttpResponse httpResponse = httpClient.execute(httpget);
				HttpEntity httpEntity = httpResponse.getEntity();
				data = EntityUtils.toString(httpEntity);
				// is = new ByteArrayInputStream(data.getBytes());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			return data;

		}

	}

	public String fetch_tos(String sessionToken) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("USER_FETCH_TOS").toString()
							+ "?session_token=" + sessionToken).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String userAcceptTos(String sessionToken, String acceptanceToken) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("USER_ACCEPT_TOS").toString()
							+ "?session_token=" + sessionToken
							+ "&acceptance_token=" + acceptanceToken).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String userGetSessionToken(String email, String password,
			String app_id, String app_key, String version) {
		String result = null;
		String sessionToken;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("USER_GET_SESSION_TOKEN").toString()
							+ "?email=" + email + "&password=" + password
							+ "&application_id=" + app_id + "&signature="
							+ createSignature(email, password, app_id, app_key)
							+ (version != null ? "&version=" + version : null))
					.get();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String userGetLoginToken(String email, String password,
			String app_id, String app_key, String version) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("USER_GET_LOGIN_TOKEN").toString()
							+ "?email=" + email + "&password=" + password
							+ "&application_id=" + app_id + "&signature="
							+ createSignature(email, password, app_id, app_key)
							+ (version != null ? "&version=" + version : null))
					.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String userRenewSessionToken(String sessionToken, String version) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("USER_RENEW_SESSION_TOKEN").toString()
							+ "?session_token=" + sessionToken
							+ (version != null ? "&version=" + version : null))
					.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String userGetInfo(String sessionToken, String version) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("USER_GET_INFO").toString()
							+ "?session_token=" + sessionToken
							+ (version != null ? "&version=" + version : null))
					.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String userMyFileReveision(String sessionToken, String version) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("USER_MYFILES_REVISION").toString()
							+ "?session_token=" + sessionToken
							+ (version != null ? "&version=" + version : null))
					.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String userUpdate(String sessionToken, String display_name,
			String first_name, String last_name, String birth_date,
			String gender, String website, String location, String newsletter,
			String primary_usage, String version) {
		String result = null;
		StringBuilder str = new StringBuilder();
		try {

			result = new fetch_url().execute(
					mediafire_urls.get("USER_UPDATE").toString()
							+ "?session_token="
							+ sessionToken
							+ str.toString()
							+ (display_name != null ? "&display_name="
									+ display_name : null)
							+ (first_name != null ? "&first_name=" + first_name
									: null)
							+ (last_name != null ? "&last_name=" + last_name
									: null)
							+ (birth_date != null ? "&birth_date=" + birth_date
									: null)
							+ (gender != null ? "&gender=" + gender : null)
							+ (website != null ? "&website=" + website : null)
							+ (location != null ? "&location=" + location
									: null)
							+ (newsletter != null ? "&newsletter=" + newsletter
									: null)
							+ (primary_usage != null ? "&primary_usage="
									+ primary_usage : null)
							+ (version != null ? "&version=" + version : null)

			).get();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String fileCopy(String sessionToken, String quick_key,
			String folder_key, String version) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("FILE_COPY").toString()
							+ "?session_token="
							+ sessionToken
							+ "&quick_key="
							+ quick_key
							+ (folder_key != null ? "&folder_key=" + folder_key
									: null) + (version != null ? "&version=" + version : null)).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String filedelete(String sessionToken, String quick_key,
			 String version) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("FILE_DELETE").toString()
							+ "?session_token="
							+ sessionToken
							+ "&quick_key="
							+ quick_key
							+ (version != null ? "&version=" + version : null)).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String fileGetInfo(String sessionToken, String quick_key,
			 String version) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("FILE_GET_INFO").toString()
							+ "?session_token="
							+ sessionToken
							+ "&quick_key="
							+ quick_key
							+ (version != null ? "&version=" + version : null)).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String fileGetLinks(String sessionToken, String quick_key,
			 String version) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("FILE_GET_LINKS").toString()
							+ "?session_token="
							+ sessionToken
							+ "&quick_key="
							+ quick_key
							+ (version != null ? "&version=" + version : null)).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public String fileMove(String sessionToken, String quick_key,
			String folder_key, String version) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("FILE_MOVE").toString()
							+ "?session_token="
							+ sessionToken
							+ "&quick_key="
							+ quick_key
							+ (folder_key != null ? "&folder_key=" + folder_key
									: null) + (version != null ? "&version=" + version : null)).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String fileOneTimeDownload(String sessionToken, String quick_key,
			String getCountsOnly, String duration,String email_notification,String success_callback_url,
			String error_callback_url,String bind_ip,
			String burn_after_use,String version) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("FILE_MOVE").toString()
							+ "?session_token="
							+ sessionToken
							+(quick_key!= null ? "&quick_key=" + quick_key: null)
							+ (getCountsOnly!= null ? "&get_counts_only=" + getCountsOnly: null)
							+ (duration!= null ? "&duration=" + duration: null)
							+ (email_notification!= null ? "&email_notification=" + email_notification: null)
							+ (success_callback_url!= null ? "&success_callback_url=" + success_callback_url: null)
							+ (error_callback_url!= null ? "&error_callback_url=" + error_callback_url: null)
							+ (bind_ip!= null ? "&bind_ip=" + bind_ip: null)
							+ (burn_after_use!= null ? "&burn_after_use=" + burn_after_use: null)
							+ (version != null ? "&version=" + version : null)).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String fileUpdateInfo(String sessionToken, String quick_key,
			String filename, String description,String tags,String privacy,
			String timezone,String version) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("FILE_UPDATE").toString()
							+ "?session_token="
							+ sessionToken
							+"&quick_key=" + quick_key
							+ (filename!= null ? "&filename=" + filename: null)
							+ (description!= null ? "&description=" + description: null)
							+ (tags!= null ? "&tags=" + tags: null)
							+ (privacy!= null ? "&privacy=" + privacy: null)
							+ (timezone!= null ? "&timezone=" + timezone: null)
							+ (version != null ? "&version=" + version : null)).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String fileUpdate(String sessionToken, String from_quick_key,
			String to_quick_key, String version) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("FILE_UPDATE_FILE").toString()
							+ "?session_token="
							+ sessionToken
							+ "&from_quick_key="+ from_quick_key
							+ "&to_quick_key="+ to_quick_key
							+ (version != null ? "&version=" + version : null)).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String fileUpdatePassword(String sessionToken, String quick_key,
			String password, String version) {
		String result = null;
		try {
			result = new fetch_url().execute(
					mediafire_urls.get("FILE_UPDATE_PASSWORD").toString()
							+ "?session_token="
							+ sessionToken
							+ "&quick_key="+ quick_key
							+ (password != null ? "&password=" + password: null)
							+ (version != null ? "&version=" + version : null)
							
					).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String createSignature(String email, String password, String app_id,
			String app_key) {
		String str = email + password + app_id + app_key;
		try {
			String shaString = makeSHA1Hash(str);
			return shaString;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
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
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////

}
