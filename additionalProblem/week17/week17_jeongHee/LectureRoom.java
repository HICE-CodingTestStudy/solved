package additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LectureRoom {
    //https://www.acmicpc.net/problem/1374
    //강의실
    static class TimeInfo {
        long start;
        long end;

        public TimeInfo(long start, long end) {
            this.start = start;
            this.end = end;
        }

    }

    static Comparator<TimeInfo> startFast = new Comparator<TimeInfo>() {
        @Override
        public int compare(TimeInfo o1, TimeInfo o2) {
            if (o1.start != o2.start) {
                if (o1.start > o2.start) return 1;
                else return -1;
            }
            if (o1.end != o2.end) {
                if (o1.end > o2.end) return 1;
                else return -1;
            }
            return 0;
        }
    };

    static Comparator<TimeInfo> endFast = new Comparator<TimeInfo>() {
        @Override
        public int compare(TimeInfo o1, TimeInfo o2) {
            if (o1.end != o2.end) {
                if (o1.end > o2.end) return 1;
                else return -1;
            }
            if (o1.start != o2.start) {
                if (o1.start > o2.start) return 1;
                else return -1;
            }
            return 0;
        }
    };
    static int N;
    static HashMap<Long, PriorityQueue<TimeInfo>> hm = new HashMap<>();
    static PriorityQueue<TimeInfo> lectures = new PriorityQueue<>(startFast);
    static ArrayList<Long> keys = new ArrayList<>();


    static int solution() {
        PriorityQueue<TimeInfo> pq = new PriorityQueue<>(endFast);
        int ans = 1;
        while (!lectures.isEmpty()) {
            TimeInfo addedLecture = lectures.poll();
            while (!pq.isEmpty() && pq.peek().end <= addedLecture.start) {
                pq.poll();
            }
            pq.add(addedLecture);
            ans = Math.max(pq.size(), ans);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            st.nextToken();
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            lectures.add(new TimeInfo(start, end));
        }
        keys.addAll(hm.keySet());
        Collections.sort(keys);
        System.out.println(solution());
    }
}
