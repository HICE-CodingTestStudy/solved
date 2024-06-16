import java.io.*;
import java.util.TreeSet;

public class Hongik {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static TreeSet<Integer> landmarks;
    private static int N, Q, current;

    public static void main(String[] args) throws Exception {
        parseInput();
        simulation();
    }

    private static void parseInput() throws Exception {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        Q = Integer.parseInt(input[1]);
        landmarks = new TreeSet<>();
        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            boolean isLandmark = Integer.parseInt(input[i]) == 1;
            if (isLandmark) landmarks.add(i);
        }
    }

    private static void simulation() throws Exception {
        while (Q-- > 0) {
            String[] input = br.readLine().split(" ");
            int command = Integer.parseInt(input[0]);
            if (command == 1)
                changeLandmark(Integer.parseInt(input[1]) - 1);
            else if (command == 2)
                move(Integer.parseInt(input[1]));
            else {
                int dist = findClosestLandmark();
                bw.write(dist + "\n");
            }
        }
        bw.flush();
    }

    private static void changeLandmark(int l) {
        if (landmarks.contains(l)) landmarks.remove(l);
        else landmarks.add(l);
    }

    private static void move(int x) {
        current = (current + x) % N;
    }

    private static int findClosestLandmark() {
        if (landmarks.isEmpty()) return -1;
        if (landmarks.contains(current)) return 0;

        Integer lb = landmarks.higher(current);
        return lb == null ? N - current + landmarks.first() : lb - current;
    }
}
