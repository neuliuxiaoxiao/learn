package neu.jvm;

public class FinalizeDemo {
	public static FinalizeDemo Hook=null;
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("ִ��finalize����");
		FinalizeDemo.Hook=this;
	}
	public static void main(String[] args) throws InterruptedException {
		Hook = new FinalizeDemo();
		//��һ������
		Hook = null;
		System.gc();
		Thread.sleep(500);
		if(Hook!=null){
			System.out.println("�һ�����");
		}else{
			System.out.println("���Ѿ�����");
		}
		//�ڶ��� ������ȫһ��
		System.gc();
		Thread.sleep(500);
		if(Hook!=null){
			System.out.println("�һ�����");
		}else{
			System.out.println("���Ѿ�����");
		}
	}
}
