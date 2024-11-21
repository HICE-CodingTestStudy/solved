import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class League {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static long[] sums, sqsums;
    private static int N, Q;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        sums = new long[N];
        sqsums = new long[N];

        st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        sums[0] = first;
        sqsums[0] = (long) Math.pow(first, 2);

        for (int i = 1; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            sums[i] = sums[i - 1] + n;
            sqsums[i] = sqsums[i - 1] + (long) Math.pow(n, 2);
        }
    }

    private static String solution() throws Exception {
        StringBuilder sb = new StringBuilder();

        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;

            long sum = sums[r] - (l == 0 ? 0 : sums[l - 1]);
            long sqsum = sqsums[r] - (l == 0 ? 0 : sqsums[l - 1]);
            long fun = (long) ((Math.pow(sum, 2) - sqsum) / 2);
            sb.append(fun).append("\n");
        }

        return sb.toString();
    }
}
