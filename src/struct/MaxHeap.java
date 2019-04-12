package struct;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap <T extends Comparable<T>>{

	private List<T> mHeap;//队列 实际上是动态数组arrayList的实例
	public MaxHeap(){
		this.mHeap=new ArrayList<T>();
	}
	/*
	 * 最大堆的向下调整算法
	 *
	 * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
	 *
	 * 参数说明： start -- 被下调节点的起始位置(一般为0，表示从第1个开始) 
	 * end -- 截至范围(一般为数组中最后一个元素的索引)
	 */
	private void filterdown(int start,int end){
		int current  = start;//当前节点的位置
		int left = 2*current+1;//左孩子的位置
		T tmp = mHeap.get(current);//当前节点的大小
		while(left<=end){
			int cmp = mHeap.get(left).compareTo(mHeap.get(left+1));
			//left是左孩子  left+1 是右孩子
			if(left<end&&cmp<0){
				left++;//左右两个孩子中选择较大者，即mHeap[left+1]
			}
			cmp=tmp.compareTo(mHeap.get(left));
			if(cmp>=0)
				break;
			else{
				//交换较大子节点与current
				mHeap.set(current, mHeap.get(left));
				current=left;
				//然后继续找左孩子节点 继续循环
				left=2*left+1;
			}
		}
		mHeap.set(current, tmp);
	}
	
	/**
	 *删除最大堆中的data
	 *返回值：0成功 -1失败 
	 */
	public int remove(T data){
		//如果堆已空，返回-1
		if(mHeap.isEmpty()==true) return -1;
		//获取data在数组中的索引
		int index = mHeap.indexOf(data);
		if(index==-1) return -1;//代表不存在该元素
		int size = mHeap.size();
		mHeap.set(index, mHeap.get(size-1));//用最后的元素填补
		mHeap.remove(size-1);//删除最后的元素
		if(mHeap.size()>1)
			filterdown(index,mHeap.size()-1);//从index号位置开始自上向下调整最大堆
		return 0;
		
	}
	
	/*
	 * 最大堆的向上调整算法(从start开始向上直到0，调整堆)
	 *
	 * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
	 *
	 * 参数说明： start -- 被上调节点的起始位置(一般为数组中最后一个元素的索引)
	 */
	private void filterup(int start){
		int current = start;//当前节点的位置
		int p = (current-1)/2;//父节点的位置
		T tmp = mHeap.get(current);//当前节点的大小
		while(current>0){//实现是已序号为0开始的 ，故大于0 代表的是已经挪到头了
			int cmp = mHeap.get(p).compareTo(tmp);
			if(cmp>0){
				break;
			}else{
				mHeap.set(current, mHeap.get(p));//这里就将第一次比较过后的父节点放到了current
				current=p;//将父节点的位置替换为current
				p=(p-1)/2;//寻找前父节点的父节点，因为current变成了父节点 ，故需要找到原父节点的父节点进行比较
			}
		}
		mHeap.set(current, tmp);
		
	}
	
	/**
	 * 将data插入到二叉堆中
	 */
	public void insert(T data){
		int size = mHeap.size();
		mHeap.add(data);//将数组插在表尾
		filterup(size); //向上调整堆
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mHeap.size(); i++)
			sb.append(mHeap.get(i) + " ");

		return sb.toString();
	}
		
}
