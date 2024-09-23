import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Mutalisk {
    private static class Unit {
        int[] scv;
        int count;

        Unit(int[] scv, int count) {
            this.scv = scv;
            this.count = count;
        }

        public boolean isEnd() {
            for (int i = 0; i < N; i++) {
                if (scv[i] > 0) return false;
            }
            return true;
        }

        @Override
        public boolean equals(Object obj) {
            Unit other = (Unit) obj;
            int count = 0;
            for (int i = 0; i < N; i++)
                if (other.scv[i] == scv[i]) count++;
            return count == N;
        }

        @Override
        public int hashCode() {
            return Objects.hash(Arrays.hashCode(scv));
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] ATTACK = {{0, 1, 2}, {1, 0, 2}, {0, 2, 1}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};

    private static int N;

    public static void main(String[] args) throws Exception {
        Set<Unit> visited = new HashSet<>();
        Queue<Unit> queue = new ArrayDeque<>();
        int[] scv = init();
        queue.add(new Unit(scv, 0));

        int count = 0;
        while (!queue.isEmpty()) {
            Unit unit = queue.poll();

            if (unit.isEnd()) {
                count = unit.count;
                break;
            }

            for (int i = 0; i < Math.max(N * (N - 1), 1); i++) {
                int[] temp = new int[N];
                int amount = 9;
                for (int j = 0; j < N; j++) {
                    int idx = ATTACK[i][j];
                    temp[idx] = unit.scv[idx] - amount;
                    amount /= 3;
                }

                Unit next = new Unit(temp, unit.count + 1);
                if (visited.contains(next)) continue;
                visited.add(next);
                queue.add(next);
            }
        }

        System.out.println(count);
    }

    private static int[] init() throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] scv = new int[N + 1];
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }
        return scv;
    }
}
