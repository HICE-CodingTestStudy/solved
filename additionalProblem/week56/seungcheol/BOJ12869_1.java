import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, res;
    static int[] damege = new int[]{9, 3, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] scvs = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scvs[i] = Integer.parseInt(st.nextToken());
        }
        if (N == 1) {
            int share = scvs[0] / 9;
            System.out.println(share + ((scvs[0] % 9) == 0 ? 0 : 1));
        } else if (N == 2) {
            int max = Math.max(scvs[0], scvs[1]);
            int min = Math.min(scvs[0], scvs[1]);
            int cnt = 0;
            while (true) {
                cnt++;
                max = Math.max(0, max - 9);
                min = Math.max(0, min - 3);
                int sum = max + min;
                if(sum == 0) {
                    System.out.println(cnt);
                    break;
                }
                max = Math.max(max, min);
                min = sum - max;
            }
        } else {
            res = Integer.MAX_VALUE;
            System.out.println(dfs(scvs, 0));
        }
    }

    private static int dfs(int[] scvs, int depth) {
        if(res < depth) {
            return Integer.MAX_VALUE;
        }
        if (isDone(scvs)) {
            return depth;
        }
        Arrays.sort(scvs);

        //제일 큰거는 9빼기
        //나머지 두개는 둘 다 해보기
        int[] copy1 = new int[N];
        copy1[0] = calc(scvs[0] - 3);
        copy1[1] = calc(scvs[1] - 1);
        copy1[2] = calc(scvs[2] - 9);
        int[] copy2 = new int[N];
        copy2[0] = calc(scvs[0] - 1);
        copy2[1] = calc(scvs[1] - 3);
        copy2[2] = calc(scvs[2] - 9);

        return Math.min(dfs(copy1, depth + 1), dfs(copy2, depth + 1));
    }

    private static int calc(int num) {
        return Math.max(0, num);
    }

    private static boolean isDone(int[] scvs) {
        return scvs[0] + scvs[1] + scvs[2] == 0;
    }
}