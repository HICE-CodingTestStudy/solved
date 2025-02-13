import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Hotel {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int MAX = Integer.MAX_VALUE / 2;

    private static int C, N, maxC;
    private static int[][] cities;

    public static void main(String[] args) throws Exception {
        parseInput();
        int answer = solution();
        System.out.println(answer);
    }

    private static void parseInput() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        cities = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customers = Integer.parseInt(st.nextToken());

            cities[i][0] = cost;
            cities[i][1] = customers;
            int m = C / customers;
            if (C % customers > 0) m++;
            maxC = Math.max(maxC, m * customers);
        }
    }

    private static int solution() {
        int[][] memo = new int[N][maxC+1];
        for (int i = 0; i < N; i++) Arrays.fill(memo[i], MAX);

        for (int pow = 0; pow * cities[0][1] <= maxC; pow++) {
            int temp = pow * cities[0][1];
            memo[0][temp] = pow * cities[0][0];
        }

        for (int idx = 1; idx < N; idx++) {
            for (int customer = 0; customer <= maxC; customer++) {
                for (int pow = 0; pow * cities[idx][1] <= customer; pow++) {
                    int temp = pow * cities[idx][1];
                    int cost = pow * cities[idx][0];

                    memo[idx][customer] = Math.min(memo[idx][customer], memo[idx-1][customer-temp] + cost);
                }
            }
        }

        int minCost = MAX;
        for (int c = C; c <= maxC; c++) {
            minCost = Math.min(minCost, memo[N-1][c]);
        }

        return minCost;
    }
}