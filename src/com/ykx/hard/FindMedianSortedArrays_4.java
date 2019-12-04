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

    /**
     * 上边的两种思路，时间复杂度都达不到题目的要求 O(log(m+n)O(log(m+n)。
     * 看到 log，很明显，我们只有用到二分的方法才能达到。
     * 我们不妨用另一种思路，题目是求中位数，其实就是求第 k 小数的一种特殊情况，而求第 k 小数有一种算法。
     *
     * 解法二中，我们一次遍历就相当于去掉不可能是中位数的一个值，也就是一个一个排除。
     * 由于数列是有序的，其实我们完全可以一半儿一半儿的排除。
     * 假设我们要找第 k 小数，我们可以每次循环排除掉 k/2 个数。
     *
     * 时间复杂度：每进行一次循环，我们就减少 k/2 个元素，所以时间复杂度是 O(log(k)，
     * 而 k=(m+n)/2，所以最终的复杂也就是 O(log(m+n）。
     *
     * 空间复杂度：虽然我们用到了递归，但是可以看到这个递归属于尾递归，
     * 所以编译器不需要不停地堆栈，所以空间复杂度为 O(1)O(1)。
     *
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
     *
     */
    public double findMedianSortedArrays3(int[] nums1,int[] nums2){
        int m=nums1.length,n=nums2.length;
        int left=(m+n+1)/2,right=(m+n+2)/2;//将奇数和偶数的情况合并，如果是奇数求出的left=right
        return (getThk(nums1,0,m-1,nums2,0,n-1,left)+getThk(nums1,0,m-1,nums2,0,n-1,right))/2;
    }
    //利用递归的方式求两个数组合起来中第k小的数
    public double getThk(int[] nums1,int start1,int end1,int[] nums2,int start2,int end2,int k){
        int len1=end1-start1+1,len2=end2-start2+1;
        if(len1>len2) //确保len1（即nums1）是较小的那个数组，这样如果有数组空了，一定是nums1
            return getThk(nums2,start2,end2,nums1,start1,end1,k);
        if(len1==0)//数组nums1为空时，第k小的数就在nums2的第k位置
            return nums2[start2+k-1];
        if(k==1)//当k=1时，第1小的数为nums[1]和nums[1]中较小的那一个
            return Math.min(nums1[start1],nums2[start2]);

        int i=start1+Math.min(len1,k/2)-1;
        int j=start2+Math.min(len2,k/2)-1;
        if(nums1[i]>nums2[j])//舍弃nums2[j]左边的部分（较小的那一部分）
            return getThk(nums1,start1,end1,nums2,j+1,end2,k-(j+1-start2));
        else//舍弃nums2[i]左边的部分
            return getThk(nums1,i+1,end1,nums2,start2,end2,k-(i+1-start1));
    }


    /**
     *https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
     */
    public double findMedianSortedArrays4(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) {
            return findMedianSortedArrays4(B,A); // 保证 m <= n
        }
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;
            if (j != 0 && i != m && B[j-1] > A[i]){ // i 需要增大
                iMin = i + 1;
            }
            else if (i != 0 && j != n && A[i-1] > B[j]) { // i 需要减小
                iMax = i - 1;
            }
            else { // 达到要求，并且将边界条件列出来单独考虑
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; } // 奇数的话不需要考虑右半部分

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0; //如果是偶数的话返回结果
            }
        }
        return 0.0;
    }

    @Test
    public void test(){
        int[] nums1=new int[]{1,2};
        int[] nums2=new int[]{3,4};
        double res1=findMedianSortedArrays1(nums1,nums2);
        double res2=findMedianSortedArrays2(nums1,nums2);
        double res3=findMedianSortedArrays3(nums1,nums2);
        double res4=findMedianSortedArrays4(nums1,nums2);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
    }
}
