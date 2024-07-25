import java.io.*;

public class MooGame {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MAX = 25;

    private static int[] mooLen;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int K = findKthMoo(N);
        char answer = solution(K, N);
        System.out.println(answer);
    }

    private static int findKthMoo(int N) {
        mooLen = new int[MAX + 1];
        mooLen[0] = 3;
        for (int i = 1; i < MAX + 1; i++) {
            mooLen[i] = 2 * mooLen[i - 1] + i + 3;
            if (mooLen[i] >= N) return i;
        }
        return MAX;
    }

    private static char solution(int k, int n) {
        if (k == 0 || n <= 3) return n == 1 ? 'm' : 'o';

        if (n <= mooLen[k - 1]) return solution(k - 1, n); // 왼쪽 (k - 1)번째 Moo 수열
        if (n > mooLen[k - 1] + k + 3) return solution(k - 1, n - (mooLen[k - 1] + k + 3)); // 오른쪽 (k - 1)번째 Moo 수열
        return (n - mooLen[k - 1]) == 1 ? 'm' : 'o'; // 가운데 o (k + 2)개짜리 조각
    }
}
/*
S(0) = "m o o"
S(1) = "m o o m o o o m o o"
S(2) = "m o o m o o o m o o m o o o o m o o m o o o m o o"
 */