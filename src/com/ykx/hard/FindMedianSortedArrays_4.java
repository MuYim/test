package com.ykx.hard;

import org.junit.Test;

public class FindMedianSortedArrays_4 {
    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * 你可以假设 nums1 和 nums2 不会同时为空。
     *
     * 示例 1:
     * nums1 = [1, 3]
     * nums2 = [2]
     * 则中位数是 2.0
     * 示例 2:
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * 则中位数是 (2 + 3)/2 = 2.5
     */

    /**
     * 将两个数组合并为一个数组，然后求中位数
     *
     * 时间复杂度：遍历全部数组 O(m+n)[没有满足要求]
     * 空间复杂度：开辟了一个数组，保存合并后的两个数组 O(m+n)
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int n=nums1.length,m=nums2.length,i=0,j=0,k=0;
        int[] nums=new int[n+m];
        int mid=(n+m)/2;
        while(i<n&&j<m)
            nums[k++]=(nums1[i]<nums2[j])?nums1[i++]:nums2[j++];
        while(i<n)
            nums[k++]=nums1[i++];
        while(j<m)
            nums[k++]=nums2[j++];
        if((m+n)%2==0)
            return (double) (nums[mid]+nums[mid-1])/2;
        else
            return nums[mid];
    }

    /**
     * 我们不需要将两个数组真的合并，我们只需要找到中位数在哪里就可以了。
     *
     * 用 len 表示合并后数组的长度，如果是奇数，我们需要知道第 （len+1）/2 个数就可以了，
     * 如果遍历的话需要遍历 int(len/2 ) + 1 次。
     * 如果是偶数，我们需要知道第 len/2和 len/2+1 个数，也是需要遍历 len/2+1 次。
     * 所以遍历的话，奇数和偶数都是 len/2+1 次。
     *
     * 返回中位数的话，奇数需要最后一次遍历的结果就可以了，偶数需要最后一次和上一次遍历的结果。
     * 所以我们用两个变量 left 和 right，right 保存当前循环的结果，在每次循环前将 right 的值赋给 left。
     * 这样在最后一次循环的时候，left 将得到 right 的值，也就是上一次循环的结果，
     * 接下来 right 更新为最后一次的结果。
     *
     * 时间复杂度：遍历 len/2+1 次，len=m+n，所以时间复杂度依旧是 O(m+n)[也没有满足要求]。
     * 空间复杂度：我们申请了常数个变量，也就是 m，n，len，left，right，aStart，bStart 以及 i。
     * 总共 8 个变量，所以空间复杂度是 O(1）。
     */
    public double findMedianSortedArrays2(int[] nums1,int[] nums2){
        int n=nums1.length,m=nums2.length;
        int len=m+n,aStart=0,bStart=0;
        int left=-1,right=-1;
        for(int i=0;i<len/2+1;i++){
            left=right;
            if(aStart<n&&(bStart>=m||nums1[aStart]<nums2[bStart]))
                right=nums1[aStart++];
            else
                right=nums2[bStart++];
        }
        if(len%2==0)
            return (double) (right+left)/2;
        else
            return right;
    }
    @Test
    public void test(){
        int[] nums1=new int[]{1,3};
        int[] nums2=new int[]{2};
        double res1=findMedianSortedArrays1(nums1,nums2);
        double res2=findMedianSortedArrays2(nums1,nums2);
        System.out.println(res1);
        System.out.println(res2);
    }
}
