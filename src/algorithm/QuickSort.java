package algorithm;

/**
 *快速排序  时间复杂度在最坏情况下是O(N2)，平均的时间复杂度是O(N*lgN)。
 *快速排序的时间复杂度在最坏情况下是O(N2)，平均的时间复杂度是O(N*lgN)。
这句话很好理解：假设被排序的数列中有N个数。遍历一次的时间复杂度是O(N)，需要遍历多少次呢？至少lg(N+1)次，最多N次。
(01) 为什么最少是lg(N+1)次？快速排序是采用的分治法进行遍历的，我们将它看作一棵二叉树，它需要遍历的次数就是二叉树的深度，而根据完全二叉树的定义，它的深度至少是lg(N+1)。因此，快速排序的遍历次数最少是lg(N+1)次。
(02) 为什么最多是N次？这个应该非常简单，还是将快速排序看作一棵二叉树，它的深度最大是N。因此，快读排序的遍历次数最多是N次。
 */


/**
 * 快速排序流程：
(1) 从数列中挑出一个基准值。
(2) 将所有比基准值小的摆放在基准前面，所有比基准值大的摆在基准的后面(相同的数可以到任一边)；在这个分区退出之后，该基准就处于数列的中间位置。
(3) 递归地把"基准值前面的子数列"和"基准值后面的子数列"进行排序
 */
public class QuickSort {

	public void sort(int[] sourceArray,int left,int right) {
		if(left<right){
			int i=left,j=right,x=sourceArray[i];
			while(left<right){//
				while(i<j&&sourceArray[j]>x)
					j--;//从右往左找第一个小于x的数
				if(i<j){
					sourceArray[i]=sourceArray[j];//如果找到了就交换  然后i往后挪一位
					i++;
				}
				while(i<j&&sourceArray[i]<x)
					i++;//从左往右找第一个大于x的数
				if(i<j){
					sourceArray[j]=sourceArray[i];
					j--;
				}
			}
			sourceArray[i]=x;
			//调用一次sort 即代表已经找到一个基准值  将大于它的都放在后面  小于它的都放在前面了  然后在递归即可
			sort(sourceArray,left,i-1);//递归
			sort(sourceArray,i+1,right);//递归
		}
		
	}

}
