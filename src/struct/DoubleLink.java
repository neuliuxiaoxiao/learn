package struct;

//双向链表
public class DoubleLink<T> {

	// 表头
	private DNode<T> mHead;
	// 节点个数
	private int mCount;

	// 结构体
	private class DNode<T> {
		public DNode prev;
		public DNode next;
		public T value;

		public DNode(T value, DNode prev, DNode next) {
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}

	// 构造函数
	public DoubleLink() {
		mHead = new DNode<T>(null, null, null);
		mHead.prev = mHead.next = mHead;
		mCount = 0;
	}

	public int size() {
		return mCount;
	}

	public boolean isEmpty() {
		return mCount == 0;
	}

	// 获取第index位置的节点
	private DNode<T> getNode(int index) {
		if (index < 0 || index >= mCount) {
			throw new IndexOutOfBoundsException();
		}
		if (index <= mCount / 2) {
			DNode<T> node = mHead.next;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			return node;
		}
		DNode<T> rnode = mHead.prev;
		int rindex = mCount - index - 1;
		for (int j = 0; j < rindex; j++) {
			rnode = rnode.next;
		}
		return rnode;
	}

	// 获取第index位置的节点的值
	public T get(int index) {
		return getNode(index).value;
	}

	// 获取第一个节点的值
	public T getFirst() {
		return getNode(0).value;
	}

	// 获取最后一个节点的值
	public T getLast() {
		return getNode(mCount - 1).value;
	}
	//将节点插入到第index位置之前
	public void insert(int index,T t){
		if(index==0){
			DNode<T> node = new DNode<T>(t,mHead,mHead.next);
			mHead.next.prev=node;//头节点的下一个节点的前一个节点变为node
			mHead.next=node;//头节点的下一个节点变为node
			mCount++;
			return ;
		}
		DNode<T> inode = getNode(index);
		DNode<T> tnode = new DNode<T>(t,inode.prev,inode);
		inode.prev.next=tnode;
		inode.next=tnode;
		mCount++;
		return ;
	}
	public void insertFirst(T t){
		insert(0,t);
	}
	//将节点追加到链表的末尾
	public void appendLast(T t){
		DNode<T> node = new DNode<T>(t,mHead.prev,mHead);
		mHead.prev.next=node;
		mHead.prev=node;
		mCount++;
	}
	//删除index位置的节点
	public void del(int index){
		DNode<T> inode = getNode(index);
		inode.prev.next=inode.next;
		inode.next.prev=inode.prev;
		inode=null;
		mCount--;
	}
	public void deleteFirst(){
		del(0);
	}
	public void deleteLast(){
		del(mCount-1);
	}
	
}
