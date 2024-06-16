package week50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ16457 {
    static int n, m, k, max;
    static boolean[] isExist;
    static int[] isSelected;
    static int[][] key;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        max = 0;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        isExist = new boolean[2 * n + 1];
        key = new int[m][k];
        int existCnt = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                int skill = Integer.parseInt(st.nextToken());
                key[i][j] = skill;
                if(isExist[skill]) continue;
                isExist[skill] = true;
                existCnt++;
            }
        }
        //사용된 수와 n중 더 작은 값으로 n갱신
        //안해주면 조합을 짤 때 실제 사용된 수보다 n이 크면 조합을 다 만들지 못함
        // 4 1 2 / 1 2 3 일때 n = 4이면 4개의 수를 선택해야 조합이 완성되지만 실제 사용된 수는 3개라 조합을 만들지 못함
        n = Math.min(n, existCnt);
        isSelected = new int[n];

        comb(1, 0);
        System.out.println(max);
    }

    public static void comb(int start, int depth) {
        if (depth == n) {
            isAvailable();
            return;
        }

        for (int i = start; i <= 2 * n; i++) {
            if(!isExist[i]) continue; //실제 사용된 수만 사용하기 위해
            isSelected[depth] = i;
            comb(i + 1, depth + 1);
        }
    }

    private static void isAvailable() {
        Map<Integer, Boolean> exists = new HashMap<>();
        for (int i = 0; i < n; i++) {
            exists.put(isSelected[i], true);
        }
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            boolean flag = true;
            for (int j = 0; j < k; j++) {
                if (exists.getOrDefault(key[i][j], false)) continue;
                flag = false;
                break;
            }
            cnt = flag ? cnt + 1 : cnt;
        }
        max = Math.max(max, cnt);
    }
}
