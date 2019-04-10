package struct;

import java.util.Stack;

//用栈实现队列
public class StackList<T> {

	private Stack<T> mIn = null;
	private Stack<T> mOut=null;
	private int mCount=0;
	public StackList(){
		mIn = new Stack<T>();
		mOut = new Stack<T>();
		mCount=0;
	}
	private void add(T t){
		while(!mOut.empty()){
			mIn.push(mOut.pop());
		}
		mIn.push(t);
		mCount++;
	} 
	public T get(){
		while(!mIn.empty()){
			mOut.push(mIn.pop());
		}
		mCount --;
		return mOut.pop();
	}
	private int size(){
		return mCount;
	}
	private boolean isEmpty(){
		return mCount==0;
	}

	public static void main(String[] args) {
		StackList slist = new StackList();
		slist.add(10);
		slist.add(11);
		slist.add(12);
		slist.add(13);
		slist.add(14);
		slist.get();
		slist.add(30);
		System.out.printf("isEmpty()=%b\n", slist.isEmpty());
		System.out.printf("size()=%d\n", slist.size());
		while (!slist.isEmpty()) {
			System.out.printf("%d\n", slist.get());
		}
	}
}
