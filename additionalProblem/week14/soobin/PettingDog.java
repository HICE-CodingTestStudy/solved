package week14.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class PettingDog {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /*
    * Ex)
    * 1) gap = 9 : 1 + 2 + 3 + 2 + 1 => 5일
    * 2) gap = 11 : 11 - 9 = 2 => 1 + 2 + 3 + 2 + [2] + 1 => 5 + 1일
    * 3) gap = 14 : 14 - 9 = 5 = 2 + 3 => 1 + 2 + 3 + [3 + 2] + 2 + 1 => 5 + 2일
     */
    private static long minDay(long monkey, long dog) {
        long gap = dog - monkey;
        if (gap < 4) return gap;

        long k = (long) Math.sqrt(gap);
        long square = (long) Math.pow(k, 2);

        if (gap == square) return 2 * k - 1;
        else if (gap > square && gap < square + k + 1) return 2 * k;
        return 2 * k + 1;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        long monkey = Long.parseLong(st.nextToken());
        long dog = Long.parseLong(st.nextToken());

        bw.write(String.valueOf(minDay(monkey, dog)));
        bw.flush();
    }
}
