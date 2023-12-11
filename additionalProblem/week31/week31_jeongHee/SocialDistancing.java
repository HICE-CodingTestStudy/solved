package stack;

import java.util.*;

public class SocialDistancing {
    //https://school.programmers.co.kr/learn/courses/30/lessons/81302#fn1
    //거리두기
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Coordinate {
        int i, j;
        int distance;

        public Coordinate(int i, int j, int distance) {
            this.i = i;
            this.j = j;
            this.distance = distance;
        }
    }

    public List<Integer> solution(String[][] places) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            boolean isValid = true;
            for (int j = 0; j < 5 && isValid; j++) {
                for (int k = 0; k < 5 && isValid; k++) {
                    if (places[i][j].charAt(k) == 'P') {
                        Queue<Coordinate> queue = new LinkedList<>();
                        boolean[][] visited = new boolean[5][5];
                        visited[j][k] = true;
                        queue.add(new Coordinate(j, k, 0));
                        while (!queue.isEmpty()) {
                            Coordinate c = queue.poll();
                            if (c.distance!=0 && places[i][c.i].charAt(c.j) == 'P') {
                                ans.add(0);
                                isValid = false;
                                break;
                            }
                            if(c.distance==2) continue;
                            for (int l = 0; l < 4; l++) {
                                int nextI = c.i + dx[l];
                                int nextJ = c.j + dy[l];
                                if (nextI < 0 || nextJ < 0 || nextI >= 5 || nextJ >= 5)
                                    continue;
                                if (visited[nextI][nextJ])
                                    continue;
                                visited[nextI][nextJ] = true;
                                if (places[i][nextI].charAt(nextJ) != 'X')
                                    queue.add(new Coordinate(nextI, nextJ, c.distance + 1));
                            }
                        }
                    }
                }
            }
            if (isValid) ans.add(1);
        }
        return ans;
    }

}
