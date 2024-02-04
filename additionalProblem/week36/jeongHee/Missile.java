package queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Missile {
    //https://school.programmers.co.kr/learn/courses/30/lessons/181188
    //요격 시스템
    PriorityQueue<Coordinate> toIn = new PriorityQueue<>(startAsc);
    PriorityQueue<Coordinate> toOut = new PriorityQueue<>(endAsc);

    static class Coordinate implements Comparable<Coordinate> {
        int start;
        int end;

        @Override
        public int compareTo(Coordinate o) {
            return start - o.start;
        }

        public Coordinate(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static Comparator<Coordinate> startAsc = new Comparator<Coordinate>() {
        @Override
        public int compare(Coordinate o1, Coordinate o2) {
            return o1.start - o2.start;
        }
    };
    static Comparator<Coordinate> endAsc = new Comparator<Coordinate>() {
        @Override
        public int compare(Coordinate o1, Coordinate o2) {
            return o1.end - o2.end;
        }
    };

    public int solution(int[][] targets) {
        for (int i = 0; i < targets.length; i++) {
            toIn.add(new Coordinate(targets[i][0], targets[i][1]));
        }
        int ans = 1;
        toOut.add(toIn.poll());
        while (!toIn.isEmpty()) {
            if (toOut.isEmpty() || toOut.peek().end <= toIn.peek().start) {
                toOut.clear();
                toOut.add(toIn.poll());
                ans++;
                continue;
            }
            toOut.add(toIn.poll());

        }
        return ans;
    }

}
