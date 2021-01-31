package com.blockhead7360.dms.launcher.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.blockhead7360.dms.launcher.files.FM;

public class CrashReport {
	
	public static void validationFail(String failMessage) {
		
		File dir = new File(FM.base, "reports");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		SimpleDateFormat format = new SimpleDateFormat("hh-mm-ss-a MM-dd-yyyy");
		String timestamp = format.format(new Date());
		
		File file = new File(dir, "valid-fail " + timestamp + ".txt");
		
		String message = "Sup lmao. DMSLauncher validated your mod files and found problems with them. Here's what they are:\nTIME: " + timestamp + "\n"
				+ failMessage;
		
		try {
			
			Files.write(file.toPath(), message.getBytes(), StandardOpenOption.CREATE);
			
		} catch (IOException e) {
			
		}
		
	}

	public static void error(Exception ex, String description, boolean shutdown) {

		File dir = new File(FM.base, "reports");
		if (!dir.exists()){
			dir.mkdirs();
		}

		String stack = "UNABLE TO SAVE! (Contact Dilan)";
		String severe = (shutdown ? "High (will shut down when this view is closed)" : "Low (no shutdown required)");

		SimpleDateFormat format = new SimpleDateFormat("hh-mm-ss-a MM-dd-yyyy");
		String timestamp = format.format(new Date());

		if (ex == null) {

			stack = "None to save";

		} else {

			File fx = new File(dir, "crash " + timestamp + ".txt");
			PrintStream ps = null;
			try {
				ps = new PrintStream(fx);
				stack = "Saved to " + fx.getPath();
				ex.printStackTrace(ps);
				ps.close();
				
			} catch (FileNotFoundException e1x) {

			}

		}



		U.eD("ERROR OH NO ERROR OH NO NO NO", "The DMSLauncher encountered a problem. It probably wasn't anything you did don't worry.\n\n"
				+ "Error description:\n" + description + "\n\nStack trace: " + stack + "\nSeverity: " + severe + "\n\nYou should probably tell Dilan about this.", null);

		if (shutdown) System.exit(0);

		return;

	}

//	public CrashReport(Exception e) {
//
//	}
//
//	public CrashReport(Exception e, ErrorCode2 ex) {
//
//	}

	//	private String timestamp;
	//	private Exception exception_exact;
	//	private String exception;
	//	
	//	public CrashReport(Exception ex) {
	//		new CrashReport(ex, ErrorCode.DEFAULT);
	//	}
	//	
	//	public CrashReport(Exception ex, ErrorCode e) {
	//		String desc = null;
	//		
	//		if (e == ErrorCode.DEFAULT) desc = "An error has occurred.";
	//		if (e == ErrorCode.LINK) desc = "Unable to open the external link.";
	//		if (e == ErrorCode.MAIL_LINK) desc = "Unable to open the external mail link.";
	//		if (e == ErrorCode.RAM_SET) desc = "Unable to set the RAM.";
	//		if (e == ErrorCode.DOWNLOAD_WRITE) desc = "Unable to download the required file(s).";
	//		if (e == ErrorCode.WRITE) desc = "Unable to manage the necessary file(s) on your computer.";
	//		if (e == ErrorCode.FILE_LAUNCH) desc = "Unable to open the necessary file.";
	//		if (e == ErrorCode.REMOTE_CONNECTION) desc = "Unable to connect to the server.";
	//		if (e == ErrorCode.UPDATE_CHECK) desc = "Unable to check for updates.";
	//		if (e == ErrorCode.UNZIP) desc = "Unable to unzip the necessary file.";
	//		if (e == ErrorCode.CREATE_FILE) desc = "Unable to create the new file(s) on your computer.";
	//		if (e == ErrorCode.READ) desc = "Unable to read the necessary file(s) on your computer.";
	//		if (e == ErrorCode.READ_INTERNET_PAGE) desc = "Unable to read the required internet page(s).";
	//		if (e == ErrorCode.SETTINGS_LOAD) desc = "Unable to load DMSLauncher settings from file.";
	//		if (e == ErrorCode.SETTINGS_STORE) desc = "Unable to save DMSLauncher settings to file.";
	//		
	//		new CrashReport(ex, desc);
	//	}
	//	
	//	public CrashReport(Exception ex, String description) {
	//		
	//		ex.printStackTrace();
	//		
	//		String desc  = description;
	//		
	//		if (ex.getMessage().contains("Permission denied: recv failed")) {
	//			U.eD("Oh shoot.", "Heyyyy\n\nSo it looks like your version of Windows is so old that it has a really old bug preventing you from continuing.\n"
	//					+ "Idk what to do just ask me (Dilan).\n\nBtw you\'re welcome for actually catching this error in the app lol I\'m so cool.", null);
	//			return;
	//		}
	//		
	//		SimpleDateFormat format = new SimpleDateFormat("hh-mm-ss-a MM-dd-yyyy");
	//		timestamp = format.format(new Date());
	//		
	//		this.exception_exact = ex;
	//		
	//		StringWriter swx = new StringWriter();
	//		PrintWriter pwx = new PrintWriter(swx);
	//		ex.printStackTrace(pwx);
	//		pwx.close();
	//		this.exception = swx.toString();
	//		try {
	//			swx.close();
	//		} catch (IOException e) {
	//			
	//		}
	//		
	//		if (exception.contains("UnknownHostException") || exception.contains("MalformedURLException")) {
	//			
	//			desc = "Unable to connect to the internet.\nYou\'ll have to check your internet connection and try again.";
	//		}
	//		
	//		if (desc == null) desc = "An error has occurred.";
	//		int response = U.eD("DMSLauncher Error", desc + "\n\n\nWould you like to report this error?", "Report", "Report", "Save to File", "Close");
	//		
	//		
	//		if (response == JOptionPane.YES_OPTION) {
	//			String name = JOptionPane.showInputDialog(null, "Please enter your name", "Report Error", JOptionPane.QUESTION_MESSAGE);
	//			
	//			return;
	//		}
	//		if (response == JOptionPane.NO_OPTION) {
	//			file(false);
	//			return;
	//		}
	//		
	//		System.exit(0);
	//		
	//		return;
	//	}
	//	
	//	public void file(boolean offline) {
	//		File dir = new File(FM.base, "stacktrace");
	//		if (!dir.exists()){
	//			dir.mkdirs();
	//		}
	//
	//		File fx = new File(dir, timestamp + ".txt");
	//		PrintStream ps = null;
	//		try {
	//			ps = new PrintStream(fx);
	//		} catch (FileNotFoundException e1x) {
	//			U.eD("Unable to save the error", "There was a problem saving the error to a file.\n\nYou can create a ticket at http://gh.blockhead7360.com/DMSLauncher/issues", null);
	//			return;
	//		}
	//		
	//		exception_exact.printStackTrace(ps);
	//		ps.close();
	//		if (offline) U.iD("Stack trace saved to file", "An error has occurred. The stack trace has been saved to \n" + fx.getPath(), null);
	//		else U.iD("Stack trace saved to file", "The stack trace has been saved to \n" + fx.getPath(), null);
	//		return;
	//	}

}
