import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Chunbae {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        String answer = solution(N, M) ? "YES" : "NO";
        System.out.println(answer);
    }

    private static boolean solution(long N, long M) {
        Set<Long> hearts = new HashSet<>();
        hearts.add(N);

        while (N != 1) {
            if (hearts.contains(M)) return true;
            if (N % 2 == 0) {
                N /= 2;
                hearts.add(N);
            }
            else {
                long low = (N - 1) / 2;
                long high = (N - 1) / 2 + 1;
                hearts.add(low);
                hearts.add(high);
                N = low % 2 == 1 ? low : high;
            }
        }

        hearts.add(N);
        return hearts.contains(M);
    }
}
