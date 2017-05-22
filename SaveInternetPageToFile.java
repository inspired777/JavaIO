package javaIO;

import java.io.*;
import java.net.*;

import javax.naming.NoPermissionException;

public class SaveInternetPageToFile {
	// Download page from https://lenta.ru/ and save to 2 files
	// If exception is catched, the files should be deleted
	// Buffer size = 64 kB

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		InputStream in = null;

		String fileName0 = "d:/tmp/tmp0.html";
		String fileName1 = "d:/tmp/tmp1.html";

		OutputStream out0 = null;
		OutputStream out1 = null;
		DataOutput dataOut0;
		DataOutput dataOut1;

		try {
			
			in = new URL("https://lenta.ru/").openStream();

			Reader reader = new InputStreamReader(in, "UTF8");// "windows-1251");

			out0 = new BufferedOutputStream(new FileOutputStream(fileName0));
			dataOut0 = new DataOutputStream(out0);

			out1 = new BufferedOutputStream(new FileOutputStream(fileName1));
			dataOut1 = new DataOutputStream(out1);

			char[] buff = new char[64 * 1024];// 64 * 1024
			int count;
			while ((count = reader.read(buff)) != -1) {
				System.out.println(new String(buff, 0, count));

				dataOut0.writeChars(new String(buff, 0, count));
				dataOut1.writeChars(new String(buff, 0, count));

			}

			out0.flush();
			out1.flush();

		} catch (IOException e) {
			boolean ok = new File(fileName0).delete();
			if (!ok) {
				// NOP
				// throw new IOException("Exception deleting file "+ fileName0);
			}
			ok = new File(fileName1).delete();
			if (!ok) {
				// NOP
				// throw new IOException("Exception deleting file "+ fileName1);
			}
			throw new IOException("Problem downloading from the Internet", e);
		} finally {
			in.close();
			out0.close();
			out1.close();

		}

	}

	public static void saveToFile(int in, String fileName) throws IOException {
		OutputStream out = null;
		try {

			out = new BufferedOutputStream(new FileOutputStream(fileName));

			DataOutput dataOut = new DataOutputStream(out);
			dataOut.write(in);
			out.flush();

		} catch (IOException e) {
			new File(fileName).delete();
			out.close();
			throw new IOException("Problem saving to file \'" + fileName + "\'", e);
		} finally {

			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// NOP
					//System.out.print("Exception: " + e.toString());
				}
			}

		}
	}

}
