package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LightUp {
    //https://www.acmicpc.net/problem/11967
    //불켜기
    static int N;
    static List<List<List<Coordinate>>> info = new ArrayList<>();
    static boolean[][] light;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Coordinate {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void solution() {
        light[1][1] = true;
        boolean[][] visited = new boolean[N + 1][N + 1];
        visited[1][1] = true;
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(1, 1));
        while (!queue.isEmpty()) {
            Coordinate now = queue.poll();
            for (int i = 0; i < info.get(now.x).get(now.y).size(); i++) {
                Coordinate c = info.get(now.x).get(now.y).get(i);
                light[c.x][c.y] = true;
            }
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (!visited[i][j]) continue;
                    for (int k = 0; k < 4; k++) {
                        int nextI = i + dx[k];
                        int nextJ = j + dy[k];
                        if (nextI <= 0 || nextJ <= 0 || nextI >= N + 1 || nextJ >= N + 1) continue;
                        if (visited[nextI][nextJ]) continue;
                        if (!light[nextI][nextJ]) continue;
                        visited[nextI][nextJ] = true;
                        queue.add(new Coordinate(nextI, nextJ));
                    }
                }
            }
        }
    }

    static int countAns() {
        int ans = 0;
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if(light[i][j]) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        light = new boolean[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            info.add(new ArrayList<>());
            for (int j = 0; j < N + 1; j++) {
                info.get(i).add(new ArrayList<>());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            info.get(Integer.parseInt(st.nextToken())).get(Integer.parseInt(st.nextToken()))
                    .add(new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        solution();
        System.out.println(countAns());
    }
}
