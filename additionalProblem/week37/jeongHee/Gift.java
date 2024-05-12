import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Gift {
    //https://school.programmers.co.kr/learn/courses/30/lessons/258712
    //가장 많이 받은 선물
    static int[] exchange[], total, ans;
    static Map<String, Integer> index = new HashMap<>();

    static public int solution(String[] friends, String[] gifts) {
        exchange = new int[friends.length][friends.length];
        total = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            index.putIfAbsent(friends[i], i);
        }
        for (int i = 0; i < gifts.length; i++) {
            String[] info = gifts[i].split(" ");
            int from = index.get(info[0]);
            int to = index.get(info[1]);
            exchange[from][to]++;
            total[from]++;
            total[to]--;
        }
        ans = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                if (exchange[i][j] != exchange[j][i]) {
                    if (exchange[i][j] > exchange[j][i])
                        ans[i]++;
                    else ans[j]++;
                    continue;
                }
                if (total[i] == total[j]) continue;
                if (total[i] > total[j])
                    ans[i]++;
                else ans[j]++;
            }
        }
        int max = 0;
        for (int i = 0; i < ans.length; i++) {
            max = Math.max(max, ans[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        solution(new String[]{"muzi", "ryan", "frodo", "neo"}, new String[]{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"});
    }
}
