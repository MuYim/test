package com.ykx.medium;

import org.junit.Test;

public class LongestPalindrome_5 {
    public String longestPalindrome(String s) {
        int n = s.length();
        int count = 0;
        String res = "";
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String sub = s.substring(i, j+1);
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
            for (int i = 0; i <=((n - 1) / 2); i++) {
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

    @Test
    public void test() {
        String s = "babad";
        String res1 = longestPalindrome(s);
        System.out.println(res1);
    }
}
