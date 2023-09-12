package queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Orange {
    //https://school.programmers.co.kr/learn/courses/30/lessons/138476
    //귤 고르기
    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int j : tangerine) {
            if (hm.containsKey(j))
                hm.put(j, hm.get(j)+1);
            else hm.put(j, 1);
        }
        ArrayList<Integer> values = new ArrayList<>(hm.values());
        values.sort(Comparator.reverseOrder());
        int ans = 0;
        int i = 0;
        while (ans < k) {
            ans += values.get(i);
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        Orange o = new Orange();
        o.solution(6, new int[]{1, 3, 2, 5, 4, 5, 2, 3});
    }
}
