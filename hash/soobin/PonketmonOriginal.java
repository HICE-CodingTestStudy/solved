package soobin;

import java.util.HashMap;
import java.util.Map;

public class PonketmonOriginal {
    public static int solution(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> picked = new HashMap<>();

        // 쉬운 방법을 생각 못 하고 그지같이 푼 방법
        // 포켓몬 별로 마리 수 저장
        for(int pmon: nums) map.put(pmon, map.getOrDefault(pmon, 0) + 1);
        for(int i = 0; i < nums.length / 2; i++) {
            // 이미 고르지 않은 애들만 필터링해서 하나씩 뽑기
            Map.Entry<Integer, Integer> pick = map.entrySet().stream()
                    .filter(entry -> picked.get(entry.getKey()) == null)
                    .findFirst().orElse(null);
            if(pick == null) break;
            // 생각해보니 이것도 value 0 할 필요 없어서 Map이 아니라 Set을 써야 했을 듯함
            picked.put(pick.getKey(), 0);
        }
        return picked.size();
    }

    public static void main(String[] args) {
        int[] nums = {3,3,3,2,2,4,4,4};
        System.out.println(solution(nums));
    }
}