import java.io.*;
import java.util.*;

public class Slime {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MOD = 1000000007;

    private static Queue<Long> slimes;
    private static int N;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            parseInput();
            long answer = solution();
            bw.write(answer + "\n");
        }
        bw.flush();
    }

    private static void parseInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        slimes = new PriorityQueue<>();
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++)
            slimes.add(Long.parseLong(input[i]));
    }

    private static long solution() {
        long answer = 1;
        while (slimes.size() > 1) {
            long newSlime = slimes.poll() * slimes.poll();
            answer *= newSlime % MOD;
            answer %= MOD;
            slimes.add(newSlime);
        }

        return answer % MOD;
    }
}
