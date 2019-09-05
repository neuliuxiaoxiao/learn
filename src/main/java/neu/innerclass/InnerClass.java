package neu.innerclass;

public class InnerClass<T,E,F> {
	
	private T t;
	private E e;
	private F f;

	
	private String name;
	public class Name {
		private final static  int age=0;
	
	}
	public void get(){
		System.err.println(InnerClass.Name.age);
	}
	@Override
	public String toString() {
		return "InnerClass [t=" + t + ", e=" + e + ", f=" + f + ", name=" + name + "]";
	}
	
}
