package com.supinfo.javazipper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		ZipEntry entry = null;
		byte[] buffer = new byte[64];
		
		try {
			File zipFile = new File("zippedFile.zip");
			
			if(zipFile.exists())
				zipFile.delete();
			
			zipFile.createNewFile();
			
			fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(fos);
			
			File folder = new File("tozip");
			
			for(File file : folder.listFiles()) {				
				FileInputStream fis = new FileInputStream(file);
				
				entry = new ZipEntry(file.getName());
				zos.putNextEntry(entry);
				
				while(fis.read(buffer) > -1) {
					zos.write(buffer);
					
					buffer = new byte[buffer.length];
				}
				
				zos.flush();
				zos.closeEntry();
				
				if(fis != null) fis.close();
			}
			
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		} 
		finally {
			if(zos != null) zos.close();
			if(fos != null) fos.close();
		}
	}

}
