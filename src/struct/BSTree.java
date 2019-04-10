package struct;

public class BSTree<T extends Comparable<T>> {
	private BSTNode<T> mRoot;//根节点
	public class BSTNode<T extends Comparable<T>>{
		T key;
		BSTNode<T> left;
		BSTNode<T> right;
		BSTNode<T> parent;
		public BSTNode(T key,BSTNode<T> parent,BSTNode<T> left,BSTNode<T> right){
			this.key=key;
			this.left=left;
			this.right=right;
			this.parent=parent;
		}
	}
	
	//前序遍历
	private void preOrder(BSTNode<T> tree){
		if(tree!=null){
			System.out.print(tree.key+" ");
			preOrder(tree.left);
			preOrder(tree.right);
		}
	}
	public void preOrder() {
	    preOrder(mRoot);
	}
	//中序遍历
	private void inOrder(BSTNode<T> tree) {
	    if(tree != null) {
	        inOrder(tree.left);
	        System.out.print(tree.key+" ");
	        inOrder(tree.right);
	    }
	}
	public void inOrder() {
	    inOrder(mRoot);
	}
	//后序遍历
	private void postOrder(BSTNode<T> tree) {
	    if(tree != null)
	    {
	        postOrder(tree.left);
	        postOrder(tree.right);
	        System.out.print(tree.key+" ");
	    }
	}
	public void postOrder() {
	    postOrder(mRoot);
	}
	//递归查找二叉树X的键值为key的节点
	private BSTNode<T> search(BSTNode<T> x,T key){
		if(x==null)
			return x;
		int cmp = key.compareTo(x.key);
		if(cmp<0){
			return search(x.left,key);
		}else if (cmp>0){
			return search(x.right,key);
		}else{
			return x;
		}
	}
	public BSTNode<T> search(T key){
		return search(mRoot,key);
	}
	
	//非递归查找节点
	private BSTNode<T> iterativeSearch(BSTNode<T> x,T key){
		while(x!=null){
			int cmp = key.compareTo(x.key);
			if(cmp<0)
				x=x.left;
			else if(cmp>0)
				x=x.right;
			else 
				return x;
		}
		return x;
	}
	public BSTNode<T> iterativeSearch(T key){
		return iterativeSearch(mRoot, key);
	}
	//查找最大节点
	private BSTNode<T> maximun(BSTNode<T> tree){
		if(tree==null)
			return null;
		while(tree.right!=null){
			tree=tree.right;
		}
		return tree;
	}
	public T maximum(){
		BSTNode<T> p = maximun(mRoot);
		if(p!=null){
			return p.key;
		}
		return null;
	}

	// 查找最小节点
	private BSTNode<T> minimun(BSTNode<T> tree) {
		if (tree == null)
			return null;
		while (tree.left != null) {
			tree = tree.left;
		}
		return tree;
	}

