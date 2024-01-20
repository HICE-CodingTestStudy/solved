public class Delivery {
    private int lastDelivered, lastPickedUp, totalParcels;

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        initLastIndex(n);
        countTotalPackages(deliveries, pickups);
        return deliverAndPickup(cap, deliveries, pickups);
    }

    private void initLastIndex(int n) {
        lastDelivered = n - 1;
        lastPickedUp = n - 1;
    }

    private void countTotalPackages(int[] deliveries, int[] pickups) {
        for (int i = 0; i < deliveries.length; i++)
            totalParcels += deliveries[i] + pickups[i];
    }

    private long deliverAndPickup(int cap, int[] deliveries, int[] pickups) {
        long totalDistance = 0;

        while (totalParcels > 0) {
            totalDistance += 2L * (Math.max(lastDelivered, lastPickedUp) + 1);
            lastDelivered = loadParcels(cap, lastDelivered, deliveries);
            lastPickedUp = loadParcels(cap, lastPickedUp, pickups);
        }

        return totalDistance;
    }

    private int loadParcels(int cap, int last, int[] pacels) {
        if (last < 0) return -1;

        while (last >= 0 && cap > 0) {
            int packages = Math.min(cap, pacels[last]);
            totalParcels -= packages;
            cap -= packages;
            pacels[last] -= packages;

            last = pacels[last] == 0 ? last - 1 : last;
        }
        while (last >= 0 && pacels[last] == 0) last--;

        return last;
    }
}
