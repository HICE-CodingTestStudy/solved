import java.util.*;

public class SheepAndWolf {
    private List<Integer>[] graph;
    private int[] memo, info;
    private int answer, N;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        initialize();
        makeGraph(edges);
        gatherSheep(0, 1, 0, 0);

        return answer;
    }

    private void initialize() {
        N = info.length;
        memo = new int[1 << (N + 1)];
        Arrays.fill(memo, -1);
    }

    private void makeGraph(int[][] edges) {
        graph = new List[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
    }

    private void gatherSheep(int idx, int mask, int sheep, int wolf) {
        if (memo[mask] > -1) return;

        if (info[idx] == 0) sheep++;
        else wolf++;

        // 양들 다 잡아 먹히면 다른 노드로 가도 의미 X
        if (sheep == wolf) {
            memo[mask] = 0;
            return;
        }

        memo[mask] = sheep;
        answer = Math.max(answer, sheep);

        // 지금 방문 상태에서 갈 수 있는 다른 노드 방문
        for (int i = 0; i < graph.length; i++) {
            if ((mask & (1 << i)) == 0) continue;

            for (int animal : graph[i]) {
                if ((mask & (1 << animal)) > 0) continue;

                gatherSheep(animal, mask + (1 << animal), sheep, wolf);
            }
        }
    }
}
