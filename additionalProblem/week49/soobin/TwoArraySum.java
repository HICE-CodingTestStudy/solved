import java.io.*;
import java.util.*;

public class TwoArraySum {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int[] aSum = new int[N + 1];
        String[] input = br.readLine().split(" ");
        for (int i = 1; i <= N; i++)
            aSum[i] = aSum[i - 1] + Integer.parseInt(input[i - 1]);

        int M = Integer.parseInt(br.readLine());
        int[] bSum = new int[M + 1];
        input = br.readLine().split(" ");
        for (int i = 1; i <= M; i++)
            bSum[i] = bSum[i - 1] + Integer.parseInt(input[i - 1]);


        Map<Integer, Integer> bSumDup = new HashMap<>();
        for (int i = 1; i <= M; i++)
            for (int j = 0; j <= i - 1; j++) {
                int subArr = bSum[i] - bSum[j];
                bSumDup.put(subArr, bSumDup.getOrDefault(subArr, 0) + 1);
            }

        long answer = 0;
        for (int i = 1; i <= N; i++)
            for (int j = 0; j <= i - 1; j++) {
                int a = aSum[i] - aSum[j];
                if (!bSumDup.containsKey(T - a)) continue;
                int nB = bSumDup.get(T - a);
                answer += nB;
            }


        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
