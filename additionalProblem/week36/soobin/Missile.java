import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Missile {
    private double lastInterceptor = -0.5;

    public int solution(int[][] targets) {
        Queue<int[]> bombs = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        bombs.addAll(Arrays.asList(targets));
        int numInterceptors = 0;

        while (!bombs.isEmpty()) {
            int[] bomb = bombs.poll();
            if (isDefensible(bomb)) continue;

            lastInterceptor = bomb[1] - 0.5;
            numInterceptors++;
        }

        return numInterceptors;
    }

    private boolean isDefensible(int[] bomb) {
        return (double) bomb[0] < lastInterceptor && (double) bomb[1] > lastInterceptor;
    }
}
