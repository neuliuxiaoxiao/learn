package neu.reflect;

public class Student {
	private String name;
	public Student() {
        System.out.println("创建了一个Student实例--无参构造器");
    }
	// 有参构造函数
    public Student(String str) {
        System.out.println("调用了有参构造函数");
    }
 // 无参数方法
    public void setName1 (){
        System.out.println("调用了无参方法：setName1（）");
    }

    // 有参数方法
    public void setName2 (String str){
        System.out.println("调用了有参方法setName2（String str）:" + str);
    }
}
