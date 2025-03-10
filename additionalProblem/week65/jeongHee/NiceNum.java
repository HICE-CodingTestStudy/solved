import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NiceNum {
    static int N;
    static String ans = "";

    static String toString(int[] arr) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            s.append(arr[i]);
        }
        return s.toString();
    }

    static boolean isValid(int count, int index, int[] arr) {
        for (int i = index; i < index + count; i++) {
            if (arr[i] != arr[i + count]) return true;
        }
        return false;
    }

    static boolean solution(int count, int[] arr) {
        if (count == N) {
            ans = toString(arr);
            return true;
        }
        for (int i = 1; i <= 3; i++) {
            if (arr[count - 1] == i) continue;
            arr[count] = i;
            if (count <= 2) {
                if(solution(count + 1, arr)) return true;
            }
            boolean isValid = true;
            for (int j = 2; j <= (count + 1) / 2; j++) {
                if (!isValid(j, count - (j * 2 - 1), arr)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                if(solution(count + 1, arr)) return true;
            }
        }
        return false;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N];
        arr[0] = 1;
        solution(1, arr);
        System.out.println(ans);
    }
}
