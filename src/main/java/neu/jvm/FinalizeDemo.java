package neu.jvm;

public class FinalizeDemo {
	public static FinalizeDemo Hook=null;
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("执行finalize方法");
		FinalizeDemo.Hook=this;
	}
	public static void main(String[] args) throws InterruptedException {
		Hook = new FinalizeDemo();
		//第一次拯救
		Hook = null;
		System.gc();
		Thread.sleep(500);
		if(Hook!=null){
			System.out.println("我还活着");
		}else{
			System.out.println("我已经死了");
		}
		//第二次 代码完全一样
		System.gc();
		Thread.sleep(500);
		if(Hook!=null){
			System.out.println("我还活着");
		}else{
			System.out.println("我已经死了");
		}
	}
}
