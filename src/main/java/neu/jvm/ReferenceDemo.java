package neu.jvm;

/**
 * 
 * ���ü������㷨 ��ÿһ����������һ�����ü�������ÿ����һ���ط����� ��������ʱ�򣬼������ͼ�1����֮�෴��ÿ������ʧЧ��ʱ��ͼ�1��
 * �ŵ㣺ʵ�ּ򵥡����ܸߡ�
 * 
 * ȱ�㣺��������Ƶ������cpu���㡢������ռ�úܶ�λ�˷ѿռ䡢����Ҫ��ȱ�����޷����ѭ�����õ����⡣
 *
 */
public class ReferenceDemo {

	public Object instance = null;
	private static final int _1MB = 1024 * 1024;
	private byte[] bigSize = new byte[10 * _1MB];

	public static void main(String[] args) {
		System.out.println("��ʼ��" + Runtime.getRuntime().freeMemory() / (1024 * 1024));
		ReferenceDemo referenceDemo = new ReferenceDemo();
		ReferenceDemo referenceDemo2 = new ReferenceDemo();
		referenceDemo.instance=referenceDemo2;
		referenceDemo2.instance=referenceDemo;
		System.out.println("���У�" + Runtime.getRuntime().freeMemory() / (1024 * 1024));
		referenceDemo=null;
		referenceDemo=null;
		System.gc();
		System.out.println("������" + Runtime.getRuntime().freeMemory() / (1024 * 1024));

	}
}
