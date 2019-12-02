package com.ykx.medium;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstring_3 {
    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串的长度。
     *
     * 示例 :
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     */

    /**
     * 暴力法1
     *
     */
    public int lengthOfLongestSubstring1(String s) {
        if(s!=null&&!"".equals(s)) {
            StringBuilder subStr = new StringBuilder();
            subStr.append(s.charAt(0));
            int subStart = 1, flag, count = 1;
            int i = subStart;
            while ( i < s.length()) {
                flag = 0;
                for (int j = 0; j < subStr.length(); j++) {
                    if (s.charAt(i) == subStr.charAt(j)) {
                        if (subStr.length() > count)
                            count = subStr.length();
                        subStr = new StringBuilder();
                        subStr.append(s.charAt(subStart));
                        subStart++;
                        i = subStart;
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    subStr.append(s.charAt(i));
                    if (subStr.length() > count)
                        count = subStr.length();
                    i++;
                }
            }
            return count;
        }
        else
            return 0;
    }

    /**
     * 暴力法2
     *
     * 假设我们有一个函数 boolean allUnique(String substring) ，
     * 如果子字符串中的字符都是唯一的，它会返回 true，否则会返回 false。
     * 我们可以遍历给定字符串 s 的所有可能的子字符串并调用函数 allUnique。
     * 如果事实证明返回值为 true，那么我们将会更新无重复字符子串的最大长度的答案。
     * 时间复杂度O(n^3)
     *
     * Set:注重独一无二的性质,该体系集合可以知道某物是否已近存在于集合中,不会存储重复的元素
     * Set 集合存和取的顺序不一致。
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

    /**
     * 滑动窗口法
     *
     * 暴力法非常简单，但它太慢了。那么我们该如何优化它呢？
     * 在暴力法中，我们会反复检查一个子字符串是否含有有重复的字符，但这是没有必要的。
     * 如果从索引 i 到 j - 1之间的子字符串 s_{ij}已经被检查为没有重复字符。
     * 我们只需要检查 s[j]对应的字符是否已经存在于子字符串 s_{ij}中。
     *
     * 要检查一个字符是否已经在子字符串中，我们可以检查整个子字符串，
     * 这将产生一个复杂度为 O(n^2)的算法，但我们可以做得更好。
     *
     * 通过使用 HashSet 作为滑动窗口，
     * 我们可以用 O(1) 的时间来完成对字符是否在当前的子字符串中的检查。
     *
     * 滑动窗口是数组/字符串问题中常用的抽象概念。
     * 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，
     * 即 [i, j)（左闭，右开）。而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。
     * 例如，我们将 [i, j) 向右滑动 1 个元素，则它将变为 [i+1, j+1)（左闭，右开）。
     *
     * 回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i, j)（最初 j = i）中。
     * 然后我们向右侧滑动索引 j，如果它不在 HashSet 中，我们会继续滑动 j。
     * 直到 s[j] 已经存在于 HashSet 中。
     * 此时，我们找到的没有重复字符的最长子字符串将会以索引 i 开头。
     * 如果我们对所有的 i 这样做，就可以得到答案。
     */
    public int lengthOfLongestSubstring3(String s){
        int i=0,j=0,count=0;
        Set<Character> subStr=new HashSet<>();
        while(i<s.length()&&j<s.length()){
            if(!subStr.contains(s.charAt(j))){
                subStr.add(s.charAt(j++));
                count=Math.max(count,j-i);
            }
            else{
                subStr.remove(s.charAt(i++));
            }
        }
        return count;
    }

    /**
     * 优化的滑动窗口
     *
     * 上述的方法最多需要执行 2n 个步骤。事实上，它可以被进一步优化为仅需要 n 个步骤。
     * 我们可以定义字符到索引的映射，而不是使用集合来判断一个字符是否存在。
     * 当我们找到重复的字符时，我们可以立即跳过该窗口。
     *
     * 也就是说，如果 s[j] 在 [i, j) 范围内有与 j重复的字符，我们不需要逐渐增加 i 。
     * 我们可以直接跳过 [i，j']范围内的所有元素，并将 i 变为 j' + 1
     */
    public int lengthOfLongestSubstring4(String s){
        int count=0;
        Map<Character,Integer> map=new HashMap<>();
        for(int i=0,j=0;j<s.length();j++){
            if(map.get(s.charAt(j))!=null){
                i=Math.max(map.get(s.charAt(j)),i);
            }
            count=Math.max(count,j-i+1);
            map.put(s.charAt(j),j+1);
        }
        return count;
    }

    /**
     * Java（假设字符集为 ASCII 128）
     *
     * 以前的我们都没有对字符串 s 所使用的字符集进行假设。
     *
     * 当我们知道该字符集比较小的时侯，我们可以用一个整数数组作为直接访问表来替换 Map。
     *
     * 常用的表如下所示：
     *
     * int [26] 用于字母 ‘a’ - ‘z’ 或 ‘A’ - ‘Z’
     * int [128] 用于ASCII码
     * int [256] 用于扩展ASCII码
     *
     * 此方法用时最少
     */
    public int lengthOfLongestSubstring5(String s){
        int[] asc=new int[128];
        int count=0;
        for(int i=0,j=0;j<s.length();j++){
            i=Math.max(asc[s.charAt(j)],i);
            count=Math.max(count,j-i+1);
            asc[s.charAt(j)]=j+1;
        }
        return count;
    }

    @Test
    public void test() {
        int count1=lengthOfLongestSubstring1("dvdf");
        int count2=lengthOfLongestSubstring2("dvdf");
        int count3=lengthOfLongestSubstring3("dvdf");
        int count4=lengthOfLongestSubstring4("dvdf");
        int count5=lengthOfLongestSubstring5("dvdf");
        System.out.println("法一："+count1);
        System.out.println("法二："+count2);
        System.out.println("法三："+count3);
        System.out.println("法四："+count4);
        System.out.println("法五："+count5);
    }
}
