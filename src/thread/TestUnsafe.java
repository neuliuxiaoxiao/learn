package thread;

import java.lang.reflect.Field;
//如何导入
//https://blog.csdn.net/csdlwzy/article/details/84330598
import sun.misc.Unsafe;  
public class TestUnsafe {

	static final Unsafe unsafe;
	static final long stateOffset;
	private volatile long state=0;
	
	static{
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			unsafe=(Unsafe)field.get(null);
			stateOffset = unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			throw new Error(e);
		}
	}
	
	public static void main(String[] args) {
		TestUnsafe test = new TestUnsafe();
		Boolean success = unsafe.compareAndSwapInt(test,stateOffset,0,1);
		System.out.println(success);
	}
}
