package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	static List<List<String>>  res = new ArrayList<>();
	//判断是否是回文串
	public static boolean isPalindrome(String s) {
        if(s.length() == 0)
             return true;
        int l = 0, r = s.length() - 1;
        while(l < r){
            //确定指定的字符是否为字母或数字
            if(!Character.isLetterOrDigit(s.charAt(l))){
                l++;
            }else if(!Character.isLetterOrDigit(s.charAt(r))){
                r--;
            }else{
                if(Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
                    return false;
                l++;
                r--;
            } 
        }
        return true;
    }
	
	//回文串分割
	public static List<List<String>> partition(String s){
		if(s==null||s.length()==0)
			return res;
		dfs(s,new ArrayList<String>(),0);
		return res;
		
	}
	public static void dfs(String s,List<String> remain,int left){
		if(left==s.length()){  //判断终止条件
            res.add(new ArrayList<String>(remain));  //添加到结果中
            return;
        }
		
		 for(int right=left;right<s.length();right++){  //从left开始，依次判断left->right是不是回文串
	            if(isPalindrom(s,left,right)){  //判断是否是回文串
	                remain.add(s.substring(left,right+1));   //添加到当前回文串到list中
	                dfs(s,remain,right+1);  //从right+1开始继续递归，寻找回文串
	                remain.remove(remain.size()-1);  //回溯，从而寻找更长的回文串
	            }
	        }
	}
	
	public static boolean isPalindrom(String s,int left,int right){
		while(left<right&&Character.toLowerCase(s.charAt(left))==Character.toLowerCase(s.charAt(right))){
			left++;
			right--;
		}
		return left>=right;
	}
	
	//单词拆分
	public static boolean wordBreak(String s,List<String> wordDict){
		int n=s.length();
		int max_length=0;
		for(String temp:wordDict){//找出wordDict里面的最长
			max_length=temp.length()>max_length?temp.length():max_length;
		}
		boolean[] memo = new boolean[n+1];
		memo[0]=true;//因为用到了自底向上的动态规划，需要知道第一个的值
		for(int i=1;i<n;i++){
			for(int j=i-1;j>=0&&max_length>i-j;j--){
				//1...j i   j=i-1
				//分割成功  memo[i] 保存的是前i个字符是否可以拆分 然后就可以遍历j 
				//依次判断第i个与前面的组成的是否包含在wordDict里面
				if(memo[j]&&wordDict.contains(s.subSequence(j,i))){
					memo[i]=true;
					break;
				}
			}
		}
		return memo[n];
	}
	
	//反转字符串
	public static char[] reverseString(char[] s) {
		int i = 0, j = s.length - 1;
		while (i < j) {
			char tmp = s[i];
			s[i] = s[j];
			s[j] = tmp;
			i++;
			j--;
		}
		return s;
	}
	
	public static int StrToInt(String str) {
		str = str.trim();//去除空格开头
		int result =0;//结果
		boolean isPos=true;//正负数
		for(int i=0;i<str.length();i++){
			char c = str.charAt(i);
			if(i==0&&(c=='-'||c=='+')){//判断正负数
				isPos = c=='+'?true:false;
			}else if(c>='0'&&c<='9'){
				//检查溢出时最大整数要先减去即将加的最末位再除以10
				if(result>(Integer.MAX_VALUE-(c-'0'))/10){
					return isPos?Integer.MAX_VALUE:Integer.MIN_VALUE;
				}
				result *=10;
				result +=c-'0';
			}else{//这里是不满足的情况下特殊处理
				return isPos?result:-result;
			}
		}
		return isPos?result:-result;
	}
	
	
	
	public static void main(String[] args) {
		char[] s={'h','e','l','l','o'};
		System.out.println(reverseString(s));
	}
}
