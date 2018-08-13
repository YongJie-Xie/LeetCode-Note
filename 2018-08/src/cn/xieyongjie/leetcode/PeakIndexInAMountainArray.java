package cn.xieyongjie.leetcode;

/**
 * 题目：山脉数组的峰顶索引
 * 我们把符合下列属性的数组 A 称作山脉：
 * · A.length >= 3
 * · 存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * 给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。
 * <p>
 * 示例1：
 * 输入：[0,1,0]
 * 输出：1
 * 示例2：
 * 输入：[0,2,1,0]
 * 输出：1
 * <p>
 * 提示：
 * 1. 3 <= A.length <= 10000
 * 2. 0 <= A[i] <= 10^6
 * 3. A 是如上定义的山脉
 * <p>
 * 总结：
 * 找规律
 *
 * @author YongJie-Xie
 * @data 08/12/2018
 */
public class PeakIndexInAMountainArray {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Show method result
        int[] A = new int[]{0,1,2,3,4,5,6,7,8, 2, 1, 0};
        System.out.println(new PeakIndexInAMountainArray().peakIndexInMountainArray(A));

        System.out.println("执行时间：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     *
     * @param A 确定为山脉的数组
     * @return 山峰的下标
     */
    private int peakIndexInMountainArray(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) {
                return i;
            }
        }
        return 0;
    }
}