	public T minimum() {
		BSTNode<T> p = minimun(mRoot);
		if (p != null) {
			return p.key;
		}
		return null;
	}
	//前驱节点
	public BSTNode<T> predecessor(BSTNode<T> x){
		// 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
		if(x.left!=null)
			return maximun(x.left);
		// 如果x没有左孩子。则x有以下两种可能：
	    // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
	    // (01) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
		BSTNode<T> y = x.parent;
		while((y!=null)&&(x==y.left)){
			//左节点
			x=y;
			y=y.parent;
		}
		return y;
	}	
	//后继节点
	public BSTNode<T> successor(BSTNode<T> x) {
	    // 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
	    if (x.right != null)
	        return minimun(x.right);

	    // 如果x没有右孩子。则x有以下两种可能：
	    // (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
	    // (02) x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
	    BSTNode<T> y = x.parent;
	    while ((y!=null) && (x==y.right)) {
	        x = y;
	        y = y.parent;
	    }

	    return y;
	}
	/* 
	 * 将结点插入到二叉树中
	 *
	 * 参数说明：
	 *     tree 二叉树的
	 *     z 插入的结点
	 */
	//插入节点
	private void insert(BSTree<T> bst,BSTNode<T> z){
		int cmp ;
		BSTNode<T> y =null;
		BSTNode<T> x = bst.mRoot;
		while(x!=null){
			y=x;
			cmp = z.key.compareTo(x.key);
			//此处是可以插入相同节点的
			if(cmp<0){
				x=x.left;
			}else{
				x=x.right;
			}
		}
		z.parent=y;
		if(y==null){
			bst.mRoot=z;
		}else{
			cmp = z.key.compareTo(y.key);
			if(cmp<0){
				y.left=z;
			}else{
				y.right=z;
			}
		}
	}
	public void insert(T key){
		BSTNode<T> z=new BSTNode<T>(key,null,null,null);
		if(z!=null)
			insert(this,z);
	}
	/* 
	 * 删除结点(z)，并返回被删除的结点
	 *
	 * 参数说明：
	 *     bst 二叉树
	 *     z 删除的结点
	 */
	//删除节点
	//删除节点分为3种情况 1没有子节点 2有左节点或右节点 3有左右节点
	//1和2 删除的节点就是他本身   3中找到该节点的后继节点，直接用后继节点替换掉该节点 然后删除后继节点，因为后继节点一定是没有左节点的，即回到1和2了
	private BSTNode<T> remove(BSTree<T> bst,BSTNode<T> z){
		//这里没起个好名字，让人看着默默奇妙，实际上 x 就是子节点 child
		BSTNode<T> x=null;
		//真正删除的节点
		BSTNode<T> y=null;
		//只有一个节点或者没有节点时
		if((z.left==null)||(z.right==null)){
			//z 就是要删除的节点
			y=z;
		}else{
			 //当有两个子节点时，删除后继结点
			y=successor(z);
		}
		//获取子节点，不管是左是右
		if(y.left!=null){
			x=y.left;
		}else{
			x=y.right;
		}
		//如果存在子节点，就关联被删节点的父节点
		if(x!=null){
			x.parent=y.parent;
		}
		//如果父节点是空，说明要删的是根节点
		if (y.parent == null){
			 //设置根为 child（此时根只有一个或没有节点）
			bst.mRoot=x;
		}else if(y==y.parent.left){//要删的是左节点
			y.parent.left=x;
		}else{
			y.parent.right=x;
		}
		//如果要删的节点和一开始传入的不一样，就是后继的情况
		if(y!=z){
			//后继的值传给本来要删除的节点
			z.key=y.key;
		}
		return y;
	}
	//删除节点
	public void remove(T key){
		BSTNode<T> z,node;
		if((z=search(mRoot, key))!=null){
			if((node=remove(this,z))!=null){
				//清理
				node=null;
			}
		}
	}
	
	/*
	 * 打印"二叉查找树"
	 *
	 * key        -- 节点的键值 
	 * direction  --  0，表示该节点是根节点;
	 *               -1，表示该节点是它的父结点的左孩子;
	 *                1，表示该节点是它的父结点的右孩子。
	 */
	private void print(BSTNode<T> tree,T key,int direction){
		if(tree!=null){
			if(direction==0){
				System.out.printf("%2d is root\n",tree.key);
			}else{
				System.out.printf("%2d is %2d's %6s child\n",tree.key,key,direction==1?"right":"left");
			}
			print(tree.left,tree.key,-1);
			print(tree.right,tree.key,1);
		}
	}
	public void print(){
		if(mRoot!=null){
			print(mRoot,mRoot.key,0);
		}
	}
	/**
	 * 销毁二叉树
	 */
	private void destory(BSTNode<T> tree){
		if(tree==null) return ;
		if(tree.left!=null)
			destory(tree.left);
		if(tree.right!=null)
			destory(tree.right);
		tree=null;
	}
	public void clear(){
		destory(mRoot);
		mRoot=null;
	}
}
