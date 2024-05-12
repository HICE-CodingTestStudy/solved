package com.example.demo.heap;

import java.util.Map;
import java.util.PriorityQueue;

public class BronzeSilverGoldOut {
    //https://school.programmers.co.kr/learn/courses/30/lessons/172927
    //광물 캐기
    static class Section implements Comparable<Section> {
        int dia;
        int iron;
        int stone;
        int cost;

        public Section(int dia, int iron, int stone) {
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
            this.cost = 25 * dia + 5 * iron + stone;
        }

        @Override
        public int compareTo(Section o) {
            return o.cost - cost;
        }
    }

    public int solution(int[] picks, String[] minerals) {
        int dia = picks[0];
        int iron = picks[1];
        int stone = picks[2];
        PriorityQueue<Section> pq = new PriorityQueue<>();
        int ans = 0;
        for (int i = 0; i <= minerals.length / 5; i++) {
            int a = 0, b = 0, c = 0;
            for (int j = 0; j < 5; j++) {
                int index = i * 5 + j;

                //도끼가 넘치는 경우
                if (index >= minerals.length) break;

                //이 뒤는 도달 못함
                if ((dia + iron + stone) * 5 < index) break;
                if (minerals[index].equals("diamond")) a++;
                if (minerals[index].equals("iron")) b++;
                if (minerals[index].equals("stone")) c++;
            }
            pq.add(new Section(a, b, c));
        }

        while (!pq.isEmpty()) {
            int diaCost = pq.peek().dia + pq.peek().stone + pq.peek().iron;
            int ironCost = pq.peek().dia * 5 + pq.peek().iron + pq.peek().stone;
            int stoneCost = pq.poll().cost;

            //철철철철철다 의 경우 5철을 철로 다를 다로 캐는게 나음
            if (diaCost == ironCost && ironCost != stoneCost && iron > 0) {
                ans += ironCost;
                iron--;
                continue;
            }
            if (ironCost == stoneCost && stone > 0) {
                ans += stoneCost;
                stone--;
                continue;
            }

            //다이아도끼 순서대로 캠
            if (dia > 0) {
                ans += diaCost;
                dia--;
            } else if (iron > 0) {
                ans += ironCost;
                iron--;
            } else if (stone > 0) {
                ans += stoneCost;
                stone--;
            } else break;
        }
        return ans;
    }

}
