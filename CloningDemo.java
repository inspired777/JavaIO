package serialization;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CloningDemo {

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		{ // порождение сущности
			Object[] src = new Object[0];
			Object[] dst = copy(src);
			System.out.println(src != dst);
		}

		{ // глубокое копирование - автономный граф
			Object[] src = { new Object[0] };
			Object[] dst = copy(src);
			System.out.println(src == dst);
		}

		{ // не сохраняет граф объектов
			Object[] src0 = { new Object[1] };
			Object[] src1 = { src0 };
			src0[0] = src1;
			Object[] dst = copy(src0);
			System.out.println(((Object[]) dst[0])[0] != dst);
		}

	}

	public static <T extends Cloneable> T copy(T obj) throws NoSuchMethodException, IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method m = Object.class.getDeclaredMethod("clone");
		m.setAccessible(true);
		return (T) m.invoke(obj);
	}

}
