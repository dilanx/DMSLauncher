package com.blockhead7360.dms.launcher.internet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.blockhead7360.dms.launcher.views.Play;

public class FilesUtility {

	public static void download(URL url, String file) throws IOException{
		 
		InputStream in = url.openStream();
		FileOutputStream fos = new FileOutputStream(new File(file));

		int length = -1;
		byte[] buffer = new byte[4096];
		while ((length = in.read(buffer)) > -1) {
			fos.write(buffer, 0, length);
		}
		fos.close();
		in.close();
	}
	
	public static void downloadAndShowProgress(URL url, String file, Play playFrame) throws IOException {
		InputStream in = url.openStream();
		FileOutputStream fos = new FileOutputStream(new File(file));
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		long filesize = httpConnection.getContentLengthLong();
		long downloaded = 0;
		playFrame.addToConsole("> " + (downloaded / 1000000) + " MB / " + (filesize/1000000) + " MB");
		Play.progressBar2.setValue(0);
		Play.progressBar2.setMaximum((int) filesize/1000000);
		int length = -1;
		byte[] buffer = new byte[4096];
		while ((length = in.read(buffer)) > -1) {
			
			Play.progressBar2.setValue((int) (downloaded + length)/1000000);
			Play.console.setText(Play.console.getText().replaceAll("> " + (downloaded/1000000) + " MB", "> " + ((downloaded + length)/1000000) + " MB"));
			downloaded += length;

			fos.write(buffer, 0, length);
		}
		
		Play.progressBar2.setValue(Play.progressBar2.getMaximum());
		Play.console.setText(Play.console.getText().replaceAll("> " + (downloaded/1000000) + " MB", ": " + (downloaded/1000000) + " MB"));
		
		fos.close();
		in.close();
	}
	
}
