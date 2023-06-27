package binarySearch.jeongHee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SumOfThreeNumbers {
    //https://www.acmicpc.net/problem/2295
    //세 수의 합
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
            return nums[mid];
        }
        return -1;
    }
    public static int solution(int[] nums, ArrayList<Integer> visited, int max, int count){
        if(count==3){
            return binarySearch(nums,visited.get(0)+visited.get(1)+visited.get(2));
        }
        for (int i = max; i >= 0 ; i--) {
            if(!visited.contains(nums[i])){
                visited.add(nums[i]);
                int sol = solution(nums,visited,max,count+1);
                if(sol!=-1)
                    return sol;
                visited.remove(visited.size()-1);
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i]=sc.nextInt();
        }
        Arrays.sort(nums);
        int max = N-1;
        for (int i = N-1; i >1 ; i--) {
            if(nums[0]+nums[1]+nums[i]>nums[N-1]){
                max=i-1;
                break;
            }
        }

        System.out.println(solution(nums,new ArrayList<Integer>(),max,0));

    }
}
