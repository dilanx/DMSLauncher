package com.blockhead7360.dms.launcher.internet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blockhead7360.dilanscript.java.DStructure;
import com.blockhead7360.dilanscript.java.DilanScript;
import com.blockhead7360.dms.program.manageserver.passwordencoder.DMSLauncherPasswordEncoder;

public class ScriptReader {

	private static final String baseUrl = "https://pastebin.com/raw/whfdWcEj";

	public static String image;
	public static String changelog;
	public static String password;

	public static String libraries;
	public static String minecraftWindows;
	public static String minecraftMac;
	public static String extras;

	public static String disablePlay = null;
	public static boolean disableServerIndicator = false;

	public static String host, user, pass, dir;
	public static int port = 21;

	public static String serverIP;

	public static List<DMSAccount> accounts;
	public static Map<String, String> options;

	public static String rulesSub, rulesWarn;
	public static List<String> rulesKeys;
	public static Map<String, String> rules;

	public static Map<String, InternetServerData> getServers() {

		Map<String, InternetServerData> servers = new HashMap<>();

		DStructure servers_s = InternetReader.readDS2(baseUrl).enter();

		for (String key : servers_s.getNestKeys()) {

			DStructure serverData = servers_s.nest(key);
			servers.put(key, new InternetServerData(serverData.get("script"), serverData.get("id"), serverData.get("accounts")));

		}

		return servers;
	}

	public static void read(String link) {

		DilanScript ds = InternetReader.readDS2(link);

		DStructure content = ds.enter();

		serverIP = content.get("server-ip");

		image = content.get("img");

		changelog = content.get("changelog");

		password = DMSLauncherPasswordEncoder.decode(content.get("type"));


		libraries = content.get("libverlaunch");

		minecraftWindows = content.get("mc-win");

		minecraftMac = content.get("mc-mac");

		extras = content.get("extra");

		DStructure ftp = content.nest("ftp");
		host = ftp.get("domain");
		user = ftp.get("idstr");
		pass = DMSLauncherPasswordEncoder.decode(ftp.get("idnum"));
		dir = ftp.get("dir");

		if (!content.get("disable-play").equalsIgnoreCase("no")) {

			disablePlay = content.get("disable-play");

		}

		if (content.get("disable-server-indicator").equalsIgnoreCase("yes")) {

			disableServerIndicator = true;

		}

		options = content.nest("options").getAllData();

		accounts = new ArrayList<DMSAccount>();

		DStructure accounts_s = content.nest("accounts");

		if (accounts_s != null) {
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

		rulesSub = content.get("rules-subtitle");

		rulesWarn = content.get("rules-warning");

		rulesKeys = new ArrayList<String>();
		rules = new HashMap<String, String>();

		DStructure rules_s = content.nest("rules");

		String buffer = "";

		int i = 1;

		while ((buffer = rules_s.get("title" + i)) != null) {

			rulesKeys.add(buffer);
			rules.put(buffer, rules_s.get("info" + i));
			i++;

		}



	}




}
