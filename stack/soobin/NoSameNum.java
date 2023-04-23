package soobin;

import java.util.*;

public class NoSameNum {
    public static Stack<Integer> solution(int[] arr) {
        Stack<Integer> answer = new Stack<>();

        for (int n : arr) {
            if(!answer.empty() && answer.peek().equals(n)) continue;
            else answer.push(n);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,3,3,0,1};
        System.out.println(solution(arr));
    }
}
