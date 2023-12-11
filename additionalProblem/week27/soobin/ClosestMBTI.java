package week27.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class ClosestMBTI {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int PIGEON_NUM = 33;

    private static String[] mbties;
    private static int N;

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            if (N >= PIGEON_NUM) return;

            mbties = new String[N];
            for (int i = 0; i < N; i++) mbties[i] = st.nextToken();
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.format("%d\n", answer));
        } catch (IOException e) {}
    }

    private static int compare(String one, String other) {
        int count = 0;
        for (int i = 0; i < 4; i++)
            if (one.charAt(i) != other.charAt(i)) count++;
        return count;
    }

    private static int solution() {
        if (N >= PIGEON_NUM) return 0;

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N - 2; i++)
            for (int j = i + 1; j < N - 1; j++)
                for (int k = j + 1; k < N; k++) {
                    int first = compare(mbties[i], mbties[j]);
                    int second = compare(mbties[j], mbties[k]);
                    int third = compare(mbties[k], mbties[i]);
                    answer = Math.min(answer, first + second + third);
                }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            parseInput();
            int answer = solution();
            printOutput(answer);
        }
        bw.flush();
    }
}
