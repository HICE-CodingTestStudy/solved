import java.io.*;

public class Meteor {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static char[][] originalPicture, restoredPicture;
    private static int R, S, gap = Integer.MAX_VALUE;

    public static void main(String[] args) {
        parseInput();
        countMeteorGap();
        restore();
        print();
    }

    private static void parseInput() {
        try {
            String[] input = br.readLine().split(" ");
            R = Integer.parseInt(input[0]);
            S = Integer.parseInt(input[1]);
            originalPicture = new char[R][S];

            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                for (int j = 0; j < S; j++)
                    originalPicture[i][j] = line.charAt(j);
            }
        } catch (IOException ignored) {}
    }

    private static void countMeteorGap() {
        for (int i = 0; i < S; i++) {
            int meteor = -1, ground = R;
            for (int j = 0; j < R; j++) {
                char c = originalPicture[j][i];
                if (c == 'X') meteor = Math.max(meteor, j);
                if (c == '#') ground = Math.min(ground, j);
            }
            if (meteor == -1) continue;
            gap = Math.min(gap, ground - meteor - 1);
        }
    }

    private static void restore() {
        restoredPicture = new char[R][S];

        for (int i = 0; i < R; i++)
            for (int j = 0; j < S; j++) restoredPicture[i][j] = '.';

        for (int i = 0; i < S; i++) {
            for (int j = 0; j < R; j++) {
                char original = originalPicture[j][i];
                if (original == 'X') restoredPicture[j + gap][i] = original;
                else if (original == '#') restoredPicture[j][i] = original;
            }
        }
    }

    private static void print() {
        try {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < S; j++)
                    bw.write(restoredPicture[i][j]);
                bw.write("\n");
            }
            bw.flush();
        } catch (IOException ignored) {}
    }
}