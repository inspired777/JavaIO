package javaIO;

import java.io.*;

public class CopyFiles {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileFromName = "d:/image0.jpg";

		String fileToName = "d:/image2.jpg";

		InputStream inFile = null;
		OutputStream outFile = null;
		try {
			inFile = new FileInputStream(fileFromName);
			outFile = new FileOutputStream(fileToName);
			long startTime = System.currentTimeMillis();
			copyImages(inFile, outFile);
			long stopTime = System.currentTimeMillis();
			System.out.println("Elapsed time = " + (stopTime - startTime));
		} catch (IOException e) {
			throw new IOException(
					"Exception while copying from file \'" + fileFromName + "\' to \'" + fileToName + "\'", e);
		} finally {
			closeQuietly(inFile);
			closeAndFlushQuietly(outFile);
		}
	}

	public static void copyImages(InputStream in, OutputStream out) throws IOException {
		byte[] buff = new byte[2];// 64 * 1024
		int count = 0;

		while ((count = in.read(buff)) != -1) {
			out.write(buff, 0, count);
		}

	}

	public static void closeQuietly(InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException ignore) {
				// NOP
			}
		}
	}

	public static void closeAndFlushQuietly(OutputStream out) {
		if (out != null) {
			try {
				out.flush();
			} catch (IOException ignore) {
				/* NOP */ }

			try {
				out.close();
			} catch (IOException ignore) {
				/* NOP */ }
		}
	}

}
