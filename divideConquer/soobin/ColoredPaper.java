package soobin;

import java.io.*;
import java.util.StringTokenizer;

public class ColoredPaper {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int white = 0;
    private static int blue = 0;
    private static char[][] paper;

    private static boolean isOneColor(int rowStart, int rowEnd, int colStart, int colEnd) {
        char seed = paper[rowStart][colStart];
        for (int i = rowStart; i <= rowEnd; i++)
            for (int j = colStart; j <= colEnd; j++)
                if (paper[i][j] != seed) return false;
        return true;
    }

    private static boolean isSinglePiece(int rowStart, int rowEnd, int colStart, int colEnd) {
        return rowStart == rowEnd && colStart == colEnd && rowStart == colStart && rowEnd == colEnd;
    }

    private static void splitPaper(int rowStart, int rowEnd, int colStart, int colEnd) {
        if (isSinglePiece(rowStart, rowEnd, colStart, colEnd)
                || isOneColor(rowStart, rowEnd, colStart, colEnd)) {
            if (paper[rowStart][colStart] == '0') white++;
            if (paper[rowStart][colStart] == '1') blue++;
            return;
        }

        int width = (colEnd - colStart) / 2;
        int height = (rowEnd - rowStart) / 2;

        // 왼쪽 위
        splitPaper(rowStart, rowStart + height, colStart, colStart + width);
        // 왼쪽 아래
        splitPaper(rowStart + height + 1, rowEnd, colStart, colStart + width);
        // 오른쪽 위
        splitPaper(rowStart, rowStart + height, colStart + width + 1, colEnd);
        // 오른쪽 아래
        splitPaper(rowStart + height + 1, rowEnd, colStart + width + 1, colEnd);
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        paper = new char[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++)
                paper[i][j] = st.nextToken().charAt(0);
        }

        splitPaper(0, N - 1, 0, N - 1);

        bw.write(String.valueOf(white));
        bw.newLine();
        bw.write(String.valueOf(blue));
        bw.flush();
    }
}
