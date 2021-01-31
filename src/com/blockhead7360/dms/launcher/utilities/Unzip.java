package com.blockhead7360.dms.launcher.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {
	
	public static void unzip(String zipFile, String destination) throws IOException {
		
		byte[] buffer = new byte[1024];
		
		@SuppressWarnings("resource")
		ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
		
		File destDir = new File(destination);
		
		ZipEntry entry;
		
		while ((entry = zis.getNextEntry()) != null) {
			
			File newFile = newFile(destDir, entry);
			
			if (entry.isDirectory()) {
				
				if (!newFile.isDirectory() && !newFile.mkdirs()) {
					throw new IOException("Failed to create directory: " + newFile);
				}
				
			} else {
				
				File parent = newFile.getParentFile();
				if (!parent.isDirectory() && !parent.mkdirs()) {
					throw new IOException("Failed to create directory: " + parent);
				}
				
				FileOutputStream fos = new FileOutputStream(newFile);
				
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				
			}
			
		}
		
		zis.closeEntry();
		zis.close();
		
	}
	
	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
	    File destFile = new File(destinationDir, zipEntry.getName());

	    String destDirPath = destinationDir.getCanonicalPath();
	    String destFilePath = destFile.getCanonicalPath();

	    if (!destFilePath.startsWith(destDirPath + File.separator)) {
	        throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
	    }

	    return destFile;
	}
	
	
}
