package neu.algorithm;

/**
 *首先在未排序的数列中找到最小(or最大)元素，然后将其存放到数列的起始位置；
 *接着，再从剩余未排序的元素中继续寻找最小(or最大)元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕 
 */
public class SelectSort {

	public void selectSort(int[] array,int n){
		for(int i=0;i<n;i++){
			int min =i;
			// 找出"a[i+1] ... a[n]"之间的最小元素，并赋值给min。
			for(int j=i+1;j<n;j++){
				if(array[j]<array[min]){
					min=j;
				}
			}
			// 若min!=i，则交换 a[i] 和 a[min]。
			// 交换之后，保证了a[0] ... a[i] 之间的元素是有序的。
			if(min!=i){
				int tmp = array[i];
				array[i]=array[min];
				array[min]=tmp;
			}
		}
	}
}
