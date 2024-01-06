package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Thief {
    //https://www.acmicpc.net/problem/1202
    //보석 도둑
    static class Jewel {
        int m, v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }

    static Comparator<Jewel> sortByM = new Comparator<Jewel>() {
        @Override
        public int compare(Jewel o1, Jewel o2) {
            return o1.m - o2.m;
        }
    };
    static Comparator<Jewel> sortByV = new Comparator<Jewel>() {
        @Override
        public int compare(Jewel o1, Jewel o2) {
            return o2.v - o1.v;
        }
    };
    static int N, K;
    static List<Jewel> jewels = new ArrayList<>();
    static List<Integer> bags = new ArrayList<>();
    static BigInteger ans = BigInteger.ZERO;

    static void solution() {
        Collections.sort(jewels, sortByM);
        Collections.sort(bags);
        PriorityQueue<Jewel> canSelect = new PriorityQueue<>(sortByV);
        int jewelIndex = 0;
        for (Integer bag : bags) {
            while (jewelIndex<jewels.size() && jewels.get(jewelIndex).m <= bag) {
                canSelect.add(jewels.get(jewelIndex));
                jewelIndex++;
            }
            if (canSelect.isEmpty()) continue;
            ans = ans.add(BigInteger.valueOf(canSelect.poll().v));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(bf.readLine()));
        }
        solution();
        System.out.println(ans);
    }
}
