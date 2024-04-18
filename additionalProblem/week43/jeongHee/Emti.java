class Solution {
    static int N, M;
    static int[][] user;
    static int[] emoticon, answer;
    static final int[] percent = {10, 20, 30, 40};

    static void recursive(int count, int[] discount) {
        if (count == M) {
            int totalM = 0, totalCount = 0;
            for (int i = 0; i < N; i++) {
                int money = 0;
                for (int j = 0; j < M; j++) {
                    if (discount[j] >= user[i][0]) {
                        money += emoticon[j] / 100 * (100 - discount[j]);
                    }
                    if (money >= user[i][1]) {
                        money = 0;
                        totalCount++;
                        break;
                    }
                }
                if (money >= user[i][1]) {
                    money = 0;
                    totalCount++;
                }
                totalM += money;

            }
            if (answer[0] <= totalCount) {
                if (answer[0] == totalCount) {
                    answer[1] = Math.max(answer[1], totalM);
                } else {
                    answer[0] = totalCount;
                    answer[1] = totalM;
                }
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            discount[count] = percent[i];
            recursive(count + 1, discount);
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        N = users.length;
        M = emoticons.length;
        answer = new int[2];
        emoticon = emoticons;
        user = users;
        recursive(0, new int[M]);
        return answer;
    }
}