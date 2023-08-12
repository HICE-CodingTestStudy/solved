import java.io.*;

public class WomenPresident {
    private static int[][] memo = new int[15][15];

    private static int people(int floor, int house) {
        if (floor == 0) return memo[floor][house] = house + 1;

        if (memo[floor][house] > 0) return memo[floor][house];

        for (int i = 0; i <= house; i++)
            memo[floor][house] += people(floor - 1, i);

        return memo[floor][house];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            bw.write(String.valueOf(people(k, n - 1)));
            bw.newLine();
        }

        bw.flush();
    }
}
