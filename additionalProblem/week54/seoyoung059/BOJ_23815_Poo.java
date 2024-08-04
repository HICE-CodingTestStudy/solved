package solved.additionalProblem.week54.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_23815_Poo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n + 1][2];
        arr[0][0] = 0;
        arr[0][1] = 1;
        StringTokenizer st;
        String tmp;
        int num;
        boolean over = false;
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 2; j++) {
                tmp = st.nextToken();
                num = tmp.charAt(1) - '0';
                for (int k = 0; k < 2; k++) {
                    if (arr[i - 1][k] <= 0) continue;
                    switch (tmp.charAt(0)) {
                        case '+':
                            arr[i][k] = Math.max(arr[i][k], arr[i - 1][k] + num);
                            break;
                        case '-':
                            arr[i][k] = Math.max(arr[i][k], arr[i - 1][k] - num);
                            break;
                        case '*':
                            arr[i][k] = Math.max(arr[i][k], arr[i - 1][k] * num);
                            break;
                        case '/':
                            arr[i][k] = Math.max(arr[i][k], arr[i - 1][k] / num);
                            break;
                    }
                }
            }
            arr[i][0] = Math.max(arr[i][0], arr[i - 1][1]);
            if (arr[i][0] <= 0 && arr[i][1] <= 0) {
                over = true;
                break;
            }
//            System.out.println(Arrays.toString(arr[i]));
        }

        System.out.println(over ? "ddong game" : Math.max(arr[n][0], arr[n][1]));
    }
}
