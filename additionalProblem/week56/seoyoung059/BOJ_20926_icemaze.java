package fin.HICE.week56.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_20926_icemaze {

    static class Node implements Comparable<Node> {
        int y;
        int x;
        int time;

        Node(int y, int x, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }


        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][] maze = new int[h + 2][w + 2];
        int[][] visited = new int[h + 2][w + 2];
        for (int i = 0; i < h + 2; i++) {
            maze[i][0] = -1;
            maze[i][w + 1] = -1;
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < w + 2; i++) {
            maze[0][i] = -1;
            maze[h + 1][i] = -1;
        }
        String str;
        int ey = -1, ex = -1, ty = -1, tx = -1;
        for (int i = 0; i < h; i++) {
            str = br.readLine();
            for (int j = 0; j < w; j++) {
                switch (str.charAt(j)) {
                    case 'T':
                        maze[i + 1][j + 1] = 0;
                        ty = i + 1;
                        tx = j + 1;
                        break;
                    case 'R':
                        maze[i + 1][j + 1] = -2;
                        break;
                    case 'H':
                        maze[i + 1][j + 1] = -1;
                        break;
                    case 'E':
                        maze[i + 1][j + 1] = -3;
                        ey = i + 1;
                        ex = j + 1;
                        break;
                    default:
                        maze[i + 1][j + 1] = str.charAt(j) - '0';
                }
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(ty, tx, 0));
        visited[ty][tx] = 0;
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        Node curr;
        int ny, nx, tmp;
        while (!pq.isEmpty()) {
            curr = pq.poll();
//            System.out.println(curr.y + " " + curr.x + " " + curr.time);
            for (int i = 0; i < 4; i++) {
                tmp = visited[curr.y][curr.x];
                ny = curr.y;
                nx = curr.x;
                while (maze[ny + dy[i]][nx + dx[i]] >= 0) {
                    ny += dy[i];
                    nx += dx[i];
                    tmp += maze[ny][nx];
                }
//                System.out.println((ny+dy[i])+" "+(nx+dx[i]));
                switch (maze[ny + dy[i]][nx + dx[i]]) {
                    case -1:
                        continue;
                    case -2:
                        if (visited[ny][nx] > tmp) {
                            visited[ny][nx] = tmp;
                            pq.add(new Node(ny, nx, tmp));
                        }
                        break;
                    case -3:
                        if (visited[ny + dy[i]][nx + dx[i]] > tmp) {
                            visited[ny + dy[i]][nx + dx[i]] = tmp;
                            pq.add(new Node(ny + dy[i], nx + dx[i], tmp));
                        }

                }

            }
        }

//        for (int i = 0; i < h+2; i++) {
//            System.out.println(Arrays.toString(visited[i]));
//        }
        System.out.println(visited[ey][ex] == Integer.MAX_VALUE ? -1 : visited[ey][ex]);
    }
}
