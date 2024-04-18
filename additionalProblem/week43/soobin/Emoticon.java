public class Emoticon {
    private final int[] DISCOUNT_RATIO = {10, 20, 30, 40};

    private int[][] users;
    private int[] emoticons;
    private int m, discountIdx, maxEmoticonPlus = -1, maxEmoticonSales = -1;

    public int[] solution(int[][] users, int[] emoticons) {
        initialize(users, emoticons);
        findMinDiscountRatio();
        setDiscountRatePerEmoticon(0, new int[m]);

        return new int[] {maxEmoticonPlus, maxEmoticonSales};
    }

    private void initialize(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        m = emoticons.length;
    }

    private void findMinDiscountRatio() {
        int minDiscountRatio = 40;

        for (int[] user : users)
            minDiscountRatio = Math.min(minDiscountRatio, user[0]);

        if (minDiscountRatio <= 10) discountIdx = 0;
        else if (minDiscountRatio <= 20) discountIdx = 1;
        else if (minDiscountRatio <= 30) discountIdx = 2;
        else discountIdx = 3;
    }

    private void setDiscountRatePerEmoticon(int n, int[] discount) {
        if (n == m) {
            buyEmoticons(discount);
            return;
        }

        for (int i = discountIdx; i < 4; i++) {
            discount[n] = DISCOUNT_RATIO[i];
            setDiscountRatePerEmoticon(n + 1, discount);
        }
    }

    private void buyEmoticons(int[] discount) {
        int emoticonPlus = 0, sales = 0;

        for (int i = 0; i < users.length; i++) {
            int userRatio = users[i][0], userLimit = users[i][1];
            double total = buy(userRatio, discount);
            if (total >= userLimit) emoticonPlus++;
            else sales += total;
        }

        updateMax(emoticonPlus, sales);
    }

    private double buy(int userRatio, int[] discount) {
        double total = 0;

        for (int i = 0; i < m; i++)
            if (discount[i] >= userRatio)
                total += discount(emoticons[i], discount[i]);

        return total;
    }

    private double discount(int price, int ratio) {
        return price * ((double) (100 - ratio) / 100);
    }

    private void updateMax(int emoticonPlus, int sales) {
        if (emoticonPlus > maxEmoticonPlus) {
            maxEmoticonPlus = emoticonPlus;
            maxEmoticonSales = sales;
        } else if (emoticonPlus == maxEmoticonPlus)
            maxEmoticonSales = Math.max(sales, maxEmoticonSales);
    }
}
