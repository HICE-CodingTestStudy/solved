import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Good {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static long[] arr;
    private static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (isGood(arr[i], i)) {
                count++;
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println("\n" + count);
    }

    private static boolean isGood(long target, int idx) {
        int l = 0, r = N - 1;

        while (l < r) {
            if (l == idx) {
                l++;
                continue;
            }
            if (r == idx) {
                r--;
                continue;
            }

            long sum = arr[l] + arr[r];
            if (sum == target) return true;
            if (sum > target) r--;
            else l++;
        }

        return false;
    }
}
