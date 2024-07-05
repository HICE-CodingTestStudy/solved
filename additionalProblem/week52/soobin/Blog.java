import java.io.*;
import java.util.*;

public class Blog {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int X = Integer.parseInt(input[1]);
        input = br.readLine().split(" ");

        Deque<Integer> deque = new ArrayDeque<>();
        int numDate = 1, sum = 0;
        for (int i = 0; i < X; i++) {
            int n = Integer.parseInt(input[i]);
            sum += n;
            deque.addLast(n);
        }

        int maxVisitor = sum;
        for (int i = X; i < N; i++) {
            int n = Integer.parseInt(input[i]);
            sum -= deque.pollFirst();
            sum += n;
            deque.addLast(n);
            if (sum == maxVisitor) numDate++;
            if (sum > maxVisitor) {
                maxVisitor = sum;
                numDate = 1;
            }
        }

        if (maxVisitor == 0) bw.write("SAD");
        else bw.write(maxVisitor + "\n" + numDate);
        bw.flush();
    }
}
