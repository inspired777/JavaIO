package javaIO;

import java.io.*;
import java.util.Arrays;
import java.util.zip.*;

public class WriteReadFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String fileName = "d:/myFile.txt";
		String name = "John";
		byte age = 45;
		int[] salary = { 200, 150, 70, 488, 931 };

		writeToFile(fileName, name, age, salary);

		readFromFile(fileName);

	}

	public static void writeToFile(String fileName, String name, byte age, int[] salary) throws IOException {

		OutputStream out = null;

		try {
			out = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(new File(fileName))));

			DataOutput dataOut = new DataOutputStream(out);

			dataOut.writeUTF(name);

			dataOut.writeByte(age);
			dataOut.writeInt(salary.length);

			for (int salaryElem : salary) {
				dataOut.writeInt(salaryElem);
			}

			out.flush();
			out.close();

		} catch (IOException e) {
			throw new IOException("Failed to write file " + fileName, e);
		} finally {
			closeAndFlushQuietly(out);
		}

	}

	public static void readFromFile(String fileName) throws IOException {

		GZIPInputStream in = null;

		try {
			in = new GZIPInputStream(new BufferedInputStream(new FileInputStream(new File(fileName))));

			DataInput dataIn = new DataInputStream(in);

			String name = dataIn.readUTF();
			byte age = dataIn.readByte();

			int[] salaryArr = new int[dataIn.readInt()];

			for (int i = 0; i < salaryArr.length; i++) {
				salaryArr[i] = dataIn.readInt();
			}

			System.out.println("name = " + name);
			System.out.println("age = " + age);
			System.out.println("salaryArr = " + Arrays.toString(salaryArr));

			in.close();
		} catch (IOException e) {
			throw new IOException("Failed to read file " + fileName, e);
		} finally {
			closeQuietly(in);
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
			} catch (IOException e) {
				// NOP
			} finally {
				try {
					out.close();
				} catch (IOException ignore) {
					/* NOP */}
			}
		}

	}

}
