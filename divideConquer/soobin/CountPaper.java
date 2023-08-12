package soobin;

import java.io.*;
import java.util.StringTokenizer;

public class CountPaper {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int minus = 0;
    private static int zero = 0;
    private static int plus = 0;
    private static int[][] paper;

    private static boolean isOneValue(int rowStart, int rowEnd, int colStart, int colEnd) {
        int seed = paper[rowStart][colStart];
        for (int i = rowStart; i <= rowEnd; i++)
            for (int j = colStart; j <= colEnd; j++)
                if (paper[i][j] != seed) return false;
        return true;
    }

    private static boolean isSinglePiece(int rowStart, int rowEnd, int colStart, int colEnd) {
        return rowStart == rowEnd && colStart == colEnd && rowStart == colStart && rowEnd == colEnd;
    }

    private static void splitPaper(int rowStart, int rowEnd, int colStart, int colEnd) {
        if (isSinglePiece(rowStart, rowEnd, colStart, colEnd) || isOneValue(rowStart, rowEnd, colStart, colEnd)) {
            if (paper[rowStart][colStart] == -1) minus++;
            if (paper[rowStart][colStart] == 0) zero++;
            if (paper[rowStart][colStart] == 1) plus++;
            return;
        }

        int width = (colEnd - colStart) / 3;
        int height = (rowEnd - rowStart) / 3;

        // 1
        splitPaper(rowStart, rowStart + height, colStart, colStart + width);
        // 2
        splitPaper(rowStart, rowStart + height, colStart + width + 1, colStart + (width * 2) + 1);
        // 3
        splitPaper(rowStart, rowStart + height, colStart + (width * 2) + 2, colEnd);

        // 4
        splitPaper(rowStart + height + 1, rowStart + (height * 2) + 1, colStart, colStart + width);
        // 5
        splitPaper(rowStart + height + 1, rowStart + (height * 2) + 1, colStart + width + 1, colStart + (width * 2) + 1);
        // 6
        splitPaper(rowStart + height + 1, rowStart + (height * 2) + 1, colStart + (width * 2) + 2, colEnd);

        // 7
        splitPaper(rowStart + (height * 2) + 2, rowEnd, colStart, colStart + width);
        // 8
        splitPaper(rowStart + (height * 2) + 2, rowEnd, colStart + width + 1, colStart + (width * 2) + 1);
        // 9
        splitPaper(rowStart + (height * 2) + 2, rowEnd, colStart + (width * 2) + 2, colEnd);
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++)
                paper[i][j] = Integer.parseInt(st.nextToken());
        }

        splitPaper(0, N-1, 0, N-1);

        bw.write(String.format("%d\n", minus));
        bw.write(String.format("%d\n", zero));
        bw.write(String.format("%d\n", plus));
        bw.flush();
    }
}
