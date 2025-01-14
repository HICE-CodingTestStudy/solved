package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CardMixing {
    //https://www.acmicpc.net/problem/1091
    //카드 섞기
    static int N;
    static List<Set<Integer>> ans = new ArrayList<>();
    static int[] order;
    static Set<String> isVisited = new HashSet<>();

    static String makeString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]).append(" ");
        }
        return sb.toString();
    }

    static int solution() {
        int[] before = new int[N];
        for (int i = 0; i < N; i++) {
            before[i] = i;
        }
        int[] next = new int[N];
        int count = 0;
        while (true) {
            String s = makeString(before);
            if (isVisited.contains(s)) return -1;
            isVisited.add(s);
            boolean isValid = true;
            for (int i = 0; i < N; i++) {
                int index = i % 3;
                if (!ans.get(index).contains(before[i])) {
                    isValid = false;
                    break;
                }
            }
            if(isValid) return count;
            for (int i = 0; i < N; i++) {
                next[order[i]] = before[i];
            }
            before = next.clone();
            count++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        order = new int[N];
        for (int i = 0; i < 3; i++) {
            ans.add(new HashSet<>());
        }
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            ans.get(x).add(i);
        }
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            order[i] = x;
        }
        System.out.println(solution());
    }
}
