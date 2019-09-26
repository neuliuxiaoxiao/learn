package neu.learn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {
	public int maxDepth(TreeNode root) {

		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		return left > right ? left + 1 : right + 1;
	}

	public boolean isValidBST(TreeNode root) {
		if (root == null) {
            return true;
        }
		return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public boolean valid(TreeNode root, long low, long high) {
		if (root == null) {
            return true;
        }
		if (root.val <= low || root.val >= high) {
            return false;
        }
		return valid(root.left, low, root.val) && valid(root.right, root.val, high);
	}

	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
            return true;
        }
		return symmetric(root.left, root.right);
	}
	public TreeNode sortedArrayToBST(int[] nums) {
        if(nums==null||nums.length==0) {
            return null;
        }
        return sortedArrayToBST(nums,0,nums.length-1);
    }
	public TreeNode sortedArrayToBST(int[] nums,int start,int end){
		if(start>end) {
            return null;
        }
		int mid = (start+end)/2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left=sortedArrayToBST(nums,start,mid-1);
		root.right=sortedArrayToBST(nums,mid+1,end);
		return root;
	}
	
	private boolean symmetric(TreeNode p, TreeNode q) /* 鍒ゆ柇涓ゆ５鏍戞槸鍚﹀绉� */
	{
		if (p == null && q == null) {
            return true; // 閮芥槸绌�
        }
		if (p == null || q == null) {
            return false; // 鍙湁涓�涓┖
        }
		return (p.val == q.val) && symmetric(p.left, q.right) && symmetric(p.right, q.left);
		/* 鏍憄鍜屾爲q瀵圭О鐨勬潯浠讹細p鍜宷鐨勫�肩浉鍚岋紝骞朵笖p鐨勫乏瀛愭爲涓巕鐨勫彸瀛愭爲瀵圭О锛宲鐨勫彸瀛愭爲涓巕鐨勫乏瀛愭爲瀵圭О */
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(root==null) {
            return result;
        }
		Queue<TreeNode> queue = new LinkedList<TreeNode>(); 
		queue.offer(root);
		while(!queue.isEmpty()){
			List<Integer> level = new ArrayList<Integer>();
			TreeNode curr = null;
			int size = queue.size();
			for(int i=0;i<size;i++){
				curr = queue.poll();
				level.add(curr.val);
				if(curr.left!=null){
					queue.offer(curr.left);
				}
				if(curr.right!=null){
					queue.offer(curr.right);
				}
			}
			result.add(level);
		}
		return result;
	}
}
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}
