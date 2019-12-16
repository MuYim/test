package com.ykx.medium;

import org.junit.Test;

public class MaxArea_11 {
    /**
     * 11. 盛最多水的容器
     * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     *
     * 示例:
     * 输入: [1,8,6,2,5,4,8,3,7]
     * 输出: 49
     */

    /**
     * 暴力破解
     * 时间复杂度：O(n^2)
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int maxArea=0;
        for(int i=0;i<height.length;i++)
            for(int j=i+1;j<height.length;j++){
                int area=Math.min(height[i],height[j])*(j-i);
                if(maxArea<area)
                    maxArea=area;
            }
        return maxArea;
    }

    /**
     * 两个指针
     * 时间复杂度：O(n)
     * @param height
     * @return
     */
    public int maxArea2(int[] height){
        int maxArea=0,left=0,right=height.length-1;
        while(left<right){
            maxArea=Math.max(maxArea,Math.min(height[left],height[right])*(right-left));
            if(height[left]<height[right])
                left++;
            else
                right--;
        }
        return maxArea;
    }
    @Test
    public void test(){
        int[] height =new int[]{1,8,6,2,5,4,8,3,7};
        int a=maxArea1(height);
        int b=maxArea2(height);
        System.out.println(a);
        System.out.println(b);

    }
}
