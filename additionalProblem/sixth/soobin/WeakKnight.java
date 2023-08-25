package sixth.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class WeakKnight {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int answer = 0;
        if (N == 1) answer = 1;
        else if (N == 2) answer = Math.min(4, (M + 1) / 2);
        else if (N >= 3 && M < 7) answer = Math.min(4, M);
        else answer = M - 2;

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
