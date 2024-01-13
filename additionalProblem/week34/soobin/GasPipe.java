import java.io.*;

public class GasPipe {
    interface Block {
        int changeDirection(int prevDirection);

        static Block getBlockByType(char type) {
            switch (type) {
                case VerticalStraight.BLOCK_TYPE: return new VerticalStraight();
                case HorizontalStraight.BLOCK_TYPE: return new HorizontalStraight();
                case Cross.BLOCK_TYPE: return new Cross();
                case LeftTopCurved.BLOCK_TYPE: return new LeftTopCurved();
                case LeftBottomCurved.BLOCK_TYPE: return new LeftBottomCurved();
                case RightBottomCurved.BLOCK_TYPE: return new RightBottomCurved();
                case RightTopCurved.BLOCK_TYPE: return new RightTopCurved();
                default: return null;
            }
        }
    }
    static class StraightBlock implements Block {
        public int changeDirection(int prevDirection) {
            return prevDirection;
        }

        protected static boolean isStraight(int dir1, int dir2) {
            return Math.abs(dir1 - dir2) == 2;
        }
    }
    static class VerticalStraight extends StraightBlock {
        public static final char BLOCK_TYPE = '|';

        public static boolean isVerticalStraight(int dir1, int dir2) {
            return isStraight(dir1, dir2) && (dir1 == UP || dir1 == DOWN);
        }
    }
    static class HorizontalStraight extends StraightBlock {
        public static final char BLOCK_TYPE = '-';

        public static boolean isHorizontalStraight(int dir1, int dir2) {
            return isStraight(dir1, dir2) && (dir1 == LEFT || dir1 == RIGHT);
        }
    }
    static class Cross extends StraightBlock {
        public static final char BLOCK_TYPE = '+';

        public static boolean isCross(int row, int col) {
            int numUnvisited = 0;
            for (int[] move : moves) {
                int nextRow = row + move[0], nextCol = col + move[1];
                if (isValidBlock(nextRow, nextCol) && !visited[nextRow][nextCol]) numUnvisited++;
            }
            return numUnvisited == 2;
        }
    }
    static class LeftTopCurved implements Block {
        public static final char BLOCK_TYPE = '1';

        public int changeDirection(int prevDirection) {
            return prevDirection == UP ? RIGHT : DOWN;
        }

        public static boolean isLeftTopCurved(int dir1, int dir2) {
            return (dir1 == UP && dir2 == LEFT) || (dir1 == LEFT && dir2 == UP);
        }
    }
    static class LeftBottomCurved implements Block {
        public static final char BLOCK_TYPE = '2';

        public int changeDirection(int prevDirection) {
            return prevDirection == DOWN ? RIGHT : UP;
        }

        public static boolean isLeftBottomCurved(int dir1, int dir2) {
            return (dir1 == DOWN && dir2 == LEFT) || (dir1 == LEFT && dir2 == DOWN);
        }
    }
    static class RightBottomCurved implements Block {
        public static final char BLOCK_TYPE = '3';

        public int changeDirection(int prevDirection) {
            return prevDirection == DOWN ? LEFT : UP;
        }

        public static boolean isRightBottomCurved(int dir1, int dir2) {
            return (dir1 == DOWN && dir2 == RIGHT) || (dir1 == RIGHT && dir2 == DOWN);
        }
    }
    static class RightTopCurved implements Block {
        public static final char BLOCK_TYPE = '4';

        public int changeDirection(int prevDirection) {
            return prevDirection == UP ? LEFT : DOWN;
        }
    }

    private static class BlockDetail {
        int row, col, direction;

        BlockDetail(int row, int col, int direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        public void update(int direction) {
            this.direction = direction;
            row += moves[direction][0];
            col += moves[direction][1];
        }

        public char getBlockType(int otherDirection) {
            if (Cross.isCross(row, col)) return Cross.BLOCK_TYPE;
            if (VerticalStraight.isVerticalStraight(direction, otherDirection)) return VerticalStraight.BLOCK_TYPE;
            if (HorizontalStraight.isHorizontalStraight(direction, otherDirection)) return HorizontalStraight.BLOCK_TYPE;
            if (LeftTopCurved.isLeftTopCurved(direction, otherDirection)) return LeftTopCurved.BLOCK_TYPE;
            if (LeftBottomCurved.isLeftBottomCurved(direction, otherDirection)) return LeftBottomCurved.BLOCK_TYPE;
            if (RightBottomCurved.isRightBottomCurved(direction, otherDirection)) return RightBottomCurved.BLOCK_TYPE;
            return RightTopCurved.BLOCK_TYPE;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private static final int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3;
    private static Block[][] gameBoard;
    private static boolean[][] visited;
    private static int N, M, moscowRow, moscowCol, zagrebRow, zagrebCol;

    public static void main(String[] args) {
        parseInput();
        simulation();
    }

    private static void parseInput() {
        try {
            String[] size = br.readLine().split(" ");
            N = Integer.parseInt(size[0]);
            M = Integer.parseInt(size[1]);
            gameBoard = new Block[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < M; j++) {
                    char block = input.charAt(j);
                    if (block == '.') continue;
                    if (block == 'M') {
                        moscowRow = i;
                        moscowCol = j;
                        continue;
                    }
                    if (block == 'Z') {
                        zagrebRow = i;
                        zagrebCol = j;
                        continue;
                    }

                    gameBoard[i][j] = Block.getBlockByType(block);
                }
            }
        } catch (IOException ignored) {}
    }

    private static void simulation() {
        BlockDetail connectedWithMoscow = getConnectedBlock(moscowRow, moscowCol);
        BlockDetail connectedWithZagreb = getConnectedBlock(zagrebRow, zagrebCol);
        BlockDetail hackedBlockFromMoscow = tracePipe(connectedWithMoscow);
        BlockDetail hackedBlockFromZagreb = tracePipe(connectedWithZagreb);
        char type = hackedBlockFromMoscow.getBlockType(hackedBlockFromZagreb.direction);
        printAnswer(hackedBlockFromMoscow, type);
    }

    private static BlockDetail getConnectedBlock(int startRow, int startCol) {
        for (int direction = UP; direction <= RIGHT; direction++) {
            int[] move = moves[direction];
            int row = startRow + move[0], col = startCol + move[1];
            if (isValidBlock(row, col)) return new BlockDetail(row, col, direction);
        }
        return null;
    }

    private static boolean isValidBlock(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M && gameBoard[row][col] != null;
    }

    private static BlockDetail tracePipe(BlockDetail detail) {
        Block block = gameBoard[detail.row][detail.col];
        visited[detail.row][detail.col] = true;

        // 해킹된 블록
        if (block == null) return detail;

        int newDirection = block.changeDirection(detail.direction);
        detail.update(newDirection);
        return tracePipe(detail);
    }

    private static void printAnswer(BlockDetail hackedBlock, char type) {
        try {
            bw.write(String.format("%d %d %c", hackedBlock.row + 1, hackedBlock.col + 1, type));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
