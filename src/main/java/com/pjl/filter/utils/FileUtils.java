package com.pjl.filter.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class FileUtils {

	public static String copyFile(Path path) {

		FileInputStream instream = null;
		FileOutputStream outstream = null;
		String fileNamePath = "";

		try {
			String fileName = path.getFileName().toString();
			String newFileName = fileName.substring(0, fileName.indexOf(".")) + "Output"
					+ fileName.substring(fileName.indexOf("."));

			String absolutePath = path.toFile().getAbsolutePath();
			String filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));

			fileNamePath = filePath + File.separator + newFileName;

			File infile = new File(path.toString());
			File outfile = new File(fileNamePath);

			instream = new FileInputStream(infile);
			outstream = new FileOutputStream(outfile);

			byte[] buffer = new byte[1024];

			int length;
			/*
			 * copying the contents from input stream to output stream using
			 * read and write methods
			 */
			while ((length = instream.read(buffer)) > 0) {
				outstream.write(buffer, 0, length);
			}

			// Closing the input/output file streams
			instream.close();
			outstream.close();

			System.out.println("File copied successfully!!");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return fileNamePath;
	}
}
