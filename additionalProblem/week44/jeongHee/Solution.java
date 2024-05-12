import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    //https://www.acmicpc.net/problem/2467
    //용액
    static int N;
    static List<Integer> plus = new ArrayList<>(), minus = new ArrayList<>();

    static int binarySearch(int target, int right, List<Integer> list) {
        int left = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) == target) return mid;
            if (list.get(mid) < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    static int[] solution() {
        int[] ret = null;
        if (minus.size() == 0) return new int[]{plus.get(0), plus.get(1)};
        if (plus.size() == 0) return new int[]{minus.get(minus.size() - 2), minus.get(minus.size() - 1)};
        int gap = Integer.MAX_VALUE;
        if (minus.size() >= 2) {
            if (gap > Math.abs(minus.get(minus.size() - 1) + minus.get(minus.size() - 2))) {
                ret = new int[]{minus.get(minus.size() - 2), minus.get(minus.size() - 1)};
                gap = Math.abs(minus.get(minus.size() - 1) + minus.get(minus.size() - 2));
            }
        }
        if (plus.size() >= 2) {
            if (gap > Math.abs(plus.get(0) + plus.get(1))) {
                ret = new int[]{plus.get(0), plus.get(1)};
                gap = Math.abs(plus.get(0) + plus.get(1));
            }
        }
        for (Integer target : plus) {
            int index = binarySearch(-target, minus.size() - 1, minus);
            if (index - 1 >= 0 && Math.abs(minus.get(index - 1) + target) < gap) {
                ret = new int[]{minus.get(index - 1), target};
                gap = Math.abs(ret[0] + ret[1]);
            }
            if (Math.abs(minus.get(index) + target) < gap) {
                ret = new int[]{minus.get(index), target};
                gap = Math.abs(ret[0] + ret[1]);
            }
            if (index + 1 < minus.size() && Math.abs(minus.get(index + 1) + target) < gap) {
                ret = new int[]{minus.get(index + 1), target};
                gap = Math.abs(ret[0] + ret[1]);
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a >= 0) plus.add(a);
            else minus.add(a);
        }
        int[] ans = solution();
        System.out.println(ans[0] + " " + ans[1]);
    }

}