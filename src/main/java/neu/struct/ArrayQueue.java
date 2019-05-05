package neu.struct;

import java.lang.reflect.Array;

//用数组实现的队列  数组实现的队列，能存储任意类型的数据。
public class ArrayQueue<T> {

	private T[] mArray;
	private int mCount;
	public ArrayQueue(Class<T> type,int sz){
		mArray =(T[])Array.newInstance(type, sz);
		mCount = 0;
	}
	public void add(T val){
		mArray[mCount++]=val;
	}
	public T front(){
		return mArray[0];
	}
	public T pop(){
		T ret = mArray[0];
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
		Integer tmp = 0;
		ArrayQueue queue = new ArrayQueue(Integer.class,12);
		queue.add(10);
		queue.add(20);
		queue.add(30);
		tmp = (Integer) queue.pop();
		System.out.printf("tmp pop=%d\n", tmp);
		tmp = (Integer) queue.front();
		System.out.printf("tmp front=%d\n", tmp);
		queue.add(40);
		System.out.printf("isEmpty()=%b\n", queue.isEmpty());
		System.out.printf("size()=%d\n", queue.size());
		while (!queue.isEmpty()) {
			System.out.printf("size()=%d\n", queue.pop());
		}
	}
}
