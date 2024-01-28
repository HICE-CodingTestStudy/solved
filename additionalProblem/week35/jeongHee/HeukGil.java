package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class HeukGil {
    //https://www.acmicpc.net/problem/1911
    //흙길 보수하기
    static int N, L;
    static List<Info> infos = new ArrayList<>();
    static class Info implements Comparable<Info> {
        int start;
        int end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Info o) {
            return start - o.start;
        }
    }

    static int solution() {
        int nullBbanZi = -1;
        int ans = 0;
        for (int i = 0; i < infos.size(); i++) {
            if (nullBbanZi > infos.get(i).end) continue;
            int pool = infos.get(i).end - Math.max(nullBbanZi, infos.get(i).start) + 1;
            int nextNullBbanZi = (int) Math.ceil((double) pool / (double) L);
            nullBbanZi = Math.max(nullBbanZi, infos.get(i).start) + nextNullBbanZi * L;
            ans += nextNullBbanZi;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            infos.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1));
        }
        Collections.sort(infos);
        System.out.println(solution());
    }
}
