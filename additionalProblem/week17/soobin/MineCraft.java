package week17.soobin;

public class MineCraft {
    private final int MAX_STRESS = Integer.MAX_VALUE;
    private String[] minerals;

    private boolean isAnyPickAvailable(int[] picks) {
        return picks[0] > 0 || picks[1] > 0 || picks[2] > 0;
    }

    private int getMineralType(String mineral) {
        if (mineral.equals("diamond")) return 0;
        if (mineral.equals("iron")) return 1;
        return 2;
    }

    private int mineMineral(String mineral, int pick) {
        int mineralType = getMineralType(mineral);
        int pow = pick - mineralType >= 0 ? pick - mineralType : 0;
        return (int) Math.pow(5, pow);
    }

    private int getMinTotalStress(int[] picks, int round, int pick, int stress) {
        if (picks[pick] == 0) return MAX_STRESS;

        picks[pick]--;
        for (int i = round; i < round + 5 && i < minerals.length; i++)
            stress += mineMineral(minerals[i], pick);

        round += 5;
        if (round < minerals.length && isAnyPickAvailable(picks)) {
            int nextRound = MAX_STRESS;
            for (int i = 0; i < 3; i++)
                nextRound = Math.min(nextRound, getMinTotalStress(picks, round, i, 0));
            stress += nextRound;
        }
        picks[pick]++;

        return stress;
    }

    public int solution(int[] picks, String[] minerals) {
        this.minerals = minerals;

        int stress = MAX_STRESS;
        for (int i = 0; i < 3; i++)
            stress = Math.min(stress, getMinTotalStress(picks, 0, i, 0));

        return stress;
    }

    public static void main(String[] args) {
        MineCraft m = new MineCraft();
        int[] picks = new int[] {1, 3, 2};
        String[] minerals = new String[] {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        System.out.println(m.solution(picks, minerals));
    }
}
