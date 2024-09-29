import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class RockGroup {
    private static class Group {
        int[] rocks;

        Group(int[] rocks) {
            this.rocks = rocks;
        }

        boolean isEquals() {
            return rocks[0] == rocks[1] && rocks[1] == rocks[2];
        }

        int total() {
            return rocks[0] + rocks[1] + rocks[2];
        }

        int min() {
            return Math.min(rocks[0], Math.min(rocks[1], rocks[2]));
        }

        int max() {
            return Math.max(rocks[0], Math.max(rocks[1], rocks[2]));
        }

        @Override
        public boolean equals(Object obj) {
            Group other = (Group) obj;
            int total = total();
            int otherTotal = other.total();
            if (total != otherTotal) return false;

            return min() == other.min()
                    && max() == other.max()
                    && (total - min() - max()) == (otherTotal - other.min() - other.max());
        }

        @Override
        public int hashCode() {
            int min = Math.min(rocks[0], Math.min(rocks[1], rocks[2]));
            int max = Math.max(rocks[0], Math.max(rocks[1], rocks[2]));
            return Objects.hash(min, max, total() - min - max);
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] combinations = {{0, 1, 2}, {0, 2, 1}, {1, 2, 0}};
    private static final Set<Group> visited = new HashSet<>();

    public static void main(String[] args) throws Exception {
        Group start = init();
        System.out.println(solution(start));
    }

    private static Group init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] rocks = new int[3];
        for (int i = 0; i < 3; i++) {
            rocks[i] = Integer.parseInt(st.nextToken());
        }
        Group start = new Group(rocks);

        return start;
    }

    private static int solution(Group start) {
        Queue<Group> queue = new ArrayDeque<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Group group = queue.poll();
            if (group.isEquals()) return 1;

            int[] rocks = group.rocks;
            for (int[] comb : combinations) {
                int i = comb[0], j = comb[1], k = comb[2];
                if (rocks[i] == rocks[j]) continue;

                int[] nextRocks = new int[3];
                int small = rocks[i] < rocks[j] ? i : j;
                int big = rocks[i] < rocks[j] ? j : i;
                nextRocks[big] = rocks[big] - rocks[small];
                nextRocks[small] = rocks[small] * 2;
                nextRocks[k] = rocks[k];

                Group next = new Group(nextRocks);
                if (visited.contains(next)) continue;

                queue.add(next);
                visited.add(next);
            }
        }

        return 0;
    }
}
