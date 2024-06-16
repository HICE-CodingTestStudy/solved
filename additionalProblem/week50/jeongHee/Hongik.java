package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Hongik {
    //https://www.acmicpc.net/problem/23326
    //홍익 투어리스트
    static int N, Q, me;
    static boolean[] isSight;
    static Set<Integer> sight = new HashSet<>();

    static int solution() {
        if (isSight[me]) return 0;
        List<Integer> list = new ArrayList<>(sight);
        Collections.sort(list);
        int l = -1, r = list.size();
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) <= me) l = mid;
            else r = mid;
        }
        if (r < 0 || r >= list.size()) return -1;
        int ret = list.get(r) - me;
        if (list.get(r) < me) ret += N;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        isSight = new boolean[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            isSight[i] = !st.nextToken().equals("0");
            if (isSight[i]) sight.add(i);
        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(bf.readLine());
            int type = Integer.parseInt(st.nextToken());
            switch (type) {
                case 1:
                    int index = Integer.parseInt(st.nextToken());
                    isSight[index - 1] = !isSight[index - 1];
                    if (!sight.add(index - 1)) sight.remove(index - 1);
                    break;
                case 2:
                    int count = Integer.parseInt(st.nextToken());
                    me = (me + count) % N;
                    break;
                case 3:
                    System.out.println(solution());
            }
        }
    }

}
