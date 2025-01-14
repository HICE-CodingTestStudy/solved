package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.*;

public class Poham {
    //https://www.acmicpc.net/problem/2107
    //포함하는 구간
    /**
     * 포함하는거 나옴 -> 이전 정답들 돌면서 갱신
     * 가로지르는거 나옴
     * -> 연속적으로 가로지르는거면 그냥 현재 구간을 그걸로 교체
     * -> 중간에 포함하는게 껴있엇던경우 교체전에 이전 now 를 before에 추가해둠
     */
    static List<Info> infos = new ArrayList<>();
    static class Info implements Comparable<Info> {
        int l, r;

        public Info(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Info o) {
            if (o.l != l) return l - o.l;
            return r - o.r;
        }
    }


    static int solution() {
        Collections.sort(infos);
        Info now = infos.get(0);

        // befordAns에는 Info가 다음과 같은 의미로 쓰임 l:이전 구간의 끝자락값, r:이전 구간에 포함돼있던 구간 개수
        Queue<Info> beforeAns = new ArrayDeque<>();
        int ans = 0;
        int count = 0;
        for (int i = 1; i < infos.size(); i++) {
            if (now.r > infos.get(i).r) {
                count++;
                Queue<Info> next = new ArrayDeque<>();
                while (!beforeAns.isEmpty()) {
                    Info n = beforeAns.poll();
                    if (n.l > infos.get(i).r) {
                        n.r++;
                        next.offer(n);
                        ans = Math.max(ans, n.r);
                    }
                }
                beforeAns = next;
            } else {
                ans = Math.max(ans, count);
                if (count != 0) beforeAns.offer(new Info(now.r, count));
                count = 0;
                now = infos.get(i);
            }
        }
        ans = Math.max(ans, count);
        while (!beforeAns.isEmpty()) {
            ans = Math.max(ans, beforeAns.poll().r);
        }
        return ans;

    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            infos.add(new Info(l, r));
        }
        System.out.println(solution());
    }
}
