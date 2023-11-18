package week26.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class TwistedElectricWire {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] wires, lis;
    private static int N;

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            wires = new int[N];
            lis = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
                wires[i] = Integer.parseInt(st.nextToken()) - 1;
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int bs(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (lis[mid] < target) left = mid + 1;
            else right = mid;
        }

        return right;
    }

    private static int solution() {
        lis[0] = wires[0];
        int i = 1, j = 0;

        while (i < N) {
            if (lis[j] < wires[i]) {
                lis[j + 1] = wires[i];
                j++;
            } else {
                int idx = bs(0, j, wires[i]);
                lis[idx] = wires[i];
            }
            i++;
        }

        return j + 1;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = N - solution();
        printOutput(answer);
    }
}
