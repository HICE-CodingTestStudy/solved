package Ing.Week53.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_22955_cat {


    static class Node implements Comparable<Node> {
        int y;
        int x;
        int hp;

        Node(int y, int x, int hp) {
            this.y = y;
            this.x = x;
            this.hp = hp;
        }


        @Override
        public int compareTo(Node o) {
            return this.hp - o.hp;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String str;
        int[] cat = new int[2];
        char[][] board = new char[n][m];
        int[][] visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            for (int j = 0; j < m; j++) {
                visited[i][j] = Integer.MAX_VALUE;
                switch (str.charAt(j)) {
                    case 'C':
                        board[i][j] = 'F';
                        cat[0] = i;
                        cat[1] = j;
                        break;
                    default:
                        board[i][j] = str.charAt(j);
                }
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(cat[0], cat[1], 0));
        visited[cat[0]][cat[1]] = 0;
        Node curr;
        int cy, cx, chp, ny;
        while (!pq.isEmpty()) {
            curr = pq.poll();
            cy = curr.y;
            cx = curr.x;
            chp = visited[cy][cx];

            //도착점일 때
            if (board[cy][cx] == 'E') {
                System.out.println(curr.hp);
                return;
            }

            // 바닥이 뚫려있을 때
            if (board[cy][cx] == 'X') {
                ny = cy;
                while (board[ny][cx] == 'X') ny++;
                if (visited[ny][cx] <= chp + 10 || board[ny][cx] == 'D') continue;
                pq.offer(new Node(ny, cx, chp + 10));
                visited[ny][cx] = chp + 10;
                continue;
            }

            // 사다리 이용
            // 위로 움직임
            if (cy - 1 >= 0 && board[cy][cx] == 'L' && board[cy - 1][cx] != 'D' && visited[cy - 1][cx] > chp + 5) {
                pq.offer(new Node(cy - 1, cx, chp + 5));
                visited[cy - 1][cx] = chp + 5;
            }

            //아래로 움직임
            if (cy + 1 < n && board[cy + 1][cx] == 'L' && visited[cy + 1][cx] > chp + 5) {
                pq.offer(new Node(cy + 1, cx, chp + 5));
                visited[cy + 1][cx] = chp + 5;
            }

            if (cx + 1 < m && board[cy][cx + 1] != 'D' && visited[cy][cx + 1] > chp + 1) {
                pq.offer(new Node(cy, cx + 1, chp + 1));
                visited[cy][cx + 1] = chp + 1;
            }

            if (cx - 1 >= 0 && board[cy][cx - 1] != 'D' && visited[cy][cx - 1] > chp + 1) {
                pq.offer(new Node(cy, cx - 1, chp + 1));
                visited[cy][cx - 1] = chp + 1;
            }

//            for (int i = 0; i < n; i++) {
//                System.out.println(Arrays.toString(visited[i]));
//            }
//            System.out.println();
        }
        System.out.println("dodo sad");
    }
}
