package com.blockhead7360.dms.launcher;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.blockhead7360.dms.launcher.files.FM;
import com.blockhead7360.dms.launcher.internet.InternetReader;
import com.blockhead7360.dms.launcher.internet.InternetServerData;
import com.blockhead7360.dms.launcher.internet.ScriptReader;
import com.blockhead7360.dms.launcher.utilities.CrashReport;
import com.blockhead7360.dms.launcher.utilities.ErrorCode;
import com.blockhead7360.dms.launcher.utilities.Fonts;
import com.blockhead7360.dms.launcher.utilities.U;
import com.blockhead7360.dms.launcher.view.LoadingWindow;
import com.blockhead7360.dms.launcher.view.MainWindow;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class DMSLauncher {

	/* 
	 * 1.11.0
	 * - Update theme (dark mode)
	 * - Made changelog smaller
	 * - Fixed server status checker
	 * - Rules on DilanScript 2
	 * - Accounts not required for servers
	 * 
	 * 1.10.3
	 * - Download options feature
	 * 
	 * 1.10.2
	 * - Updated unzipper
	 * - Updated main script to DilanScript 2
	 * - Removed unnecessary DilanAPI reference
	 * - Fixed macOS Big Sur tab color glitch
	 * 
	 * 
	 * 1.10.1
	 * 
	 * 
	 * 
	 * TODO
	 * - Keyboard shortcuts maybe?
	 * - Switch to UUIDS instead of usernames for avatar
	 * - Update unzipper and maybe version of Minecraft (but I would have to figure out how to fix that launch path error)
	 * 
	 * maybe idk:
	 * Possibly make commercial (public)?
	 * Server owners create their own servers and others join with a code
	 * 
	 */


	public static final String version = "1.11.0";
	//public static final String imageUrl = "https://i.imgur.com/OtOShOj.png";

	public static String server = "NONE";
	public static String account = "NONE";
	public static String gameDir;
	public static String path = "";
	public static boolean darkMode = false;

	private static MainWindow window;

	public static String oldVersion;


	public static void newInVersion(DMSLauncher main) {

		int i = U.iD("New in DMSLauncher " + version,
				
				"Check out the full changelog lol I don't wanna rewrite it here again."
				, "Close", new String[]{"Close", "View full changelog"});
		if (i == 1) {
			try {
				Desktop.getDesktop().browse(new URI("https://repo.blockhead7360.com/changelogs/swda-10010"));
			} catch (IOException e1) {
				CrashReport.error(e1, ErrorCode.OPEN_LINK, false);
			} catch (URISyntaxException e1) {
				CrashReport.error(e1, ErrorCode.OPEN_LINK, false);
			}
		}
	}

	public DMSLauncher(String console) {
		
		FM.loadMainFiles();
		
		String dark = FM.config.getProperty("dark_mode", "false");
		if (dark.equals("true")) darkMode = true;
		
		if (darkMode) {
			
			FlatDarkLaf.install();
			
			try {
				UIManager.setLookAndFeel(new FlatDarkLaf());
			} catch (UnsupportedLookAndFeelException e) {
				CrashReport.error(e, ErrorCode.LOOK_AND_FEEL, true);
			}
			
		} else {
			
			FlatLightLaf.install();
			
			try {
				UIManager.setLookAndFeel(new FlatLightLaf());
			} catch (UnsupportedLookAndFeelException e) {
				CrashReport.error(e, ErrorCode.LOOK_AND_FEEL, true);
			}
			
		}
		
		/*
		if (!U.isMacOS()) {
			
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				CrashReport.error(e, ErrorCode.LOOK_AND_FEEL, true);
			}
			
		}*/

		LoadingWindow lw = new LoadingWindow("starting");

		if (!InternetReader.hasInternet()) {

			U.eD("Internet Connection Error", "The DMSLauncher requires an internet connection, and you don't have that right now.\nCome back later when your computer is connected to the internet.", "Exit", "Exit");

			System.exit(0);

			return;

		}

		Fonts.init();
		
		window = new MainWindow();
		window.loadWindow();
		
		Update update = new Update();
		if (update.isAvailable()) {
			
			window.update(update);
			
			return;
			
		}

		Map<String, InternetServerData> servers = ScriptReader.getServers();
		
		Login login = new Login(this, servers);
		boolean success = login.connectToServer(false, FM.config.getProperty("server_name"), FM.config.getProperty("server_pass"), FM.config.getProperty("server_account"));
		
		if (!success) {
			
			window.login(login);
			
		}
		
		lw.delete();

		
//		if (FM.config.getProperty("server_name") == null) {
//			server = "NONE";
//		}else {
//			serv = FM.config.getProperty("server_name");
//			pass = FM.config.getProperty("server_pass");
//
//			if (pass == null) server = "NONE";
//			else if (!servers.get(serv).equals(DMSLauncherPasswordEncoder.encode(pass))) {
//				server = "NONE";
//			} else {
//				server = serv;
//			}
//
//		}

//
//		if (!servers.keySet().contains(server)) {
//
//			this.window.login(login);
//			lw.delete();
//			return;
//
//		} else {
//			
//			login.connectToServer(server, pass, id);
//			
//		}
		
		

	}

	public boolean isGameInstalled() {
		return new File(FM.base, server + File.separator + "Game" + File.separator + "installationcomplete.txt").exists();
	}

	public static MainWindow getWindow() {
		return window;
	}

	public static void main(String[] args) {
		new DMSLauncher("");
	}

}
