package soobin;

import java.util.HashMap;
import java.util.Map;

public class PonketmonOriginal {
    public static int solution(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> picked = new HashMap<>();

        for(int pmon: nums) map.put(pmon, map.getOrDefault(pmon, 0) + 1);
        for(int i = 0; i < nums.length / 2; i++) {
            Map.Entry<Integer, Integer> pick = map.entrySet().stream()
                    .filter(entry -> picked.get(entry.getKey()) == null)
                    .findFirst().orElse(null);
            if(pick == null) break;
            picked.put(pick.getKey(), 0);
        }
        return picked.size();
    }

    public static void main(String[] args) {
        int[] nums = {3,3,3,2,2,4,4,4};
        System.out.println(solution(nums));
    }
}