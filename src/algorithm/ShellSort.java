package algorithm;
/**
 *它的基本思想是：对于n个待排序的数列，取一个小于n的整数gap(gap被称为步长)将待排序元素分成若干个组子序列，
 *所有距离为gap的倍数的记录放在同一个组中；然后，对各组内的元素进行直接插入排序。 这一趟排序完成之后，每一个组的元素都是有序的
 *然后减小gap的值，并重复执行上述的分组和排序。重复这样的操作，当gap=1时，整个数列就是有序的。
 * 算法稳定性 -- 假设在数列中存在a[i]=a[j]，若在排序之前，a[i]在a[j]前面；并且排序之后，a[i]仍然在a[j]前面。则这个排序算法是稳定的！
 */
public class ShellSort {

	/**
	 * 希尔排序
	 * 参数说明： a -- 待排序的数组 n -- 数组的长度
	 */
	public void shellSort(int[] array,int n){
		// gap为步长，每次减为原来的一半。
		for(int gap=n/2;gap>0;gap/=2){
			//共gap个组，对每一个组进行插入排序
			for(int i=0;i<gap;i++){
				groupSort(array,n,i,gap);
			}
		}
	}

	/**
	 * 对希尔排序中的单个组进行排序
	 *
	 * 参数说明： a -- 待排序的数组 n -- 数组总的长度 i -- 组的起始位置 gap -- 组的步长
	 *
	 * 组是"从i开始，将相隔gap长度的数都取出"所组成的！
	 */
	public void groupSort(int[] array,int n,int i,int gap){
		for(int j=i+gap;j<n;j+=gap){
			// 如果a[j] < a[j-gap]，则寻找a[j]位置，并将后面数据的位置都后移。
			if(array[j]<array[j-gap]){
				int tmp = array[j];
				int k=j-gap;
				while(k>=0&&array[k]>tmp){
					array[k+gap]=array[k];
					k-=gap;
				}
				array[k+gap]=tmp;
			}
		}
	}
}
