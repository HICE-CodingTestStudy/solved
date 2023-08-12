package binarySearch.jeongHee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class AlzipPing {
    public static int binarySearch(int[] nums, int target){
        int left = 0;
        int right = nums.length-1;
        int mid = (left+right)/2;
        while (left<=right){
            if(nums[mid]<target){
                left = mid+1;
                mid = (left+right)/2;
                continue;
            }
            if(nums[mid]>target){
                right = mid-1;
                mid = (left+right)/2;
                continue;
            }
            return mid;
        }
        return -1;
    }
    // https://www.acmicpc.net/problem/18870
    // 좌표 압축
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] savedNums = new int[N];
        HashSet<Integer> hs = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            hs.add(num);
            savedNums[i]=num;
        }
        int index = 0;
        int[] nums = new int[hs.size()];
        for (Integer h : hs) {
            nums[index++]=h;
        }
        Arrays.sort(nums);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(binarySearch(nums,savedNums[i]));
            sb.append(" ");
        }
        System.out.println(sb);
    }
}
