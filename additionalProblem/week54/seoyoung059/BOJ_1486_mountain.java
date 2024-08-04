package solved.additionalProblem.week54.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1486_mountain {

    static int n, m;
    static final int MAX = 999_999_999;

    static class Node implements Comparable<Node> {
        int y;
        int x;
        int time;

        Node (int y, int x, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }


        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    static boolean isValid(int y, int x) {
        return 0<=y && y<n && 0<=x && x<m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] mountain = new int[n][m];
        int[][] upTime = new int[n][m];
        int[][] downTime = new int[n][m];
        String line;
        for (int i = 0; i < n; i++) {
            line = br.readLine();
            for (int j = 0; j < m; j++) {
                if(line.charAt(j) > 'Z') {
                    mountain[i][j] = line.charAt(j) - 'a' + 26;
                } else mountain[i][j] = line.charAt(j) - 'A';
                upTime[i][j] = MAX;
                downTime[i][j] = MAX;
            }
        }
        upTime[0][0] = 0;
        downTime[0][0] = 0;

        // 올라가는거 내려가는거 따로 해서 더해야 할듯
        int[] dy = {+1, -1, 0, 0};
        int[] dx = {0, 0, -1, +1};

        int answer = 0;

        PriorityQueue<Node> upside = new PriorityQueue<>();
        upside.offer(new Node(0, 0, 0));
        Node curr; int ny, nx, gap, tmp;
        while(!upside.isEmpty()) {
            if(upside.peek().time > d) break;
            curr = upside.poll();
            if(upTime[curr.y][curr.x] < curr.time) continue;
            for (int i = 0; i < 4; i++) {
                ny = curr.y+dy[i];
                nx = curr.x+dx[i];
                if(!isValid(ny, nx)) continue;
                gap = mountain[ny][nx] - mountain[curr.y][curr.x];
                if(Math.abs(gap)>t) continue;
                if(gap > 0) {
                    tmp = (int) Math.pow(gap, 2);
                } else tmp = 1;
                if(upTime[ny][nx] <= upTime[curr.y][curr.x] + tmp) continue;
                upTime[ny][nx] = upTime[curr.y][curr.x] + tmp;
                upside.offer(new Node(ny, nx, upTime[ny][nx]));
            }
        }


        PriorityQueue<Node> downside = new PriorityQueue<>();
        downside.offer(new Node(0, 0, 0));
        while(!downside.isEmpty()) {
            if(downside.peek().time > d) break;
            curr = downside.poll();
            if(downTime[curr.y][curr.x] < curr.time) continue;
            for (int i = 0; i < 4; i++) {
                ny = curr.y+dy[i];
                nx = curr.x+dx[i];
                if(!isValid(ny, nx)) continue;
                gap = mountain[ny][nx] - mountain[curr.y][curr.x];
                if(Math.abs(gap)>t) continue;
                if(gap < 0) {
                    tmp = (int) Math.pow(gap, 2);
                } else tmp = 1;
                if(downTime[ny][nx] <= downTime[curr.y][curr.x] + tmp) continue;
                downTime[ny][nx] = downTime[curr.y][curr.x] + tmp;
                downside.offer(new Node(ny, nx, downTime[ny][nx]));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(upTime[i][j]+downTime[i][j] <= d)
                    answer = Math.max(answer, mountain[i][j]);
            }
        }

        System.out.println(answer);
    }
}
