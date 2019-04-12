package algorithm;


/**
 *冒泡排序  时间复杂度是O(N2)。
 */
public class BubbleSort implements IArraySort {

	@Override
	public void sort(int[] sourceArray) {
		int n = sourceArray.length;
		int flag;//标记  代表是否发生了交换 如果没有发生交换 说明数组已经是有序的了 所以可以直接返回
		for(int i=n-1;i>0;i--){
			flag=0;//初始化标记为0
			//做了i次排序，需要比较的也就只有i个了
			//比如第一次 最大的到最后去了  那就只需要比较前n-1个
			for(int j=0;j<i;j++){
				if(sourceArray[j]>sourceArray[j+1]){
					int tmp = sourceArray[j];
					sourceArray[j]=sourceArray[j+1];
					sourceArray[j+1]=tmp;
					flag = 1;//发生变换
				}
			}
			if(flag==0)
				break;
		}
	}

}
