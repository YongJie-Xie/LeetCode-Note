package cn.xieyongjie.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 题目：无重复字符的最长子串
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * <p>
 * 示例：
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列 而不是子串。
 * <p>
 * 总结：
 * Set集合的用法，contains()可以判断时候包含某元素
 * 要特别注意特殊情况
 * 窗口长度从0开始，只增不减，所以能找到最长字符串。
 * 优化的滑动窗口其实是把左边的下标移动到重复的字母上，而不是加一。
 * Map 可以用一个整数数组作为直接访问表来代替。（字符集比较小的时候）
 *
 * @author YongJie-Xie
 * @data 08/12/2018
 */
public class LongestSubstringWithoutRepeatingCharacters {
    private static int TIMES = 0;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Show method result
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring3("public int lengthOfLongestSubstring(String s) "));

        System.out.println("执行时间：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 我的方法：
     * 转换为char数组，然后用set集合进行查重
     *
     * @param s 给定字符串
     * @return 最长子串的长度
     */
    private int lengthOfLongestSubstring0(String s) {
        int length = 0;
        char[] ch = s.toCharArray();
        Set<Character> set;
        for (int i = 0; i < ch.length; i++) {
            set = new HashSet<>(10);
            for (int j = i; j < ch.length; j++) {
                TIMES++;
                char c2 = ch[j];
                if (set.contains(c2)) {
                    length = Math.max(length, j - i);
                    break;
                } else if (j == ch.length - 1) {
                    length = Math.max(length, j - i + 1);
                    break;
                }
                set.add(c2);
            }
            set.clear();
        }
        System.out.println("共执行 " + TIMES + " 次");
        return length == 0 ? s.length() : length;
    }

    /**
     * 方法1：
     * 暴力法，取出子字符串，然后逐个判断是否有重复的字符
     *
     * @param s 给定字符串
     * @return 最长子串的长度
     */
    private int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s, i, j)) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        System.out.println("共执行 " + TIMES + " 次");
        return ans;
    }

    /**
     * 方法1的附带方法，判断是否包含重复字符
     *
     * @param s     给定字符串
     * @param start 开始范围
     * @param end   结束范围
     * @return 子字符串中的字符是否唯一
     */
    private boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            TIMES++;
            Character ch = s.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }

    /**
     * 方法2：
     * 滑动窗口
     *
     * @param s 给定字符串
     * @return 最长子串的长度
     */
    private int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // 尝试扩大 [i, j] 的范围
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
            TIMES++;
        }
        System.out.println("共执行 " + TIMES + " 次");
        return ans;
    }

    /**
     * 方法3：
     * 优化的滑动窗口
     * 使用 HashMap
     *
     * @param s 给定字符串
     * @return 最长子串的长度
     */
    private int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        // 当前Character索引
        Map<Character, Integer> map = new HashMap<>(10);
        // 尝试扩大 [i, j] 的范围
        for (int j = 0, i = 0; j < n; j++) {
            TIMES++;
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        System.out.println("共执行 " + TIMES + " 次");
        return ans;
    }

    /**
     * 方法4：
     * 优化的滑动窗口（用一个整数数组作为直接访问表来替换 Map）
     *
     * @param s 给定字符串
     * @return 最长子串的长度
     */
    private int lengthOfLongestSubstring4(String s) {
        int n = s.length(), ans = 0;
        // 当前 Character 索引
        int[] index = new int[128];
        // 尝试扩大 [i, j] 的范围
        for (int j = 0, i = 0; j < n; j++) {
            // 判断 i 的位置是否需要被改变
            i = Math.max(index[s.charAt(j)], i);
            // 求出最大长度
            ans = Math.max(ans, j - i + 1);
            // 记录最大长度时的右区间下标 + 1，若 i 的位置需要修改时直接读取索引数组的值即可
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}
