package week1.soobin;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class RemoteController {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int START_CHANNEL = 100;

    private static final Set<Character> broken = new HashSet<>();

    private static int solution(int N, int M) {
        if (N == 100) return 0;
        if (M == 10) return N > START_CHANNEL ? N - START_CHANNEL : START_CHANNEL - N;

        int currentMin = Integer.MAX_VALUE;
        if (N < 100) {
            for (int i = 100; i >= 0; i--) {
                String channel = String.valueOf(i);
                boolean canPushButton = true;

                for (char button : channel.toCharArray()) {
                    if (broken.contains(button)) {
                        canPushButton = false;
                        break;
                    }
                }
                if (!canPushButton) continue;

                currentMin = Math.min(Math.min(Math.abs(N - i) + channel.length(), START_CHANNEL - N), currentMin);
            }
            return Math.min(currentMin, START_CHANNEL - N);
        }

        for (int i = START_CHANNEL; i - N <= N - START_CHANNEL; i++) {
            String channel = String.valueOf(i);
            boolean canPushButton = true;

            for (char button : channel.toCharArray()) {
                if (broken.contains(button)) {
                    canPushButton = false;
                    break;
                }
            }
            if (!canPushButton) continue;

            currentMin = Math.min(Math.min((Math.abs(N - i)) + channel.length(), N - START_CHANNEL), currentMin);
        }
        return Math.min(currentMin, N - START_CHANNEL);
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) broken.add(st.nextToken().charAt(0));
        }

        bw.write(String.valueOf(solution(N, M)));
        bw.flush();
    }
}
