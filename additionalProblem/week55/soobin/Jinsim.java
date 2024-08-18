import java.io.*;
import java.util.StringTokenizer;

public class Jinsim {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final String[] DIR = {" L\n", " R\n"};

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            solution();
        }
        bw.flush();
    }

    private static void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int limit = Integer.parseInt(st.nextToken()) - 1;
        int K = Integer.parseInt(st.nextToken());

        if (K >= limit) {
            String dir = K == limit ? DIR[0] : DIR[1];
            bw.write(limit + dir);
            return;
        }

        long n = findStage(limit, K);
        long total = sigma(n) * K;

        String dir;
        long x;
        if (n % 2 == 0) {
            x = -(n / 2) * K + (total - limit);
            dir = total == limit ? DIR[1] : DIR[0];
        } else {
            x = (n / 2 + 1) * K - (total - limit);
            dir = total == limit ? DIR[0] : DIR[1];
        }
        bw.write(x + dir);
    }

    private static long findStage(int limit, int K) {
        long left = 0, right = 10000;

        while (left < right) {
            long mid = (left + right) / 2;
            if (sigma(mid) * K < limit) left = mid + 1;
            else right = mid;
        }

        return left;
    }

    private static long sigma(long n) {
        return (n * (n + 1)) / 2;
    }
}
