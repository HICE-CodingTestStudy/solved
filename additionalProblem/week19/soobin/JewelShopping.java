package week19.soobin;

import java.util.*;

public class JewelShopping {
    private Set<String> getTotalJewelSet(String[] gems) {
        return new HashSet<>(List.of(gems));
    }

    private static Queue<int[]> getAllPossibleSections(String[] gems, int total) {
        Map<String, Integer> jewelWindow = new HashMap<>();
        Queue<int[]> candidates = new PriorityQueue<>(
                (o1, o2) -> (o1[1] - o1[0]) != (o2[1] - o2[0])
                        ? (o1[1] - o1[0]) - (o2[1] - o2[0])
                        : o1[0] - o2[0]
        );

        int end = 0;
        for (int start = 0; start < gems.length; start++) {
            while (jewelWindow.keySet().size() < total && end < gems.length) {
                jewelWindow.put(gems[end], jewelWindow.getOrDefault(gems[end], 0) + 1);
                end++;
            }

            if (jewelWindow.keySet().size() == total) candidates.add(new int[] {start + 1, end});

            if (jewelWindow.get(gems[start]) == 1) jewelWindow.remove(gems[start]);
            else jewelWindow.put(gems[start], jewelWindow.get(gems[start]) - 1);
        }

        return candidates;
    }

    public int[] solution(String[] gems) {
        Set<String> jewels = getTotalJewelSet(gems);
        int total = jewels.size();

        Queue<int[]> candidates = getAllPossibleSections(gems, total);

        return candidates.poll();
    }

    public static void main(String[] args) {
        JewelShopping j = new JewelShopping();
        String[] gems = new String[] {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        int[] answer = j.solution(gems);
        System.out.println(answer[0] + " " + answer[1]);
    }
}
