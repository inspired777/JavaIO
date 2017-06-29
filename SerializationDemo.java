package serialization;

import java.io.*;

public class SerializationDemo {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		{ // порождение сущности
			Object[] src = new Object[0];
			Object[] dst = copy(src);
			System.out.println(src != dst);
		}

		{ // глубокое копирование - автономный граф
			Object[] src = { new Object[0] };
			Object[] dst = copy(src);
			System.out.println(src != dst);
		}

		{ // не сохраняет граф объектов
			Object[] src0 = { new Object[1] };
			Object[] src1 = { src0 };
			src0[0] = src1;
			Object[] dst = copy(src0);
			System.out.println(((Object[]) dst[0])[0] == dst);
		}

	}

	public static <T extends Serializable> T copy(T obj) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream buff = new ByteArrayOutputStream();
		ObjectOutput objectOutput = new ObjectOutputStream(buff);
		objectOutput.writeObject(obj);
		objectOutput.flush();
		objectOutput.close();

		byte[] rawData = buff.toByteArray();
		ObjectInput objectInput = new ObjectInputStream(new ByteArrayInputStream(rawData));

		return (T) objectInput.readObject();
	}

}
