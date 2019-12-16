package com.ykx.medium;

import org.junit.Test;

public class IntToRoman_12 {
    /**
     * 重点：法2，贪心算法
     */
    /**
     * 12. 整数转罗马数字
     * <p>
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。
     * 12 写做 XII ，即为 X + II 。
     * 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。
     * 但也存在特例，例如 4 不写做 IIII，而是 IV。
     * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
     * 同样地，数字 9 表示为 IX。
     * <p>
     * 这个特殊的规则只适用于以下六种情况：
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     * <p>
     * 示例 1:
     * 输入: 3
     * 输出: "III"
     * <p>
     * 示例 2:
     * 输入: 4
     * 输出: "IV"
     * <p>
     * 示例 3:
     * 输入: 9
     * 输出: "IX"
     * <p>
     * 示例 4:
     * 输入: 58
     * 输出: "LVIII"
     * 解释: L = 50, V = 5, III = 3.
     * <p>
     * 示例 5:
     * 输入: 1994
     * 输出: "MCMXCIV"
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     */
    public String intToRoman(int num) {
        StringBuffer roman = new StringBuffer();
        //千位
        int a = num / 1000;
        num %= 1000;
        while (a > 0) {
            roman.append('M');
            a--;
        }
        //百位
        int b = num / 100;
        num %= 100;
        if (b == 9)
            roman.append("CM");
        else if (b == 4)
            roman.append("CD");
        else if (b >= 5 && b < 9) {
            roman.append('D');
            while (b > 5) {
                roman.append('C');
                b--;
            }
        } else
            while (b > 0) {
                roman.append('C');
                b--;
            }
        //十位
        int c = num / 10;
        num %= 10;
        if (c == 9)
            roman.append("XC");
        else if (c == 4)
            roman.append("XL");
        else if (c >= 5 && c < 9) {
            roman.append('L');
            while (c > 5) {
                roman.append('X');
                c--;
            }
        } else
            while (c > 0) {
                roman.append('X');
                c--;
            }
        //个位
        int d = num;
        if (d == 9)
            roman.append("IX");
        else if (d == 4)
            roman.append("IV");
        else if (d >= 5 && d < 9) {
            roman.append('V');
            while (d > 5) {
                roman.append('I');
                d--;
            }
        } else
            while (d > 0) {
                roman.append('I');
                d--;
            }
        return roman.toString();
    }

    /**
     * 贪心算法
     *
     * @param num
     * @return
     */
    public String intToRoman1(int num) {
        int[] moneys = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int index = 0;
        StringBuffer roman = new StringBuffer();
        while (num > 0) {
            if (num >= moneys[index]) {
                roman.append(romans[index]);
                num -= moneys[index];
            } else
                index++;
        }
        return roman.toString();
    }

    @Test
    public void test() {
        int num = 1994;
        String res1 = intToRoman(num);
        String res2 = intToRoman1(num);
        System.out.println(res1);
        System.out.println(res2);

    }
}
