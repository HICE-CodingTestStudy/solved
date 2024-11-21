package ing.week57.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1445_sundaymorning {

    static class Node implements Comparable<Node> {
        int y;
        int x;
        int g;
        int nextG;

        Node(int y, int x, int g, int nextG) {
            this.y = y;
            this.x = x;
            this.g = g;
            this.nextG = nextG;
        }

        @Override
        public int compareTo(Node o) {
            if(this.g==o.g) {
                return this.nextG - o.nextG;
            }
            return this.g - o.g;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "y=" + y +
                    ", x=" + x +
                    ", g=" + g +
                    ", nextG=" + nextG +
                    '}';
        }
    }

    static int n, m;
    static boolean[][] map;
    static int[][][] visited;


    static boolean isValid(int y, int x){
        return y>=0 && y<n && x>=0 && x<m;
    }

    static boolean isVisited(int y, int x, int g, int nextG){
        if(visited[0][y][x]<g){
            return true;
        } else if(visited[0][y][x] == g) {
            if(visited[1][y][x] <= nextG) return true;
            else return false;
        }
        return false;
    }

    static boolean nextToG(int y, int x){
        int ny, nx;
        for (int i = 0; i < 4; i++) {
            ny = y+dy[i];
            nx = x+dx[i];
            if(isValid(ny, nx) && map[ny][nx]){
                return true;
            }
        }
        return false;
    }

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new boolean[n][m];
        int[] start = new int[2];
        int[] end = new int[2];
        String str;
        visited = new int[2][n][m];
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            for (int j = 0; j < m; j++) {
                visited[0][i][j] = Integer.MAX_VALUE;
                visited[1][i][j] = Integer.MAX_VALUE;
                switch (str.charAt(j)) {
                    case 'g':
                        map[i][j] = true;
                        break;
                    case 'S':
                        start[0] = i;
                        start[1] = j;
                        break;
                    case 'F':
                        end[0] = i;
                        end[1] = j;
                        break;
                }
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start[0], start[1], 0, 0));

        visited[0][start[0]][start[1]] = 0;
        visited[1][start[0]][start[1]] = 0;

        Node curr; int ny, nx, ng, nng;
        while(!q.isEmpty()) {
            curr = q.poll();
//            System.out.println(curr.y + " " + curr.x);
            if(curr.y == end[0] && curr.x == end[1]) {break;}
            for (int i = 0; i < 4; i++) {
                ny = curr.y + dy[i];
                nx = curr.x + dx[i];
                if(!isValid(ny, nx)) continue;
                ng = (map[curr.y][curr.x])?curr.g+1:curr.g;
                nng = (nextToG(curr.y, curr.x))?curr.nextG+1:curr.nextG;

                if(isVisited(ny, nx, ng, nng)) continue;
                visited[0][ny][nx] = ng;
                visited[1][ny][nx] = nng;
                q.offer(new Node(ny, nx, ng, nng));
            }
        }


        StringBuilder sb = new StringBuilder();
        sb.append(visited[0][end[0]][end[1]] - (map[start[0]][start[1]] ? 1 : 0))
                .append(" ")
                .append(visited[1][end[0]][end[1]] - (nextToG(start[0], start[1])?1:0));
        System.out.println(sb);
    }
}
