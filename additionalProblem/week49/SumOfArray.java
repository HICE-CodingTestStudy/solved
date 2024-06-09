package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SumOfArray {
    //https://www.acmicpc.net/problem/2143
    //두 배열의 합
    static int T, n, m;
    static int[] a, b;
    static Map<Integer, Integer> sumA = new HashMap<>(), sumB = new HashMap<>();

    static void getSum(int[] arr, Map<Integer, Integer> map) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
        }
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], 0);
            map.put(arr[i], map.get(arr[i]) + 1);
            for (int j = i + 1; j < arr.length; j++) {
                map.putIfAbsent(arr[j] - arr[i], 0);
                map.put(arr[j] - arr[i], map.get(arr[j] - arr[i]) + 1);
            }
        }
    }

    static long solution() {
        getSum(a, sumA);
        getSum(b, sumB);
        long count = 0;
        for (Integer a : sumA.keySet()) {
            count += (long) sumA.get(a) * sumB.getOrDefault(T - a, 0);
        }
        return count;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        n = Integer.parseInt(bf.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(bf.readLine());
        b = new int[m];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }
}
