package additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinAndMax {
    //https://www.acmicpc.net/problem/2357
    //최소값과 최대값
    static int N;
    static int M;
    static DP[] dpMin;
    static DP[] dpMax;
    static long[] nums;
    static int nodeCount;

    public enum Tag {
        MIN, MAX;
    }

    public static class DP {
        int left;
        int right;
        long value;

        public DP(int left, int right, long value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }

    public static int findMid(int n) {
        int cnt = 0;
        while (n / 2 != 0) {
            n /= 2;
            cnt++;
        }
        return (int) Math.pow(2, cnt);
    }

    //특정 배열의 left 부터 right 까지 최대/최소를 dp 배열에 저장함
    public static long fill(int left, int right, int index, DP[] arr, Tag tag) {
        //구간의 크기가 1이라면 해당 배열의 값을 저장함
        if (left == right) {
            arr[index] = new DP(left, right, nums[left]);
            return nums[left];
        }
        //아니라면 왼/오 로 나눔
        int mid = (left + right) / 2;
        arr[index] = new DP(left, right,
                tag == Tag.MAX ?
                        Math.max(
                                fill(left, mid, index * 2, arr, tag),
                                fill(mid + 1, right, index * 2 + 1, arr, tag))
                        : Math.min(
                        fill(left, mid, index * 2, arr, tag),
                        fill(mid + 1, right, index * 2 + 1, arr, tag))
        );
        return arr[index].value;
    }

    //left, right = 원하는 구간
    //startLeft, startRight = 원하는 구간이 속해있는 2^n 칸 짜리의 구간(후보 구간)
    public static long solution(int startLeft, int startRight, int left, int right, int index, DP[] arr, Tag tag) {
        //후보 구간이 원하는 구간이랑 딱 맞는 경우
        if (arr[index].left == left && arr[index].right == right)
            return arr[index].value;
        int mid = (startLeft + startRight) / 2;

        //후보 구간을 둘로 나눴을때 왼쪽 구간에만 원하는 구간이 속하는 경우
        if (right <= mid) {
            return solution(startLeft, mid, left, right, index * 2, arr, tag);
        }

        //후보 구간을 둘로 나눴을때 오른쪽 구간에만 원하는 구간이 속하는 경우
        if (mid < left) {
            return solution(mid + 1, startRight, left, right, index * 2 + 1, arr, tag);
        }

        //후보 구간을 둘로 나눴을때 왼/오 모두에 원하는 구간이 속하는 경우
        return tag == Tag.MAX ?
                Math.max(
                        solution(startLeft, mid, left, mid, index * 2, arr, tag),
                        solution(mid + 1, startRight, mid + 1, right, index * 2 + 1, arr, tag)
                )
                : Math.min(
                        solution(startLeft, mid, left, mid, index * 2, arr, tag),
                        solution(mid + 1, startRight, mid + 1, right, index * 2 + 1, arr, tag)
                );
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodeCount = findMid(N - 1) * 2;
        dpMax = new DP[nodeCount * 2];
        dpMin = new DP[nodeCount * 2];
        nums = new long[nodeCount];
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(bf.readLine());
        }
        //트리의 리프노드 개수를 2^n 꼴로 맞춘다
        for (int i = N; i < nums.length; i++) {
            nums[i] = nums[N - 1];
        }

        //트리를 계속 반으로 나눠가면서 해당 구간 최대/최소를 저장해놓는다.
        fill(0, nodeCount - 1, 1, dpMax, Tag.MAX);
        fill(0, nodeCount - 1, 1, dpMin, Tag.MIN);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            StringBuilder sb = new StringBuilder();
            sb.append((solution(0, nodeCount - 1, left - 1, right - 1, 1, dpMin, Tag.MIN)));
            sb.append(" ");
            sb.append((solution(0, nodeCount - 1, left - 1, right - 1, 1, dpMax, Tag.MAX)));
            System.out.println(sb);
        }
    }
}