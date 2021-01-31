package com.blockhead7360.dms.launcher.external;

public class UnZipper {
	
	
//	/* I DID NOT MAKE THIS I GOT THIS OFF THE INTERNET */
//	
//	
//	/**
//	 * Size of the buffer to read/write data
//	 */
//	private static final int BUFFER_SIZE = 4096;
//	
//	public static void unzip(String zipFilePath, String destDirx) throws IOException{
//		
//		String fileZip = zipFilePath;
//        File destDir = new File(destDirx);
//        byte[] buffer = new byte[1024];
//        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
//        ZipEntry zipEntry = zis.getNextEntry();
//        
//        while (zipEntry != null) {
//		     File newFile = newFile(destDir, zipEntry);
//		     if (zipEntry.isDirectory()) {
//		         if (!newFile.isDirectory() && !newFile.mkdirs()) {
//		             throw new IOException("Failed to create directory " + newFile);
//		         }
//		     } else {
//		         // fix for Windows-created archives
//		         File parent = newFile.getParentFile();
//		         if (!parent.isDirectory() && !parent.mkdirs()) {
//		             throw new IOException("Failed to create directory " + parent);
//		         }
//		         
//		         // write file content
//		         FileOutputStream fos = new FileOutputStream(newFile);
//		         int len;
//		         while ((len = zis.read(buffer)) > 0) {
//		             fos.write(buffer, 0, len);
//		         }
//		         fos.close();
//		     }
//		 zipEntry = zis.getNextEntry();
//		}
//        
//        zis.closeEntry();
//        zis.close();
//		
//		
//		
//		
//	}
//	
//	
//	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
//	    File destFile = new File(destinationDir, zipEntry.getName());
//
//	    String destDirPath = destinationDir.getCanonicalPath();
//	    String destFilePath = destFile.getCanonicalPath();
//
//	    if (!destFilePath.startsWith(destDirPath + File.separator)) {
//	        throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
//	    }
//
//	    return destFile;
//	}
//	
//	
//	/**
//	 * Extracts a zip file specified by the zipFilePath to a directory specified by
//	 * destDirectory (will be created if does not exists)
//	 * @param zipFilePath
//	 * @param destDirectory
//	 * @throws IOException
//	 */
////	public static void unzip(String zipFilePath, String destDirectory) throws IOException {
////		File destDir = new File(destDirectory);
////		if (!destDir.exists()) {
////			destDir.mkdir();
////		}
////		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
////		ZipEntry entry = zipIn.getNextEntry();
////		// iterates over entries in the zip file
////		while (entry != null) {
////			String filePath = destDirectory + File.separator + entry.getName();
////			if (!entry.isDirectory()) {
////				// if the entry is a file, extracts it
////				extractFile(zipIn, filePath);
////			} else {
////				// if the entry is a directory, make the directory
////				File dir = new File(filePath);
////				dir.mkdir();
////			}
////			zipIn.closeEntry();
////			entry = zipIn.getNextEntry();
////		}
////		zipIn.close();
////	}
////	/**
////	 * Extracts a zip entry (file entry)
////	 * @param zipIn
////	 * @param filePath
////	 * @throws IOException
////	 */
////	private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
////		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
////		byte[] bytesIn = new byte[BUFFER_SIZE];
////		int read = 0;
////		while ((read = zipIn.read(bytesIn)) != -1) {
////			bos.write(bytesIn, 0, read);
////		}
////		bos.close();
////	}
}
