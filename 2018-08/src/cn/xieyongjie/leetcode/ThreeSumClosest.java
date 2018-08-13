package cn.xieyongjie.leetcode;

import java.util.Arrays;

/**
 * 题目：最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * <p>
 * 示例：
 * 给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * <p>
 * 总结：
 * 三个不定值的时候可以选择固定一个，另外两个进行逼近。
 *
 * @author YongJie-Xie
 * @data 08/12/2018
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Show method result
        System.out.println(new ThreeSumClosest().threeSumClosest2(new int[]{-1, 2, 1, -4}, 1));

        System.out.println("执行时间：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 我的方法：
     * 暴力解决
     *
     * @param nums   包括 n 个整数的数组
     * @param target 目标值
     * @return 与 target 最接近的三数之和
     */
    private int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (target == sum) {
                        return sum;
                    }
                    closest = Math.abs(sum - target) < Math.abs(closest - target) ? sum : closest;
                }
            }
        }
        return closest;
    }

    /**
     * 方法：
     * 大神写的
     * 思路是先排序，然后固定第一个值，然后后面的值从两遍往中间逼近
     *
     * @param nums   包括 n 个整数的数组
     * @param target 目标值
     * @return 与 target 最接近的三数之和
     */
    private int threeSumClosest2(int[] nums, int target) {
        // 定义一个最大值和结果值
        int minVal = Integer.MAX_VALUE;
        int result = 0;
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int diff = Math.abs(sum - target);
                if (sum == target) {
                    return sum;
                } else if (sum > target) {
                    k--;
                    if (diff < minVal) {
                        minVal = diff;
                        result = sum;
                    }
                } else {
                    j++;
                    if (diff < minVal) {
                        minVal = diff;
                        result = sum;
                    }
                }
            }
        }
        return result;
    }
}
