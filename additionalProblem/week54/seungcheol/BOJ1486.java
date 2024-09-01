import static java.lang.Math.max;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    //더 많은 시간이 남은 경우에만 진행시키기 위한 times 배열
    static int[][] times;

    //집에 돌아올때 걸리는 시간
    static int[][] returnHotelTimes;
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static int N, M, T, D;
    static int MAX = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        returnHotelTimes = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);
                if (c >= 'A' && c <= 'Z') {
                    map[i][j] = c - 'A';
                } else if (c >= 'a' && c <= 'z') {
                    map[i][j] = c - 'a' + 26;
                }
                returnHotelTimes[i][j] = MAX;
            }
        }

        //각 위치에서 호텔로 돌아가는 최소 시간을 저장해두기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                checkReturnHotel(i, j);
            }
        }
        System.out.println(dijkstra());
    }

    private static void checkReturnHotel(int x, int y) {
        //사용 시간이 작은 것 우선
        PriorityQueue<Position> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.time, o2.time));
        pq.offer(new Position(x, y, 0));
        int[][] dist = new int[N][M];
        dist[x][y] = MAX;

        while (!pq.isEmpty()) {
            Position p = pq.poll();

            if(p.x == 0 && p.y == 0) {
                returnHotelTimes[x][y] = p.time;
                return;
            }

            if (dist[p.x][p.y] != 0 && dist[p.x][p.y] < p.time) continue;

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (isOutRange(nx, ny)) continue;

                int diff = map[p.x][p.y] - map[nx][ny];
                if (Math.abs(diff) > T) continue;

                //해당 위치에서 돌아가는 값이 이미 구해져있으면 그냥 사용
                //(해당 위치에서 집 가는 비용 + 해당 위치까지 이동한 비용)
                int move = p.time;
                if (diff >= 0) {
                    move++;
                } else {
                    move += (diff * diff);
                }

                if (returnHotelTimes[nx][ny] != MAX) {
                    dist[0][0] = returnHotelTimes[nx][ny] + move;
                    pq.offer(new Position(0,0, dist[0][0]));
                    continue;
                }
                if (dist[nx][ny] != 0 && dist[nx][ny] <= move) continue;
                dist[nx][ny] = move;
                pq.offer(new Position(nx, ny, move));
            }
        }
        returnHotelTimes[x][y] = MAX;
    }

    private static int dijkstra() {
        PriorityQueue<Position> pq = new PriorityQueue<>();
        pq.offer(new Position(0, 0, D));
        times = new int[N][M];
        times[0][0] = D;

        //복귀한 것 중 높이 최대
        int result = 0;
        while (!pq.isEmpty()) {
            Position p = pq.poll();

            //갱신되었는데 큐에 남아있으면 스킵
            if(times[p.x][p.y] < p.time) continue;
            //호텔로 복귀 할 수 있는 경우 (복귀한 것 보다 큰 높이만 확인)
            if (result < map[p.x][p.y] && p.time >= returnHotelTimes[p.x][p.y]) {
//                System.out.println(p.x + " " + p.y + " " + result);
                result = Math.max(result, map[p.x][p.y]);
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (isOutRange(nx, ny)) continue;
                //높이 차이
                int diff = map[p.x][p.y] - map[nx][ny];
                //차이가 T이상 이면 못가
                if (Math.abs(diff) > T) continue;
                // >= 0 이면 이동하려는 곳이 더 낮은 것
                // 시간이 가능하다면 이동 (또한, 이동하고 남은 시간이 더 크면 이동)
                if (diff >= 0 && p.time - 1 >= 0 && times[nx][ny] < p.time - 1) {
                    pq.offer(new Position(nx, ny, p.time - 1));
                    times[nx][ny] = p.time - 1;
                } else if (p.time - (diff * diff) >= 0 && times[nx][ny] < p.time - (diff * diff)){ // 더 높은 곳 시간이 가능하다면 이동
                    pq.offer(new Position(nx, ny, p.time - (diff * diff)));
                    times[nx][ny] = p.time - (diff * diff);
                }
            }
        }
        return result;
    }

    public static boolean isOutRange(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) return true;
        return false;
    }

}

class Position implements Comparable<Position> {

    int x, y, time;

    public Position(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }

    @Override
    public int compareTo(Position o) {
        //남은시간이 큰거 우선
        return Integer.compare(o.time, this.time);
    }
}