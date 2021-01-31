package com.blockhead7360.dms.launcher.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.blockhead7360.dilanscript.java.DilanScript;
import com.blockhead7360.dms.launcher.DMSLauncher;
import com.blockhead7360.dms.launcher.utilities.CrashReport;
import com.blockhead7360.dms.launcher.utilities.ErrorCode;

public class InternetReader {
	
	public static DilanScript readDS2(String link) {
		
		try {
			return new DilanScript(new URL(link));
		} catch (MalformedURLException e) {
			CrashReport.error(e, ErrorCode.INTERNET_READ, true);
		} catch (Exception e) {
			CrashReport.error(e, ErrorCode.DS_PARSE, true);
		}
		
		return null;
		
	}
	
	public static String readContentNoDilanScript(DMSLauncher main, String link) {
		String content = null;
		try {
			URL home = new URL(link);
			BufferedReader in = new BufferedReader(new InputStreamReader(home.openStream()));
			List<String> list = new ArrayList<String>();
			String str;
			while((str = in.readLine()) != null) {
				list.add(str);
			}
			in.close();
			content = list.toString();
		} catch (IOException e) {
			if (link.contains("mcapi.us")) return "error";
			CrashReport.error(e, ErrorCode.INTERNET_READ, true);
		}
		return content;
	}
	
	public static JsonObject getServerQuery(String link) {
			
			try {
				URL url = new URL(link);
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
				
				String str = br.readLine();
				
				JsonReader jr = Json.createReader(new StringReader(str));
				
				JsonObject obj = jr.readObject();
				
				jr.close();
				
				return obj;

			} catch (IOException e) {
				return null;
			}
			
	}
	
	public static String readContent(String link) {
		String content = null;
		try {
			URL home = new URL(link);
			BufferedReader in = new BufferedReader(new InputStreamReader(home.openStream()));
			List<String> list = new ArrayList<String>();
			String str;
			while((str = in.readLine()) != null) {
				list.add(str);
			}
			in.close();
			content = list.toString().split("iiDilanScriptii-")[1].split("iiEndScript")[0];
		} catch (IOException e) {
			CrashReport.error(e, ErrorCode.INTERNET_READ, true);
		}
		return content;
	}
	
	public static boolean hasInternet() {

		try {
			URL url = new URL("http://www.google.com");

			HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();

			urlConnect.getContent();

		} catch (UnknownHostException e) {
			return false;
		}
		catch (IOException e) {
			return false;
		}
		return true;
	}

}
