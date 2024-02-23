import java.util.*;

public class Gift {
    private Map<String, Integer> friendIndex;
    private int[][] exchangedGifts;
    private int[] giftScores;
    private int N;
    public int solution(String[] friends, String[] gifts) {
        initialize(friends);
        exchangeGifts(gifts);
        return getMaxGifts();
    }

    private void initialize(String[] friends) {
        N = friends.length;
        exchangedGifts = new int[N][N];
        giftScores = new int[N];

        friendIndex = new HashMap<>();
        for (int i = 0; i < N; i++)
            friendIndex.put(friends[i], i);
    }

    private void exchangeGifts(String[] gifts) {
        for (String gift : gifts) {
            String[] fromTo = gift.split(" ");
            int from = friendIndex.get(fromTo[0]);
            int to = friendIndex.get(fromTo[1]);

            exchangedGifts[from][to]++;
            giftScores[from]++;
            giftScores[to]--;
        }
    }

    private int getMaxGifts() {
        int maxGifts = 0;

        for (int i = 0; i < N; i++) {
            int nextMonthGift = 0;

            for (int j = 0; j < N; j++) {
                if (i == j || exchangedGifts[i][j] < exchangedGifts[j][i]) continue;

                if (exchangedGifts[i][j] > exchangedGifts[j][i]
                        || giftScores[i] > giftScores[j])
                    nextMonthGift++;
            }

            maxGifts = Math.max(maxGifts, nextMonthGift);
        }

        return maxGifts;
    }
}
