package com.ykx.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Convert_6 {
    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     *
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     *
     * 示例 2:
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     */

    /**
     * 按z排列顺序将字符串放入二维数组中，然后再按行取出
     * 时间复杂度：O(n)
     * 空间复杂度：O(n^2)
     */
    public String convert1(String s, int numRows) {
        int i=0,j=0,num=0,count=s.length();
        char[][] res=new char[numRows][count];
        while(num<count){
            if(i==0){
                while(i<numRows){
                    if(num<count) {
                        res[i][j] = s.charAt(num);
                        num++;
                        i++;
                    }
                    else
                        break;
                }//循环结束时i=numRows
                j++;
                i--;//使i成为数组下标的最后一个
            }
            else{
                i--;//移动到下一个位置
                while(i>0){
                    if(num<count) {
                        res[i][j] = s.charAt(num++);
                        i--;
                        j++;
                    }
                    else
                        break;
                }
            }
        }
        String str="";
        for(int m=0;m<numRows;m++)
            for(int n=0;n<j;n++){
                if('\0'!=res[m][n])//判断char是否为"用'\0'!=res[m][n]
                str=str+res[m][n];
            }
        return str;
    }

    /**
     * 对上面方法的改进，减少空间复杂度
     * 2.按行排序
     * 思路
     * 通过从左向右迭代字符串，我们可以轻松地确定字符位于 Z 字形图案中的哪一行。
     * 算法
     * 我们可以使用 min(numRows,len(s)) 个列表来表示 Z 字形图案中的非空行。
     * 从左到右迭代 ss，将每个字符添加到合适的行。可以使用当前行和当前方向这两个变量对合适的行进行跟踪。
     * 只有当我们向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public String convert2(String s,int numRows){
        if(numRows==1)return s;
        List<StringBuffer> rows=new ArrayList<>();
        for(int i=0;i<Math.min(s.length(),numRows);i++)
            rows.add(new StringBuffer());
        int curRow=0;
        boolean goingBack=false;
        for(char a:s.toCharArray()){
            rows.get(curRow).append(a);
            if(curRow==0||curRow==numRows-1)
                goingBack=!goingBack;
            curRow+=goingBack?1:-1;
        }
        StringBuffer res=new StringBuffer();
        for(StringBuffer row:rows)
            res.append(row);
        return res.toString();
    }
    @Test
    public void test(){
        String s="LEETCODEISHIRING";
        String res1=convert1(s,4);
        String res2=convert2(s,4);
        System.out.println(res1);
        System.out.println(res2);

    }
}
