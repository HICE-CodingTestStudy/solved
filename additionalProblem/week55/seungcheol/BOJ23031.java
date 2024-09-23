import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[][][] isSwitch; //[x][y] [0] : 해당 위치가 스위치인지, [1] : 켜졌는지
    //해당 위치에 좀비 수
    static int[][] map;
    static Queue<Position> zombies;

    //아래 -> 왼 -> 위 -> 오
    //L : ++, R : --
    static int[] dx = {1, 0, -1, 0, -1, -1, 1, 1};
    static int[] dy = {0, -1, 0, 1, -1, 1, -1, 1};
    static int N;
    static Position Ahri;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isSwitch = new boolean[N][N][2];
        map = new int[N][N];
        zombies = new ArrayDeque<>();

        String moving = br.readLine();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);
                //스위치면 표시하고 빈칸으로
                if(c == 'S') {
                    isSwitch[i][j][0] = true;
                    map[i][j] = 0;
                } else if(c == 'Z') {
                    zombies.offer(new Position(i, j, 0));
                    //해당 위치에 좀비 하나 추가
                    map[i][j]++;
                }
            }
        }
        System.out.println(simulation(moving));
    }

    private static String simulation(String moving) {
        Ahri = new Position(0, 0, 0);
        for (int i = 0; i < moving.length(); i++) {
            int cx = Ahri.x;
            int cy = Ahri.y;

            char command = moving.charAt(i);
            if (command == 'F') {
                int nx = cx + dx[Ahri.dir];
                int ny = cy + dy[Ahri.dir];
                Ahri = move(cx, cy, nx, ny, Ahri.dir, false);
                //이동한 해당 위치가 스위치이면
                if (isSwitch[Ahri.x][Ahri.y][0]) {
                    //스위치 켜기
                    switchOn(Ahri.x, Ahri.y);
                }
                //좀비가 있을 때
                if (map[Ahri.x][Ahri.y] != 0){
                    //스위치가 켜져있으면
                    if (isSwitch[Ahri.x][Ahri.y][1]) {
                        continue;
                    }
                    //꺼져있으면 기절
                    return "Aaaaaah!";
                }
            } else if (command == 'R') {
                Ahri.dir = (Ahri.dir + 1) % 4;
            } else if (command == 'L') {
                Ahri.dir = (Ahri.dir - 1 + 4) % 4;
            }
            //좀비가 움직였을 때 기절하면 끝
            if (moveZombies()) {
                return "Aaaaaah!";
            }
        }
        //끝까지 기절안하면 성공
        return "Phew...";
    }

    private static boolean moveZombies() {
        while (!zombies.isEmpty()) {
            int size = zombies.size();
            while (size-- > 0) {
                Position zombie = zombies.poll();

                int cx = zombie.x;
                int cy = zombie.y;
                int nx = cx + dx[zombie.dir];
                int ny = cy + dy[zombie.dir];
                Position next = move(cx, cy, nx, ny, zombie.dir, true);
                //움직인 위치에 아리가 있고 스위치가 꺼져있으면 끝
                if(Ahri.equals(next) && !isSwitch[next.x][next.y][1]) return true;
                //아니면 맵에서 좀비 갱신하고 큐에 다시 넣기
                zombies.offer(next);
                //같은 위치에 좀비가 여러마리일 수 있으니 이동 전 위치에 좀비 한마리 빼기
                map[cx][cy]--;
                //이동한 위치에 좀비 하나 추가
                map[next.x][next.y]++;
            }
            //좀비가 한턴 이동을 끝냄
            return false;
        }
        return false;
    }

    private static Position move(int cx, int cy, int nx, int ny, int dir, boolean isZombie) { //좀비면 방향 전환, 아리면 그냥 방향 그대로 가만히
        if (isOutRange(nx, ny)) return new Position(cx, cy, isZombie ? (dir + 2) % 4 : dir);
        return new Position(nx, ny, dir);
    }

    private static void switchOn(int x, int y) {
        isSwitch[x][y][0] = false;
        isSwitch[x][y][1] = true;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOutRange(nx, ny)) continue;
            isSwitch[nx][ny][1] = true;
        }
    }

    private static boolean isOutRange(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= N;
    }
}

class Position {
    int x, y, dir;

    public Position(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }
}