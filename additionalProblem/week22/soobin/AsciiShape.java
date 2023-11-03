package week22.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class AsciiShape {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int H, W;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int solution() {
        int area = 0;
        try {
            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                boolean isBeforeLine = false;
                for (int j = 0; j < W; j++) {
                    char c = line.charAt(j);
                    if (isBeforeLine) area++;
                    if (c == '/' || c == '\\')
                        isBeforeLine = !isBeforeLine;
                }
            }
        } catch (IOException e) {}
        return area;
    }

    public static void main(String[] args) throws IOException {
        parseInput();
        int answer = solution();
        printOutput(answer);
    }
}
