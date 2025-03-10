import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Secom {
    static List<int[]> block = new ArrayList<>();
    static int[] me;
    static int meCase;
    static int N, M;

    static int solution() {
        int ans = 0;
        for (int[] b : block) {
            if (meCase == 1 || meCase == 2) {
                if (Math.abs(b[0] - me[0]) != N)
                    ans += Math.abs(b[0] - me[0]) + Math.abs(b[1] - me[1]);
                else ans += Math.min((me[1] + b[1]), (M - me[1] + M - b[1])) + N;
            } else {
                if (Math.abs(b[1] - me[1]) != M)
                    ans += Math.abs(b[0] - me[0]) + Math.abs(b[1] - me[1]);
                else ans += Math.min((me[0] + b[0]), (N - me[0] + N - b[0])) + M;
            }

        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(bf.readLine()) + 1;
        while (p-- > 0) {
            st = new StringTokenizer(bf.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int[] next;
            switch (i) {
                case 1:
                    next = new int[]{0, j};
                    break;
                case 2:
                    next = new int[]{N, j};
                    break;
                case 3:
                    next = new int[]{j, 0};
                    break;
                default:
                    next = new int[]{j, M};
            }
            if (p == 0) {
                me = next;
                meCase = i;
            } else block.add(next);
        }
        System.out.println(solution());
    }

}
