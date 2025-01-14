import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Sector {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static List<int[]> sectors;

    public static void main(String[] args) throws Exception {
        parseInput();
        System.out.println(solution());
    }

    private static void parseInput() throws Exception {
        int N = Integer.parseInt(br.readLine());
        sectors = new ArrayList<>();

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sectors.add(new int[] {start, end});
        }

        sectors.sort(Comparator.comparingInt(o -> o[0]));
    }

    private static int solution() {
        int max = 0;

        for (int i = 0; i < sectors.size(); i++) {
            max = Math.max(max, countIncluding(i));
        }

        return max;
    }

    private static int countIncluding(int idx) {
        int end = sectors.get(idx)[1];
        int count = 0;

        for (int i = idx + 1; i < sectors.size(); i++) {
            int[] sector = sectors.get(i);
            if (sector[0] > end) break;
            if (sector[1] < end) count++;
        }

        return count;
    }
}