package week27.soobin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Line {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer> line;
    private static int[] memory;

    private static void parseInput() {
        try {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            memory = new int[N];
            for (int i = 0; i < N; i++)
                memory[i] = Integer.parseInt(st.nextToken());
        } catch (IOException e) {}
    }

    private static void printOutput() {
        try {
            for (int p : line) bw.write(String.format("%d ", p));
            bw.flush();
        } catch (IOException e) {}
    }

    private static void setLine() {
        line = new ArrayList<>();
        for (int i = memory.length - 1; i >= 0; i--)
            line.add(memory[i], i + 1);
    }

    public static void main(String[] args) {
        parseInput();
        setLine();
        printOutput();
    }
}
