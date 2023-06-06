package soobin;

import java.util.*;

public class LifeBoat {
    public static int solution(int[] people, int limit) {
        Arrays.sort(people);
        Deque<Integer> weights = new ArrayDeque<>();
        for (int person: people) weights.add(person);

        int answer = 0;
        while (weights.size() >= 2) {
            int heavy = weights.pollLast();
            int light = weights.peekFirst();
            if (heavy + light <= limit) weights.pollFirst();
            answer++;
        }

        if (!weights.isEmpty()) answer++;

        return answer;
    }

    public static void main(String[] args) {
        int[] people = {10,20,30,40,50,60,70,80,90};
        int limit = 100;
        System.out.println(solution(people, limit));
    }
}
