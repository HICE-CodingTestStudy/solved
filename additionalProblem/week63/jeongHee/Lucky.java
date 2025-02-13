import java.util.*;
import java.io.*;

public class Main {
    static int N, ans;
    static String s;
    static int[] info;

    static void init(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.putIfAbsent(s.charAt(i), 0);
            map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
        }
        info = new int[map.keySet().size()];
        int i = 0;
        for (Character c : map.keySet()) {
            info[i++] = map.get(c);
        }
    }

    static void solution(int count, int[] visited, int[] arr) {
        if (count == N) {
            ans++;
            return;
        }
        for (int i = 0; i < info.length; i++) {
            if (visited[i] >= info[i]) continue;
            if (count == 0 || arr[count - 1] != i) {
                visited[i]++;
                arr[count] = i;
                solution(count + 1, visited, arr);
                visited[i]--;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        N = s.length();
        init(s);
        solution(0, new int[info.length], new int[N]);
        System.out.println(ans);
    }
}