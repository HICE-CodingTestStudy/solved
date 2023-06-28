package soobin;

import java.io.*;
import java.util.*;

public class CompressX {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        int[] array = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
            set.add(array[i]);
        }
        List<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int count = Collections.binarySearch(sorted, array[i]);
            sb.append(count).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
