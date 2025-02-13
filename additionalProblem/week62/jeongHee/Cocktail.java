package jeongHee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Cocktail {
    // https://www.acmicpc.net/problem/1033
    // 칵테일
    static final int[] prime = {2, 3, 5, 7};
    static List<Graph> list = new ArrayList<>();
    static int N;

    static class Info {
        int first, second, p, q;

        public Info(int first, int second, int p, int q) {
            this.first = first;
            this.second = second;
            this.p = p;
            this.q = q;
        }
    }

    static class Graph implements Comparable<Graph> {
        int index;
        List<Info> list;

        public Graph(int index, List<Info> list) {
            this.index = index;
            this.list = list;
        }

        @Override
        public int compareTo(Graph o) {
            return o.list.size() - list.size();
        }
    }

    static boolean isDividable(int[] arr, int p) {
        for (int i : arr) {
            if (i % p != 0) return false;
        }
        return true;
    }

    static void divide(int[] arr, int p) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] /= p;
        }
    }

    static void multiply(int[] arr, int p) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= p;
        }
    }

    static void preProcess(int[] arr) {
        for (int i : prime) {
            while (isDividable(arr, i)) {
                divide(arr, i);
            }
        }
    }

    static void fill(Info info, int[] arr, Queue<Info> remain) {
        if (arr[info.first] != 0 && arr[info.second] != 0) return;
        if (arr[info.first] == 0 && arr[info.second] == 0) {
            remain.offer(info);
            return;
        }
        if (arr[info.first] == 0) {
            int tf = info.first;
            info.first = info.second;
            info.second = tf;
            int tp = info.p;
            info.p = info.q;
            info.q = tp;
        }
        if (arr[info.first] % info.p == 0) {
            arr[info.second] = arr[info.first] / info.p * info.q;
            return;
        }
        multiply(arr, info.p);
        arr[info.second] = arr[info.first] / info.p * info.q;
        preProcess(arr);
    }

    static void solution() {
        Collections.sort(list);
        int[] ans = new int[N];
        Info start = list.get(0).list.get(0);
        ans[start.first] = start.p;
        ans[start.second] = start.q;
        Queue<Info> remain = new ArrayDeque<>();
        for (Graph graph : list) {
            for (Info now : graph.list) {
                fill(now, ans, remain);
            }
        }
        while (!remain.isEmpty()) {
            Info now = remain.poll();
            fill(now, ans, remain);
        }

        StringBuilder sb = new StringBuilder();
        for (int i : ans) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new Graph(i, new ArrayList<>()));
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int fir = Integer.parseInt(st.nextToken());
            int sec = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int[] arr = new int[]{p, q};
            preProcess(arr);
            list.get(fir).list.add(new Info(fir, sec, arr[0], arr[1]));
            list.get(sec).list.add(new Info(fir, sec, arr[0], arr[1]));
        }
        solution();
    }
}
