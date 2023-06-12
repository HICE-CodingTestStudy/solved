public class WayToSchool {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];

        // puddle init
        for (int[] puddle : puddles)
            map[puddle[1] - 1][puddle[0] - 1] = -1;

        // route init
        for (int i = 1; i < n; i++) {
            if (map[i][0] == -1) break;
            map[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            if (map[0][i] == -1) break;
            map[0][i] = 1;
        }

        for (int i = 1; i < n; i++)
            for(int j = 1; j < m; j++) {
                if (map[i][j] == -1) continue;

                int left = map[i-1][j] == -1 ? 0 : map[i-1][j];
                int top = map[i][j-1] == -1 ? 0 : map[i][j-1];
                map[i][j] = (left + top) == 0 ? -1 : (left + top) % 1000000007;
            }

        return map[n-1][m-1] == -1 ? 0 : map[n-1][m-1];
    }

    public static void main(String[] args) {
        WayToSchool w = new WayToSchool();
        int m = 4;
        int n = 3;
        int[][] puddles = {{2,2}};
        System.out.println(w.solution(m, n, puddles));
    }
}
