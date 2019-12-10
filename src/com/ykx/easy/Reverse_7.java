package com.ykx.easy;

import org.junit.Test;

public class Reverse_7 {
    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * <p>
     * 示例 1:
     * 输入: 123
     * 输出: 321
     * <p>
     *  示例 2:
     * 输入: -123
     * 输出: -321
     * <p>
     * 示例 3:
     * 输入: 120
     * 输出: 21
     */

    /**
     * 进行整数反转
     * pop=x%10;
     * res = res * 10 + pop;
     * 这种方法进行翻转时，res = res * 10 + pop容易发生溢出，
     * 在进行此段代码之前进行溢出判断
     * 为了便于解释，我们假设 res 是正数。
     * 如果 temp=res⋅10+pop 导致溢出，那么一定有 res≥Integer.MAX_VALUE/10
     * 如果 res > Integer.MAX_VALUE/10，那么 temp=res⋅10+pop 一定会溢出。
     * 如果 res== Integer.MAX_VALUE/10，那么只要 pop>7,temp=res⋅10+pop 就会溢出。
     * 当 res 为负时可以应用类似的逻辑。
     * int 32位时数据范围为 [−2^31,  2^31 − 1]   -2147483648~2147483647
     */
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7)) return 0;//判断是否上方溢出
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < -8)) return 0;//判断是否下方溢出
            res = res * 10 + pop;
            x = x / 10;
        }
        return res;
    }

    @Test
    public void test() {
        int a = 1534236469;
        int res1 = reverse(a);
        System.out.println(res1);
    }
}
