package struct;

import java.lang.reflect.Array;

public class GeneralArrayStack<T> {

	private static final int DEFAULT_SIZE=12;
	private T[] mArray;
	private int count;
	public GeneralArrayStack(Class<T> type){
		this(type,DEFAULT_SIZE);
	}
	public GeneralArrayStack(Class<T> type,int size){
		//不能直接使用mArray =new T[DEFAULT_SIZE]
		mArray = (T[])Array.newInstance(type, size);
		count = 0;
	}
	public void push(T val){
		mArray[count++]=val;
	}
	public T peek(){
		return mArray[count-1];
	}
	public T pop(){
		T ret = mArray[count-1];
		count--;
		return ret;
	}
	public int size(){
		return count;
	}
	public boolean isEmpty(){
		return size()==0;
	}
	public void PrintArrayStack(){
		if(isEmpty()){
			System.out.printf("stack is Empty\n");
		}
		System.out.printf("stack size()=%d\n",size());
		int i = size()-1;
		while(i>=0){
			System.out.println(mArray[i]);
			i--;
		}
	}
	public static void main(String[] args) {
		String tmp;
		GeneralArrayStack<String> astack = new GeneralArrayStack<String>(String.class);
		 astack.push("10");
		 astack.push("20");
		 astack.push("30");
		 tmp = astack.pop();
		 System.out.println("tmp pop="+tmp);
		 
		 tmp = astack.peek();
		 System.out.println("tmp peek="+tmp);
		 astack.push("40");
		 astack.PrintArrayStack();
	}
}
