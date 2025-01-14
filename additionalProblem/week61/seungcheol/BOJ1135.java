import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] times;
    static List<Integer>[] adjList;
    static boolean[] isLeaf;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        times = new int[N];
        isLeaf = new boolean[N];
        adjList = new List[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            isLeaf[i] = true;
            adjList[i] = new ArrayList<>();
        }

        //-1 세팅
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if(i == 0) continue;
            adjList[parent].add(i);
            isLeaf[parent] = false;
        }
        //dfs설명에서 [0]에서 부터 호출이 시작되지만
        // dfs로직이 0자체도 (하위 노드의 걸리는 시간 + 자기 자신의 호출 시간)의 형태를 반환한다.
        //하지만 앞서 말한것처럼 0은 호출되지 않고 여기서 부터 시작이므로 - 1을 해주어 결과를 출력해야한다.
        int result = dfs(0) - 1;
        System.out.println(result);
    }

    private static int dfs(int idx) {
        //리프 노드이면 해당 리프노드를 부르는데 1분이 걸리므로 1로 세팅 후 리턴
        if(isLeaf[idx]) return times[idx] = 1;

        //PQ에 인덱스, 해당 인덱스를 부르는 데 걸리는 시간 1분 + 해당 노드의 하위를 모두 호출하는데 걸리는 시간을 저장하는 Node
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < adjList[idx].size(); i++) {
            //현재 노드의 모든 자식을 순회
            int next = adjList[idx].get(i);
            //DFS를 통해 해당 노드의 모든 자식이 몇분 걸리는지 확인
            pq.offer(new Node(next, dfs(next)));
        }

        int max = 0;
        //자식들이 여러명이면 자식 하나당 1분씩 딜레이되는 것이다
        //예를들어 마지막 예시에서 1 - (3, 4, 5)로 자식을 3개 가짐
        //이때, 5는 하위 자식들을 (12, 13)가지고 12는 (12 - (18, 19))를 가진다.
        //그렇다면 5가 자식을 12(1분), 13(2분), 18(2분), 19(3분) 이렇게 호출하게 되면 최소 3분이 소요 + 1(1에서 5 자신 호출) = 4분 소요
        //다음으로 4는 (10, 11)을 자식으로 가지고 10(1분), 11(2분) 이렇게 호출하면 최소 2분 소요 + 1(1에서 4 자신 호출) = 3분 소요
        //다음으로 3은 (8, 9)를 자식으로 가지고 (8 - 17)을 자식으로 가진다. 그러면 8(1분), 9(2분), 17(2분)으로 호출하면 2분 소요 + 1(1에서 3 자신 호출) = 3분 소요
        //여기서 시간이 가장 오래 걸리는 것 부터 하나씩 호출하고 다음에 호출되는 애한테 자신보다 먼저 호출된 애의 시간을 더해주면 된다고 생각했다.
        //즉, 5 - 4 - 3 순으로 (4분, 3분, 3분)이 소요되므로 4호출 시 딜레이는 0분이다. 하지만 4를 호출함으로써 4, 3은 딜레이가 1분 생긴다.
        //이 딜레이를 다음 4호출 시 더해주어 4는 4분이 걸려서 하위를 모두 호출할 수 있게 된다.
        //또한, 5, 4를 호출하는 동안 딜레이가 2분 생겼고, 3을 호출하때 이를 더해주면 3은 자신 + 하위 노드들을 모두 호출할때 5분이 소요된다.
        //이 값을 1의 입장에서
        //나를 호출하는 시간 (고정 1분)
        // + 내 자식들이 모두 호출되는 시간 (5(4분 + 딜레이 0분 = 4분), 4(3분 + 딜레이 1분 = 4분)), 3(3분 + 딜레이 2분 = 5분) 중 가장 큰 값 = 5분
        //5분 + 1분 = 6분 (1과 3, 4, 5 및 그 하위 노드들을 모두 호출했을 때 걸리는 시간)이 나온다.
        //이런식으로 자기 자신 호출 시간 + 자기 자신의 하위 노드들이 걸리는 시간을 분할/정복하듯이 서브트리로 나누어 걸리는 최소 시간을 구하고 여기에 딜레이 되는 시간을 더해줌으로써
        //최소 시간을 만들어낸다.
        int addTime = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            times[node.idx] += addTime++;
            max = Math.max(max, times[node.idx]);
        }
        return times[idx] = max + 1;
    }
}

class Node implements Comparable<Node> {
    int idx, time;

    public Node(int idx, int time) {
        this.idx = idx;
        this.time = time;
    }

    @Override
    public int compareTo(Node o) {
        //시간이 많이 걸리는 거 우선순위 높혀주기
        return Integer.compare(o.time, this.time);
    }
}