package cn.xieyongjie.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：两数之和
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 * <p>
 * 示例：
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 总结：
 * 若需要用到flag标志的考虑能否直接return
 * 找不到结果可以进行向上抛出异常
 * 哈希表的containsKey()可以检查是否包含指定key
 * 注意哈希表的顺序
 *
 * @author YongJie-Xie
 * @data 08/12/2018
 */
public class TwoSum {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Show method result
        for (int i : new TwoSum().twoSum3(new int[]{2, 7, 11, 15}, 9)) {
            System.out.print(i + ", ");
        }
        System.out.println();

        System.out.println("执行时间：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 我的方法：
     * 暴力解决
     *
     * @param nums   整数数组
     * @param target 目标值
     * @return 数组中和为目标值的两个数
     */
    private int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 方法1：
     * 两遍哈希表
     *
     * @param nums   整数数组
     * @param target 目标值
     * @return 数组中和为目标值的两个数
     */
    private int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 方法2：
     * 一遍哈希表
     *
     * @param nums   整数数组
     * @param target 目标值
     * @return 数组中和为目标值的两个数
     */
    private int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
