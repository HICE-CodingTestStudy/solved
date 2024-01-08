import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChoosingNumber {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final List<Integer> numbers = new ArrayList<>();

    private static int N, M, answer;

    private static void parse() {
        try {
            String[] line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]);
            M = Integer.parseInt(line[1]);

            for (int i = 0; i < N; i++) {
                int n = Integer.parseInt(br.readLine());
                numbers.add(n);
            }
        } catch (IOException e) {}
    }

    private static void print() {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static void init() {
        Collections.sort(numbers);
        answer = numbers.get(numbers.size() - 1) - numbers.get(0);
    }

    private static void solution() {
        int start = 0, end = 0;

        while (start < N - 1) {
            while (end < N && numbers.get(end) - numbers.get(start) < M) end++;
            if (end == N) return;
            answer = Math.min(answer, numbers.get(end) - numbers.get(start));
            start++;
        }
    }

    public static void main(String[] args) {
        parse();
        init();
        solution();
        print();
    }
}
