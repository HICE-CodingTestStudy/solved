package soobin;

import java.util.HashSet;

public class Ponketmon {
    public static int solution(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int pmon: nums) set.add(pmon);
        return nums.length / 2 <= set.size() ? nums.length / 2 : set.size();
    }

    public static void main(String[] args) {
        int[] nums = {3,3,3,2,2,4,4,4};
        System.out.println(solution(nums));
    }
}