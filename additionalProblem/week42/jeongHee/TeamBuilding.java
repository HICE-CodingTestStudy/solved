import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TeamBuilding {
    //https://www.acmicpc.net/problem/22945
    //팀빌딩
    static int N;
    static int[] arr;

    static int solution() {
        int ans = 0;
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            ans = Math.max(ans, Math.min(arr[l], arr[r]) * (r - l - 1));
            //왼쪽 옮기기
            if (arr[l] < arr[r]) {
                l++;
                continue;
            }
            //오른쪽 옮기기
            r--;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }
}
