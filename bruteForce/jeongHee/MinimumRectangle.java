package bruteFroce;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MinimumRectangle {
    //https://school.programmers.co.kr/learn/courses/30/lessons/86491
    //최소 직사각형
    public int solution(int[][] sizes) {
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < sizes.length; i++) {
            left.add(Math.min(sizes[i][0],sizes[i][1]));
            right.add(Math.max(sizes[i][0],sizes[i][1]));
        }
        return left.poll()*right.poll();
    }


}
