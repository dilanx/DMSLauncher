package com.blockhead7360.dms.launcher.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Properties;

import javax.swing.JFileChooser;

import com.blockhead7360.dms.launcher.DMSLauncher;
import com.blockhead7360.dms.launcher.utilities.CrashReport;
import com.blockhead7360.dms.launcher.utilities.ErrorCode;

public class FM {
	
	public static File base_old;
	public static File base;
	

	public static String config_title = "DMSLauncher Information Storage";
	
	public static Properties config;
		
	public static void saveConfig() {
		File settings = new File(base, "dmslauncher.properties");
		try {
			config.store(new FileOutputStream(settings), config_title);
		} catch (IOException e) {
			CrashReport.error(e, ErrorCode.SETTINGS_SAVE, true);
		}
	}
	
	public static void loadMainFiles() {
		
		File defaultDirectory = new JFileChooser().getFileSystemView().getDefaultDirectory();
		
		base = new File(defaultDirectory, "Documents" + File.separator + "DMSLauncher");
		base_old = new File(defaultDirectory, "Documents" + File.separator + "DMSUpdater");
		
		if (!base.exists()) {
			base.mkdirs();
		}
		
		config = new Properties();
		
		//create file
		File settings = new File(base, "dmslauncher.properties");
		if (!settings.exists()) {
			try {
				config.store(new FileOutputStream(settings), config_title);
			} catch (IOException e) {
				CrashReport.error(e, ErrorCode.SETTINGS_SAVE, true);
			}
		}
				
		//load config
		try {
			config.load(new FileInputStream(settings));
		} catch (FileNotFoundException e) {
			CrashReport.error(e, ErrorCode.SETTINGS_LOAD, true);
		} catch (IOException e) {
			CrashReport.error(e, ErrorCode.SETTINGS_LOAD, true);
		}
		
		//pre config
		config.setProperty("app_version", DMSLauncher.version);

	}
	
	public static void loadServerFiles() {
		
		//server folder
		File server_folder = new File(base, DMSLauncher.server);
		if (!server_folder.exists()) server_folder.mkdir();
		
		String content = new File(server_folder, "Game").getPath();

		//post config
		if (config.getProperty("game_dir") == null) {
			config.setProperty("game_dir", content);
			DMSLauncher.gameDir = content;
		}else {
			String game_dir = config.getProperty("game_dir");
			if (!game_dir.contains("DMSLauncher")) {
				DMSLauncher.gameDir = game_dir;
			}else {
				config.setProperty("game_dir", content);
				DMSLauncher.gameDir = content;
			}
		}
		
		//store config
		File settings = new File(base, "dmslauncher.properties");
		try {
			config.store(new FileOutputStream(settings), config_title);
		} catch (IOException e) {
			CrashReport.error(e, ErrorCode.SETTINGS_SAVE, true);

		}
	}
	
	
	
	
	
	
	

	public static void write(File file, String text) {
		try {
			Files.write(file.toPath(), text.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			CrashReport.error(e, ErrorCode.FILE_WRITE, true);
		}
	}

	public static boolean empty(File file) {
		List<String> list = contents(file);
		return (list == null || list.isEmpty() || list.get(0).trim().isEmpty());
	}

	public static void clear(File file) {

		try {
			PrintWriter writer = new PrintWriter(file);
			writer.close();
		} catch (FileNotFoundException e) {
			CrashReport.error(e, ErrorCode.FILE_WRITE, true);
		}
	}

	/*public static void delete(File f, String... except) {
		  if (f.isDirectory()) {
		    for (File c : f.listFiles()) {
		    	if (except != null) {
		    		if (except.toString().contains(f.getName())) continue;
		    	}
		    	delete(c);
		    }
		  }
		  f.delete();
	}*/
	
	public static void delete(File f) {
		Path directory = f.toPath();
		try {
			Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
			   @Override
			   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			       Files.delete(file);
			       return FileVisitResult.CONTINUE;
			   }

			   @Override
			   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			       Files.delete(dir);
			       return FileVisitResult.CONTINUE;
			   }
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> contents(File file){
		List<String> lines = null;
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			CrashReport.error(e, ErrorCode.FILE_READ, true);
		}

		return lines;
	}

}
