import java.io.*;

public class Main {
    static int N;
    static int[] result;
    static boolean isEnd;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        result = new int[N];
        isEnd = false;
        int[] arr = new int[N];
        for (int i = 1; i <= 3; i++) {
            arr[0] = i;
            dfs(1, arr);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(result[i]);
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    public static void dfs(int depth, int[] arr) {
        if(isEnd) return;
        if (depth == N) {
            for (int i = 0; i < N; i++) {
                result[i] = arr[i];
            }
            isEnd = true;
            return;
        }
        for (int i = 1; i <= 3; i++) {
            //현재 위치에 1, 2, 3중 하나 넣기
            arr[depth] = i;
            boolean flag = false;
            //현재 위치 이전을 전부 탐색하며 연속된 겹치는 배열이 있는지 확인
            for (int j = depth - 1; j >= 0; j--) {
                //다르면 넘어가기
                if (arr[j] != i) continue;
                //arr[j] == 현재 수이면 해당 위치부터 투포인터로 서로 비교
                boolean temp = true;
                for (int k = depth, l = j; k > j; k--, l--) {
                    //왼쪽끝까지 나가면 두 부분 수열이 같지 않음 23 123 이렇게 두개를 비교할때 3=3, 2=2 1=X이므로
                    if (l < 0) {
                        temp = false;
                        break;
                    }
                    if (arr[k] == arr[l]) continue;
                    //다른 부분 발생 시 false
                    temp = false;
                    break;
                }
                //12313`12313` 이렇게 중간에 3이 나와서
                //12313부분만 체크하면 안됨 (3=3,,1!=2로 검사를 끝내면)
                //전체 숫자 배열인 12313 == 1231을 놓치게 됨.
                flag |= temp;
            }
            if (flag) continue;
            dfs(depth + 1, arr);
        }
    }
}