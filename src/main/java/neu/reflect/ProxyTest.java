package neu.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

public class ProxyTest {

	@Test
	public  void test1() throws Exception {
		// 1. 获取Student类的Class对象
		Class studentClass = Student.class;
		// 2. 通过Class对象创建Student类的对象
		Object mStudent = studentClass.newInstance();
		// 3. 通过Class对象获取Student类的name属性
		Field f = studentClass.getDeclaredField("name");
		 // 4. 设置私有访问权限
		f.setAccessible(true);
		// 5. 对新创建的Student对象设置name值
		f.set(mStudent, "Carson_Ho");
		// 6. 获取新创建Student对象的的name属性 & 输出
		System.out.println(f.get(mStudent));
	}
	@Test
	public void test2() throws Exception{
		// 1. 获取Student类的Class对象
		Class studentClass = Student.class;
		// 2.1 通过Class对象获取Constructor类对象，从而调用无参构造方法
        // 注：构造函数的调用实际上是在newInstance()，而不是在getConstructor()中调用
		Object mObj1 = studentClass.getConstructor().newInstance();
		// 2.2 通过Class对象获取Constructor类对象（传入参数类型），从而调用有参构造方法
		Object mObj2 = studentClass.getConstructor(String.class).newInstance("Carson");
	}
	@Test
	public void test3() throws Exception{
		 // 1. 获取Student类的Class对象
        Class studentClass = Student.class;
        // 2. 通过Class对象创建Student类的对象
        Object  mStudent = studentClass.newInstance();
        // 3.1 通过Class对象获取方法setName1（）的Method对象:需传入方法名
        // 因为该方法 = 无参，所以不需要传入参数
        Method msetName1 = studentClass.getMethod("setName1");
        // 通过Method对象调用setName1（）：需传入创建的实例
        msetName1.invoke(mStudent);
        // 3.2 通过Class对象获取方法setName2（）的Method对象:需传入方法名 & 参数类型
        Method msetName2 = studentClass.getMethod("setName2",String.class);
        // 通过Method对象调用setName2（）：需传入创建的实例 & 参数值
        msetName2.invoke(mStudent,"Carson_Ho");
	}
}
