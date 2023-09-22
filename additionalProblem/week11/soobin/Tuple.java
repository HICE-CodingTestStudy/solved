package week11.soobin;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Tuple {
    public int[] solution(String s) {
        s = s.substring(1, s.length() - 2);
        String[] sets = s.split("},");

        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.length()));
        for (String set: sets) {
            set = set.substring(1);
            pq.add(set);
        }

        Set<Integer> included = new HashSet<>();
        int[] answer = new int[sets.length];
        int n = Integer.parseInt(pq.poll());
        included.add(n);
        answer[0] = n;

        for (int i = 1; !pq.isEmpty(); i++) {
            sets = pq.poll().split(",");
            for (String num : sets) {
                n = Integer.parseInt(num);
                if (!included.contains(n)) {
                    included.add(n);
                    answer[i] = n;
                    break;
                }
            }
        }

        return answer;
    }

    private static void printResult(int[] arr) {
        System.out.print("** [");
        for (int n : arr)
            System.out.print(String.format("%d, ", n));
        System.out.println("] **");
    }

    public static void main(String[] args) {
        Tuple t = new Tuple();

        printResult(t.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"));
        printResult(t.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"));
        printResult(t.solution("{{20,111},{111}}"));
        printResult(t.solution("{{123}}"));
        printResult(t.solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"));
    }
}
