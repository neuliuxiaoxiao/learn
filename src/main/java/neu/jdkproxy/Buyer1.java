package neu.jdkproxy;

//小成，真正的想买Mac的对象 = 目标对象 = 被代理的对象
//实现抽象目标对象的接口
public class Buyer1 implements Subject {

	@Override
    public void buybuybuy() {
		System.out.println("小成要买Mac");
	}

}
