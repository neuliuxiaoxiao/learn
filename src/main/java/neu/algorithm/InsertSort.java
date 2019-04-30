package neu.algorithm;

/**
 *插入排序 时间复杂度是O(N2)。 
 *把n个待排序的元素看成为一个有序表和一个无序表。开始时有序表中只包含1个元素，无序表中包含有n-1个元素，排序过程中每次从无序表中取出第一个元素，
 *将它插入到有序表中的适当位置，使之成为新的有序表，重复n-1次可完成排序过程。
 */
public class InsertSort {

	public void insertSort(int sourceArray[],int n){
		// 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
		for(int i=1;i<n;i++){
			 // 记录要插入的数据
			int tmp = sourceArray[i];
			// 从已经排序的序列最右边的开始比较，找到比其小的数
			int j=i;
			while(j>0&tmp<sourceArray[j-1]){
				sourceArray[j]=sourceArray[j-1];
				j--;
			}
			 // 存在比其小的数，插入
			if(j!=i)
				sourceArray[j]=tmp;
		}
	}
}
