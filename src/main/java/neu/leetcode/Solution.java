package neu.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	static List<List<String>>  res = new ArrayList<>();
	//鍒ゆ柇鏄惁鏄洖鏂囦覆
	public static boolean isPalindrome(String s) {
        if(s.length() == 0)
             return true;
        int l = 0, r = s.length() - 1;
        while(l < r){
            //纭畾鎸囧畾鐨勫瓧绗︽槸鍚︿负瀛楁瘝鎴栨暟瀛�
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
	
	//鍥炴枃涓插垎鍓�
	public static List<List<String>> partition(String s){
		if(s==null||s.length()==0)
			return res;
		dfs(s,new ArrayList<String>(),0);
		return res;
		
	}
	public static void dfs(String s,List<String> remain,int left){
		if(left==s.length()){  //鍒ゆ柇缁堟鏉′欢
            res.add(new ArrayList<String>(remain));  //娣诲姞鍒扮粨鏋滀腑
            return;
        }
		
		 for(int right=left;right<s.length();right++){  //浠巐eft寮�濮嬶紝渚濇鍒ゆ柇left->right鏄笉鏄洖鏂囦覆
	            if(isPalindrom(s,left,right)){  //鍒ゆ柇鏄惁鏄洖鏂囦覆
	                remain.add(s.substring(left,right+1));   //娣诲姞鍒板綋鍓嶅洖鏂囦覆鍒發ist涓�
	                dfs(s,remain,right+1);  //浠巖ight+1寮�濮嬬户缁�掑綊锛屽鎵惧洖鏂囦覆
	                remain.remove(remain.size()-1);  //鍥炴函锛屼粠鑰屽鎵炬洿闀跨殑鍥炴枃涓�
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
	
	//鍗曡瘝鎷嗗垎
	public static boolean wordBreak(String s,List<String> wordDict){
		int n=s.length();
		int max_length=0;
		for(String temp:wordDict){//鎵惧嚭wordDict閲岄潰鐨勬渶闀�
			max_length=temp.length()>max_length?temp.length():max_length;
		}
		boolean[] memo = new boolean[n+1];
		memo[0]=true;//鍥犱负鐢ㄥ埌浜嗚嚜搴曞悜涓婄殑鍔ㄦ�佽鍒掞紝闇�瑕佺煡閬撶涓�涓殑鍊�
		for(int i=1;i<n;i++){
			for(int j=i-1;j>=0&&max_length>i-j;j--){
				//1...j i   j=i-1
				//鍒嗗壊鎴愬姛  memo[i] 淇濆瓨鐨勬槸鍓峣涓瓧绗︽槸鍚﹀彲浠ユ媶鍒� 鐒跺悗灏卞彲浠ラ亶鍘唈 
				//渚濇鍒ゆ柇绗琲涓笌鍓嶉潰鐨勭粍鎴愮殑鏄惁鍖呭惈鍦╳ordDict閲岄潰
				if(memo[j]&&wordDict.contains(s.subSequence(j,i))){
					memo[i]=true;
					break;
				}
			}
		}
		return memo[n];
	}
	
	//鍙嶈浆瀛楃涓�
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
		str = str.trim();//鍘婚櫎绌烘牸寮�澶�
		int result =0;//缁撴灉
		boolean isPos=true;//姝ｈ礋鏁�
		for(int i=0;i<str.length();i++){
			char c = str.charAt(i);
			if(i==0&&(c=='-'||c=='+')){//鍒ゆ柇姝ｈ礋鏁�
				isPos = c=='+'?true:false;
			}else if(c>='0'&&c<='9'){
				//妫�鏌ユ孩鍑烘椂鏈�澶ф暣鏁拌鍏堝噺鍘诲嵆灏嗗姞鐨勬渶鏈綅鍐嶉櫎浠�10
				if(result>(Integer.MAX_VALUE-(c-'0'))/10){
					return isPos?Integer.MAX_VALUE:Integer.MIN_VALUE;
				}
				result *=10;
				result +=c-'0';
			}else{//杩欓噷鏄笉婊¤冻鐨勬儏鍐典笅鐗规畩澶勭悊
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
