import java.io.*;

public class Three {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MOD = 1000000009;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        long answer = countThree(N);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static long countThree(int N) {
        if (N == 1) return 0;

        long answer = 2;
        N -= 2;
        while (N-- > 0) {
            answer *= 3;
            answer %= MOD;
        }

        return answer;
    }
}
