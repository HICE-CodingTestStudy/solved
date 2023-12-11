package week20.soobin;

import java.io.*;
import java.util.StringTokenizer;

interface TetrisBlock {
    int countPossibleCases();
}

public class Tetris {
    static abstract class AbstractBlock implements TetrisBlock {
        public int countPossibleCases() {
            int count = countHorizontal();
            return count + countVertical();
        }

        protected boolean isAllCellValid(int... cells) {
            for (int cell : cells)
                if (!isValid(cell)) return false;
            return true;
        }

        protected boolean isSameHeight(int one, int other) {
            return gameField[one] == gameField[other];
        }

        protected boolean isHeightDiffOne(int small, int tall) {
            return gameField[small] + 1 == gameField[tall];
        }

        protected boolean isHeightDiffTwo(int small, int tall) {
            return gameField[small] + 2 == gameField[tall];
        }

        protected int countHorizontal() { return 0;}
        protected int countVertical() { return 0; }
    }

    static class Block1 extends AbstractBlock {
        public int countPossibleCases() {
            int count = C; // 세로 상태일 때는 모든 열 가능

            // 가로 상태일 때는 네 칸이 모두 높이가 같아야 함
            for (int i = 0; i <= C - 4; i++) {
                if (!isAllCellValid(i, i + 1, i + 2, i + 3)) continue;
                if (isSameHeight(i, i + 1)
                        && isSameHeight(i + 1, i + 2)
                        && isSameHeight(i + 2, i + 3)) count++;
            }
            return count;
        }
    }

    static class Block2 extends AbstractBlock {
        public int countPossibleCases() {
            int count = 0;
            // 몇 도를 회전하든 모두 동일한 상태
            // 두 칸의 높이가 같으면 됨
            for (int i = 0; i < C; i++) {
                if (!isAllCellValid(i, i + 1)) continue;
                if (isSameHeight(i, i + 1)) count++;
            }
            return count;
        }
    }

    static class Block3 extends AbstractBlock {
        protected int countHorizontal() {
            int count = 0;
            // 왼쪽부터 두 칸의 높이가 같고 세 번째 칸의 높이는 한 칸 차이
            for (int i = 0; i <= C - 3; i++) {
                if (!isAllCellValid(i, i + 1, i + 2)) continue;
                if (isSameHeight(i, i + 1)
                        && isHeightDiffOne(i, i + 2)) count++;
            }
            return count;
        }

        protected int countVertical() {
            int count = 0;
            // 왼쪽 칸이 오른쪽 칸보다 한 칸 높음
            for (int i = 0; i <= C - 2; i++) {
                if (!isAllCellValid(i, i + 1)) continue;
                if (isHeightDiffOne(i + 1, i)) count++;
            }
            return count;
        }
    }

    static class Block4 extends AbstractBlock {
        protected int countHorizontal() {
            int count = 0;
            // 오른쪽부터 두 칸의 높이가 같고 첫 번째 칸의 높이는 한 칸 차이
            for (int i = 0; i <= C - 3; i++) {
                if (!isAllCellValid(i, i + 1, i + 2)) continue;
                if (isSameHeight(i + 1, i + 2)
                        && isHeightDiffOne(i + 1, i)) count++;
            }
            return count;
        }

        protected int countVertical() {
            int count = 0;
            // 오른쪽 칸이 왼쪽 칸보다 한 칸 높음
            for (int i = 0; i <= C - 2; i++) {
                if (!isAllCellValid(i, i + 1)) continue;
                if (isHeightDiffOne(i, i + 1)) count++;
            }

            return count;
        }
    }

    static class Block5 extends AbstractBlock {
        protected int countHorizontal() {
            int count = 0;
            // 세 칸의 높이가 같거나 가운데 칸이 양 옆 칸보다 한 칸 낮음
            for (int i = 0; i <= C - 3; i++) {
                if (!isAllCellValid(i, i + 1, i + 2)) continue;
                if (isSameHeight(i, i + 1) && isSameHeight(i + 1, i + 2)) count++;
                if (isSameHeight(i, i + 2) && isHeightDiffOne(i + 1, i)) count++;
            }
            return count;
        }

        protected int countVertical() {
            int count = 0;
            // 두 칸의 높이 차이가 한 칸
            for (int i = 0; i <= C - 2; i++) {
                if (!isAllCellValid(i, i + 1)) continue;
                if (isHeightDiffOne(i, i + 1) || isHeightDiffOne(i + 1, i)) count++;
            }
            return count;
        }
    }

    static class Block6 extends AbstractBlock {
        protected int countHorizontal() {
            int count = 0;
            // 세 칸의 높이가 같거나 맨 왼쪽 칸의 높이가 나머지 두 칸보다 한 칸 낮음
            for (int i = 0; i <= C - 3; i++) {
                if (!isAllCellValid(i, i + 1, i + 2)) continue;
                if (isSameHeight(i + 1, i + 2)
                        && (isSameHeight(i, i + 1) || isHeightDiffOne(i, i + 1))) count++;
            }
            return count;
        }

        protected int countVertical() {
            int count = 0;
            // 두 칸의 높이가 같거나 높이 차가 한 칸
            for (int i = 0; i <= C - 2; i++) {
                if (!isAllCellValid(i, i + 1)) continue;
                if (isSameHeight(i, i + 1) || isHeightDiffTwo(i + 1, i)) count++;
            }
            return count;
        }
    }

    static class Block7 extends AbstractBlock {
        protected int countHorizontal() {
            int count = 0;
            // 세 칸의 높이가 모두 같거나 맨 오른쪽 칸이 나머지 두 칸보다 한 칸 낮음
            for (int i = 0; i <= C - 3; i++) {
                if (!isAllCellValid(i, i + 1, i + 2)) continue;
                if (isSameHeight(i, i + 1)
                        && (isSameHeight(i + 1, i + 2) || isHeightDiffOne(i + 2, i))) count++;
            }
            return count;
        }

        protected int countVertical() {
            int count = 0;
            // 두 칸의 높이가 같거나 높이 한 칸 차이
            for (int i = 0; i <= C - 2; i++) {
                if (!isAllCellValid(i, i + 1)) continue;
                if (isSameHeight(i, i + 1) || isHeightDiffTwo(i, i + 1)) count++;
            }
            return count;
        }
    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int C, P;
    private static int[] gameField;

    private static boolean isValid(int i) {
        return i >= 0 && i < C;
    }

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            gameField = new int[C];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < C; i++)
                gameField[i] = Integer.parseInt(st.nextToken());
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static TetrisBlock createBlock(int type) {
        if (type == 1) return new Block1();
        if (type == 2) return new Block2();
        if (type == 3) return new Block3();
        if (type == 4) return new Block4();
        if (type == 5) return new Block5();
        if (type == 6) return new Block6();
        return new Block7();
    }

    private static int solution() {
        TetrisBlock block = createBlock(P);
        return block.countPossibleCases();
    }

    public static void main(String[] args) {
        parseInput();
        int answer = solution();
        printOutput(answer);
    }
}
