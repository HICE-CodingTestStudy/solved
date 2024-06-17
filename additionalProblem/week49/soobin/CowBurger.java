import java.io.*;

public class CowBurger {
    private static class Order {
        int burger, fry;

        Order(int burger, int fry) {
            this.burger = burger;
            this.fry = fry;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        Order[] orders = new Order[N];
        int nBurgers = Integer.parseInt(input[1]);
        int nFries = Integer.parseInt(input[2]);

        int[][][] memo = new int[N][nBurgers + 1][nFries + 1];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            int b = Integer.parseInt(input[0]);
            int f = Integer.parseInt(input[1]);
            orders[i] = new Order(b, f);
        }

        for (int b = 1; b <= nBurgers; b++)
            for (int f = 1; f <= nFries; f++) {
                if (orders[0].burger > b || orders[0].fry > f) continue;
                memo[0][b][f] = 1;
            }

        for (int o = 1; o < N; o++) {
            for (int b = 1; b <= nBurgers; b++) {
                for (int f = 1; f <= nFries; f++) {
                    Order order = orders[o];
                    if (order.burger > b || order.fry > f) {
                        memo[o][b][f] = memo[o - 1][b][f];
                        continue;
                    }

                    memo[o][b][f] = Math.max(
                            memo[o - 1][b][f],
                            memo[o - 1][b - order.burger][f - order.fry] + 1
                    );
                }
            }
        }

        bw.write(String.valueOf(memo[N - 1][nBurgers][nFries]));
        bw.flush();
    }
}
