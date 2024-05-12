package stack;

import java.util.*;

public class MovingDoor {
    //https://www.acmicpc.net/problem/2666
    //벽장문의 이동
    static int N;
    static List<Integer> order = new ArrayList<>();
    static int[] open = new int[2];

    static class Door {
        int first, second;
        int step;
        int cost;

        public Door(int first, int second, int step, int cost) {
            this.first = first;
            this.second = second;
            this.step = step;
            this.cost = cost;
        }
    }

    static int calcCost(int opened, int other, int target) {
        int cost = 0;
        int direction = -1;
        if (opened < target) direction = 1;
        for (int i = opened; i != target + direction; i += direction) {
            if (opened == i || other == i) continue;
            if (other == i - direction) cost += 2;
            else cost++;
        }
        return cost;
    }

    static int solution() {
        int ans = Integer.MAX_VALUE;
        Queue<Door> queue = new LinkedList<>();
        queue.add(new Door(open[0], open[1], -1, 0));
        while (!queue.isEmpty()) {
            Door now = queue.poll();
            if (now.step == order.size() - 1) {
                ans = Math.min(now.cost, ans);
                continue;
            }
            queue.add(new Door(
                    now.first,
                    order.get(now.step + 1),
                    now.step + 1,
                    now.cost + calcCost(now.second, now.first, order.get(now.step + 1)))
            );
            queue.add(new Door(
                    now.second,
                    order.get(now.step + 1),
                    now.step + 1,
                    now.cost + calcCost(now.first, now.second, order.get(now.step + 1)))
            );

        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < 2; i++) {
            open[i] = sc.nextInt();
        }
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            order.add(sc.nextInt());
        }
        System.out.println(solution());
    }

}
