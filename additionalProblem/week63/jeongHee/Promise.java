import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 원래의 기다리는 시간들의 배열(origin)에 통일적으로 같은 숫자를 빼거나 더해 조작을 한 배열(change)에 대해서
 * a 에 대해 답을 구하든 b에 대해 답을 구하든 답은 동일할것이다.
 * 따라서 기존 배열의 정가운데의 숫자(배열 크기가 짝수라면 N/2-1위치의 숫자)를 0으로 만들도록 배열을 조작하여 시작한다.
 * 그럼 0을 기준으로 왼쪽에 존재하는 숫자들의 개수 (left) 와 오른쪽에 존재하는 숫자들의 개수 (right) 은
 * N이 홀수 : 동일, 짝수 : 오른쪽이 1개 많음 일 것이다.
 * <p>
 * 이제 change에 적절한 수를 더하거나 빼서 최적화를 해 답을 구해야 한다.
 * 이때 수 x를 더한다면 left * x 만큼의 이득을 보고, right * x 만큼의 손해를 보게 되고, 정가운데의 0또한 조작 시 x 만큼의 손해를 본다.
 * 반대로 뺀다면 정 반대가 될 것이다.
 * <p>
 * 따라서 양쪽의 개수가 동일하다면 그 상태에서 어떤식으로 조작을 하든 0의 변화에 의해 손해일 수 밖에 없다. change 상태가 최적인 단하나의 케이스이다.
 * 개수가 다르다면 (right가 1 큰 경우) 수를 더해서는 절대 이득을 볼 수 없다. (이득 개수(left) 가 손해 개수 (right+1)보다 무조건 작기때문)
 * 하지만 반대로 수를 뺀다면 현상유지를 할 수 있다. (이득 개수 (right)- 손해 개수(left + 1) 이 동일하기 때문)
 * 하지만 무작정 빼서는 안되고 0바로 오른쪽의 숫자가 음수가 되지 않는 선까지만 뺄 수 있다. 음수가 되면 그때부터 손해(left+1)이 이득(right)보다 크기 때문
 * 따라서 x의 범위는 0부터 arr[N/2]-arr[N/2-1] 까지 가능하다.
 */
public class Promise {
    // https://www.acmicpc.net/problem/1183
    // 약속
    static int N;
    static int[] wait;

    static int solution() {
        // 개수가 홀수면 1개
        // 개수가 짝수면 가운데 숫자 두개끼리의 차 +1
        Arrays.sort(wait);
        if (N % 2 == 1) return 1;
        return Math.abs(wait[N / 2 - 1] - wait[N / 2]) + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        wait = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            wait[i] = a - b;
        }
        System.out.println(solution());
    }
}

