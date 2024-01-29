package stack;

public class Coupang {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int nextDeliver = n - 1;
        int nextPickUp = n - 1;
        long ans = 0;
        while (true) {
            int deliverCount = 0, pickUpCount = 0, toDeliver = 0, toPickUp = 0;
            for (int i = nextDeliver; i >= 0; i--) {
                if (deliveries[i] == 0) {
                    nextDeliver = i - 1;
                    continue;
                }
                toDeliver = Math.max(toDeliver, i + 1);
                if (deliverCount + deliveries[i] > cap) {
                    deliveries[i] -= cap - deliverCount;
                    nextDeliver = i;
                    break;
                }
                nextDeliver = i - 1;
                deliverCount += deliveries[i];
            }
            for (int i = nextPickUp; i >= 0; i--) {
                if (pickups[i] == 0) {
                    nextPickUp = i - 1;
                    continue;
                }
                toPickUp = Math.max(toPickUp, i + 1);
                if (pickUpCount + pickups[i] > cap) {
                    pickups[i] -= cap - pickUpCount;
                    nextPickUp = i;
                    break;
                }
                nextPickUp = i - 1;
                pickUpCount += pickups[i];
            }
            ans += Math.max(toDeliver, toPickUp) * 2L;
            if (nextDeliver == -1 && nextPickUp == -1) break;
        }
        return ans;
    }

    public static void main(String[] args) {
        Coupang c = new Coupang();
        System.out.println(c.solution(2, 7, new int[]{1, 0, 2, 0, 1, 0, 2},
                new int[]{0, 2, 0, 1, 0, 2, 0}));
    }
}
