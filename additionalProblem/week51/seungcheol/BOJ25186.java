import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ25186 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long max = 0;
        long sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long d = Long.parseLong(st.nextToken());
            max = Math.max(max, d);
            sum += d;
        }

        // 가장 많은 옷 가지수가 나머지의 합보다 크면 불가능
        // (1)4, (2)1, (3)1, (4)1 -> 1, 2, 1, 3, 1, 4, 1 -> 이렇게 가운데에 나머지를 다 넣어도 불가능하므로
        // 저격테케 그냥 두람이 혼자인 경우는 가능함 but 옷의 종류가 1개인데 사람이 3명이면 무조건 겹침
        System.out.println((N == 1 && sum == 1) ? "Happy" : (max > sum - max ? "Unhappy" : "Happy"));
    }
}