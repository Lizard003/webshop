package cn.yd.shop.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

// 测试代码：父类的引用,可以指向子类对象.
public class MainTest {

	public static void main(String[] args) throws Exception {
	
		// class文件的三种获取方法
		// 通过对象获取当前class文件的类型
		// Class clazz = Product.class;
		// Class clazz2 = new Product().getClass();
		Class clazz = Class.forName("cn.yd.shop.model.Product");
		Object t = clazz.newInstance();
		Method setId = clazz.getMethod("setId", Integer.class);
		setId.invoke(t, 12);
		Method getId = clazz.getMethod("getId");
		System.out.println(getId.invoke(t));
		
		Field name = clazz.getDeclaredField("name");
		name.setAccessible(true);
		name.set(t, "笔记本电脑");
		System.out.println(name.get(t));

	}
}
