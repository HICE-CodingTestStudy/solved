import java.io.BufferedReader;
import java.io.InputStreamReader;

class Painting {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[][][] memo;
    private static int[][] transactions;
    private static int N;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dp(0,1,0));
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        transactions = new int[N][N];
        memo = new int[1<<N][N][10];

        for (int i = 0; i < N; i++) {
            char[] prices = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                transactions[i][j] = prices[j] - '0';
            }
        }
    }

    private static int dp(int idx, int mask, int price) {
        if (memo[mask][idx][price] > 0) return memo[mask][idx][price];

        for (int i = 0; i < N; i++) {
            if (isInvalid(idx, i, price) || (mask & (1 << i)) > 0) continue;
            memo[mask][idx][price] = Math.max(memo[mask][idx][price], dp(i, mask + (1 << i), transactions[idx][i]));
        }

        return memo[mask][idx][price] + 1;
    }

    private static boolean isInvalid(int seller, int customer, int minPrice) {
        return seller == customer || transactions[seller][customer] < minPrice;
    }
}