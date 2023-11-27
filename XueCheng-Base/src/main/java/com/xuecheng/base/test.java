package com.xuecheng.base;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassName test
 * @Author Chen9
 * @Date 2023/11/23 21:54
 * @VERSION 1.0
 * @Description LeetCode算法测试
 * @Program XueCheng-Project
 **/
public class test {
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int x = i; x < nums.length; x++) {
                if (nums[i] + nums[x] == target) {
                    result[0] = i;
                    result[1] = x;
                    return result;
                }
            }
        }
        return null;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};

        int target = 9;
        int[] ints = twoSum(nums, target);

    }
}
