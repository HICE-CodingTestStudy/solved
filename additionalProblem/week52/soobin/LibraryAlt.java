import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/***
 * 요상한 풀이
 */
public class LibraryAlt {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] start, end;
    private static int N;

    public static void main(String[] args) throws Exception {
        init();
        solution();
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        start = new int[N];
        end = new int[N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            start[i] = Integer.parseInt(input[0]);
            end[i] = Integer.parseInt(input[1]);
        }
        Arrays.sort(start);
        Arrays.sort(end);
    }

    private static void solution() throws Exception {
        int Q = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < Q; i++) {
            int time = Integer.parseInt(input[i]);
            int startBefore = countStartBefore(time);
            int endBefore = countEndBefore(time);
            bw.write((startBefore - endBefore) + "\n");
        }
        bw.flush();
    }

    private static int countStartBefore(int time) {
        int left = 0, right = N;

        while (left < right) {
            int mid = (left + right) / 2;
            if (start[mid] <= time) left = mid + 1;
            else right = mid;
        }

        return right;
    }

    private static int countEndBefore(int target) {
        int left = 0, right = N;

        while (left < right) {
            int mid = (left + right) / 2;
            if (end[mid] >= target) right = mid;
            else left = mid + 1;
        }

        return right;
    }
}
