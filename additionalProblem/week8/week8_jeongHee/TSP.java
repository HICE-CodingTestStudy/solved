package queue;

import java.util.Arrays;
import java.util.Scanner;

public class TSP {
    //https://www.acmicpc.net/problem/2098
    //외판원 순회
    static int N;

    //dp[i][j] = 현재 i노드에 있고 j(2진수)의 각 자리가 1인 도시는 돌았을때 나머지 도시를 다 돌아 순회를 마칠 때 최소 비용
    //dp[2][011] = 1,2 노드를 돌아서 현재 2번 노드에 있는데 3번 노드까지 다 돌았을때 드는 최소 비용
    static long[][] dp;
    static int[][] map;
    static boolean[][] visited;

    public static long visit(int startNode, int visitedNode) {
        //다 돌았을때 -> 처음으로 돌아가기만 하면 됨
        if (visitedNode == (1 << N) - 1){
            //처음으로 못돌아감 -> 길없음처리
            if(map[startNode][0]==0){
                visited[startNode][visitedNode]=true;
                return Integer.MAX_VALUE;
            }
            return map[startNode][0];
        }
        //이미 이전에 똑같이 돌았던 기록이 있을때 해당 값 리턴
        if (dp[startNode][visitedNode] != Integer.MAX_VALUE || visited[startNode][visitedNode])
            return dp[startNode][visitedNode];
        //다돈 것도 아니고 이전에 방문했던 경로가 아닐 경우
        //해당 지점으로부터 남은 도시를 하나 선택해 다음으로 방문했을때, 최소가 되는 비용이 이미 정해져있는지 탐색
        //ex) dp[1][001] 상태라면
        // dp[2][011] + 1에서 2로 가는 비용 과 기존 dp[2][011]을 비교하여 1,2 노드를 돈 상태에서 모든 순회를 마칠때까지 돌 때 최소 비용을 저장한다.
        for (int i = 0; i < N; i++) {
            //이미 방문했거나 못가는 노드는 패스
            if ((visitedNode & 1 << i) != 0) continue;
            if (map[startNode][i] == 0) continue;
            if(visited[i][visitedNode|1<<i]) continue;
            dp[startNode][visitedNode] = Math.min(dp[startNode][visitedNode],visit(i,visitedNode|1<<i)+map[startNode][i]);
        }
        //현재 상태에서 남은 마을을 방문하면서 한바퀴도는 경우가 없는 경우 (이미 탐색해봤는데도 초기값이면 돌지 못하는 경우이다)
        //앞으로 이 상태가 됐을땐 돌아볼 필요가 없다.
        if(dp[startNode][visitedNode]==Integer.MAX_VALUE) visited[startNode][visitedNode]=true;
        return dp[startNode][visitedNode];

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        dp = new long[N][1 << N];
        visited = new boolean[N][1<<N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        System.out.println(visit(0,1));
    }
}
