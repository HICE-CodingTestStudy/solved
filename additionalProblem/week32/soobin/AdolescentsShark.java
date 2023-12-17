import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AdolescentsShark {
    private static class Fish {
        int row, col, direction;

        Fish(int row, int col, int direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        public void move(int newRow, int newCol) {
            this.row = newRow;
            this.col = newCol;
        }

        public Fish copy() {
            return new Fish(row, col, direction);
        }

        // 나랑 상대 물고기 서로 자리 바꾸기
        private void swap(Fish other, int oneIdx, int otherIdx) {
            map[row][col] = otherIdx;
            map[other.row][other.col] = oneIdx;

            int tempR = row, tempC = col;
            this.move(other.row, other.col);
            other.move(tempR, tempC);
        }

        // 빈 칸에 내가 가기
        private void moveToEmptyCell(int idx, int newR, int newC) {
            map[row][col] = -1;
            map[newR][newC] = idx;
            this.move(newR, newC);
        }

        // 방향을 반시계 방향으로 돌려가며 이동 가능한 칸으로 이동
        public void moveToAvailableCell(int myIdx) {
            for (int d = direction; d < d + 8; d++) {
                int[] move = moves[d % 8];
                int nr = row + move[0], nc = col + move[1];
                if (!isValid(nr, nc) || (shark.row == nr && shark.col == nc)) continue;

                int idx = map[nr][nc];
                direction = d % 8;
                if (idx == -1) moveToEmptyCell(myIdx, nr, nc);    // 물고기 없는 칸이면 나만 이동
                else swap(fishes[idx], myIdx, idx);   // 물고기 있는 칸이면 서로 바꾸기
                return;
            }
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}}; // ↑, ↖, ←, ↙, ↓, ↘, →, ↗

    private static Fish[] fishes = new Fish[17];    // 물고기들 번호 별로 현 위치, 방향 저장하는 배열
    private static int[][] map = new int[4][4];     // 공간의 위치 별로 물고기 번호 저장
    private static Fish shark;  // 상어의 현재 위치, 방향
    private static int answer;  // 상어가 먹은 물고기 번호의 합 최댓값

    private static void parseInput() {
        try {
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    int idx = Integer.parseInt(st.nextToken());
                    int direction = Integer.parseInt(st.nextToken()) - 1;
                    fishes[idx] = new Fish(i, j, direction);
                    map[i][j] = idx;
                }
            }
        } catch(IOException e) {}
    }

    private static void printAnswer() {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < 4 && c >= 0 && c < 4;
    }

    // 처음 위치에 상어 보내고 거기 있는 물고기 먹이기 -> 남은 물고기 이동
    private static int init() {
        int idx = map[0][0];
        Fish first = fishes[idx];
        shark = new Fish(0, 0, first.direction);
        map[0][0] = -1;
        fishes[idx] = null;
        moveAllFishes();

        return idx;
    }

    // 안 먹힌 물고기들 이동
    private static void moveAllFishes() {
        for (int i = 1; i < 17; i++) {
            Fish target = fishes[i];
            if (target == null) continue; // 상어한테 먹힌 물고기임
            target.moveToAvailableCell(i);
        }
    }

    // 상어 및 물고기 이동하기 이전 상태로 되돌리기 (백트래킹 시 필요)
    private static void syncPrev(Fish[] prev) {
        fishes = prev;
        for (int i = 0; i < 4; i++) Arrays.fill(map[i], -1);
        for (int i = 1; i < 17; i++) {
            Fish fish = prev[i];
            if (fish == null) continue;
            map[fish.row][fish.col] = i;
        }
    }

    // 기존 물고기 상태 저장하는 배열 복사
    private static Fish[] clonePrevFishes() {
        Fish[] prev = new Fish[17];
        for (int i = 1; i < 17; i++) {
            Fish target = fishes[i];
            prev[i] = target == null ? null : target.copy();
        }

        return prev;
    }

    private static void simulation(int sum) {
        int[] move = moves[shark.direction]; // 현재 상어의 이동 방향

        // 이동 가능한 칸 수 다 고려해야 함
        for (int i = 1; i < 4; i++) {
            int nr = shark.row + move[0] * i, nc = shark.col + move[1] * i;
            if (!isValid(nr, nc) || map[nr][nc] == -1) continue; // 공간을 벗어나거나 물고기 없는 칸 이동 불가

            /* 나중에 이동하기 이전 상태로 되돌리기 위해 따로 저장해두기 */
            Fish[] prev = clonePrevFishes();
            Fish prevShark = shark.copy();

            /* 상어 및 물고기 이동 */
            int idx = map[nr][nc]; // 먹어야 할 물고기 번호
            shark.move(nr, nc);
            if (idx != -1) {    // 물고기 먹었으면 그 자리는 빈 칸
                shark.direction = fishes[idx].direction;
                map[nr][nc] = -1;
                fishes[idx] = null;
            }
            moveAllFishes();

            /* 현재 상태에 따른 다음 상황 탐색 */
            simulation(sum + idx);

            /* 이전 상태 되돌리기 */
            shark = prevShark;
            syncPrev(prev);
        }

        answer = Math.max(answer, sum);
    }

    public static void main(String[] args) {
        parseInput();
        int start = init();
        simulation(start);
        printAnswer();
    }
}
