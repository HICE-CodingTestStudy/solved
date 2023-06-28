package soobin;

import java.io.*;
import java.util.*;

public class NumCard {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int getLowerBound(int[] arr, int target, int start, int end) {
        if (start > end) return -1;

        int mid = (start + end) / 2;

        if ((mid == 0 || target > arr[mid - 1]) && arr[mid] == target) return mid;
        else if (arr[mid] >= target) return getLowerBound(arr, target, start, mid - 1);
        else return getLowerBound(arr, target, mid + 1, end);
    }

    private static int getUpperBound(int[] arr, int target, int start, int end) {
        if (start > end) return -1;

        int mid = (start + end) / 2;

        if ((mid == arr.length - 1 || target < arr[mid + 1]) && arr[mid] == target) return mid;
        else if (arr[mid] > target) return getUpperBound(arr, target, start, mid - 1);
        else return getUpperBound(arr, target, mid + 1, end);
    }

    private static int count(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        int lower = getLowerBound(arr, target, start, end);

        if (lower == -1) return 0;

        int upper = getUpperBound(arr, target, start, end);
        return upper - lower + 1;
    }

    public static String byBinarySearch() throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            int n = Integer.parseInt(st.nextToken());
            sb.append(count(arr, n)).append(" ");
        }
        return sb.toString();
    }

    public static String byHashMap() throws IOException {
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int key = Integer.parseInt(st.nextToken());
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            int n = Integer.parseInt(st.nextToken());
            sb.append(map.getOrDefault(n, 0)).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        bw.write(byHashMap());
        bw.newLine();
        bw.flush();
    }
}
