package com.blockhead7360.dms.launcher;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import com.blockhead7360.dms.launcher.GamePlay.UpdateMode;
import com.blockhead7360.dms.launcher.files.FM;
import com.blockhead7360.dms.launcher.internet.FilesUtility;
import com.blockhead7360.dms.launcher.internet.ScriptReader;
import com.blockhead7360.dms.launcher.utilities.CrashReport;
import com.blockhead7360.dms.launcher.utilities.ErrorCode;
import com.blockhead7360.dms.launcher.utilities.U;
import com.blockhead7360.dms.launcher.utilities.Unzip;
import com.blockhead7360.dms.launcher.views.Play;

public class GameInstallation {

	private Play playFrame;


	public GameInstallation(Play playFrame) {
		this.playFrame = playFrame;
	}

	private void installAndUnzipFile(String title, URL url, File zip, int retries) {
		
		playFrame.addToConsole("Downloading " + title + "...");

		if (zip.exists()) zip.delete();

		boolean success = false;
		int currentTry = 0;

		while (!success) {

			try {
				FilesUtility.downloadAndShowProgress(url, zip.getPath(), playFrame);
				success = true;
				playFrame.addToConsole("Download successful!");
			} catch (IOException e) {
				Play.console.setText(Play.console.getText().replaceAll("> ", ": "));
				currentTry++;
				
				if (currentTry > retries) {
					
					CrashReport.error(e, ErrorCode.INTERNET_DOWNLOAD_WITH_TRIES, true);
					return;
					
				}
				
				playFrame.addToConsole("Download failed. Trying again (" + currentTry + "/" + retries + ")...");
			}

		}

		playFrame.addToConsole("Unpacking...");

		try {
			Unzip.unzip(zip.getPath(), new File(FM.base, DMSLauncher.server + File.separator + "Game").getPath());
		} catch (IOException e) {
			CrashReport.error(e, ErrorCode.UNZIP, true);
		}

		playFrame.addToConsole("Removing package...\n");
		zip.delete();
		
	}

	public void install() {
		
		Play.progressBar1.setValue(0);
		Play.progressBar1.setMaximum(6);

		playFrame.disableButtons();
		DMSLauncher.getWindow().disableTabs();

		playFrame.updatetext.setText("Game is installing...");

		Play.progressBar1.setValue(1);

		playFrame.addToConsole("\nStarting installation...\n");

		File dir = new File(FM.base, File.separator + DMSLauncher.server + File.separator + "Game");
		if (!dir.exists()) {
			dir.mkdir();
		}

		File lib = new File(FM.base, DMSLauncher.server + File.separator + "DMS.zip");
		File mc = new File(FM.base, DMSLauncher.server + File.separator + "DMSL.zip");
		File extras = new File(FM.base, DMSLauncher.server + File.separator + "DMSF.zip");
		
		try {
			
			
			installAndUnzipFile("libraries", new URL(ScriptReader.libraries), lib, 4);
			Play.progressBar1.setValue(2);
			
			URL mcDownload = new URL((U.isMacOS() ? ScriptReader.minecraftMac : ScriptReader.minecraftWindows));
			installAndUnzipFile("Minecraft", mcDownload, mc, 4);
			Play.progressBar1.setValue(3);
			
			installAndUnzipFile("some other stuff", new URL(ScriptReader.extras), extras, 4);
			Play.progressBar1.setValue(4);
			
		} catch (MalformedURLException e1) {
			CrashReport.error(e1, ErrorCode.MALFORMED_URL, true);
			return;
		}

		try {
			Files.write((new File(dir, "installationcomplete.txt")).toPath(), "Hi! Do not delete this file.".getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			CrashReport.error(e, ErrorCode.FILE_WRITE, true);
		}

		playFrame.play.setText("Play Minecraft");

		GamePlay gp = new GamePlay(playFrame);
		gp.checkForUpdates(UpdateMode.INSTALL);

	}

//	private void part2() {
//
//		playFrame.addToConsole("Unpacking...");
//
//		try {
//			UnZipper.unzip(new File(basepath, "DMSL.zip").getPath(), new File(FM.base, DMSLauncher.server + File.separator + "Game").getPath());
//		} catch (IOException e) {
//			new CrashReport(e, ErrorCode2.UNZIP);
//		}
//
//		playFrame.addToConsole("Removing package...\n");
//		new File(basepath, "DMSL.zip").delete();
//
//		
//
//		part3();
//
//	}
//
//	public void part3() {
//
//		playFrame.addToConsole("Downloading...");
//
//		try {
//			FilesUtility.downloadAndShowProgress(new URL(ScriptReader.extras), new File(FM.base, DMSLauncher.server + File.separator + "DMSF.zip").getPath(), playFrame);
//		} catch (IOException e) {
//			new CrashReport(e, ErrorCode2.DOWNLOAD_WRITE);
//		}
//
//		playFrame.addToConsole("Unpacking...");
//
//		try {
//			UnZipper.unzip(new File(basepath, "DMSF.zip").getPath(), new File(FM.base, DMSLauncher.server + File.separator + "Game").getPath());
//		} catch (IOException e) {
//			new CrashReport(e, ErrorCode2.UNZIP);
//		}
//
//		playFrame.addToConsole("Removing package...\n");
//		new File(basepath, "DMSF.zip").delete();
//
//		try {
//			Files.write((new File(basepath, "Game" + File.separator + "installationcomplete.txt")).toPath(), "Hi! Do not delete this file.".getBytes(), StandardOpenOption.CREATE);
//		} catch (IOException e) {
//			new CrashReport(e);
//		}
//
//
//		part4();
//
//	}

	public void cont() {

		

	}

}
