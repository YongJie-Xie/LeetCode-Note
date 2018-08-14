package cn.xieyongjie.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：两个排序数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
 * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 * 你可以假设 nums1 和 nums2 均不为空。
 * <p>
 * 示例1：
 * nums1 = [1, 3]
 * nums2 = [2]
 * 中位数是 2.0
 * 示例2：
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 中位数是 (2 + 3)/2 = 2.5
 * <p>
 * 总结：
 * 使用Arrays.copyOfRange()方法克隆数组比使用循环克隆数组效率高得多
 *
 *
 * @author YongJie-Xie
 * @data 08/12/2018
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Show method result

        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays2(new int[]{0, 2, 7, 10}, new int[]{1, 3, 4, 5, 8, 9}));

        System.out.println("执行时间：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 我的方法：
     * 合并两个数组到List，然后排序取出中位数
     *
     * @param nums1 有序数组
     * @param nums2 有序数组
     * @return 两个有序数组的中位数
     */
    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> integers = new ArrayList<>();
        for (int n1 : nums1) {
            integers.add(n1);
        }
        for (int n2 : nums2) {
            integers.add(n2);
        }
        Integer[] num = integers.toArray(new Integer[]{});
        Arrays.sort(num);
        if (num.length % 2 == 1) {
            return num[num.length / 2];
        } else {
            return (num[num.length / 2] + num[num.length / 2 - 1]) / 2.0;
        }
    }

    /**
     * 方法1：
     * 递归法
     *
     * @param nums1 有序数组
     * @param nums2 有序数组
     * @return 两个有序数组的中位数
     */
    private double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // 确保 m<=n
        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && nums2[j - 1] > nums1[i]) {
                // i 太小
                iMin = i + 1;
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                // i 太大
                iMax = i - 1;
            } else {
                // i 刚好
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    /**
     * 方法2：
     * 大神的方法
     * 思路是 Divide & Conquer 割 分治，具体还不是很懂
     * TODO 经典题目，必须往回看 https://hk029.gitbooks.io/leetbook 有解释
     *
     * @param nums1 有序数组
     * @param nums2 有序数组
     * @return 两个有序数组的中位数
     */
    private double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, left = (m + n + 1) / 2, right = (m + n + 2) / 2;
        return (findKth(nums1, nums2, left) + findKth(nums1, nums2, right)) / 2.0;
    }

    /**
     * 方法2的附带方法，递归
     *
     * @param nums1 分割后的数组
     * @param nums2 分割后的数组
     * @param k
     * @return
     */
    private int findKth(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        // 长度短的在前
        if (m > n) {
            return findKth(nums2, nums1, k);
        }
        // 短的肯定会先清空，所以如果短的为空了，直接取另一个的中间值
        if (m == 0) {
            return nums2[k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[0], nums2[0]);
        }
        int i = Math.min(m, k / 2), j = Math.min(n, k / 2);
        if (nums1[i - 1] > nums2[j - 1]) {
            return findKth(nums1, Arrays.copyOfRange(nums2, j, n), k - j);
        } else {
            return findKth(Arrays.copyOfRange(nums1, i, m), nums2, k - i);
        }
    }
}
