package additional;

import java.util.*;

public class JewelShopping {
    //https://school.programmers.co.kr/learn/courses/30/lessons/67258
    //보석 쇼핑
    static HashMap<String, Integer> hm = new HashMap<>();
    static HashSet<String> hs = new HashSet<>();
    static Deque<Jewel> jewel = new LinkedList<>();
    static int left = 0, right = 0; //정답의 왼/오 인덱스
    static int minLength = Integer.MAX_VALUE; //보석이 전부 포함된 문자열 길이중 가장 짧은 것

    static class Jewel {
        int index;
        String jewel;

        public Jewel(int index, String jewel) {
            this.index = index;
            this.jewel = jewel;
        }
    }

    static void filledCase() {
        //채워졌으면 -> 길이를 최소한으로 줄임
        while (true) {
            String first = jewel.peekFirst().jewel;
            if (hm.get(first) > 1) {
                jewel.pollFirst();
                hm.put(first, hm.get(first) - 1);
            } else break;
        }
        //이전보다 길이가 줄었으면 갱신
        if (jewel.size() < minLength) {
            left = jewel.peekFirst().index;
            right = jewel.peekLast().index;
            minLength = jewel.size();
        }
        //맨 앞것을 빼줌
        String first = jewel.pollFirst().jewel;
        hs.remove(first);
        hm.put(first, hm.get(first) - 1);
    }

    public int[] solution(String[] gems) {
        hs.addAll(Arrays.asList(gems));
        int type = hs.size();
        if (type == 1) return new int[]{1, 1};
        hs.clear();
        for (int i = 0; i < gems.length; i++) {
            String gem = gems[i];
            if (hs.size() == type) {
                filledCase();
            }
            //안채워졌으면 넣기
            if (hs.size() < type) {
                jewel.addLast(new Jewel(i, gem));
                hs.add(gem);
                hm.putIfAbsent(gem, 0);
                hm.put(gem, hm.get(gem) + 1);
            }
        }
        if (hs.size() == type) filledCase();
        return new int[]{left + 1, right + 1};
    }
}
