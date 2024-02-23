public class Graph {
    private final int IN = 0, OUT = 1, MAX_NODE = 1000001;
    private final int[][] numEdges = new int[MAX_NODE][2];

    private int lastNode;

    public int[] solution(int[][] edges) {
        countEdges(edges);
        return countGraphs();
    }

    private void countEdges(int[][] edges) {
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            lastNode = Math.max(lastNode, Math.max(from, to));
            numEdges[from][OUT]++;
            numEdges[to][IN]++;
        }
    }

    /*
    * 도넛 : 전체 그래프 수 - (막대 + 8자)
    * 막대 : in = 상관 X (추가된 정점 연결 가능), out = 0
    * 8자 : in = 상관 X (추가된 정점 연결 가능), out = 2
    * 그래프에 속하는 정점 : in = 상관 X, out = 1
    * 추가된 정점 : in = 0, out >= 2 ---> 전체 그래프 개수
     */
    private int[] countGraphs() {
        int newNode = 0, stick = 0, eight = 0, totalGraphs = 0;
        for (int i = 1; i <= lastNode; i++) {
            if (numEdges[i][IN] == 0 && numEdges[i][OUT] >= 2) {
                totalGraphs = numEdges[i][OUT];
                newNode = i;
            }
            else if (numEdges[i][OUT] == 0) stick++;
            else if (numEdges[i][OUT] == 2) eight++;
        }
        int donut = totalGraphs - stick - eight;

        return new int[] {newNode, donut, stick, eight};
    }
}
