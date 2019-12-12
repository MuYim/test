package com.ykx.hard;

import org.junit.Test;

public class IsMatch_10 {
    /**
     * 10. 正则表达式匹配
     *
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     *
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     *
     * 说明:
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     *
     * 示例 1:
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     *
     * 示例 2:
     * 输入:
     * s = "aa"
     * p = "a*"
     * 输出: true
     * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。
     * 因此，字符串 "aa" 可被视为 'a' 重复了一次。
     *
     * 示例 3:
     * 输入:
     * s = "ab"
     * p = ".*"
     * 输出: true
     * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     *
     * 示例 4:
     * 输入:
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     *
     * 示例 5:
     * 输入:
     * s = "mississippi"
     * p = "mis*is*p*."
     * 输出: false
     */

    /**
     * 使用递归的方式实现：
     *
     * 1.当p为空串的时候，s除非也是空串否则不能匹配，即false;
     * 2.当p非空的时候：
     *      p长度为1，此时s长度必须为1，且 要么p为“ . ”，要么s和p相等，才输出true。
     *      （比如：p=a, s=a or p=. ,s = a)
     *
     * 3. p长度大于1：
     *      3.1 p[1]不为“ * ” ： s为空时返回false; s不空时，p[0]和s[0]即为1所讨论的情况，
     *          在满足情况1的条件下，后续串递归求解。
     *          (比如：p = a. s = ab)
     *
     *      3.2 p[1]为“ * ” ：
     *
     *          3.2.1 如果匹配，
     *              若s和p删掉前两个字符的情况返回true，则true
     *              （比如：p = a*bc s = bc 时， 求得p = bc,s=bc返回true）；
     *              若返回false，则删掉s的头一个字符，循环
     *              （比如：p = a*bc s = abc ,求得p = bc,s = abc 返回false，那么s变为 bc）。
     *
     *          3.2.2 如果s[0]和p[0]不匹配，递归求解s和p删掉前两个字符的情况
     *               （比如：p = a*bc s = bc ,那么递归求解 p = bc ,s = bc的情况）；
     */
    public boolean isMatch(String s, String p) {
        if(p.isEmpty())
            return s.isEmpty();
        if(p.length()==1)
            return (s.length()==1&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.'));
        if(p.charAt(1)!='*') {
            if(s.isEmpty()) return false;
            return ((p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1)));
        }
        while(!s.isEmpty()&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.')){
            if(isMatch(s,p.substring(2)))
                return true;
            s=s.substring(1);
        }
        return isMatch(s,p.substring(2));
    }
    @Test
    public void test(){
        String s="mississippi",p="mis*is*ip*.";
        boolean a=isMatch(s,p);
        System.out.println(a);
    }
}
