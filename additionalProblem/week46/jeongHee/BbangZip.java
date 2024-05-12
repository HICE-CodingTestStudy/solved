import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BbangZip {
    //https://www.acmicpc.net/problem/3109
    //빵집
    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 0, 1}, dy = {1, 1, 1};

    static int ans = 0;

    static boolean solution(int i, int j) {
        if (j == C - 1) {
            ans++;
            return true;
        }
        for (int k = 0; k < 3; k++) {
            int nextI = i + dx[k];
            int nextJ = j + dy[k];
            if(nextI<0||nextJ<0||nextI>=R||nextJ>=C||map[nextI][nextJ]!='.') continue;
            map[nextI][nextJ] = 'x';
            if(solution(nextI,nextJ)) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = bf.readLine().toCharArray();
        }
        for (int i = 0; i < R; i++) {
            solution(i, 0);
        }
        System.out.println(ans);
    }

}
