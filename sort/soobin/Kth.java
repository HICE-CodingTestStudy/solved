package soobin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kth {
    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int l = 0; l < commands.length; l++) {
            int i = commands[l][0] - 1;
            int j = commands[l][1] - 1;
            int k = commands[l][2] - 1;

            List<Integer> partition = new ArrayList<>();
            for(; i < j + 1;) partition.add(array[i++]);
            Collections.sort(partition, null);
            answer[l] = partition.get(k);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2,5,3}, {4,4,1}, {1,7,3}};
        solution(array, commands);
    }
}
