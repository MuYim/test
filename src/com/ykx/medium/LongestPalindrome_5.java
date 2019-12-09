package com.ykx.medium;

import org.junit.Test;

public class LongestPalindrome_5 {
    /**
     *5. 最长回文子串
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 示例 1：
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     */

    /**
     * 1.暴力破解法，时间复杂度为O(n^3)，过大，提交后时间不通过
     */
    public String longestPalindrome1(String s) {
        int n = s.length();
        int count = 0;
        String res = "";
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String sub = s.substring(i, j + 1);
                int num = isPalindrome(sub);
                if (count < num) {
                    count = num;
                    res = sub;
                }
            }
        }
        return res;
    }

    public int isPalindrome(String s) {
        char[] a = s.toCharArray();
        int flag = 1, n = s.length();
        if (n == 1)
            return 1;
        else
            for (int i = 0; i <= ((n - 1) / 2); i++) {
                int j = n - i - 1;
                if (a[i] != a[j]) {
                    flag = 0;
                }
            }
        if (flag == 0)
            return 0;
        else
            return s.length();
    }
    /**
     * 2.最长公共子串
     * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-bao-gu/
     */

    public String longestPalindrome2(String s) {
        if (s.equals(""))
            return "";
        String origin = s;
        String reverse = new StringBuffer(s).reverse().toString();
        int length = s.length();
        int[] arr = new int[length];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i++)
        /**************修改的地方***************************/
            for (int j = length - 1; j >= 0; j--) {
                /**************************************************/
                if (origin.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[j] = 1;
                    } else {
                        arr[j] = arr[j - 1] + 1;
                    }
                    /**************修改的地方***************************/
                    //之前二维数组，每次用的是不同的列，所以不用置 0 。
                } else {
                    arr[j] = 0;
                }
                /**************************************************/
                if (arr[j] > maxLen) {
                    int beforeRev = length - 1 - j;
                    if (beforeRev + arr[j] - 1 == i) {
                        maxLen = arr[j];
                        maxEnd = i;
                    }

                }
            }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }
    /**
     * 3.扩展中心法
     * 我们知道回文串一定是对称的，所以我们可以每次循环选择一个中心，进行左右扩展，
     * 判断左右字符是否相等即可。
     * 由于存在奇数的字符串和偶数的字符串，所以我们需要从一个字符开始扩展，
     * 或者从两个字符之间开始扩展，所以总共有 n+n-1 个中心。
     *
     * 时间复杂度：O(n²）。
     * 空间复杂度：O(1）。
     */
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);//从一个字符开始扩展
            int len2 = expandAroundCenter(s, i, i + 1);//从两个字符开始扩展
            int len = Math.max(len1, len2);
            if (len > (end - start + 1)) {
                start = i - (len - 1) / 2;//长度减一再除以2，是为了使一个字符扩展和两个字符扩展的情况合并
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }//循环结束时L和R分别比最左和最右小1和大1，所以求长度时为R-L-1
        return R - L - 1;//长度，回文串的长度
    }

    @Test
    public void test() {
        String s = "babad";
        String res1 = longestPalindrome1(s);
        String res2 = longestPalindrome2(s);
        String res3 = longestPalindrome3(s);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);


    }
}
