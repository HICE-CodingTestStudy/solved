import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//고양이 도도의 탈출기
public class BOJ22955 {
    static char[][] map;
    static int[][] cost;
    static Dodo start;
    static Dodo end;
    static int N, M;
    static int[] dy = new int[]{-1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        cost = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'C') {
                    start = new Dodo(i, j, 0);
                    map[i][j] = 'F';
                } else if(map[i][j] == 'E') {
                    end = new Dodo(i, j, 0);
                    map[i][j] = 'F';
                }
                cost[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        int res = bfs();
        System.out.println(res == -1 ? "dodo sad" : res);
    }

    private static int bfs() {
        PriorityQueue<Dodo> pq = new PriorityQueue<>();
        cost[start.x][start.y] = 0;
        pq.offer(start);

        while (!pq.isEmpty()) {
            Dodo dodo = pq.poll();

            if(dodo.cost > cost[dodo.x][dodo.y]) continue;
            if(dodo.x == end.x && dodo.y == end.y) return dodo.cost;

            if (map[dodo.x][dodo.y] == 'F' || map[dodo.x][dodo.y] == 'L') {
                for (int i = 0; i < 2; i++) {
                    int ny = dodo.y + dy[i];
                    if(ny < 0 || ny >= M || map[dodo.x][ny] == 'D') continue;
                    if (cost[dodo.x][ny] > cost[dodo.x][dodo.y] + 1) {
                        cost[dodo.x][ny] = cost[dodo.x][dodo.y] + 1;
                        pq.offer(new Dodo(dodo.x, ny, cost[dodo.x][ny]));
                    }
                }
            }

            if (map[dodo.x][dodo.y] == 'X') {
                for (int nx = dodo.x + 1; nx < N; nx++) {
                    if(map[nx][dodo.y] == 'X') continue;
                    else if(map[nx][dodo.y] == 'D') break;
                    if (cost[nx][dodo.y] > cost[dodo.x][dodo.y] + 10) {
                        cost[nx][dodo.y] = cost[dodo.x][dodo.y] + 10;
                        pq.offer(new Dodo(nx, dodo.y, cost[nx][dodo.y]));
                    }
                    break;
                }
                continue;
            }

            //현재 위치가 사다리
            if (dodo.x > 0 && map[dodo.x][dodo.y] == 'L') {
                if (map[dodo.x - 1][dodo.y] != 'D' && cost[dodo.x - 1][dodo.y] > cost[dodo.x][dodo.y] + 5) {
                    cost[dodo.x - 1][dodo.y] = cost[dodo.x][dodo.y] + 5;
                    pq.offer(new Dodo(dodo.x - 1, dodo.y, cost[dodo.x - 1][dodo.y]));
                }
            }
            //아래에 사다리
            if (dodo.x + 1 < N && map[dodo.x + 1][dodo.y] == 'L') {
                if (map[dodo.x + 1][dodo.y] != 'D' && cost[dodo.x + 1][dodo.y] > cost[dodo.x][dodo.y] + 5) {
                    cost[dodo.x + 1][dodo.y] = cost[dodo.x][dodo.y] + 5;
                    pq.offer(new Dodo(dodo.x + 1, dodo.y, cost[dodo.x + 1][dodo.y]));
                }
            }
        }

        return -1;
    }
}

class Dodo implements Comparable<Dodo> {
    int x, y, cost;

    public Dodo(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Dodo dodo) {
        return Integer.compare(this.cost, dodo.cost);
    }
}