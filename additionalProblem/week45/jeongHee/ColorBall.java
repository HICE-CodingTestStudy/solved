import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ColorBall {
    //https://www.acmicpc.net/problem/10800
    //컬러볼
    static int N;
    static int[] sum;
    static int[] ans;
    static List<Info> infos = new ArrayList<>();

    static class Info implements Comparable<Info> {
        int index, color, size;

        public Info(int index, int color, int size) {
            this.index = index;
            this.color = color;
            this.size = size;
        }

        @Override
        public int compareTo(Info o) {
            return size - o.size;
        }
    }

    static void solution() {
        Collections.sort(infos);
        sum = new int[N + 2];
        int before = infos.get(0).size;
        int nowCount = 0;
        Queue<Info> wait = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            Info now = infos.get(i);
            if (before != now.size) {
                sum[N + 1] += nowCount;
                nowCount = 0;
                before = now.size;
                while (!wait.isEmpty()) {
                    sum[wait.peek().color] += wait.poll().size;
                }
            }
            ans[now.index] = sum[N + 1] - sum[now.color];
            nowCount += now.size;
            wait.add(now);
        }
    }

    static void printAns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ans[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        ans = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            infos.add(new Info(i, color, size));
        }
        solution();
        printAns();
    }

}
