import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RoadRepairing {
    private static class Puddle {
        int start, end;

        Puddle(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int start() { return start; }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final List<Puddle> puddles = new ArrayList<>();

    private static int L, plankEnd, numPlanks;

    public static void main(String[] args) {
        parseInput();
        countPlanks();
        printAnswer();
    }

    private static void parseInput() {
        try {
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            L = Integer.parseInt(input[1]);

            for (int i = 0; i < n; i++) {
                input = br.readLine().split(" ");
                int start = Integer.parseInt(input[0]);
                int end = Integer.parseInt(input[1]);
                puddles.add(new Puddle(start, end));
            }
            puddles.sort(Comparator.comparingInt(Puddle::start));
        } catch (IOException ignored) {}
    }

    private static void countPlanks() {
        plankEnd = puddles.get(0).start + L;
        numPlanks = 1;

        for (Puddle puddle : puddles) {
            // 웅덩이 시작 지점이 마지막 널빤지 끝보다 오른쪽이면
            if (puddle.start >= plankEnd) setNewPlank(puddle.start);
            // 웅덩이 끝 지점이 마지막 널빤지 끝보다 왼쪽이면(이미 놓인 널빤지로 덮어지면)
            if (puddle.end > plankEnd) appendPlank(puddle.end);
        }
    }

    private static void setNewPlank(int puddleStart) {
        plankEnd = puddleStart + L;
        numPlanks++;
    }

    private static void appendPlank(int puddleEnd) {
        int diff = puddleEnd - plankEnd;
        int numPlankToAppend = diff / L;
        if (diff % L != 0) numPlankToAppend++;  // 웅덩이 끝부분이 남으면

        plankEnd += L * numPlankToAppend;
        numPlanks += numPlankToAppend;
    }

    private static void printAnswer() {
        try {
            bw.write(String.valueOf(numPlanks));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
