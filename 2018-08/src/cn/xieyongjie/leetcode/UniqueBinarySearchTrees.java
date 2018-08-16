package cn.xieyongjie.leetcode;

/**
 * 题目：不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例：
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 总结：
 * Catalan 数：
 * 满足递归式 h(n)= h(0)*h(n-1)+h(1)*h(n-2) + ... + h(n-1)*h(0) (n>=2)
 *
 * @author YongJie-Xie
 * @data 08/12/2018
 */
public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Show method result
        System.out.println(new UniqueBinarySearchTrees().numTrees(19));

        System.out.println("执行时间：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    private int numTrees(int n) {
        return CatalanNumber(n);
    }
    private int CatalanNumber(int n){
        if (n == 1) {
            return 1;
        }
        return CatalanNumber(n - 1) * 2 * (2 * n - 1) / (n + 1);
    }
}
