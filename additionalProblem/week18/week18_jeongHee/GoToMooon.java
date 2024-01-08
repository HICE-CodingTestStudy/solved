package additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class GoToMooon {
    //https://www.acmicpc.net/problem/1194
    //달이 차오른다, 가자.
    static int N;
    static int M;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Coordinate {
        int i;
        int j;
        int keys;
        int count;

        public Coordinate(int i, int j, int keys, int count) {
            this.i = i;
            this.j = j;
            this.keys = keys;
            this.count = count;
        }
    }

    public static int bfs(Coordinate start) {
        boolean[][][] visited = new boolean[N][M][1 << 6];
        visited[start.i][start.j][start.keys] = true;
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Coordinate now = queue.poll();
            //달임
            if (map[now.i][now.j] == '1') return now.count;

            for (int i = 0; i < 4; i++) {
                int nextI = now.i + dx[i];
                int nextJ = now.j + dy[i];
                //인덱스 범위 벗어남
                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) continue;
                //방문함
                if (visited[nextI][nextJ][now.keys]) continue;
                //벽임
                if (map[nextI][nextJ] == '#') continue;
                //문임
                if (map[nextI][nextJ] >= 'A' && map[nextI][nextJ] <= 'F') {
                    int key = map[nextI][nextJ] + 32 - 'a';
                    //키가있음
                    if ((now.keys >> key & 1) == 1) {
                        queue.add(new Coordinate(nextI, nextJ, now.keys, now.count+1));
                        visited[nextI][nextJ][now.keys] = true;
                        continue;
                    }
                    //키가 없음
                    continue;
                }
                //키임
                if (map[nextI][nextJ] >= 'a' && map[nextI][nextJ] <= 'f') {
                    int key = map[nextI][nextJ] - 'a';
                    int nextKey = now.keys | (1 << key);
                    queue.add(new Coordinate(nextI, nextJ, nextKey, now.count+1));
                    visited[nextI][nextJ][nextKey] = true;
                    continue;
                }
                //다시 돌아온 출발지 또는 빈곳임
                queue.add(new Coordinate(nextI, nextJ, now.keys, now.count+1));
                visited[nextI][nextJ][now.keys] = true;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        Coordinate start = null;
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '0') start = new Coordinate(i, j, 0,0);
            }
        }
        System.out.println(bfs(start));
    }
}
