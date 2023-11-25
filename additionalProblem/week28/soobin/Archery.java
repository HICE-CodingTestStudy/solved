package week28.soobin;

public class Archery {
    int[] answer, apeach, lion;
    int gap = 0;

    private int calculateScoreGap() {
        int aScore = 0, lScore = 0;
        for (int i = 0; i < 11; i++) {
            if (apeach[i] == lion[i] && apeach[i] == 0) continue;
            if (apeach[i] >= lion[i]) aScore += 10 - i;
            else lScore += 10 - i;
        }

        return lScore - aScore;
    }

    private void setLowestScoredAnswer() {
        for (int i = 10; i >= 0; i--) {
            if (answer[i] < lion[i]) {
                answer = lion.clone();
                break;
            } else if (answer[i] > lion[i]) break;
        }
    }

    private void shootArrows(int score, int arrows) {
        if (score == 10) {
            if (arrows > 0) lion[score] = arrows;
            int gap = calculateScoreGap();
            if (gap < this.gap || gap == 0) {
                lion[score] = 0;
                return;
            }

            if (gap > this.gap) answer = lion.clone();
            else setLowestScoredAnswer();
            this.gap = gap;
            lion[score] = 0;
            return;
        }

        // 해당 점수 과녁을 맞춘 경우
        lion[score] = arrows - apeach[score] - 1 >= 0 ? apeach[score] + 1 : 0;
        shootArrows(score + 1, arrows - lion[score]);

        // 해당 점수 과녁을 맞추지 않은 경우
        lion[score] = 0;
        shootArrows(score + 1, arrows);
    }

    public int[] solution(int n, int[] info) {
        this.apeach = info;
        this.lion = new int[11];
        shootArrows(0, n);

        return answer == null ? new int[] {-1} : answer;
    }
}
