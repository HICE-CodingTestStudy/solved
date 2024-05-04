public class PopBalloon {
    private int[] balloons;

    public int solution(int[] a) {
        balloons = a;
        int minIdx = findMinIdx();
        int left = countLastBalloons(minIdx, 0, 1);
        int right = countLastBalloons(minIdx, balloons.length - 1, -1);
        return left + right + 1;
    }

    private int findMinIdx() {
        int min = Integer.MAX_VALUE, idx = 0;

        for (int i = 0; i < balloons.length; i++) {
            if (balloons[i] < min) {
                min = balloons[i];
                idx = i;
            }
        }

        return idx;
    }

    private int countLastBalloons(int minIdx, int start, int dir) {
        if (minIdx == start) return 0;

        int min = balloons[start], count = 1;

        while (start != minIdx) {
            if (balloons[start] < min) count++;
            min = Math.min(balloons[start], min);
            start += dir;
        }

        return count;
    }
}
