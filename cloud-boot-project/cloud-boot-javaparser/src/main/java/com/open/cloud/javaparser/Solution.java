package com.open.cloud.javaparser;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] solution = new int[2];
        Hashtable<Integer, Integer> H = new Hashtable<Integer, Integer>(numbers.length);
        // O(n) time for adding each element to the Hash table.
        for (int i = 0; i < numbers.length; i++) {
            H.put(numbers[i], i);
        }
        // Lookup for element index2 such that index2==(target-index1) (O(1))
        // O(1) for each lookup, O(n) total.
        for (int i = 0; i < numbers.length; i++) {
            if (H.get(target - numbers[i]) != null && H.get(target - numbers[i]) != i) {
                solution[0] = i + 1;
                solution[1] = H.get(target - numbers[i]) + 1;
                break;
            } else {
                continue;
            }
        }
        return solution;
    }

    /**
     * calculate sum of two number
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            Integer index1 = map.get(target - numbers[i]);
            if (index1 != null) {
                return new int[]{index1, i + 1};
            }
            map.put(numbers[i], i + 1);
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

