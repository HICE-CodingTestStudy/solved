import java.io. *;
import java.util.*;

public class Main {
    static long[] arr;
    static int N, M, max, min;//max : 가능한 곡 개수 최대, min : 이때 최소 기타 개수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new long[N];
        min = Integer.MAX_VALUE;
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            String str = st.nextToken();
            long bit = 0;
            for(int j = 0; j < M; j++) {
                if(str.charAt(j) == 'Y') {
                    bit |= (1L << j);
                }
            }
            arr[i] = bit;
        }
        dfs(0, 0, 0);
        System.out.println(max == 0 ? - 1 : min);
    }
    public static void dfs(int start, int count, long bit) {
        int cnt = 0;
        for(int i = 0; i < M; i++) {
            if((bit & (1L << i)) != 0) cnt++;     
        }
        
        if(cnt > max) {
            max = cnt;
            min = count; 
        } else if(cnt == max) {
            min = Math.min(min, count);
        }
        
        for(int i = start; i < N; i++) {
            dfs(i + 1, count + 1, bit | arr[i]);
        }
    }
}
