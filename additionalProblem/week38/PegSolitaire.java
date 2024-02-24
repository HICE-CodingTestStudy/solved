package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class PegSolitaire {
    //https://www.acmicpc.net/problem/9207
    //페그 솔리테어
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static char[][] map = new char[5][9];
    static int ansNum, ansCount;

    static String makeKey() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < 5 && j < 9;
    }

    static void solution(int count, Set<String> visited) {
        boolean canMove = false;
        int pinCount = 0;
        String s = makeKey();
        if(visited.contains(s)) return;
        visited.add(s);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j] == 'o') {
                    pinCount++;
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];
                        int nni = i + 2 * dx[k];
                        int nnj = j + 2 * dy[k];
                        if (isValid(ni, nj) && isValid(nni, nnj) && map[ni][nj] == 'o' && map[nni][nnj] == '.') {
                            canMove = true;
                            map[i][j] = map[ni][nj] = '.';
                            map[nni][nnj] = 'o';
                            solution(count + 1, visited);
                            map[i][j] = map[ni][nj] = 'o';
                            map[nni][nnj] = '.';
                            solution(count + 1, visited);
                        }
                    }

                }
            }
        }
        if (!canMove) {
            if (ansNum == pinCount) {
                ansCount = Math.min(ansCount, count);
            } else if (ansNum > pinCount) {
                ansNum = pinCount;
                ansCount = count;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        while (N-- > 0) {
            ansNum = 0;
            ansCount = 0;
            for (int i = 0; i < 5; i++) {
                String s = bf.readLine();
                for (int j = 0; j < 9; j++) {
                    map[i][j] = s.charAt(j);
                    if (map[i][j] == 'o')
                        ansNum++;
                }
            }
            solution(0, new HashSet<>());
            System.out.println(ansNum + " " + ansCount);
            bf.readLine();
        }
    }

}
