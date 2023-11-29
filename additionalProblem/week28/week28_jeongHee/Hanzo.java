package stack;

import java.util.ArrayList;
import java.util.List;

public class Hanzo {
    //https://school.programmers.co.kr/learn/courses/30/lessons/92342
    //양궁대회
    static List<List<Boolean>> points = new ArrayList<>();
    static int[] ans = null;
    static int ansPoint = 0;

    static void makePoints(int n, List<Boolean> point) {
        if (point.size() == n) {
            points.add(new ArrayList<>(point));
            return;
        }
        point.add(false);
        makePoints(n, point);
        point.remove(point.size() - 1);
        point.add(true);
        makePoints(n, point);
        point.remove(point.size() - 1);
    }

    public int[] solution(int n, int[] info) {
        makePoints(10, new ArrayList<>());
        for (int i = 0; i < points.size(); i++) {
            int tmpN = n;
            int[] tmpAns = new int[11];
            for (int j = 0; j < 10; j++) {
                if (!points.get(i).get(j)) continue;
                tmpN -= info[j] + 1;
                tmpAns[j] = info[j] + 1;
                if (tmpN < 0) break;
            }
            if (tmpN >= 0) {
                int aScore = 0, bScore = 0;
                for (int j = 0; j < 10; j++) {
                    if (info[j] == 0 && tmpAns[j] == 0) continue;
                    if (info[j] >= tmpAns[j]) aScore += 10 - j;
                    else bScore += 10 - j;
                }
                if (aScore < bScore && bScore - aScore >= ansPoint) {
                    while (tmpN-- > 0)
                        tmpAns[10]++;
                    if (ansPoint == bScore - aScore) {
                        for (int j = 10; j >=0 ; j--) {
                            if(tmpAns[j]==ans[j])
                                continue;
                            if (tmpAns[j] > ans[j]) {
                                ans = tmpAns;
                                break;
                            }
                            break;
                        }
                        continue;
                    }
                    ansPoint = bScore - aScore;
                    ans = tmpAns;
                }
            }
        }
        if (ans == null)
            return new int[]{-1};
        return ans;
    }

    public static void main(String[] args) {
        Hanzo h = new Hanzo();
        h.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0});
    }
}
