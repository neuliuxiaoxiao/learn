package neu.reflect.factory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class TestReflect {

	public static void main(String[] args) throws Exception {

		InputStream inStream = TestReflect.class.getClassLoader().getResourceAsStream("Product.properties"); 
		Properties pro = new Properties();
		pro.load(inStream);
		String Classname = pro.getProperty("ProductA");

		// 1. 通过调用工厂类的静态方法（反射原理），从而动态创建产品类实例
		// 需传入完整的类名 & 包名
		Product concreteProduct = Factory.getInstance(Classname);
		// 2. 调用该产品类对象的方法，从而生产产品
		concreteProduct.show();
	}
}
