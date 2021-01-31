package com.blockhead7360.dms.launcher.internet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;

import com.blockhead7360.dms.launcher.utilities.CrashReport;
import com.blockhead7360.dms.launcher.utilities.ErrorCode;
import com.blockhead7360.dms.launcher.views.Play;

public class FTP {

	public static FTPClient client;
	
	public static void disconnect() {
		try {
			client.disconnect();
		} catch (IOException e) {
			
			CrashReport.error(e, ErrorCode.FTP_DISCONNECTION, false);
			
		}
	}
	
	public static void download(String src, File dest, int retries, Play playFrame) {
		
		boolean success = false;
		int currentTry = 0;
		
		while (!success) {
			
			try {
				OutputStream os = new BufferedOutputStream(new FileOutputStream(dest));
				InputStream is = FTP.client.retrieveFileStream(src);
				byte[] bytes = new byte[4096];
				int byteread = -1;
				while ((byteread = is.read(bytes)) != -1){
					os.write(bytes, 0, byteread);
				}
				success = FTP.client.completePendingCommand();
				
				if (!success) {
					
					currentTry++;
					
					if (currentTry > retries) {
						
						CrashReport.error(null, ErrorCode.FTP_DOWNLOAD_WITH_TRIES, true);
						os.close();
						return;
						
					}
					
					playFrame.addToConsole("Download failed. Trying again (" + currentTry + "/" + retries + ")...");
					
				}
				os.close();
				is.close();

			} catch (FTPConnectionClosedException e) {
				
				playFrame.addToConsole("For some reason, the connection closed. Reconnecting now...\n\n");
				
				connect(playFrame, 4);
				
			} catch (IOException e) {
				
				currentTry++;
				
				if (currentTry > retries) {
					
					CrashReport.error(null, ErrorCode.FTP_DOWNLOAD_WITH_TRIES, true);
					return;
					
				}
				
				playFrame.addToConsole("Download failed. Trying again (" + currentTry + "/" + retries + ")...");
				
			}
			
		}
		
		
		
	}
	
	public static void connect(Play playFrame, int retries) {
		
		boolean success = false;
		int currentTry = 0;

		while (!success) {

			try {
				
				Play.progressBar2.setValue(0);
				Play.progressBar2.setMaximum(3);
				
				playFrame.addToConsole("\n\nConnecting to server...");
				Play.progressBar2.setValue(1);
				client = new FTPClient();
				client.connect(ScriptReader.host, ScriptReader.port);
				playFrame.addToConsole("Logging in to server...");
				Play.progressBar2.setValue(2);
				boolean loggedIn = client.login(ScriptReader.user, ScriptReader.pass);
				if (!loggedIn) {
					
					currentTry++;
					
					if (currentTry > retries) {
						
						CrashReport.error(null, ErrorCode.FTP_CONNECTION_WITH_TRIES, true);
						return;
						
					}
					
					playFrame.addToConsole("Connection failed. Trying again (" + currentTry + "/" + retries + ")...");
					continue;
					
				}
				playFrame.addToConsole("Entering server...");
				Play.progressBar2.setValue(3);
				client.enterLocalPassiveMode();
				client.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
				
				client.changeWorkingDirectory("/DMSLauncher");
				
				success = true;
				
				playFrame.addToConsole("Connected successfully!\n\n");
				
				
			} catch (IOException ex) {
				
				currentTry++;
				
				if (currentTry > retries) {
					
					CrashReport.error(ex, ErrorCode.FTP_CONNECTION_WITH_TRIES, true);
					return;
					
				}
				
				playFrame.addToConsole("Connection failed. Trying again (" + currentTry + "/" + retries + ")...");
			}

		}
		
		
	}
	
}
