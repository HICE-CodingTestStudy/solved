import java.io.*;
import java.util.ArrayDeque;

public class Main {
    static int N;
    static String s;

    static boolean isValid(int w, int m, int index) {
        if (s.charAt(index) == 'M') m++;
        else w++;
        return (Math.abs(w - m) <= N);
    }

    static int solution() {
        int first = -1;
        int m = 0, w = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isValid(w, m, i)) {
                if (s.charAt(i) == 'M') m++;
                else w++;
                continue;
            }
            if (first == -1) {
                first = i;
                continue;
            }
            if (isValid(w, m, first)) {
                if (s.charAt(i) == 'M') m++;
                else w++;
                i--;
                first = -1;
                continue;
            }
            return m + w;
        }
        if (first == -1) return m + w;
        if (isValid(w, m, first)) return w + m + 1;
        return m + w;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        s = bf.readLine();
        System.out.println(solution());
    }
}