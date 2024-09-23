import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ8980 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(start, end, cost));
        }
        int[] weights = new int[N + 1];

        int sum = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            //남은 공간과 현재 무게 중 작은 것
            int min = Math.min(edge.cost, C - weights[edge.start]);
            //쭉 돌면서 이전까지 넣어둔 무게 안에서 가장 많이 넣을 수 있는 무게를 찾음
            //그리디한 접근 -> 가장 짧은 거리를 갈때 가장 많이 물건을 가지고 가자
            //어차피 총 40의 무게만 가능할 때 -> 1 4 30, 2 3 30이면,
            // 1 4 에서 30, (2 3에서 10 == 2 3 에서 30), 1 4 에서 10은 어차피 동일한 무게를 옮기는 것임
            // Ex)
            //6 60
            //5
            //1 2 30 [30][도착 : 0][0][0][0][0]
            //3 4 40 [30][0][40][도착 : 0][0][0]
            //2 5 70 [30][가능 : 60][가능 : 20][가능 : 20][도착 : 0][0] -> 가능 도중에 더 이득인 것
            for (int i = edge.start; i <= edge.end; i++) {
                min = Math.min(min, C - weights[i]);
                if(min <= 0) break;
            }
            if(min <= 0) continue;
            for (int i = edge.start; i < edge.end; i++) {
                weights[i] += min;
            }
            sum += min;
        }
        System.out.println(sum);
    }
}

class Edge implements Comparable<Edge> {
    int start, end, cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        //끝이 가까운 것 먼저 같으면 시작이 더 큰 것 먼저
        if(this.end == o.end) return Integer.compare(o.start, this.start);
        return Integer.compare(this.end, o.end);
    }
}