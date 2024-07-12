import java.io.*;
import java.util.StringTokenizer;

public class BOJ28018 {
    static int[] arr = new int[1_000_002];
    static int[] prefix = new int[1_000_002];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            start = Math.min(start, s);
            end = Math.max(end, e);
            //시작과 끝만 체크해두고 이를 통해 선형으로 누적합 갱신
            arr[s]++;
            arr[e + 1]--;
        }

        //imos 알고리즘 -> 누적합의 확장 
        for (int i = start; i <= end; i++) {
            prefix[i] = arr[i] + prefix[i - 1];
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(prefix[num]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}