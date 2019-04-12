package struct;

import java.util.Comparator;

public class SplayTree2<T extends Comparable<T>> {

	class SplayTreeNode<T extends Comparable<T>> {
		SplayTreeNode<T> left;
		SplayTreeNode<T> right;
		T data;

		SplayTreeNode(SplayTreeNode<T> left, SplayTreeNode<T> right, T data) {
			this.left = left;
			this.right = right;
			this.data = data;
		}

		SplayTreeNode() {
			this(null, null, null);
		}

		SplayTreeNode(T data) {
			this(null, null, data);
		}
	}

	private SplayTreeNode<T> root;
	private Comparator<T> cmp;

	public SplayTree2(){
        root = null;
    }

	public SplayTree2(Comparator<T> cmp){
        this.cmp = cmp;
    }

	private int mCompare(T a, T b) {
		if (cmp != null) {
			return cmp.compare(a, b);
		}
		return a.compareTo(b);
	}

	public SplayTreeNode<T> insert(T data) {
		root = insert(root, data);
		// 进行伸展
		root = splay(root, data);
		return root;
	}

	private SplayTreeNode<T> insert(SplayTreeNode<T> tree, T data) {
		if (tree == null) {
			return new SplayTreeNode<T>(data);
		}
		int result = mCompare(data, tree.data);
		if (result < 0) {
			tree.left = insert(tree.left, data);
		} else if (result > 0) {
			tree.right = insert(tree.right, data);
		} else {
			System.out.println("已经存在该值");
			return null;
		}
		return tree;
	}

	private SplayTreeNode<T> insert(SplayTreeNode<T> rootTree, SplayTreeNode<T> tempNode) {
		if (rootTree == null) {
			return tempNode;
		} else {
			int result = mCompare(tempNode.data, rootTree.data);
			if (result < 0) {
				rootTree.left = insert(rootTree.left, tempNode);
			} else if (result > 0) {
				rootTree.right = insert(rootTree.right, tempNode);
			}
		}
		return rootTree;
	}

	public SplayTreeNode<T> search(T data) {
		return search(root, data);
	}

	private SplayTreeNode<T> search(SplayTreeNode<T> tree, T data) {
		if (tree == null) {
			return null;
		}
		int result = mCompare(data, tree.data);
		if (result < 0) {
			return search(tree.left, data);
		} else if (result > 0) {
			return search(tree.right, data);
		} else {
			// 找到目标节点，进行伸展，使其成为根节点
			root = splay(root, data);
			return tree;
		}
	}

	public T getRoot() {
		return root != null ? root.data : null;
	}

	public T findMax() {
		return findMax(root).data;
	}

	private SplayTreeNode<T> findMax(SplayTreeNode<T> tree) {
		if (tree == null) {
			return null;
		}
		while (tree.right != null) {
			tree = tree.right;
		}
		return tree;
	}

	/**
	 * 删除时，进行伸展： a. 找到删除的节点 b. 对删除的节点进行旋转，使其成为根节点 c. 删除该节点后，问题是如何将左右子树进行拼接？ (1)
	 * 若左子树不为空，则找到左子树中的最大值，因为左子树的最大值节点没有右子树 (1.1) 将选中的最大值节点进行旋转，使其成为根节点 (1.2)
	 * 将原来的右子树拼接过来 (2) 若左子树为空，则右子树直接成为完整的树
	 * 
	 * @param data
	 * @return
	 */
	public SplayTreeNode<T> remove(T data) {
		SplayTreeNode<T> newRoot, removeRoot;
		if (root == null) {
			return null;
		}
		// 找到要删除的节点
		removeRoot = search(root, data);
		// 若没找到，则返回空
		if (removeRoot == null) {
			return null;
		}
		// 对要删除的节点旋转成为根节点
		root = splay(root, data);
		// 左边不为空
		if (root.left != null) {
			// 找到左子树的最大节点值作为根节点，因为其没有右孩子存在
			newRoot = splay(root.left, findMax(root.left).data);
			// 右子树直接赋值
			newRoot.right = root.right;
		}
		// 否则直接赋值右子树
		else {
			newRoot = root.right;
		}
		// 更新树
		root = newRoot;
		// 返回删除的节点
		return removeRoot;
	}

	/**
	 * 使用自底向上的方法实现伸展
	 * 
	 * @param tree
	 *            节点
	 * @param data
	 *            目标值
	 * @return 返回目标节点
	 */
	public SplayTreeNode<T> splay(SplayTreeNode<T> tree, T data) {
		// 若树为空，则返回
		if (tree == null) {
			return null;
		}
		// 进行比较
		int result = mCompare(data, tree.data);
		// 小于0，说明目标节点在左子树
		if (result < 0) {
			// 在左子树中进行查找
			tree.left = splay(tree.left, data);
			// 表示找到了目标节点，tree为目标节点的父节点，进行右旋
			tree = rotationRight(tree);
		}
		// 大于0，说明目标节点在右子树
		else if (result > 0) {
			// 在右子树树种进行查找
			tree.right = splay(tree.right, data);
			// 表示找到了目标节点，tree为目标节点的父节点，进行左旋
			tree = rotationLeft(tree);
		} else {
			// 找到目标节点，返回
			return tree;
		}
		return tree;
	}

	/**
	 * 进行左旋转
	 * 
	 * @param root
	 *            传入目标节点的父节点
	 * @return 返回以目标节点为根节点的部分树
	 */
	private SplayTreeNode<T> rotationLeft(SplayTreeNode<T> root) {
		SplayTreeNode<T> newRoot = root.right;// A
		root.right = newRoot.left;// B
		newRoot.left = root;// C
		return newRoot;
	}

	/**
	 * 进行右旋
	 * 
	 * @param tree
	 * @return
	 */
	private SplayTreeNode<T> rotationRight(SplayTreeNode<T> tree) {
		SplayTreeNode<T> newRoot = tree.left;// A
		tree.left = newRoot.right;// B
		newRoot.right = tree;// C
		return newRoot;
	}

	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(SplayTreeNode<T> tree) {
		if (tree != null) {
			inOrder(tree.left);
			System.out.print(tree.data + " ");
			inOrder(tree.right);
		}
	}

}