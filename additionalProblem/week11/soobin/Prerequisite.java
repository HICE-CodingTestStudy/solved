package week11.soobin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Prerequisite {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer>[] prerequisite;
    private static int[] numPre;

    private static int getLevel(int x) {
        if (prerequisite[x].size() == 0) return numPre[x] = 1;

        if (numPre[x] > 0) return numPre[x];

        int num = 1;
        for (int pre : prerequisite[x])
            num = Math.max(num, getLevel(pre) + 1);
        return numPre[x] = num;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        prerequisite = new List[N];
        numPre = new int[N];
        for (int i = 0; i < N; i++)
            prerequisite[i] = new ArrayList<>();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            prerequisite[B - 1].add(A - 1);
        }

        for (int i = 0; i < N; i++)
            bw.write(String.format("%d ", getLevel(i)));
        bw.flush();
    }
}
