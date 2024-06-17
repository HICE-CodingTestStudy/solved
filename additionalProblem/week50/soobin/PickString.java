import java.io.*;
import java.util.*;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PickString {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Set<String> memo;
    private static boolean canChange;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String s = br.readLine();
            canChange = false;
            memo = new HashSet<>();
            solution(s);
            bw.write(canChange ? "1\n" : "0\n");
        }
        bw.flush();
    }

    private static void solution(String s) {
        if (canChange) return;

        int start = 0, count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) count++;
            else {
                if (count > 1) {
                    String sliced = s.substring(0, start) + s.substring(start + count);
                    if (!memo.contains(sliced)) {
                        memo.add(sliced);
                        solution(sliced);
                    }
                }
                start = i;
                count = 1;
            }
        }

        if (s.length() > 1 && count == s.length()) canChange = true;
    }
}
