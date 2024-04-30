package week47.jeongHee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BallComeOn {
    //https://www.acmicpc.net/problem/17615
    //볼 모으기
    static int N;
    static String s;

    static int solution(char color, int start, int direction) {
        int ans = 0;
        boolean haveToMove = false;
        for (int i = start; i < N && i >= 0; i += direction) {
            if (color == s.charAt(i) && haveToMove) {
                ans++;
                continue;
            }
            if (color != s.charAt(i)) haveToMove = true;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        s = bf.readLine();
        int up = Math.min(solution('R', 0, 1), solution('B', 0, 1));
        int down = Math.min(solution('R', N - 1, -1), solution('B', N - 1, -1));
        System.out.println(Math.min(up, down));
    }

}