package cn.xieyongjie.leetcode;

/**
 * 题目：寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * <p>
 * 示例1：
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例2：
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；或者返回索引 5， 其峰值元素为 6。
 * <p>
 * 提示：
 * 你的解法应该是 O(logN) 时间复杂度的。
 * <p>
 * 总结：
 * 学习一下夹逼法
 *
 * @author YongJie-Xie
 * @data 08/12/2018
 */
public class FindPeakElement {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Show method result
        System.out.println(new FindPeakElement().findPeakElement(new int[]{1,2,1,3,5,6,4}));

        System.out.println("执行时间：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 我的方法：
     * 先考虑特殊值，再渐渐逼近山峰
     *
     * @param nums 充当山高度的数组
     * @return 山峰的索引
     */
    private int findPeakElement(int[] nums) {
        if (nums.length == 1 || nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        int l = 0, r = nums.length - 1;
        while (true) {
            int i = (l + r) / 2;
            if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
                return i;
            }
            if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                l = i;
            } else {
                r = i;
            }
        }
    }
}
