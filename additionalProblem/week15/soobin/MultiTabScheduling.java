package week15.soobin;

import java.io.*;
import java.util.*;

public class MultiTabScheduling {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Set<Integer> currentPlugged;
    private static int[] devices;
    private static int N, K;

    private static void parseInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        devices = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++)
            devices[i] = Integer.parseInt(st.nextToken());
    }

    private static int findDeviceToPullOut(int startIdx) {
        int toPullOut = (int) (new ArrayList(currentPlugged)).get(0), pullOutIdx = 0;
        for (int plugged : currentPlugged) {
            int nextIdx = K;
            for (int j = startIdx; j < K; j++) {
                if (devices[j] == plugged) {
                    nextIdx = j;
                    break;
                }
            }

            if (nextIdx > pullOutIdx) {
                toPullOut = plugged;
                pullOutIdx = nextIdx;
            }
        }

        return toPullOut;
    }

    private static int solution() {
        currentPlugged = new HashSet<>();
        int numCurrentPlugged = 0, answer = 0;
        for (int i = 0; i < K; i++) {
            int device = devices[i];
            if (currentPlugged.contains(device)) continue; // 이미 꽂혀있는 기기는 고려할 필요 없음

            // 자리가 남아 있으면 역시나 고려할 필요 없음
            if (numCurrentPlugged < N) {
                currentPlugged.add(device);
                numCurrentPlugged++;
                continue;
            }

            // 지금 사용 중인 기기 중에 미래에 가장 오랫동안 사용되지 않을 기기를 뽑아버림
            int deviceToPullOut = findDeviceToPullOut(i + 1);
            currentPlugged.remove(deviceToPullOut);
            currentPlugged.add(device);
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        parseInput();
        bw.write(String.valueOf(solution()));
        bw.flush();
    }
}
