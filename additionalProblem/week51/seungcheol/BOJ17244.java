import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17244 {
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][][] isv;
    static int N, M, cnt;
    static Position start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[M][N];

        cnt = 0;
        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'X') {
                    //물건('X')을 0, 1, 2, 3, 4로 바꿔줌 (비트의 각 자리를 의미)
                    map[i][j] = (char) ('0' + cnt++);
                } else if (map[i][j] == 'S') {
                    start = new Position(i, j, 0);
                    map[i][j] = '.';
                } else if (map[i][j] == 'E') {
                    end = new Position(i, j, 0);
                    map[i][j] = '.';
                }
            }
        }
        isv = new boolean[1 << cnt][M][N];
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Position> queue = new ArrayDeque<>();
        queue.offer(new Position(start.x, start.y, 0));
        isv[0][start.x][start.y] = true;

        int breadth = 0;
        while (!queue.isEmpty()) {
            breadth++;
            int size = queue.size();
            while (size-- > 0) {
                Position position = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = position.x + dx[i];
                    int ny = position.y + dy[i];

                    //총 개수만큼 비트를 밀고 -1을 해준 것이 모든 물건을 가지고 있는 경우임 5일때 100000 - 1 = 11111 (각 자리의 비트가 물건 1개 의미)
                    if(((1 << cnt) - 1) == position.state && end.x == nx && end.y == ny) return breadth;
                    
                    if(nx < 0 || ny < 0 || nx >= M || ny >= N || map[nx][ny] == '#' || isv[position.state][nx][ny]) continue;
                    if (map[nx][ny] >= '0' && map[nx][ny] <= '4') {
                        //현재 위치가 물건인 경우 해당 물건에 해당하는 비트를 켜준다.
                        int state = position.state | (1 << (map[nx][ny] - '0'));
                        isv[state][nx][ny] = true;
                        queue.offer(new Position(nx, ny, state));
                        continue;
                    }
                    isv[position.state][nx][ny] = true;
                    queue.offer(new Position(nx, ny, position.state));
                }
            }
        }
        return breadth;
    }
}

class Position {
    int x, y, state;

    public Position(int x, int y, int state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }
}