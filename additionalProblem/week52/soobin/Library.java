import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Library {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MAX = 1000002;

    private static int[] availableSeats, sum;
    private static int N, minStart = Integer.MAX_VALUE, maxEnd = -1;

    public static void main(String[] args) throws Exception {
        init();
        prefixSum();
        printAnswer();
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        availableSeats = new int[MAX];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int st = Integer.parseInt(input[0]);
            int et = Integer.parseInt(input[1]);
            minStart = Integer.min(minStart, st);
            maxEnd = Integer.max(maxEnd, et);
            availableSeats[st]++;
            availableSeats[et + 1]--;
        }
    }

    private static void prefixSum() {
        sum = new int[MAX];
        sum[minStart] = availableSeats[minStart];
        for (int i = minStart + 1; i <= maxEnd; i++)
            sum[i] = availableSeats[i] + sum[i - 1];
    }

    private static void printAnswer() throws Exception {
        int Q = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < Q; i++) {
            int time = Integer.parseInt(input[i]);
            bw.write(sum[time] + "\n");
        }
        bw.flush();
    }
}
