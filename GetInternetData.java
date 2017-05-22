package javaIO;

import java.io.*;
import java.net.*;

// download data from https://google.com/ and write to console

public class GetInternetData {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		InputStream is = null;
		try {
			is = new URL("https://google.com/").openStream();
			
			Reader reader = new InputStreamReader(is, "UTF8");
			
			char []buff = new char [16];
			int count;
			while ((count = reader.read(buff)) != -1) {
				System.out.print(new String(buff, 0, count));
			}
			
		}
		catch (IOException e) {
			throw new IOException ("Problem while downloading data from Internet. ", e);
		}
		finally {
			if(is != null) {
				try {
				is.close();
				} catch (IOException ex) {
					//NOP
				}
			}
		}
		
	}

}
