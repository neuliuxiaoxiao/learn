package neu.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BitOperation {

	// 返回一个无符号数其二进制表达式中数字位数为1的个数
	public static int hammingWeight(int n) {
		int res = 0;
		for (int i = 0; i < 32; ++i) {
			res += (n & (n - 1));// n&1 最后一位为0 则为0 为1则为1
			n = n >> 1;
		}
		return res;
	}

	// 判断是否是2的次幂
	public static boolean isPowerOfTwo(int n) {
		int cnt = 0;
		while (n > 0) {
			cnt += (n & 1);// 一直往右移动 看有几个1
			n >>= 1;
		}
		return cnt == 1;
	}

	// 8=1000 7=0111 8&7=1000&0111=0
	//
	public static boolean isPowerOfTwo2(int n) {
		return (n > 0) && ((n & (n - 1)) == 0);
	}

	// [m,n] 0<=m<=n<= 返回此范围内所有数字的按位与 包括mn两点
	/*
	 * [26,30] 结果为左边公共部分 11010 11011 11100 11101 11110 11010 11110 &11111=11010
	 * 不等于 &11111=11110 左移一位&11110=11010 11110=11110 左移一位 11100=11000
	 * 11100=11100 左移一位 11000=11000 11000=11000 相等了
	 *
	 */
	public static int rangeBitwiseAnd(int m, int n) {
		int d = Integer.MAX_VALUE;
		while ((m & d) != (n & d)) {
			d <<= 1;
		}
		return m & d;
	}

	// DNA 查找重复串 AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT
	public static List<String> findRepeatedDnaSequences(String s) {
		List<String> res = new ArrayList<String>();
		if (s.length() <= 10)
			return res;

		Map<Integer, Integer> strmap = new HashMap<Integer, Integer>();// substring,
																		// 出现次数
		int i = 0;
		int mask = 0x7ffffff; // 111...1111 一共3+6*4 = 27bit位个1
		int cur = 0;
		while (i < 9) {
			cur = cur << 3 | (s.charAt(i) & 7);
			i++;
		}
		// i =9
		while (i < s.length()) {
			cur = ((cur & mask) << 3) | ((int) s.charAt(i) & 7);
			// ((cur & mask) << 3) ｜:取cur的后27位再补3个0，再加上i的三位
			if (!strmap.containsKey(cur)) {
				strmap.put(cur, 1);
			} else {
				if (strmap.get(cur) == 1) {
					res.add(s.substring(i - 9, i + 1)); // [i-9, i+1)
					strmap.put(cur, -1); // had be add to res
				}
			}
			i++;
		}
		return res;
	}

	public static List<String> findRepeatedDnaSequences2(String s) {
		Set set = new HashSet();
		Set repeat = new HashSet();

		for (int i = 0; i < s.length() - 9; i++) {

			if (!set.add(s.substring(i, i + 10))) {
				repeat.add(s.substring(i, i + 10));
			}
		}
		return new ArrayList(repeat);

	}

	// 查找4的次幂
	public boolean isPowerOfFour(int num) {
		if (num <= 0)
			return false;
		// 先判断是否是 2 的幂
		if ((num & num - 1) != 0)
			return false;
		// 再判断如果进行与运算之后是否还是本身
		// 这个特殊的数有如下特点：
		// 足够大，但不能超过 32 位，即最大为 31 个 1
		// 它的二进制表示中奇数位为 1 ，偶数位为 0
		if ((num & 0x55555555) == num)
			return true;
		return false;
	}

	public static void main(String[] args) {
		// System.out.println(hammingWeight(2+4+8+16));
		// System.out.println(isPowerOfTwo2(12));
		// System.out.println(1&1);
		findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
	}

}
