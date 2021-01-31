package com.blockhead7360.dms.launcher.utilities;

public class ErrorCode {

	public static final String
	ERROR = "",
	OPEN_LINK = "Unable to open the external link in your internet browser.",
	OPEN_FILE = "Unable to open the external file.",
	LOOK_AND_FEEL = "Unable to set the default look and feel (you don't get this error on Macs, how sad).",
	UNZIP = "Unable to unzip the files and save them properly.",
	INTERNET_READ = "Couldn't access the internet for some reason.\nThe website being read might be down, but you should also probably check your internet connection.",
	INTERNET_DOWNLOAD_WITH_TRIES = "Tried several times to download the files but still failed. (from: internet)",
	INTERNET_DOWNLOAD = "Failed to download the file. (from: internet)",
	FTP_CONNECTION_WITH_TRIES = "Tried several times to connect to the FTP server but sadly could not.",
	FTP_DOWNLOAD_WITH_TRIES = "Tried several times to download the files but still failed. (from: FTP server)",
	FTP_DISCONNECTION = "Tried to disconnect from the FTP server but it wouldn't let us lol? Idek.",
	MALFORMED_URL = "The URL for this download isn't good.",
	FILE_WRITE = "Tried to write to a file but something unexpected stopped that from happening.",
	FILE_READ = "Tried to read a file but don't know how to read (jk there was an error).",
	UPDATE_CHECK = "Failed to check for updates (not good).",
	RAM_FIND = "Failed to find current RAM allocation (something might be wrong with the launcher profiles file).",
	RAM = "RAM allocation modification failed (oh no).",
	SETTINGS_SAVE = "Couldn't save the DMSLauncher properties (sad and not good).\nYou may have to log in again next time you open the launcher.",
	SETTINGS_LOAD = "Couldn't load the DMSLauncher properties. You didn't edit that file, did you?",
	DS_PARSE = "Failed to parse DilanScript (admin error).";
	
	

}
