import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class News {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static List<Integer>[] empTree;

    public static void main(String[] args) throws Exception {
        parseInput();
        int max = notifyNews(0);
        System.out.println(max - 1);
    }

    private static void parseInput() throws Exception {
        int N = Integer.parseInt(br.readLine());
        empTree = new List[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        empTree[0] = new ArrayList<>();
        st.nextToken();

        for (int i = 1; i < N; i++) {
            int boss = Integer.parseInt(st.nextToken());
            empTree[i] = new ArrayList<>();
            empTree[boss].add(i);
        }
    }

    private static int notifyNews(int idx) {
        if (empTree[idx].isEmpty()) return 1;

        Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int child : empTree[idx])
            pq.add(notifyNews(child) + 1);

        int max = 0, i = 0;
        while (!pq.isEmpty())
            max = Math.max(max, pq.poll() + i++);

        return max;
    }
}