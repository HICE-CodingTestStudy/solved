import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Guitar {
    //https://www.acmicpc.net/problem/1497
    //기타 콘서트
    static int N, M;
    static String[] info;
    static int ansCount = 0;

    static void solution(int count, int index, int[] play) {
        if (count == 0) {
            int possible = 0;
            for (int j : play) {
                if (j != 0) possible++;
            }
            if (possible > ansCount)
                ansCount = possible;
        }
        for (int i = index; i < N; i++) {
            int[] next = play.clone();
            for (int j = 0; j < play.length; j++) {
                if (info[i].charAt(j) == 'Y') next[j]++;
            }
            solution(count - 1, i + 1, next);
        }
    }

    static void printAns() {
        int ans = -1;
        for (int i = 1; i <= N; i++) {
            int before = ansCount;
            solution(i, 0, new int[M]);
            if(before<ansCount) ans = i;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        info = new String[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            st.nextToken();
            info[i] = st.nextToken();
        }
        printAns();
    }
}
