package struct;

public class ArrayQueue {

	private int[] mArray;
	private int mCount;
	public ArrayQueue(int sz){
		mArray = new int[sz];
		mCount = 0;
	}
	public void add(int val){
		mArray[mCount++]=val;
	}
	public int front(){
		return mArray[0];
	}
	public int pop(){
		int ret = mArray[0];
		mCount --;
		for(int i=1;i<=mCount;i++){
			mArray[i-1]=mArray[i];
		}
		return ret;
	}
	public int size(){
		return mCount;
	}
	public boolean isEmpty(){
		return size()==0;
	}

	public static void main(String[] args) {
		int tmp = 0;
		ArrayQueue queue = new ArrayQueue(12);
		queue.add(10);
		queue.add(20);
		queue.add(30);
		tmp = queue.pop();
		System.out.printf("tmp pop=%d\n", tmp);
		tmp = queue.front();
		System.out.printf("tmp front=%d\n", tmp);
		queue.add(40);
		System.out.printf("isEmpty()=%b\n", queue.isEmpty());
		System.out.printf("size()=%d\n", queue.size());
		while (!queue.isEmpty()) {
			System.out.printf("size()=%d\n", queue.pop());
		}
	}
}
