import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class MorningWalk {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer>[] tree;
    private static boolean[] isIndoor, visited;
    private static int N;

    public static void main(String[] args) throws Exception {
        parseInput();
        int answer = solution();
        System.out.println(answer);
    }

    private static void parseInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        String line = br.readLine();
        tree = new List[N];
        isIndoor = new boolean[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            isIndoor[i] = line.charAt(i) == '1';
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]) - 1;
            int v = Integer.parseInt(input[1]) - 1;
            tree[u].add(v);
            tree[v].add(u);
        }
    }

    private static int solution() {
        int numPaths = 0;

        for (int i = 0; i < N; i++) {
            if (!isIndoor[i]) continue;

            visited[i] = true;
            for (int adj : tree[i]) {
                if (visited[adj]) continue;

                int numIndoor = isIndoor[adj] ? 1 : findPath(adj);
                numPaths += numIndoor * (numIndoor + 1);
            }
        }

        return numPaths;
    }

    private static int findPath(int start) {
        Stack<Integer> stack = new Stack<>();
        visited[start] = true;
        stack.push(start);

        int numIndoor = 0;
        while (!stack.isEmpty()) {
            int n = stack.pop();

            for (int adj : tree[n]) {
                if (visited[adj]) continue;

                if (isIndoor[adj]) numIndoor++;
                else {
                    visited[adj] = true;
                    stack.push(adj);
                }
            }
        }

        return numIndoor;
    }
}
