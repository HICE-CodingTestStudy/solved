import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lecture {
    //https://www.acmicpc.net/problem/2109
    //순회 강연
    static int N;

    static class Info {
        int d, p;

        public Info(int d, int p) {
            this.d = d;
            this.p = p;
        }


    }

    static Comparator<Info> orderByD = new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            return o2.d - o1.d;
        }
    };

    static Comparator<Info> orderByP = new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            return o2.p - o1.p;
        }
    };
    static PriorityQueue<Info> infos = new PriorityQueue<>(orderByD);
    static PriorityQueue<Info> pq = new PriorityQueue<>(orderByP);

    static int solution() {
        if(infos.isEmpty()) return 0;
        int ans = 0;
        int beforeD = infos.peek().d;
        for (int i = beforeD; i >= 1; i--) {
            while (!infos.isEmpty() && infos.peek().d == i) {
                pq.add(infos.poll());
            }
            if (!pq.isEmpty()) ans += pq.poll().p;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            infos.add(new Info(d, p));
        }
        System.out.println(solution());
    }
}
