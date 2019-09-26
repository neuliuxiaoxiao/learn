package neu.leetcode;

//������Ʊ
public class DynamicStock {

	public static void main(String[] args) {
		System.out.println(maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
		System.out.println(maxProfit2(new int[] { 7, 1, 5, 3, 6, 4 }));
		System.out.println(maxProfit3(new int[] { 7, 1, 5, 3, 6, 4 }));
	}

	// ֻ����һ��
	public static int maxProfit(int[] prices) {
		if (prices.length <= 1) {
            return 0;
        }
		int buy = -prices[0], shell = 0;
		for (int i = 1; i < prices.length; i++) {
			buy = Math.max(buy, -prices[i]);
			shell = Math.max(shell, buy + prices[i]);
		}
		return shell;
	}

	// ���޴�
	public static int maxProfit2(int[] prices) {
		if (prices.length <= 1) {
            return 0;
        }
		int buy = -prices[0], shell = 0;
		for (int i = 1; i < prices.length; i++) {
			shell = Math.max(shell, buy + prices[i]);
			buy = Math.max(buy, shell-prices[i]);//˵������֮ǰ����ӵ��������Ǯ
		}
		return shell;
	}
	
	//��������
	public static int maxProfit3(int[] prices) {
        int fstBuy = Integer.MIN_VALUE, fstSell = 0;
        int secBuy = Integer.MIN_VALUE, secSell = 0;
        for(int i = 0; i < prices.length; i++) {
            fstBuy = Math.max(fstBuy, -prices[i]);
            fstSell = Math.max(fstSell, fstBuy + prices[i]);
            secBuy = Math.max(secBuy, fstSell -  prices[i]);
            secSell = Math.max(secSell, secBuy +  prices[i]); 
        }
        return secSell;
    }
}
