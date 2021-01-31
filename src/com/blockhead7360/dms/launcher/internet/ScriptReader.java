package com.blockhead7360.dms.launcher.internet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blockhead7360.dilanscript.java.DStructure;
import com.blockhead7360.dilanscript.java.DilanScript;
import com.blockhead7360.dms.launcher.DMSLauncher;
import com.blockhead7360.dms.program.manageserver.passwordencoder.DMSLauncherPasswordEncoder;

public class ScriptReader {

	private static final String baseUrl = "https://pastebin.com/raw/whfdWcEj";
	
	public static String image;
	public static String changelog;
	public static String password;
	
	public static String ruleLink;
	
	public static String libraries;
	public static String minecraftWindows;
	public static String minecraftMac;
	public static String extras;
	
	public static String disablePlay = null;
	public static boolean disableServerIndicator = false;
	
	public static String host, user, pass;
	public static int port = 21;
	
	public static String serverIP;
	
	public static List<DMSAccount> accounts;
	public static Map<String, String> options;
	
	
	public static Map<String, String> getServers(){
		List<String> servers = getRawServers();
		Map<String, String> serversAndPasswords = new HashMap<String, String>();
		for (int i = 0; i < servers.size(); i++) {
			serversAndPasswords.put(servers.get(i).split("iiIii")[0], servers.get(i).split("iiIii")[2]);
		}
		return serversAndPasswords;
	}
	
	private static List<String> getRawServers() {
		String findUrl = InternetReader.readContent(baseUrl);
		String[] list_array = findUrl.split("-iiXii-");
		List<String> list = new ArrayList<String>();
		for (String s : list_array) list.add(s);
		list.remove(0); //removes the scriptRunning
		return list;
	}
	
	public static void read() {
		String myLink = null;
		for (String servers : getRawServers()) {
			if (servers.split("iiIii")[0].equals(DMSLauncher.server)) {
				myLink = servers.split("iiIii")[1];
				break;
			}
		}
		
		DilanScript ds = InternetReader.readDS2(myLink);
				
		DStructure content = ds.enter();
		
		serverIP = content.get("server-ip");
		
		image = content.get("img");
		
		changelog = content.get("changelog");
		
		password = DMSLauncherPasswordEncoder.decode(content.get("type"));
		
		
		libraries = content.get("libverlaunch");
	
		minecraftWindows = content.get("mc-win");
		
		minecraftMac = content.get("mc-mac");
		
		extras = content.get("extra");
		
		ruleLink = content.get("rules");
		
		DStructure ftp = content.nest("ftp");
		host = ftp.get("domain");
		user = ftp.get("idstr");
		pass = DMSLauncherPasswordEncoder.decode(ftp.get("idnum"));
		
		if (!content.get("disable-play").equalsIgnoreCase("no")) {
			
			disablePlay = content.get("disable-play");
			
		}
		
		if (content.get("disable-server-indicator").equalsIgnoreCase("yes")) {
			
			disableServerIndicator = true;
			
		}
		
		options = content.nest("options").getAllData();
		
		accounts = new ArrayList<DMSAccount>();
		
		DStructure accounts_s = content.nest("accounts");
		
		for (String id : accounts_s.getNestKeys()) {
			
			DStructure account = accounts_s.nest(id);
			
			String username = account.get("user");
			String status = account.get("status");
			String statusColor = account.get("color");
			String lastPay = account.get("last-pay");
			String nextPay = account.get("next-pay");
			String blockButton = account.get("block");
			String blockReason = account.get("reason");
			
			DMSAccount dmsa = new DMSAccount(id, username, status, statusColor, lastPay, nextPay, blockButton, blockReason);
			accounts.add(dmsa);
			
		}
		
		
	}
	
	
	
	
}
