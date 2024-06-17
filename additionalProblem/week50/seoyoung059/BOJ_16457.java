package ing.week50.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16457 {

    static int n, m, k, answer;
    static int[] arr;

    static void chooseKeys(int cnt, int idx, int check) {
        if (cnt == n) {
            int tmp = 0;
            for (int i = 0; i < m; i++) {
                tmp += ((check | arr[i]) == check) ? 1 : 0;
            }
            answer = Math.max(tmp, answer);
            return;
        }
        if (idx > 2 * n) return;
        chooseKeys(cnt + 1, idx + 1, check | (1 << idx));
        chooseKeys(cnt, idx + 1, check);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 10 이하
        m = Integer.parseInt(st.nextToken()); // 100 이하
        k = Integer.parseInt(st.nextToken()); // n 이하

        arr = new int[m];
        int tmp;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            tmp = 0;
            for (int j = 0; j < k; j++) {
                tmp |= (1 << Integer.parseInt(st.nextToken()));
            }
            arr[i] = tmp;
        }

        chooseKeys(0, 1, 0);
        System.out.println(answer);
    }
}
