package soobin;

import java.util.*;

public class DoubleEndedPQ {
    public static int[] solution(String[] operations) {
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();
        int[] answer = new int[2];

        for(String operation : operations) {
            Scanner scanner = new Scanner(operation);
            char type = scanner.next().charAt(0);
            int value = Integer.parseInt(scanner.next());

            if(type == 'I') {
                max.add(value);
                min.add(value);
            }
            if(type == 'D') {
                if(value == 1 && !max.isEmpty()) {
                    int remove = max.poll();
                    min.remove(remove);
                }
                if(value == -1 && !min.isEmpty()) {
                    int remove = min.poll();
                    max.remove(remove);
                }
            }
        }

        answer[0] = max.isEmpty() ? 0 : max.poll();
        answer[1] = min.isEmpty() ? 0 : min.poll();
        return answer;
    }

    public static void main(String[] args) {
        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        int[] answer = solution(operations);
        System.out.println(String.format("[%d,%d]", answer[0], answer[1]));
    }
}
