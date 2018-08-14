package cn.xieyongjie.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：最小时间差
 * 给定一个 24 小时制（小时:分钟）的时间列表，找出列表中任意两个时间的最小时间差并已分钟数表示。
 * <p>
 * 示例：
 * 输入: ["23:59","00:00"]
 * 输出: 1
 * <p>
 * 提示：
 * 1. 列表中时间数在 2~20000 之间。
 * 2. 每个时间取值在 00:00~23:59 之间。
 * <p>
 * 总结：
 *
 * @author YongJie-Xie
 * @data 08/12/2018
 */
public class MinimumTimeDifference {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Show method result
        List<String> strings = new ArrayList<>();
        strings.add("23:59");
        strings.add("21:53");
        strings.add("22:39");
        strings.add("23:38");
        strings.add("08:11");
        strings.add("23:02");
        strings.add("10:21");
        strings.add("00:01");
        System.out.println(new MinimumTimeDifference().findMinDifference(strings));

        System.out.println("执行时间：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 我的方法：
     * 把时间转换为分钟，然后排序对比。
     *
     * @param timePoints 时间列表
     * @return 最小时间差
     */
    private int findMinDifference(List<String> timePoints) {
        int[] times = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            String[] strs = timePoints.get(i).split(":");
            times[i] = Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
        }
        Arrays.sort(times);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < times.length; i++) {
            int diff;
            if (i < times.length - 1) {
                diff = times[i + 1] - times[i];

            } else {
                diff = 1440 - times[i] + times[0];
            }
            if (diff == 0) {
                return 0;
            }
            min = min < diff ? min : diff;
        }
        return min;
    }

    /**
     * 方法1：
     * 大神的方法
     * 思路是利用数组来记录时间点，然后通过计算数组最小差来确定最小时间差。
     *
     * @param timePoints 时间列表
     * @return 最小时间差
     */
    private int findMinDifference1(List<String> timePoints) {
        if (timePoints == null || timePoints.size() == 0) {
            return 0;
        }
        if (timePoints.size() > 1440) {
            return 0;
        }
        //都转换成分钟保存
        //int[] arr= new int[timePoints.size()];
        //时间的范围是0-1440 所以使用一个大小为1441的数组记录该时间点是否出现
        int[] time = new int[1440];
        // int index=0;
        for (String s : timePoints) {
            // 利用 ascii 优化
            char[] hour = s.substring(0, 2).toCharArray();
            char[] minu = s.substring(3).toCharArray();
            int t = ((hour[0] - 48) * 10 + (hour[1] - 48)) * 60 + ((minu[0] - 48) * 10 + (minu[1] - 48));
            if (time[t] == 0) {
                time[t]++;
            } else {
                return 0;
            }
        }
        int min = 1440;
        int pre = -1;
        int first = -1, last = -1;
        for (int i = 0; i < 1440; i++) {
            if (time[i] != 0) {
                if (pre > -1 && Math.abs(i - pre) < min) {
                    min = Math.abs(i - pre);
                }
                if (first == -1) {
                    first = i;
                }
                pre = i;
                last = i;
            }
        }
        int s = (1440 - last) + first;
        min = min < s ? min : s;
        return min;
    }
}
