import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //https://www.acmicpc.net/problem/15729
    //방탈출
    static int N;
    static boolean[] button;

    static int solution() {
        int count = 0;
        boolean[] now = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (now[i] != button[i]) {
                count++;
                for (int j = i; j < N && j < i + 3; j++) {
                    now[j] = !now[j];
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        button = new boolean[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            button[i] = a == 1;
        }
        System.out.println(solution());
    }
}