import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CityBuilding {
    //https://www.acmicpc.net/problem/21924
    //도시 건설
    static int N, M;
    static int[] parent, height;
    static long maxCost;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

    static int find(int x) {
        if (parent[x] < 0)
            return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py)
            return false;
        if (height[py] > height[px]) {
            int tmp = px;
            px = py;
            py = tmp;
        }
        if (height[px] == height[py])
            height[px]++;
        parent[px] += parent[py];
        parent[py] = px;
        return true;
    }

    static long solution() {
        int count = 0;
        long cost = 0;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            if(count==N-1) return maxCost - cost;
            if(union(now[0],now[1])) {
                cost += now[2];
                count++;
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        height = new int[N + 1];
        parent = new int[N + 1];
        Arrays.fill(parent, -1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            maxCost += c;
            pq.add(new int[]{a, b, c});
        }
        System.out.println(solution());
    }
}
