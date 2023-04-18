package stack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class HateSameNumber {
    //같은 숫자는 싫어
    //https://school.programmers.co.kr/learn/courses/30/lessons/12906
    public int[] solution(int []arr) {
        Stack<Integer> ansStack = new Stack<>();
        for (int j : arr) {
            if (!ansStack.isEmpty() && j == ansStack.peek())
                continue;
            ansStack.push(j);
        }
        Object[] arrayAns = ansStack.toArray();
        int[] ans = new int[arrayAns.length];
        int index = 0;
        for (Object a : arrayAns) {
            ans[index++]= (int) a;
        }
        return ans;
    }
}
