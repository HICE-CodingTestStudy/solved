package week50;

import java.io.*;
import java.util.*;

public class BOJ23326 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int current = 0;
        boolean[] map = new boolean[N];

        TreeSet<Integer> treeSet = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken()) == 1;
            if(map[i]) treeSet.add(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1){
                int idx = Integer.parseInt(st.nextToken()) - 1;
                map[idx] = !map[idx];
                if(map[idx]){
                    treeSet.add(idx);
                } else {
                    treeSet.remove(idx);
                }
            } else if (cmd == 2) {
                int move = Integer.parseInt(st.nextToken());
                current = (current + move) % N;
            } else {
                if(treeSet.isEmpty()) {
                    sb.append(-1).append("\n");
                    continue;
                }
                int idx;
                Integer ceiling = treeSet.ceiling(current);
                if(ceiling == null) idx = treeSet.first() + (N - current);
                else idx = ceiling - current;
                sb.append(idx).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
