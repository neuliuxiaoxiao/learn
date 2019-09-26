package neu.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BitOperation {

	// ����һ���޷�����������Ʊ��ʽ������λ��Ϊ1�ĸ���
	public static int hammingWeight(int n) {
		int res = 0;
		for (int i = 0; i < 32; ++i) {
			res += (n & (n - 1));// n&1 ���һλΪ0 ��Ϊ0 Ϊ1��Ϊ1
			n = n >> 1;
		}
		return res;
	}

	// �ж��Ƿ���2�Ĵ���
	public static boolean isPowerOfTwo(int n) {
		int cnt = 0;
		while (n > 0) {
			cnt += (n & 1);// һֱ�����ƶ� ���м���1
			n >>= 1;
		}
		return cnt == 1;
	}

	// 8=1000 7=0111 8&7=1000&0111=0
	//
	public static boolean isPowerOfTwo2(int n) {
		return (n > 0) && ((n & (n - 1)) == 0);
	}

	// [m,n] 0<=m<=n<= ���ش˷�Χ���������ֵİ�λ�� ����mn����
	/*
	 * [26,30] ���Ϊ��߹������� 11010 11011 11100 11101 11110 11010 11110 &11111=11010
	 * ������ &11111=11110 ����һλ&11110=11010 11110=11110 ����һλ 11100=11000
	 * 11100=11100 ����һλ 11000=11000 11000=11000 �����
	 *
	 */
	public static int rangeBitwiseAnd(int m, int n) {
		int d = Integer.MAX_VALUE;
		while ((m & d) != (n & d)) {
			d <<= 1;
		}
		return m & d;
	}

	// DNA �����ظ��� AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT
	public static List<String> findRepeatedDnaSequences(String s) {
		List<String> res = new ArrayList<String>();
		if (s.length() <= 10) {
            return res;
        }

		Map<Integer, Integer> strmap = new HashMap<Integer, Integer>();// substring,
																		// ���ִ���
		int i = 0;
		int mask = 0x7ffffff; // 111...1111 һ��3+6*4 = 27bitλ��1
		int cur = 0;
		while (i < 9) {
			cur = cur << 3 | (s.charAt(i) & 7);
			i++;
		}
		// i =9
		while (i < s.length()) {
			cur = ((cur & mask) << 3) | ((int) s.charAt(i) & 7);
			// ((cur & mask) << 3) ��:ȡcur�ĺ�27λ�ٲ�3��0���ټ���i����λ
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

	// ����4�Ĵ���
	public boolean isPowerOfFour(int num) {
		if (num <= 0) {
            return false;
        }
		// ���ж��Ƿ��� 2 ����
		if ((num & num - 1) != 0) {
            return false;
        }
		// ���ж��������������֮���Ƿ��Ǳ���
		// �����������������ص㣺
		// �㹻�󣬵����ܳ��� 32 λ�������Ϊ 31 �� 1
		// ���Ķ����Ʊ�ʾ������λΪ 1 ��ż��λΪ 0
		if ((num & 0x55555555) == num) {
            return true;
        }
		return false;
	}

	public static void main(String[] args) {
		// System.out.println(hammingWeight(2+4+8+16));
		// System.out.println(isPowerOfTwo2(12));
		// System.out.println(1&1);
		findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
	}

}
