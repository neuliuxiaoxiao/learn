package neu.leetcode;

//动态规划买卖股票
public class DynamicStock {

	public static void main(String[] args) {
		
	}
	//只买买一次股票 [7,1,5,3,6,4]
	public static int maxProfit(int[] prices) {
		if (prices.length<=1)
			return 0;
		int buy =-prices[0],shell=0;
		for(int i=1;i<prices.length;i++){
			buy=Math.max(buy, -prices[i]);//看当天买进和之前买进谁用的钱少
			shell=Math.max(shell, buy+prices[i]);//看当天卖出和之前卖出谁剩的钱多
		}
		return shell;
	}
}
